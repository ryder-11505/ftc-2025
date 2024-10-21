package org.firstinspires.ftc.teamcode.drive.advanced;

import android.util.Log;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.drive.RollbackLocalizer;
import org.firstinspires.ftc.teamcode.drive.advanced.subsystems.Logging;
import org.firstinspires.ftc.teamcode.util.DashboardUtil;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;
import java.util.List;

public class VisionDetection {
    public static List<AprilTagDetection> detections = new ArrayList<>();
    public Limelight3A limelight;

    public VisionDetection(HardwareMap hardwareMap) {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        limelight.setPollRateHz(30);
        limelight.pipelineSwitch(0);
        limelight.start();
    }

    long lastResult = 0;
    long iteration = 0;
    public void update(RollbackLocalizer localizer, TelemetryPacket packet) {
        iteration += 1;

        if (iteration % 20 == 0) { // Every 20 cycles
            LLStatus status = limelight.getStatus();
            limelight.updateRobotOrientation(localizer.getPoseEstimate().getHeading());
            Logging.LOG("(limelight) Temp", status.getTemp());
            Logging.LOG("(limelight) CPU", status.getCpu());
            Logging.LOG("(limelight) FPS", status.getFps());
        }

        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid() && result.getControlHubTimeStamp() > lastResult) {
            lastResult = result.getControlHubTimeStamp();

            // Access general information
            Pose3D botpose = result.getBotpose_MT2();
            double captureLatency = result.getCaptureLatency();
            double targetingLatency = result.getTargetingLatency();
            double parseLatency = result.getParseLatency();
            double staleness = result.getStaleness();
            Logging.LOG("(limelight) latency", captureLatency + targetingLatency + parseLatency + staleness);
            Logging.LOG("(limelight) Last Pose", botpose);

            packet.fieldOverlay().setStroke("#00ffff");
            DashboardUtil.drawRobot(packet.fieldOverlay(), new Pose2d(botpose.getPosition().x, botpose.getPosition().y, botpose.getOrientation().getYaw()));

            localizer.newDelayedVisionPose(new Pose2d(botpose.getPosition().x, botpose.getPosition().y, botpose.getOrientation().getYaw()), captureLatency + targetingLatency + parseLatency + staleness);
        } else {
            Logging.LOG("(limelight) Status", "No data available");
        }
    }
}
