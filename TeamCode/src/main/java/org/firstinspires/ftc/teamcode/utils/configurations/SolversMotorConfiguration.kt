package org.firstinspires.ftc.teamcode.utils.configurations

import com.qualcomm.robotcore.hardware.PIDCoefficients
import com.seattlesolvers.solverslib.controller.wpilibcontroller.SimpleMotorFeedforward
import com.seattlesolvers.solverslib.hardware.motors.Motor
import java.util.Optional

class SolversMotorConfiguration {
    var inverted                : Boolean = false
    var runMode                 : Motor.RunMode
    var zeroPowerBehavior       : Motor.ZeroPowerBehavior
    var velocityCoefficients    : PIDCoefficients
    var velocityFeedforward     : SimpleMotorFeedforward

    init {
        this.runMode                 = Motor.RunMode.RawPower
        this.zeroPowerBehavior       = Motor.ZeroPowerBehavior.FLOAT
        this.velocityCoefficients    = PIDCoefficients(0.1, 0.0, 0.0)
        this.velocityFeedforward     = SimpleMotorFeedforward(0.0, 0.1, 0.0)
    }

    fun withInverted(value: Boolean): SolversMotorConfiguration {
        this.inverted = value
        return this
    }

    fun withRunMode(value: Motor.RunMode): SolversMotorConfiguration {
        this.runMode = value
        return this
    }

    fun withZeroPowerBehavior(value: Motor.ZeroPowerBehavior): SolversMotorConfiguration {
        this.zeroPowerBehavior = value
        return this
    }

    fun withVelocityCoefficients(value: PIDCoefficients): SolversMotorConfiguration {
        this.velocityCoefficients = value
        return this
    }

    fun withVelocityFeedforward(value: SimpleMotorFeedforward): SolversMotorConfiguration {
        this.velocityFeedforward = value
        return this
    }

    fun createConfiguration(inverted: Optional<Boolean>, runMode: Optional<Motor.RunMode>,
                            zeroPowerBehavior: Optional<Motor.ZeroPowerBehavior>, velocityCoefficients: Optional<PIDCoefficients>,
                            feedforwardCoefficients: Optional<SimpleMotorFeedforward>
    ): SolversMotorConfiguration {
        val newConfig: SolversMotorConfiguration = SolversMotorConfiguration()

        if (inverted.isPresent) {
            newConfig.withInverted(inverted.get())
        }

        if (runMode.isPresent) {
            newConfig.withRunMode(runMode.get())
        }

        if (zeroPowerBehavior.isPresent) {
            newConfig.withZeroPowerBehavior(zeroPowerBehavior.get())
        }

        if (velocityCoefficients.isPresent) {
            newConfig.withVelocityCoefficients(velocityCoefficients.get())
        }

        if (feedforwardCoefficients.isPresent) {
            newConfig.withVelocityFeedforward(feedforwardCoefficients.get())
        }

        return newConfig
    }
}