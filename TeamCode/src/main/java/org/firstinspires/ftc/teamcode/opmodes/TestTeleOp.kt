package org.firstinspires.ftc.teamcode.opmodes

import com.bylazar.gamepad.Gamepad
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.CommandOpMode
import com.seattlesolvers.solverslib.command.CommandScheduler
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.command.button.Trigger
import com.seattlesolvers.solverslib.gamepad.GamepadEx
import com.seattlesolvers.solverslib.gamepad.GamepadKeys
import com.seattlesolvers.solverslib.hardware.motors.Motor
import org.firstinspires.ftc.teamcode.subsystems.Indexer.Indexer
import org.firstinspires.ftc.teamcode.subsystems.Intake.Intake
import org.firstinspires.ftc.teamcode.subsystems.hood.Hood
import org.firstinspires.ftc.teamcode.subsystems.mecanum.mecanum
import org.firstinspires.ftc.teamcode.utils.extensions.a
import org.firstinspires.ftc.teamcode.utils.extensions.b
import org.firstinspires.ftc.teamcode.utils.extensions.dpadDown
import org.firstinspires.ftc.teamcode.utils.extensions.dpadUp
import org.firstinspires.ftc.teamcode.utils.extensions.leftBumper
import org.firstinspires.ftc.teamcode.utils.extensions.onFalse
import org.firstinspires.ftc.teamcode.utils.extensions.onTrue
import org.firstinspires.ftc.teamcode.utils.extensions.rightBumper
import org.firstinspires.ftc.teamcode.utils.extensions.x
import org.firstinspires.ftc.teamcode.utils.extensions.y

@TeleOp(name = "Test TeleOp", group = "TeleOp")
class TestTeleOp (): CommandOpMode() {

    lateinit var controller : GamepadEx

    /*lateinit var frontRightMotor : Motor
    lateinit var frontLeftMotor : Motor
    lateinit var backRightMotor : Motor
    lateinit var backLeftMotor : Motor*/

    lateinit var indexer: Indexer
    lateinit var intake: Intake

    lateinit var mecanum: mecanum

    lateinit var hood: Hood

    var position : Double = 0.0

    override fun initialize() {
        /*frontRightMotor = Motor(hardwareMap, "frontRightMotor")
        frontLeftMotor = Motor(hardwareMap,"frontLeftMotor")
        backRightMotor = Motor(hardwareMap,"backRightMotor")
        backLeftMotor = Motor(hardwareMap,"backLeftMotor")*/

        controller = GamepadEx(gamepad1)

        indexer = Indexer(hardwareMap)
        intake = Intake(hardwareMap)
        mecanum = mecanum(hardwareMap)
        hood = Hood(hardwareMap)


        /*controller.y()
            .onTrue(InstantCommand({frontRightMotor.set(1.0)}))
            .onFalse(InstantCommand({frontRightMotor.set(0.0)}))
        controller.x()
            .onTrue(InstantCommand({frontLeftMotor.set(1.0)}))
            .onFalse(InstantCommand({frontLeftMotor.set(0.0)}))
        controller.b()
            .onTrue(InstantCommand({backRightMotor.set(1.0)}))
            .onFalse(InstantCommand({backRightMotor.set(0.0)}))
        controller.a()
            .onTrue(InstantCommand({backLeftMotor.set(1.0)}))
            .onFalse(InstantCommand({backLeftMotor.set(0.0)}))

        Trigger({controller.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1})
            .whenActive(InstantCommand({backLeftMotor.set(1.0)}))
            .whenInactive(InstantCommand({backLeftMotor.set(0.0)}))
        Trigger({controller.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.1})
            .whenActive(InstantCommand({backRightMotor.set(1.0)}))
            .whenInactive(InstantCommand({backRightMotor.set(0.0)}))
        controller.leftBumper()
            .onTrue(InstantCommand({frontRightMotor.set(1.0)}))
            .onFalse(InstantCommand({frontRightMotor.set(0.0)}))
        controller.rightBumper()
            .onTrue(InstantCommand({frontLeftMotor.set(1.0)}))
            .onFalse(InstantCommand({frontLeftMotor.set(0.0)}))*/

        controller.leftBumper()
            .onTrue(InstantCommand({intake.enableIntake(); indexer.startCentering(); indexer.startIndexer()}))
            .onFalse(InstantCommand({intake.disableIntake(); indexer.stopCentering(); indexer.stopIndexer()}))
        controller.rightBumper()
            .onTrue(InstantCommand({intake.reverseIntake()}))
            .onFalse(InstantCommand({intake.disableIntake()}))
        controller.dpadUp()
            .onTrue(InstantCommand({position += 0.1}))
        controller.dpadDown()
            .onTrue(InstantCommand({position -= 0.1}))
    }

    override fun runOpMode() {
        initialize()
        waitForStart()
        while (!isStopRequested && opModeIsActive()) {
            CommandScheduler.getInstance().run()
            mecanum.drive(
                -controller.leftY,
                -controller.leftX,
                -controller.rightX
            )

            hood.setServo(position)

            if (position < 0.0 ) {
                position = 0.0
            }

            if (position > 1.0 ){
                position = 1.0
            }

            telemetry.addData("position", position)
            telemetry.update()
        }

        reset()
    }




}