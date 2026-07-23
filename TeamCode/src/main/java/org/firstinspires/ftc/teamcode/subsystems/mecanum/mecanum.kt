package org.firstinspires.ftc.teamcode.subsystems.mecanum

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.drivebase.MecanumDrive
import com.seattlesolvers.solverslib.hardware.motors.Motor
import org.firstinspires.ftc.robotcore.external.navigation.Velocity
import org.firstinspires.ftc.teamcode.utils.configurations.controlModeConfigurations.VelocityModeConfiguration


class mecanum (hardwareMap: HardwareMap) {
    lateinit var frontRight: Motor
    lateinit var backRight: Motor

    lateinit var backLeft: Motor

    lateinit var frontLeft: Motor

    // input motors exactly as shown below
    lateinit var mecanum: MecanumDrive

    init {
        frontRight = Motor(hardwareMap, "frontRightMotor")
        backRight = Motor(hardwareMap,"backRightMotor")
        backLeft = Motor(hardwareMap,"backLeftMotor")
        frontLeft = Motor(hardwareMap,"frontLeftMotor")

        frontRight.setInverted(true)
        backRight.setInverted(false)
        backLeft.setInverted(false)
        frontLeft.setInverted(true)

        mecanum = MecanumDrive(frontLeft, frontRight, backLeft, backRight)
    }

    fun drive(driveVelocity: Double, lateralVelocity: Double, rotationVelocity: Double){
        mecanum.driveRobotCentric(lateralVelocity,driveVelocity,rotationVelocity)
    }

}