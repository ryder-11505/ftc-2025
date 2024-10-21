package org.firstinspires.ftc.teamcode.drive.advanced;

import com.acmerobotics.roadrunner.geometry.Pose2d;

/**
 * Simple static field serving as a storage medium for the bot's pose.
 * This allows different classes/opmodes to set and read from a central source of truth.
 * A static field allows data to persist between opmodes.
 */
public class PoseStorage {
    public static Pose2d currentPose = new Pose2d(12, -61.5, -Math.PI / 2);

    public static boolean isRedAlliance = false;
    public static boolean isHardLoop = false;
}