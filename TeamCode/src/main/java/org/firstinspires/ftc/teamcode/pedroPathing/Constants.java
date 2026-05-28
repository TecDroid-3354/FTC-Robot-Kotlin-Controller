package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.OTOSConstants;
import com.pedropathing.paths.PathConstraints;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS.Pose2D;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.Mass;
import org.firstinspires.ftc.teamcode.utils.PedroPathing;

import java.util.Optional;

public class Constants {

    // Follower Constants, must be passed as a property to the createFollower() method
    public static FollowerConstants followerConstants   = PedroPathing.INSTANCE.createFollowerConstants(
            Optional.of(Mass.fromKilograms(15.0)),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()
    );

    // Mecanum drivetrain constants, must be passed as a property to the createFollower() method
    public static MecanumConstants driveConstants       = PedroPathing.INSTANCE.createMecanumConstants(
            Optional.empty(),
            Optional.empty(),
            Optional.of(1.0),
            Optional.empty(),
            Optional.empty()
    );

    public static OTOSConstants otosLocalizerConstants  = PedroPathing.INSTANCE.createOTOSLocalizerConstants(
            Optional.of(new Pose2D(0.75, 0.0, Math.PI / 2.0)),
            Optional.of(1.126999),
            Optional.of(0.9951)
    );

    public static PathConstraints pathConstraints       = PedroPathing.INSTANCE.createPathConstraints(
            Optional.of(1.0),
            Optional.of(1.0)
    );

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                // If willing to use an OTOS for localization this line is needed
                .OTOSLocalizer(otosLocalizerConstants)
                // Your mecanum drivetrain constants, these line should always appear here as its necessary for
                // motor creation
                .mecanumDrivetrain(driveConstants)
                // Per documentation, this line must not be commented
                .pathConstraints(pathConstraints)
                // Build the follower
                .build();
    }
}
