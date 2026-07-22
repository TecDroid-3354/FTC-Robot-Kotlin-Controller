package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.command.Robot
import com.seattlesolvers.solverslib.command.RunCommand
import com.seattlesolvers.solverslib.gamepad.GamepadEx
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.subsystems.Indexer.Indexer
import org.firstinspires.ftc.teamcode.subsystems.Intake.Intake
import org.firstinspires.ftc.teamcode.subsystems.Shooter.Shooter
import org.firstinspires.ftc.teamcode.subsystems.SimpleMotorSubsystem
import org.firstinspires.ftc.teamcode.subsystems.hood.Hood
import org.firstinspires.ftc.teamcode.utils.Alliance
import org.firstinspires.ftc.teamcode.utils.AngularVelocity
import org.firstinspires.ftc.teamcode.utils.TecDroidRobot
import org.firstinspires.ftc.teamcode.utils.constants.RobotConstants
import org.firstinspires.ftc.teamcode.utils.extensions.leftBumper
import org.firstinspires.ftc.teamcode.utils.extensions.onFalse
import org.firstinspires.ftc.teamcode.utils.extensions.onTrue
import org.firstinspires.ftc.teamcode.utils.extensions.rightBumper
import org.firstinspires.ftc.teamcode.utils.extensions.whileTrue
import org.firstinspires.ftc.teamcode.utils.extensions.y

class Robot(private val alliance: Alliance, private val hardwareMap: HardwareMap, private val controller: GamepadEx, telemetry: Telemetry): TecDroidRobot(telemetry, hardwareMap) {

    /* Declare your subsystems here */
    //private lateinit var sampleMotor: SimpleMotorSubsystem

    //private lateinit var hood: Hood

    private lateinit var shooter: Shooter

    private lateinit var intake: Intake

    private lateinit var indexer: Indexer

    init {
        subsystemInitialization()
    }

    /* Initialize your subsystems here */
    override fun subsystemInitialization() {
        //sampleMotor = SimpleMotorSubsystem(hardwareMap)
        //hood = Hood(hardwareMap)
        shooter = Shooter(hardwareMap)
        intake = Intake(hardwareMap)
        indexer = Indexer(hardwareMap)
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


        //controller.y()
            //.onTrue(InstantCommand({hood.moveHoodToPosition()}))
            //.onFalse(InstantCommand({hood.moveHoodDownPosition()}))

        controller.rightBumper()
            .whileTrue(RunCommand({ shooter.setShooterVelocity(AngularVelocity.fromRpm(3000.0))}))
            .onFalse(InstantCommand({shooter.stopShooter()}))

        controller.leftBumper()
            .onTrue(InstantCommand({intake.enableIntake(); indexer.startCentering(); indexer.startIndexer()}))
            .onFalse(InstantCommand({intake.disableIntake(); indexer.stopCentering(); indexer.stopIndexer()}))
    }

    /* Initialize your auto commands here, set chassis alliance and starting pose */
    override fun initAuto() {}

    /* When the teleop ends, declare what to do */
    override fun onEnd() {}

    // Print telemetry using the pTelemetry object on RobotConstants.Telemetry. It will be printed on both Panels and Driver Hub.
    override fun updateTelemetry() {
        RobotConstants.Telemetry.pTelemetry.addData("Shooter velocity", shooter.getVelocity().rps)
    }
}