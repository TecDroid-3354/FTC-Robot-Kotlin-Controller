package org.firstinspires.ftc.teamcode.subsystems.Shooter

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.hardware.PIDCoefficients
import com.qualcomm.robotcore.hardware.PIDFCoefficients
import com.seattlesolvers.solverslib.controller.wpilibcontroller.SimpleMotorFeedforward
import com.seattlesolvers.solverslib.hardware.motors.Motor

object ShooterConstants {

    object Identification {
        val shooterMotorId = "shooterMotor"
    }

    object Configuration {
        val isShooterMotorInverted = false
        val shooterMotorZeroBeheavor = Motor.ZeroPowerBehavior.FLOAT
        val shooterMotorMode = Motor.RunMode.RawPower
    }

    @Configurable
    object Tunables {
        @JvmField
        var pidCoefficients = PIDCoefficients(0.01, 0.0, 0.0)
        @JvmField
        var feedforward = SimpleMotorFeedforward(0.0,1.15)
    }
}