package org.firstinspires.ftc.teamcode

import com.qualcomm.hardware.sparkfun.SparkFunOTOS
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.seattlesolvers.solverslib.command.CommandOpMode
import com.seattlesolvers.solverslib.hardware.motors.Motor
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup

@TeleOp(name = "Encoder testing")
class OpMode: CommandOpMode() {

    lateinit var frontRight : Motor
    lateinit var backRight : Motor
    lateinit var frontLeft : Motor
    lateinit var backLeft : Motor
    lateinit var otos: SparkFunOTOS

    override fun initialize() {
        frontRight = Motor(hardwareMap, "frontRight")
        backRight = Motor(hardwareMap, "backRight")
        frontLeft = Motor(hardwareMap, "frontLeft")
        backLeft = Motor(hardwareMap, "backLeft")
        otos = hardwareMap.get(SparkFunOTOS::class.java, "otos")
    }

    override fun runOpMode() {

        initialize()

        waitForStart()

        while (opModeIsActive() && isStopRequested.not()) {
            telemetry.addData("Encoder position: FR", frontRight.encoder.position.unaryMinus())
            telemetry.addData("Encoder position: BR", backRight.encoder.position.unaryMinus())
            telemetry.addData("Encoder position: FL", frontLeft.encoder.position)
            telemetry.addData("Encoder position: BL", backLeft.encoder.position)
            telemetry.addData("Otos position", otos.position)
            telemetry.update()
            run()
        }
    }
}