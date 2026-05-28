package org.firstinspires.ftc.teamcode.utils

import com.pedropathing.control.FilteredPIDFCoefficients
import com.pedropathing.control.PIDFCoefficients
import com.pedropathing.follower.FollowerConstants
import com.pedropathing.ftc.drivetrains.MecanumConstants
import com.pedropathing.ftc.localization.constants.OTOSConstants
import com.pedropathing.paths.PathConstraints
import com.qualcomm.hardware.sparkfun.SparkFunOTOS
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.seattlesolvers.solverslib.util.MathUtils
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit
import java.util.Optional

object PedroPathing {

    data class PIDFSwitchThresholds(
        val drivePIDFSwitch             : Distance,
        val translationalPIDFSwitch     : Distance,
        val headingPIDFSwitch           : Angle
    )

    private object MotorDefaultNames {

        // Front right motor default name, declared in the Control HUB configuration
        const val FRONT_RIGHT_MOTOR_NAME: String            = "frontRight"

        // Back right motor default name, declared in the Control HUB configuration
        const val BACK_RIGHT_MOTOR_NAME : String            = "backRight"

        // Front left motor default name, declared in the Control HUB configuration
        const val FRONT_LEFT_MOTOR_NAME : String            = "frontLeft"

        // Back left motor default name, declared in the Control HUB configuration
        const val BACK_LEFT_MOTOR_NAME  : String            = "backLeft"
    }

    // Follower Constants
    private val followerDefaultConstants: FollowerConstants = FollowerConstants()
    // Mecanum Constants
    private val mecanumDefaultConstants : MecanumConstants  = MecanumConstants()
    // OTOS Localizer Constants
    private val otosDefaultConstants    : OTOSConstants     = OTOSConstants()
    // Values directly obtained per documentation. Path constraints
    private val pathDefaultConstraints  : PathConstraints   = PathConstraints(0.99, 100.0, 1.0, 1.0)

    /* INITIALIZATION CODE */
    init {
        // Initializing defaults

        with(followerDefaultConstants) {
            mass                        (Mass.fromKilograms(10.0).kilograms)
            isUseSecondaryHeadingPIDF                       = false
            isUseSecondaryDrivePIDF                         = false
            isUseSecondaryTranslationalPIDF                 = false
        }

        with(mecanumDefaultConstants) {
            // ------------ Our motor's name --------------- //
            rightFrontMotorName         (MotorDefaultNames.FRONT_RIGHT_MOTOR_NAME)
            rightRearMotorName          (MotorDefaultNames.BACK_RIGHT_MOTOR_NAME)
            leftFrontMotorName          (MotorDefaultNames.FRONT_LEFT_MOTOR_NAME)
            leftRearMotorName           (MotorDefaultNames.BACK_LEFT_MOTOR_NAME)

            // ---------- Our motor's direction ---------- //

            // By default, wheels on the other side will travel in the opposite direction
            leftFrontMotorDirection     (DcMotorSimple.Direction.FORWARD)
            leftRearMotorDirection      (DcMotorSimple.Direction.FORWARD)
            // By default, wheels on one side will travel in a negative or positive direction depending on your mounting
            rightFrontMotorDirection    (DcMotorSimple.Direction.REVERSE)
            rightRearMotorDirection     (DcMotorSimple.Direction.REVERSE)

            // ---------- Our motor's max power ---------- //
            maxPower(1.0)
        }

        with(otosDefaultConstants) {
            hardwareMapName             ("otos")
            // The default angular return units by standard
            angleUnit                   (AngleUnit.RADIANS)
            // The default distance return units by standard
            linearUnit                  (DistanceUnit.INCH)
        }
    }

    /**
     * Creates a new [FollowerConstants] with the requested optional parameters. If not present, the default values
     * will be grabbed by the new configuration.
     * Default [Mass] is 10 kilograms. This value MUST be changed according to your robot's mass.
     * @param mass your robot's mass. It is used to compensate for centripetal force.
     * @param headingPIDFCoefficients the robot's primary heading PIDF Coefficients. Run the [org.firstinspires.ftc.teamcode.pedroPathing.HeadingTuner] OpMode and adjust
     * the coefficients while TURNING the robot manually to the left or right side and observing its behavior.
     * The robot wil STAY IN ITS PLACE, this is intentional. WHILE RUNNING THIS OPMODE DON'T WORRY ABOUT THE ROBOT'S TRANSLATION.
     * The objective is to react as quickly as possible while avoiding oscillations.
     * First tune the F value, increase it until the robot barely moves.
     * After that, tune the P value. Turn the robot to the left or right side. If it oscillates to much, reduce the P value.
     * If it does not return to its original position or moves too slow, increase the P value.
     * Adjust the D value if needed.
     * See the following youtube video for better visualization: https://www.youtube.com/watch?v=-7M8puRdnfA
     * You can adjust the PIDF value LIVE when accessing panels http://192.168.43.1:8001/ and then moving on to
     * Tuning -> Follower -> Constants section of panels.
     * @param forwardZeroPowerAcceleration The deceleration rate of the robot over the x axis (forward) in inches per second squared.
     * To obtain this value, run the [org.firstinspires.ftc.teamcode.pedroPathing.ForwardZeroPowerAccelerationTuner] OpMode. Make sure you have enough space
     * for the robot to accelerate to 30 in/s forward. Typically, values closer to your max forward velocity yield better results. The robot will speed up until reaching the
     * target velocity and then measure the deceleration rate.
     * THIS VALUE IS REQUIRED TO BE NEGATIVE. If not, the robot will think that it will go faster under 0 power.
     * Then register the number displayed on the Driver Hub's telemetry.
     * If needed you can adjust the forward max velocity in [org.firstinspires.ftc.teamcode.pedroPathing.ForwardZeroPowerAccelerationTuner]
     * @param lateralZeroPowerAcceleration The deceleration rate of the robot over the y axis (lateral) in inches per second squared.
     * To obtain this value, run the [org.firstinspires.ftc.teamcode.pedroPathing.LateralZeroPowerAccelerationTuner] OpMode. Make sure you have enough space
     * for the robot to accelerate to 30 in/s to the left. Typically, values closer to your max lateral velocity yield better results. The robot will speed up until reaching the
     * target velocity and then measure the deceleration rate.
     * THIS VALUE IS REQUIRED TO BE NEGATIVE. If not, the robot will think that it will go faster under 0 power.
     * Then register the number displayed on the Driver Hub's telemetry.
     * If needed you can adjust the lateral max velocity in [org.firstinspires.ftc.teamcode.pedroPathing.LateralZeroPowerAccelerationTuner].
     * @param translationalPIDFCoefficients the robot's primary translational PIDF Coefficients.
     * Run the [org.firstinspires.ftc.teamcode.pedroPathing.TranslationalTuner] OpMode and adjust the coefficients while MOVING the robot
     * left or right and observing its behavior towards the movement.
     * The robot wil STAY IN ITS PLACE, this is intentional. WHILE RUNNING THIS OPMODE DON'T WORRY ABOUT THE ROBOT'S HEADING.
     * The objective is to react as quickly as possible while avoiding oscillations.
     * First tune the F value by setting all the other coefficients to 0. Increase this value until the robot barely moves while staying
     * still in place.
     * Then proceed to tune the P value. Increase this value according to the robot's behavior when moving it left or right.
     * If the robot overshoots when returning to its original place, try reducing the P value. If the robot takes too much time to
     * get back to its original position, try increasing this value.
     * Adjust the D value if needed.
     * See the following video for better understanding and visualization: https://www.youtube.com/watch?v=qe2eo_Mhtes
     * You can adjust the PIDF value LIVE when accessing panels http://192.168.43.1:8001/ and then moving on to
     * Tuning -> Follower -> Constants section of panels.
     * @param drivePIDFCoefficients the robot's primary drive PIDF Coefficients.
     * Run the [org.firstinspires.ftc.teamcode.pedroPathing.DriveTuner] OpMode and adjust the coefficients while the robot moves.
     * When starting this OpMode, the robot will automatically move forwards and backwards by 40 inches. Observe how the robot over or undershoots
     * the path and adjust the coefficients in a proper way. Make sure you have enough space in case of overshooting.
     * First, tune the F value by setting all the other coefficients to 0. The robot will probably stop its movement. Increase this value until the robot barely
     * moves.
     * Then proceed to tune the P value, start with a low value like 0.1 and start increasing it if the robot is taking too long to reach its target. Decrease it if th e
     * robot overshoots the 40 inch threshold.
     * Adjust the D value if needed.
     * Adjust the [PathConstraints.brakingStrength] value if needed.
     * You can adjust the PIDF value LIVE when accessing panels http://192.168.43.1:8001/ and then moving on to
     * Tuning -> Follower -> Constants section of panels.
     * @param movementPIDFSwitchValues this values represent the limit when the secondary PIDF controller in each type of movement
     * (turning, translational and drive) starts operating. It should only be used when the secondary PIDFs are enabled.
     * To adjust this value first give a value to any secondary PIDF when [createFollowerConstants], use low values to determine where
     * this threshold is in inches. Then, set this value to your preference if willing to use the secondary PIDF soon or late along the path.
     * Adjust [PIDFSwitchThresholds] for each type of movement.
     * @param secondaryDrivePIDFCoefficients the robot's secondary drive PIDF Coefficients. While running the [org.firstinspires.ftc.teamcode.pedroPathing.DriveTuner] OpMode
     * and the [drivePIDFCoefficients] are undoubtedly well tuned, increase slowly the P value to ensure smooth correction. No other
     * coefficients are needed here.
     * It is recommended to start enabling and tuning THIS secondary PIDF Coefficients first and then move on to heading and translational.
     * You can adjust the PIDF value live when accessing to panels http://192.168.43.1:8001/ and then moving on to
     * Tuning -> Follower -> Constants section of panels.
     * This PIDF will be used when trespassing the [FollowerConstants.drivePIDFSwitch] property. You can adjust this value to your convenience.
     * The [FollowerConstants.isUseSecondaryDrivePIDF] flag is automatically enabled if this parameter is present and disabled if not.
     * Leave it as [Optional.empty] until you have properly tuned the primary PIDF Coefficients for each type of movement.
     * @param secondaryHeadingPIDFCoefficients the robot's secondary heading PIDF Coefficients. While running the [org.firstinspires.ftc.teamcode.pedroPathing.HeadingTuner] OpMode
     * and the [headingPIDFCoefficients] are undoubtedly well tuned, increase slowly the P value to ensure smoother correction. No other
     * coefficients are needed here.
     * It is recommended to start enabling and tuning a secondary PIDF to correct the DRIVE type of movement first and then move on to heading and translational PIDF tuning.
     * You can adjust the PIDF value live when accessing to panels http://192.168.43.1:8001/ and then moving on to
     * Tuning -> Follower -> Constants section of panels.
     * This PIDF will be used when trespassing the [FollowerConstants.translationalPIDFSwitch] property. You can adjust this value to your convenience.
     * The [FollowerConstants.isUseSecondaryHeadingPIDF] flag is automatically enabled if this parameter is present and disabled if not.
     * Leave it as [Optional.empty] until you have properly tuned the drive secondary PIDF.
     * @param secondaryTranslationalPIDFCoefficients the robot´'s secondary translational PIDF Coefficients. While running the [org.firstinspires.ftc.teamcode.pedroPathing.TranslationalTuner] OpMode
     * and the [translationalPIDFCoefficients] are undoubtedly well tuned, increase slowly the P value to ensure smoother correction. No other
     * coefficients are needed here.
     * It is recommended to start enabling and tuning a secondary PIDF to correct the DRIVE type of movement first and then move on to translational and heading PIDF tuning.
     * You can adjust the PIDF value live when accessing to panels http://192.168.43.1:8001/ and then moving on to
     * Tuning -> Follower -> Constants section of panels.
     * The [FollowerConstants.isUseSecondaryTranslationalPIDF] flag is automatically enabled if this parameter is present and disabled if not.
     * Leave it as [Optional.empty] until you have properly tuned the drive's secondary PIDF.
     * @param centripetalScaling the centripetal force correction enables the robot to accurately follow curved paths.
     * For this value to be tuned, your robot's [Mass] is needed by the [FollowerConstants].
     * Run the [org.firstinspires.ftc.teamcode.pedroPathing.CentripetalTuner] OpMode to tune this value.
     * Once started, the robot will move forward and to the left following a curved path while correcting itself.
     * If the robot corrects itself towards the outside of the curve increase this value.
     * If the robot corrects itself towards the inside of the curve decrease this value.
     * You can use Panels to visualize the robot in the Panels or dashboard field by accessing to http://192.168.43.1:8001/ and then moving on to
     * Tuning -> Follower -> Constants section of panels.
     * @return a new [FollowerConstants] containing the specified parameters.
     * @see followerDefaultConstants
     */
    fun createFollowerConstants(mass: Optional<Mass>, headingPIDFCoefficients: Optional<PIDFCoefficients>,
                                forwardZeroPowerAcceleration: Optional<Double>, lateralZeroPowerAcceleration: Optional<Double>,
                                translationalPIDFCoefficients: Optional<PIDFCoefficients>, drivePIDFCoefficients: Optional<PIDFCoefficients>,
                                movementPIDFSwitchValues: Optional<PIDFSwitchThresholds>, secondaryDrivePIDFCoefficients: Optional<PIDFCoefficients>,
                                secondaryHeadingPIDFCoefficients: Optional<PIDFCoefficients>, secondaryTranslationalPIDFCoefficients: Optional<PIDFCoefficients>,
                                centripetalScaling: Optional<Double>
    ): FollowerConstants {
        // Creating the new FollowerConstants(). It will hold the new configurations
        val newFollowerConstants        : FollowerConstants = followerDefaultConstants

        // If present, mass will be updated to a new value. Adjust this according to your robot's weight
        if (mass.isPresent) {
            newFollowerConstants.mass(
                MathUtils.clamp(
                    mass.get().kilograms,
                    Double.MIN_VALUE,
                    Double.MAX_VALUE
                )
            )
        }

        // If present, heading PIDF coefficients will be applied.
        if (headingPIDFCoefficients.isPresent) {
            newFollowerConstants.headingPIDFCoefficients(headingPIDFCoefficients.get())
        } else { newFollowerConstants.headingPIDFCoefficients(PIDFCoefficients(0.01, 0.0, 0.0, 0.0)) }

        // If present, forward zero power acceleration will be set
        if (forwardZeroPowerAcceleration.isPresent) {
            newFollowerConstants.forwardZeroPowerAcceleration(
                MathUtils.clamp(
                    forwardZeroPowerAcceleration.get(),
                    Double.NEGATIVE_INFINITY,
                    Double.MIN_VALUE.unaryMinus()
                )
            )
        }

        // If present, lateral zero power acceleration will be set
        if (lateralZeroPowerAcceleration.isPresent) {
            newFollowerConstants.lateralZeroPowerAcceleration(
                MathUtils.clamp(
                    lateralZeroPowerAcceleration.get(),
                    Double.NEGATIVE_INFINITY,
                    Double.MIN_VALUE.unaryMinus()
                )
            )
        }

        // If present, translational PIDF Coefficients will be applied to the robot's lateral movement.
        if (translationalPIDFCoefficients.isPresent) {
            newFollowerConstants.translationalPIDFCoefficients(translationalPIDFCoefficients.get())
        } else { newFollowerConstants.translationalPIDFCoefficients(PIDFCoefficients(0.01, 0.0, 0.0, 0.0)) }

        // If present, drive PIDF Coefficients will be used to create a FilteredPIDFCoefficients object with a default t value of 0.6.
        if (drivePIDFCoefficients.isPresent) {
            newFollowerConstants.drivePIDFCoefficients(
                FilteredPIDFCoefficients(
                    drivePIDFCoefficients.get().P,
                    drivePIDFCoefficients.get().I,
                    drivePIDFCoefficients.get().D,
                    0.55,
                    drivePIDFCoefficients.get().F
                )
            )
        }

        // If present, the limit for the secondary PIDF Controllers will be overridden with the new ones passed.
        if (movementPIDFSwitchValues.isPresent) {
            newFollowerConstants.headingPIDFSwitch          = movementPIDFSwitchValues.get().headingPIDFSwitch.radians
            newFollowerConstants.translationalPIDFSwitch    = movementPIDFSwitchValues.get().translationalPIDFSwitch.inches
            newFollowerConstants.drivePIDFSwitch            = movementPIDFSwitchValues.get().drivePIDFSwitch.inches
        }

        // If present, the secondary drive PIDFCoefficients() object will be used to create a FilteredPIDFCoefficients() with a default t value.
        if (secondaryDrivePIDFCoefficients.isPresent) {
            newFollowerConstants.isUseSecondaryDrivePIDF    = true
            newFollowerConstants.secondaryDrivePIDFCoefficients(
                FilteredPIDFCoefficients(
                    secondaryDrivePIDFCoefficients.get().P,
                    secondaryDrivePIDFCoefficients.get().I,
                    secondaryDrivePIDFCoefficients.get().D,
                    0.55,
                    secondaryDrivePIDFCoefficients.get().F
                )
            )
        } else { newFollowerConstants.isUseSecondaryDrivePIDF = false }

        // If present, secondary heading PIDF Coefficients will be applied along with the enabled flag.
        if (secondaryHeadingPIDFCoefficients.isPresent) {
            newFollowerConstants.isUseSecondaryHeadingPIDF  = true
            newFollowerConstants.secondaryHeadingPIDFCoefficients(secondaryHeadingPIDFCoefficients.get())
        } else { newFollowerConstants.isUseSecondaryHeadingPIDF = false }

        // If present, secondary translational PIDF Coefficients will be applied along with the enabled flag.
        if (secondaryTranslationalPIDFCoefficients.isPresent) {
            newFollowerConstants.isUseSecondaryTranslationalPIDF = true
            newFollowerConstants.secondaryTranslationalPIDFCoefficients(secondaryTranslationalPIDFCoefficients.get())
        } else { newFollowerConstants.isUseSecondaryTranslationalPIDF = false }

        // If present, the centripetal scaling value will be set to the follower's constants. The default value is 0.0005.
        if (centripetalScaling.isPresent) {
            newFollowerConstants.centripetalScaling(centripetalScaling.get())
        }
        
        // Return the new FollowerConstants() object.
        return newFollowerConstants
    }

    /**
     * Creates a new [MecanumConstants] with the requested optional parameters. If not present, the parameter will not be
     * set and the default values used by the [MecanumConstants] will be then used.
     * Default [DcMotorSimple.Direction] on the right side is [DcMotorSimple.Direction.REVERSE]
     * Default [DcMotorSimple.Direction] on the left side is [DcMotorSimple.Direction.FORWARD]
     * Default Max power is 1.0.
     * @param rightSideDirection the desired motor's [DcMotorSimple.Direction] on the right side of the mecanum.
     * @param leftSideDirection the desired motor's [DcMotorSimple.Direction] on the left side of the mecanum.
     * @param maxPower the maximum applied power to the mecanum motors
     * @param xVelocity the average maximum velocity over the x axis of the robot (Forward and reverse). Can be obtained
     * by running the [org.firstinspires.ftc.teamcode.pedroPathing.ForwardVelocityTuner] OpMode. The robot will move autonomously
     * by 48 inches over the robot's x axis. After reaching its target, the robot will automatically stop.
     * Then register the number displayed on the Driver Hub's telemetry.
     * Given the case that the robot does not stops, check out your localizer constants. Its possible that they are wrongly
     * configured.
     * @param yVelocity the average maximum velocity over the y axis of the robot (Left and right). Can be obtained
     * by running the [org.firstinspires.ftc.teamcode.pedroPathing.LateralVelocityTuner] OpMode. The robot will move autonomously
     * by 48 inches over the robot's y axis. After reaching its target, the robot will automatically stop.
     * Then register the number displayed on the Driver Hub's telemetry.
     * Given the case that the robot does not stops, check out your localizer constants. Its possible that they are wrongly
     * configured.
     * @return a new [MecanumConstants] containing the specified parameters. If not it will simply return the defualt values.
     * @see [mecanumDefaultConstants]
     */
    fun createMecanumConstants(rightSideDirection: Optional<DcMotorSimple.Direction>, leftSideDirection: Optional<DcMotorSimple.Direction>,
                               maxPower: Optional<Double>, xVelocity: Optional<LinearVelocity>, yVelocity: Optional<LinearVelocity>
    ): MecanumConstants {
        // Creating a new MecanumConstants(). It will hold the new configurations
        val newMecanumConstants         : MecanumConstants  = mecanumDefaultConstants

        // If present, the right side of the mecanum will spin at the specified direction
        if (rightSideDirection.isPresent) {
            newMecanumConstants.rightFrontMotorDirection(rightSideDirection.get())
            newMecanumConstants.rightRearMotorDirection(rightSideDirection.get())
        }

        // If present, the left side of the mecanum will spin at the specified direction
        if (leftSideDirection.isPresent) {
            newMecanumConstants.leftFrontMotorDirection(leftSideDirection.get())
            newMecanumConstants.leftRearMotorDirection(leftSideDirection.get())
        }

        // If present, a new maximum achievable power will be set to the mecanum
        if (maxPower.isPresent) {
            newMecanumConstants.maxPower(maxPower.get())
        }

        // If present, a new xVelocity will be passed to the configuration.
        // This value must be obtained by running the Forward Velocity Test
        if (xVelocity.isPresent) {
            newMecanumConstants.xVelocity(xVelocity.get().inps)
        }

        // If present, a new yVelocity will be passed to the configuration.
        // This value must be obtained by running the Lateral Velocity Test
        if (yVelocity.isPresent) {
            newMecanumConstants.yVelocity(yVelocity.get().inps)
        }

        // Return the new MecanumConstants()
        return newMecanumConstants
    }

    /**
     * Creates a new [OTOSConstants] based on the requested [offset], [linearScalar] and [angularScalar].
     * Default values are included so new ones are not necessary to be given.
     * Default [com.qualcomm.robotcore.hardware.HardwareMap] name is "otos".
     * Default [DistanceUnit] is [DistanceUnit.INCH].
     * Default [AngleUnit] is [AngleUnit.RADIANS].
     * @param offset a [SparkFunOTOS.Pose2D] representing the sensor offset.
     * The distance along the x and y axis of the sensor towards the robot center. Must be asked to the
     * design team for precise measurements. The theta component of the Pose2D must be given so that when driving the
     * robot forward the X value increases and when driving it to the left, the y values increase.
     * @param linearScalar a scale factor for the sensor x and y given values. Must be between 0.872 and 1.127. To get this factor, turn on the
     * [org.firstinspires.ftc.teamcode.pedroPathing.ForwardTuner] and drive the robot forward by 48 inches.
     * Then register the number displayed on the Driver Hub's telemetry.
     * @param angularScalar a scale factor for the sensor's integrated IMU readings. Must be between 0.872 and 1.127. To get this factor, turn on the
     * [org.firstinspires.ftc.teamcode.pedroPathing.TurnTuner] and rotate the robot one single rotation.
     * Then register the number displayed on the Driver Hub's telemetry.
     * @return a new [OTOSConstants] holding the specified parameters. If not, it will simply return the default values
     * @see otosDefaultConstants
     */
    fun createOTOSLocalizerConstants(offset: Optional<SparkFunOTOS.Pose2D>, linearScalar: Optional<Double>,
                                     angularScalar: Optional<Double>
    ): OTOSConstants {
        // Creating a new OtosConstants() object. It will hold the new configurations
        val otosNewConstants            : OTOSConstants     = otosDefaultConstants

        // If present, setting the new offset value.
        // If not present, an empty Pose2D() will be passed.
        if (offset.isPresent) {
            otosNewConstants.offset(offset.get())
        } else { otosNewConstants.offset(SparkFunOTOS.Pose2D()) }

        // If present, it will clamp the linear scalar value between its boundaries to avoid misunderstandings
        // when setting a bigger value.
        // Bigger values will be ignored by the OTOS Sensor
        if (linearScalar.isPresent) {
            otosNewConstants.linearScalar(
                MathUtils.clamp(
                    linearScalar.get(),
                    SparkFunOTOS.MIN_SCALAR + Double.MIN_VALUE,
                    SparkFunOTOS.MAX_SCALAR - Double.MIN_VALUE
                )
            )
        }

        // If present, it will clamp the angular scalar value between its boundaries to avoid misunderstandings
        // when setting a bigger value.
        // Bigger values will be ignored by the OTOS Sensor
        if (angularScalar.isPresent) {
            otosNewConstants.angularScalar(
                MathUtils.clamp(
                    angularScalar.get(),
                    SparkFunOTOS.MIN_SCALAR + Double.MIN_VALUE,
                    SparkFunOTOS.MAX_SCALAR - Double.MIN_VALUE
                )
            )
        }

        // Return the new OTOSConstants()
        return otosNewConstants
    }

    /**
     * Creates a new [PathConstraints] with the specified braking parameters. If not specified, default values will be
     * given as parameters.
     * Default timeout is 100 miliseconds. This represents how much time after completing a path the [com.pedropathing.follower.Follower] will
     * try to correct the path error.
     * Default tValue is 0.99. This represents a value between 0.0 and 1.0 which tells the [com.pedropathing.follower.Follower] when the path is considered complete.
     * @param brakingStrength this controls how strong the braking is. A higher value means stronger braking, a lower value means
     * slighter braking. This value can be obtained while running the [org.firstinspires.ftc.teamcode.pedroPathing.DriveTuner]. It is recommended to, first, observe
     * the follower's behavior during this test and adjust this value ONLY if needed when the robot overshoots or braking is made vastly abrupt.
     * Leave it empty if not needed.
     * @param brakingStart this controls how early along the path the robot will start braking. The higher the value the earlier the braking starts. The lower the value the
     * later the braking starts. It is recommended to play with this value ONLY for optimization, NOT for mitigating overshoot.
     * Leave it empty if not needed.
     * @return a new [PathConstraints] containing the specified configurations
     * @see pathDefaultConstraints
     */
    fun createPathConstraints(brakingStrength: Optional<Double>, brakingStart: Optional<Double>): PathConstraints {
        // Creating the new PathConstraints(). It will hold the new configurations
        val newPathConstraints          : PathConstraints   = pathDefaultConstraints

        // If present, braking strength will be set. If not, it will be set to 1.0
        if (brakingStrength.isPresent) {
            newPathConstraints.brakingStrength = brakingStrength.get()
        } else { newPathConstraints.brakingStrength = 1.0 }

        // If present, braking start will be set. If not, it will be set to 1.0
        if (brakingStart.isPresent) {
            newPathConstraints.brakingStart = brakingStart.get()
        } else { newPathConstraints.brakingStart = 1.0 }

        // Return the new PathConstraints()
        return newPathConstraints
    }
}