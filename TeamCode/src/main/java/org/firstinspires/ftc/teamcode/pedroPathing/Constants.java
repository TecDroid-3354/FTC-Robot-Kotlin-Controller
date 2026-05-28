package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.DriveEncoderConstants;
import com.pedropathing.ftc.localization.constants.OTOSConstants;

import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS.Pose2D;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.utils.PedroPathing;

public class Constants {

    // Front right motor name, declared in the Control HUB configuration
    public static String frontRightMotorName            = "frontRight";

    // Back right motor name, declared in the Control HUB configuration
    public static String backRightMotorName             = "backRight";

    // Front left motor name, declared in the Control HUB configuration
    public static String frontLeftMotorName             = "frontLeft";

    // Back left motor name, declared in the Control HUB configuration
    public static String backLeftMotorName              = "backLeft";

    // Follower Constants, must be passed as a property to the createFollower() method
    public static FollowerConstants followerConstants   = new FollowerConstants()
            // Mass of our robot in kilograms
            .mass(15.0)
            ;

    // Mecanum drivetrain constants, must be passed as a property to eh createFollower() method
    public static MecanumConstants driveConstants       = new MecanumConstants()
            .maxPower(1.0) // A value from 0 to 1 representing the maximum power our mecanum receives
            // ------------ Our motor's name --------------- //
            .rightFrontMotorName(frontRightMotorName)
            .rightRearMotorName(backRightMotorName)
            .leftFrontMotorName(frontLeftMotorName)
            .leftRearMotorName(backLeftMotorName)
            // ---------- Our motor's direction ---------- //
            // One of our chassis' side must go forward
            .rightFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            // Usually, the contrary side should go the other way
            .leftFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            // TODO Run the forward velocity tuner. The robot should move forward by 48 inches
            //.xVelocity(1.0)
            // TODO Run the lateral velocity tuner. The robot should strafe 48 inches and then abruptly stop.
            //.yVelocity(1.0)
            ;

    public static OTOSConstants otosLocalizerConstants = new OTOSConstants()
            // Get this value using the forward or lateral tuner. It should be the same.
            // Push the robot forward 48 inches and register the linear scalar
            // This value cannot be more or equal than 1.127 or less or equal than 0.872
            .linearScalar(1.126999)
            // Get this value using the turn tuner.
            // Fix the robot to a known reference and rotate counterclockwise one rotation
            // This value cannot be more or equal than 1.127 or less or equal than 0.872
            .angularScalar(0.9951)
            // Otos hardware map name
            .hardwareMapName("otos")
            // Unit in which the OTOS will report distance
            .linearUnit(DistanceUnit.INCH)
            // Unit in which the OTOS will report its IMU angle
            .angleUnit(AngleUnit.RADIANS)
            // The OTOS's offset relative to the robot's center.
            // The x and y values need to be passed in the same unit as above.
            // The angular offset is to be passed in the same unit as above.
            .offset(new Pose2D(0.75, 0.0, Math.PI / 2.0))
            ;
    public static DriveEncoderConstants driveEncoderLocalizerConstants = new DriveEncoderConstants()
            // ------------ Our motor's name --------------- //
            .rightFrontMotorName(frontRightMotorName)
            .rightRearMotorName(backRightMotorName)
            .leftFrontMotorName(frontLeftMotorName)
            .leftRearMotorName(backLeftMotorName)
            // --------- Our encoder's direction ----------- //
            .rightFrontEncoderDirection(Encoder.REVERSE)
            .rightRearEncoderDirection(Encoder.REVERSE)
            .leftFrontEncoderDirection(Encoder.FORWARD)
            .leftRearEncoderDirection(Encoder.FORWARD)
            // Robot's length and width values in INCHES
            // TODO Corroborate these values
            .robotWidth(15.75)
            .robotLength(15.5)
            // Run the forward tuner several times and average these results
            // TODO Run the forward tuner. Move the robot 48 inches and register the result
            //.forwardTicksToInches(0.012)
            // Run the lateral tuner several times and average these results
            // TODO Run the lateral tuner. Move the robot 48 inches and register the result
            //.strafeTicksToInches(1.0)
            // Run the turn tuner several times
            // TODO Run the turn tuner. Rotate the robot one full rotation counterclockwise and register the result
            //.turnTicksToInches(1.0)
            // TODO Test the localizer with the Localization test
            ;
            // TODO Test using the localizer test

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                // If willing to use the drive encoders for localization, un-comment this line
                // ------------------------------------------------------- //
                //.driveEncoderLocalizer(driveEncoderLocalizerConstants)
                // ------------------------------------------------------- //
                // If willing to use an OTOS for localization this line is needed
                .OTOSLocalizer(otosLocalizerConstants)
                // Your mecanum drivetrain constants, these line should always appear here as its necessary for
                // motor creation
                .mecanumDrivetrain(driveConstants)
                // Per documentation, this line must not be commented
                .pathConstraints(new PathConstraints(0.99, 100.0, 1.0, 1.0))
                // Build the follower
                .build();
    }
}
