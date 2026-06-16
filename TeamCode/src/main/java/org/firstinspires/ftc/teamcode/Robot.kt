package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.Robot
import com.seattlesolvers.solverslib.gamepad.GamepadEx
import org.firstinspires.ftc.teamcode.utils.Alliance

class Robot(alliance: Alliance, hardwareMap: HardwareMap, controller: GamepadEx): Robot() {

    /* Declare your subsystems here */

    /* Call the subsystemInitialization method, done in class init */
    init {
        subsystemInitialization()
    }

    /* Initialize your subsystems here */
    fun subsystemInitialization() {

    }

    /* Initialize your teleop commands here */
    fun initTeleOp() {}

    /* Initialize your auto commands here */
    fun initAuto() {}

    /* When the teleop ends, declare what to do */
    fun onEnd() {}
}