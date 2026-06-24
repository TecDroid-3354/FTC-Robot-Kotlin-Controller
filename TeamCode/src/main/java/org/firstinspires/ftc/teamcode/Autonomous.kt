package org.firstinspires.ftc.teamcode

import com.seattlesolvers.solverslib.command.CommandOpMode
import com.seattlesolvers.solverslib.command.SequentialCommandGroup
import com.seattlesolvers.solverslib.gamepad.GamepadEx
import org.firstinspires.ftc.teamcode.utils.Alliance
import com.seattlesolvers.solverslib.command.Command

class Autonomous(private val alliance: Alliance): CommandOpMode() {

    lateinit var controller: GamepadEx
    lateinit var robot: Robot

    override fun initialize() {
        super.reset()
        controller = GamepadEx(gamepad1)
        robot = Robot(alliance, hardwareMap, controller)
        robot.initAuto()

        val autonomousCommand: Command = SequentialCommandGroup(
            /* Write your commands here, don't forget to use parallels, race or sequential if needed */
        )

        autonomousCommand.schedule()
    }

    override fun run() {
        robot.run()
    }

    override fun end() {
        robot.onEnd()
    }
}