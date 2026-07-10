package org.firstinspires.ftc.teamcode.utils.configurations.controlModeConfigurations

import com.qualcomm.robotcore.hardware.PIDFCoefficients
import org.firstinspires.ftc.teamcode.utils.Angle
import org.firstinspires.ftc.teamcode.utils.devices.ControlMode

class PositionModeConfiguration: ControlModeConfiguration {

    override val controlMode        : ControlMode
        get() = ControlMode.POSITION

    var positionCoefficients        : PIDFCoefficients                  = PIDFCoefficients()

    var positionLimits              : ClosedRange<Angle>                =
        Angle(Double.NEGATIVE_INFINITY)..Angle(Double.POSITIVE_INFINITY)

    var positionTolerance           : Double                            = 0.5

    fun withPIDFCoefficients(value: PIDFCoefficients)                   : PositionModeConfiguration {
        this.positionCoefficients = value
        return this
    }

    fun withPositionLimits(value: ClosedRange<Angle>)       : PositionModeConfiguration {
        this.positionLimits = value
        return this
    }

    fun withPositionTolerance(value: Double)                : PositionModeConfiguration {
        this.positionTolerance = value
        return this
    }
}