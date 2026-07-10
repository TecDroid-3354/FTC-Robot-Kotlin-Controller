package org.firstinspires.ftc.teamcode.utils.configurations.controlModeConfigurations

import com.qualcomm.robotcore.hardware.PIDCoefficients
import org.firstinspires.ftc.teamcode.utils.Angle
import org.firstinspires.ftc.teamcode.utils.AngularAcceleration
import org.firstinspires.ftc.teamcode.utils.AngularVelocity
import org.firstinspires.ftc.teamcode.utils.devices.ControlMode

class TrapezoidalModeConfiguration: ControlModeConfiguration {

    override val controlMode        : ControlMode
        get() = ControlMode.TRAPEZOIDAL

    var cruiseVelocity              : AngularVelocity       = AngularVelocity(0.0)

    var acceleration                : AngularAcceleration   = AngularAcceleration(0.0)

    var positionTolerance           : Double                = 0.5

    var profileLimits               : ClosedRange<Angle>    = Angle(Double.NEGATIVE_INFINITY)..
            Angle(Double.POSITIVE_INFINITY)

    var profileCoefficients         : PIDCoefficients       = PIDCoefficients()

    fun withCruiseVelocity(value: AngularVelocity)          : TrapezoidalModeConfiguration {
        this.cruiseVelocity         = value
        return this
    }

    fun withAcceleration(value: AngularAcceleration)        : TrapezoidalModeConfiguration {
        this.acceleration           = value
        return this
    }

    fun withPositionTolerance(value: Double)                : TrapezoidalModeConfiguration {
        this.positionTolerance = value
        return this
    }

    fun withProfileLimits(value: ClosedRange<Angle>)        : TrapezoidalModeConfiguration {
        this.profileLimits = value
        return this
    }

    fun withProfileCoefficients(value: PIDCoefficients)     : TrapezoidalModeConfiguration {
        this.profileCoefficients    = value
        return this
    }
}