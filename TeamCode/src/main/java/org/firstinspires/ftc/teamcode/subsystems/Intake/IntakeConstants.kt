package org.firstinspires.ftc.teamcode.subsystems.Intake

import com.seattlesolvers.solverslib.hardware.motors.Motor

class IntakeConstants {
    object identification{
        val intakeMotorId = "intakeMotor"
    }

    object configuration{
        val isInMotorInverted = true
        val inMotorMode = Motor.RunMode.RawPower
        val inMotorZeroBeheaviour = Motor.ZeroPowerBehavior.FLOAT
    }
}