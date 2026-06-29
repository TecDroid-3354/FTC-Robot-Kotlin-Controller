package org.firstinspires.ftc.teamcode.utils.configurations.controlModeConfigurations

import org.firstinspires.ftc.teamcode.utils.devices.ControlMode

sealed interface ControlModeConfiguration {

    val controlMode: ControlMode
}