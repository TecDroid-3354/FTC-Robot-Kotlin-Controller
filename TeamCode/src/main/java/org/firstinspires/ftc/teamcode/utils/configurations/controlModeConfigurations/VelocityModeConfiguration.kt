package org.firstinspires.ftc.teamcode.utils.configurations.controlModeConfigurations

import com.qualcomm.robotcore.hardware.PIDCoefficients
import com.seattlesolvers.solverslib.controller.wpilibcontroller.SimpleMotorFeedforward
import org.firstinspires.ftc.teamcode.utils.devices.ControlMode

class VelocityModeConfiguration: ControlModeConfiguration {

    override val controlMode        : ControlMode
        get() = ControlMode.VELOCITY

    var velocityCoefficients        : PIDCoefficients           = PIDCoefficients()

    var feedforwardCoefficients     : SimpleMotorFeedforward    = SimpleMotorFeedforward(0.0, 0.0, 0.0)

    fun withVelocityCoefficients(value: PIDCoefficients)        : VelocityModeConfiguration {
        this.velocityCoefficients = value
        return this
    }

    fun withFeedforwardCoefficients(value: SimpleMotorFeedforward): VelocityModeConfiguration {
        this.feedforwardCoefficients = value
        return this
    }
}