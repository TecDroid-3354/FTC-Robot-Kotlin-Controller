package org.firstinspires.ftc.teamcode

import com.seattlesolvers.solverslib.command.CommandOpMode
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.command.button.Trigger
import com.seattlesolvers.solverslib.gamepad.GamepadEx
import com.seattlesolvers.solverslib.gamepad.GamepadKeys
import org.firstinspires.ftc.teamcode.utils.Alliance

open class TeleOpMode(val alliance: Alliance): CommandOpMode() {
    lateinit var controller: GamepadEx
    lateinit var robot: Robot

    override fun initialize() {
        controller = GamepadEx(gamepad1)
        robot = Robot(alliance, hardwareMap, controller, telemetry)
        robot.initTeleOp()

        Trigger({ controller.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1 })
            .whenActive(InstantCommand({}))
            .whenInactive(InstantCommand({}))

    }

    override fun run() {
        robot.run()
    }

    override fun end() {
        robot.onEnd()
    }
}