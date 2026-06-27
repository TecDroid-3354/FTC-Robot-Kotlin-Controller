package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.command.Robot
import com.seattlesolvers.solverslib.gamepad.GamepadEx
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.subsystems.SimpleMotorSubsystem
import org.firstinspires.ftc.teamcode.utils.Alliance
import org.firstinspires.ftc.teamcode.utils.TecDroidRobot

class Robot(private val alliance: Alliance, private val hardwareMap: HardwareMap, private val controller: GamepadEx, telemetry: Telemetry): TecDroidRobot(telemetry, hardwareMap) {

    /* Declare your subsystems here */
    private lateinit var sampleMotor: SimpleMotorSubsystem

    /* Initialize your subsystems here */
    override fun subsystemInitialization() {
        sampleMotor = SimpleMotorSubsystem(hardwareMap)
    }

    /* Initialize your teleop controller commands here */
    override fun initTeleOp() {
        /* a button, turn on velocity mode */
        controller

        /* b button, turn on position mode */
        controller

        /* y button, turn on raw power mode */
        controller

        /* turn on motor depending on mode */
        controller
    }

    /* Initialize your auto commands here, set chassis alliance and starting pose */
    override fun initAuto() {}

    /* When the teleop ends, declare what to do */
    override fun onEnd() {}

    // Print telemetry using the pTelemetry object on RobotConstants.Telemetry. It will be printed on both Panels and Driver Hub.
    override fun updateTelemetry() {}
}