package com.userjhansen.automap.Maps;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.userjhansen.automap.AutoPart;
import com.userjhansen.automap.PartType;
import com.userjhansen.automap.RobotParams;

public class InsideTwo implements Map {
    public Pose2d startFloorPixel = new Pose2d(15, -60, Math.PI / 2);

    public static AutoPart[] floorPixelParts = {
            new AutoPart(PartType.FORWARD, 9),
            new AutoPart(PartType.SPLINE_TO, new Pose2d(15, -24, Math.PI / 2), RobotParams.intakeOffset, Math.PI / 2),
            new AutoPart(PartType.WAIT, 3),
            new AutoPart(PartType.ACTION, 0),
            new AutoPart(PartType.STRAFE, new Pose2d(15, -40)),
            new AutoPart(PartType.SPLINE_TO, new Pose2d(40, -40, Math.PI), Math.PI),
    };

    public static AutoPart[] backdropPixelParts = {
            new AutoPart(PartType.STRAFE, new Pose2d(58, -35, Math.PI), RobotParams.liftOffset),
            new AutoPart(PartType.WAIT, 3),
            new AutoPart(PartType.ACTION, 1),
    };


    @Override
    public Pose2d getStartPosition() {
        return startFloorPixel;
    }

    @Override
    public AutoPart[] getStartParts() {
        return floorPixelParts;
    }

    @Override
    public AutoPart[] getBackdropParts() {
        return backdropPixelParts;
    }
}
