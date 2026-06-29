package org.firstinspires.ftc.teamcode.utils.configurations.controlModeConfigurations

import org.firstinspires.ftc.teamcode.utils.devices.ControlMode

class PercentageModeConfiguration: ControlModeConfiguration {

    override val controlMode        : ControlMode
        get() = ControlMode.PERCENTAGE

    var maxPower                    : Double = 1.0

    fun withMaxPower(value: Double) : PercentageModeConfiguration {
        this.maxPower = value.coerceIn(-1.0, 1.0)
        return this
    }
}