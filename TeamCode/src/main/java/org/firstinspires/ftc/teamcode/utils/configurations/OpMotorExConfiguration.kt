package org.firstinspires.ftc.teamcode.utils.configurations

import org.firstinspires.ftc.teamcode.utils.configurations.controlModeConfigurations.ControlModeConfiguration
import org.firstinspires.ftc.teamcode.utils.configurations.controlModeConfigurations.PercentageModeConfiguration
import org.firstinspires.ftc.teamcode.utils.configurations.genericConfigurations.GenericMotorConfiguration

class OpMotorExConfiguration(
    var genericMotorConfiguration: GenericMotorConfiguration = GenericMotorConfiguration(),
    var controlModeConfiguration: ControlModeConfiguration = PercentageModeConfiguration()
) {

    fun withGenericMotorConfiguration(value: GenericMotorConfiguration): OpMotorExConfiguration {
        this.genericMotorConfiguration = value
        return this
    }

    fun withControlModeConfiguration(value: ControlModeConfiguration): OpMotorExConfiguration {
        this.controlModeConfiguration = value
        return this
    }
}