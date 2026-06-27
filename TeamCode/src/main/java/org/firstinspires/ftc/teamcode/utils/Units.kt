package org.firstinspires.ftc.teamcode.utils

import kotlin.math.PI
    // -------------------- DISTANCE --------------------
    @JvmInline
    value class Distance(val meters: Double) {
        companion object {
            @JvmStatic
            fun fromCm(cm: Double)                              : Distance              = Distance(cm / 100.0)
            @JvmStatic
            fun fromInches(inches: Double)                      : Distance              = Distance(inches * 0.0254)
            @JvmStatic
            fun fromMeters(meters: Double)                      : Distance              = Distance(meters)
        }

        val cm              : Double get() = meters * 100.0
        val inches          : Double get() = meters / 0.0254

        operator fun plus(other: Distance)              = Distance(meters + other.meters)
        operator fun minus(other: Distance)             = Distance(meters - other.meters)
        operator fun times(factor: Double)              = Distance(meters * factor)
        operator fun div(factor: Double)                = Distance(meters / factor)
    }

    // -------------------- ANGLE --------------------
    @JvmInline
    value class Angle(val rotations: Double) {
        companion object {
            @JvmStatic
            fun fromDegrees(deg: Double)                        : Angle                 = Angle(deg / 360.0)
            @JvmStatic
            fun fromRadians(rad: Double)                        : Angle                 = Angle(rad / (2.0 * PI))
            @JvmStatic
            fun fromRotations(rot: Double)                      : Angle                 = Angle(rot)
        }

        val degrees         : Double get() = rotations * 360.0
        val radians         : Double get() = rotations * 2.0 * PI

        operator fun plus(other: Angle)                 = Angle(rotations + other.rotations)
        operator fun minus(other: Angle)                = Angle(rotations - other.rotations)
        operator fun times(factor: Double)              = Angle(rotations * factor)
        operator fun div(factor: Double)                = Angle(rotations / factor)
    }

    // -------------------- LINEAR VELOCITY --------------------
    @JvmInline
    value class LinearVelocity(val mps: Double) {
        companion object {
            @JvmStatic
            fun fromCmps(cmps: Double)                          : LinearVelocity        = LinearVelocity(cmps / 100.0)
            @JvmStatic
            fun fromInps(inps: Double)                          : LinearVelocity        = LinearVelocity(inps * 0.0254)
            @JvmStatic
            fun fromMps(mps: Double)                            : LinearVelocity        = LinearVelocity(mps)
        }

        val cmps            : Double get() = mps * 100.0
        val inps            : Double get() = mps / 0.0254
        val mpsVal          : Double get() = mps

        operator fun plus(other: LinearVelocity)        = LinearVelocity(mps + other.mps)
        operator fun minus(other: LinearVelocity)       = LinearVelocity(mps - other.mps)
        operator fun times(factor: Double)              = LinearVelocity(mps * factor)
        operator fun div(factor: Double)                = LinearVelocity(mps / factor)
    }

    // -------------------- ANGULAR VELOCITY --------------------
    @JvmInline
    value class AngularVelocity(val rotPerSec: Double) {
        companion object {
            @JvmStatic
            fun fromDegPerSec(degps: Double)                    : AngularVelocity       = AngularVelocity(degps / 360.0)
            @JvmStatic
            fun fromRadPerSec(radps: Double)                    : AngularVelocity       = AngularVelocity(radps / (2.0 * PI))
            @JvmStatic
            fun fromRpm(rpm: Double)                            : AngularVelocity       = AngularVelocity(rpm / 60.0)
            @JvmStatic
            fun fromRps(rps: Double)                            : AngularVelocity       = AngularVelocity(rps)
        }

        val radPerSec       : Double get() = rotPerSec * 2.0 * PI
        val degPerSec       : Double get() = rotPerSec * 360.0
        val rpm             : Double get() = rotPerSec * 60.0
        val rps             : Double get() = rotPerSec

        operator fun plus(other: AngularVelocity)       = AngularVelocity(rotPerSec + other.rotPerSec)
        operator fun minus(other: AngularVelocity)      = AngularVelocity(rotPerSec - other.rotPerSec)
        operator fun times(factor: Double)              = AngularVelocity(rotPerSec * factor)
        operator fun div(factor: Double)                = AngularVelocity(rotPerSec / factor)
    }

    // -------------------- ACCELERATION --------------------
    @JvmInline
    value class AngularAcceleration(val rotPerSecSq: Double) {
        companion object {
            @JvmStatic
            fun fromDegPerSecSq(degPerSecPerSec: Double)        : AngularAcceleration   = AngularAcceleration(degPerSecPerSec / 360.0)
            @JvmStatic
            fun fromRadPerSecSq(radPerSecPerSec: Double)        : AngularAcceleration   = AngularAcceleration(radPerSecPerSec / (2.0 * PI))
            @JvmStatic
            fun fromRpmPerSec(rpmPerSec: Double)                : AngularAcceleration   = AngularAcceleration(rpmPerSec / 60.0)
            @JvmStatic
            fun fromRotPerSecPerSec(rotPerSecPerSec: Double)    : AngularAcceleration   = AngularAcceleration(rotPerSecPerSec)
        }

        val radPerSecPerSec : Double get() = rotPerSecSq * 2.0 * PI
        val degPerSecPerSec : Double get() = rotPerSecSq * 360.0
        val rpmPerSec       : Double get() = rotPerSecSq * 60.0
        val rotPerSecPerSec : Double get() = rotPerSecSq

        operator fun plus(other: AngularAcceleration)   = AngularAcceleration(rotPerSecSq + other.rotPerSecSq)
        operator fun minus(other: AngularAcceleration)  = AngularAcceleration(rotPerSecSq - other.rotPerSecSq)
        operator fun times(factor: Double)              = AngularAcceleration(rotPerSecSq * factor)
        operator fun div(factor: Double)                = AngularAcceleration(rotPerSecSq / factor)
    }

    // -------------------- MASS --------------------
    @JvmInline
    value class Mass(val kilograms: Double) {
        companion object {
            @JvmStatic
            fun fromKilograms(kilograms: Double)                : Mass                  = Mass(kilograms)
            @JvmStatic
            fun fromGrams(grams: Double)                        : Mass                  = Mass(grams / 1000.0)
            @JvmStatic
            fun fromPounds(pounds: Double)                      : Mass                  = Mass(pounds * 0.453592)
        }

        val grams           : Double get()  = kilograms * 1000.0
        val pounds          : Double get()  = kilograms * 2.20462

        operator fun plus(other: Mass)                  = Mass(kilograms + other.kilograms)
        operator fun minus(other: Mass)                 = Mass(kilograms - other.kilograms)
        operator fun times(factor: Double)              = Mass(kilograms * factor)
        operator fun div(factor: Double)                = Mass(kilograms / factor)
    }

    // -------------------- VOLTAGE --------------------

    @JvmInline
    value class Voltage(val volts: Double) {

        companion object {
            @JvmStatic
            fun fromVolts(volts: Double)                        : Voltage               = Voltage(volts)
        }

        operator fun plus(other: Voltage)               = Voltage(volts + other.volts)
        operator fun minus(other: Voltage)              = Voltage(volts - other.volts)
        operator fun times(factor: Double)              = Voltage(volts * factor)
        operator fun div(factor: Double)                = Voltage(volts / factor)
    }