package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.command.Robot
import com.seattlesolvers.solverslib.gamepad.GamepadEx
import org.firstinspires.ftc.teamcode.subsystems.SimpleMotorSubsystem
import org.firstinspires.ftc.teamcode.utils.Alliance
import org.firstinspires.ftc.teamcode.utils.a
import org.firstinspires.ftc.teamcode.utils.onFalse
import org.firstinspires.ftc.teamcode.utils.onTrue

class Robot(private val alliance: Alliance, private val hardwareMap: HardwareMap, private val controller: GamepadEx): Robot() {

    /* Declare your subsystems here */
    private lateinit var sampleMotor: SimpleMotorSubsystem

    /* Call the subsystemInitialization method, done in class init */
    init {
        subsystemInitialization()
    }

    /* Initialize your subsystems here */
    fun subsystemInitialization() {
        sampleMotor = SimpleMotorSubsystem(hardwareMap)
    }

    /* Initialize your teleop controller commands here */
    fun initTeleOp() {
        controller.a()
            .onTrue(InstantCommand({ sampleMotor.enableMotor() }))
            .onFalse(InstantCommand({ sampleMotor.disableMotor() }))
    }

    /* Initialize your auto commands here, set chassis alliance and starting pose */
    fun initAuto() {}

    /* When the teleop ends, declare what to do */
    fun onEnd() {}
}