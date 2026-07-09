package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.PIDCoefficients
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.command.Robot
import com.seattlesolvers.solverslib.command.RunCommand
import com.seattlesolvers.solverslib.command.SubsystemBase
import com.seattlesolvers.solverslib.command.button.Trigger
import com.seattlesolvers.solverslib.controller.wpilibcontroller.SimpleMotorFeedforward
import com.seattlesolvers.solverslib.gamepad.GamepadEx
import com.seattlesolvers.solverslib.gamepad.GamepadKeys
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.subsystems.SimpleMotorSubsystem
import org.firstinspires.ftc.teamcode.utils.Alliance
import org.firstinspires.ftc.teamcode.utils.AngularVelocity
import org.firstinspires.ftc.teamcode.utils.TecDroidRobot
import org.firstinspires.ftc.teamcode.utils.configurations.OpMotorExConfiguration
import org.firstinspires.ftc.teamcode.utils.configurations.controlModeConfigurations.PercentageModeConfiguration
import org.firstinspires.ftc.teamcode.utils.configurations.controlModeConfigurations.VelocityModeConfiguration
import org.firstinspires.ftc.teamcode.utils.configurations.genericConfigurations.GenericMotorConfiguration
import org.firstinspires.ftc.teamcode.utils.constants.RobotConstants.Telemetry.pTelemetry
import org.firstinspires.ftc.teamcode.utils.devices.OpMotorEx
import org.firstinspires.ftc.teamcode.utils.extensions.leftBumper
import org.firstinspires.ftc.teamcode.utils.extensions.onFalse
import org.firstinspires.ftc.teamcode.utils.extensions.onTrue
import org.firstinspires.ftc.teamcode.utils.extensions.rightBumper

class Robot(private val alliance: Alliance, private val hardwareMap: HardwareMap, private val controller: GamepadEx, telemetry: Telemetry): TecDroidRobot(telemetry, hardwareMap) {

    /* Declare your subsystems here */
    private lateinit var shooterMotor: OpMotorEx

    private lateinit var intakeMotor: OpMotorEx

    private lateinit var rightMotor: OpMotorEx
    private lateinit var leftMotor: OpMotorEx

    init {
        subsystemInitialization()
    }

    /* Initialize your subsystems here */
    override fun subsystemInitialization() {
        shooterMotor = OpMotorEx(hardwareMap, "sm")
        shooterMotor.applyConfigurationAndResetEncoder(OpMotorExConfiguration(
            GenericMotorConfiguration(),
            VelocityModeConfiguration()
                .withFeedforwardCoefficients(SimpleMotorFeedforward(0.0, 1.5, 0.0))
                .withVelocityCoefficients(PIDCoefficients(0.1, 0.0, 0.0))
        ))

        intakeMotor = OpMotorEx(hardwareMap, "im")
        intakeMotor.applyConfigurationAndResetEncoder(OpMotorExConfiguration(
            GenericMotorConfiguration().withInverted(true),
            PercentageModeConfiguration()
        ))

        rightMotor = OpMotorEx(hardwareMap, "rm")
        rightMotor.applyConfigurationAndResetEncoder(OpMotorExConfiguration(
            GenericMotorConfiguration().withInverted(true)
        ))

        leftMotor = OpMotorEx(hardwareMap, "lm")
        leftMotor.applyConfigurationAndResetEncoder(OpMotorExConfiguration())
    }

    /* Initialize your teleop controller commands here */
    override fun initTeleOp() {
        Trigger {controller.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.3}
            .whileActiveOnce(InstantCommand({ shooterMotor.setVelocity(AngularVelocity(80.0)) }))
            .whenInactive(InstantCommand({ shooterMotor.stopMotor() }))

        controller.rightBumper()
            .onTrue(InstantCommand({ intakeMotor.setPower(1.0) }))
            .onFalse(InstantCommand({ intakeMotor.setPower(0.0) }))

        controller.leftBumper()
            .onTrue(InstantCommand({ intakeMotor.setPower(-0.2) }))
            .onFalse(InstantCommand({ intakeMotor.setPower(0.0) }))
    }

    /* Initialize your auto commands here, set chassis alliance and starting pose */
    override fun initAuto() {}

    /* When the teleop ends, declare what to do */
    override fun onEnd() {}

    // Print telemetry using the pTelemetry object on RobotConstants.Telemetry. It will be printed on both Panels and Driver Hub.
    override fun printTelemetry() {
        RunCommand({
            rightMotor.setPower(-controller.leftY + controller.rightX)
            leftMotor.setPower(-controller.leftY - controller.rightX)
        }).schedule()
        pTelemetry.addData("Motor set power", intakeMotor.getOutput())
        pTelemetry.addData("Motor velocity", shooterMotor.getVelocity().get().rps)
        pTelemetry.addData("Motor position", intakeMotor.getPosition().get().rotations)
        pTelemetry.addData("Motor acceleration", shooterMotor.getAcceleration().get().rotPerSecPerSec)
    }
}