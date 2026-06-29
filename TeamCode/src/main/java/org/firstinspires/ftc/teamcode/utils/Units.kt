package org.firstinspires.ftc.teamcode.utils

import kotlin.math.PI
import kotlin.time.Duration.Companion.seconds


// -------------------- DISTANCE --------------------
    @JvmInline
    value class Distance(val meters: Double): Comparable<Distance> {
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

        operator fun plus(other: Distance): Distance    = Distance(meters + other.meters)
        operator fun minus(other: Distance): Distance   = Distance(meters - other.meters)
        operator fun times(factor: Double): Distance    = Distance(meters * factor)
        operator fun div(factor: Double): Distance      = Distance(meters / factor)
        override fun compareTo(other: Distance): Int    { return meters.compareTo(other.meters) }
    }

    // -------------------- ANGLE --------------------
    @JvmInline
    value class Angle(val rotations: Double): Comparable<Angle> {
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

        operator fun plus(other: Angle): Angle          = Angle(rotations + other.rotations)
        operator fun minus(other: Angle): Angle         = Angle(rotations - other.rotations)
        operator fun times(factor: Double): Angle       = Angle(rotations * factor)
        operator fun div(factor: Double): Angle         = Angle(rotations / factor)
        override fun compareTo(other: Angle): Int       { return rotations.compareTo(other.rotations) }
    }

    // -------------------- LINEAR VELOCITY --------------------
    @JvmInline
    value class LinearVelocity(val mps: Double): Comparable<LinearVelocity> {
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

        operator fun plus(other: LinearVelocity): LinearVelocity    = LinearVelocity(mps + other.mps)
        operator fun minus(other: LinearVelocity): LinearVelocity   = LinearVelocity(mps - other.mps)
        operator fun times(factor: Double): LinearVelocity          = LinearVelocity(mps * factor)
        operator fun div(factor: Double): LinearVelocity            = LinearVelocity(mps / factor)
        override fun compareTo(other: LinearVelocity): Int          { return mps.compareTo(other.mps) }
    }

    // -------------------- ANGULAR VELOCITY --------------------
    @JvmInline
    value class AngularVelocity(val rotPerSec: Double): Comparable<AngularVelocity> {
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

        operator fun plus(other: AngularVelocity): AngularVelocity  = AngularVelocity(rotPerSec + other.rotPerSec)
        operator fun minus(other: AngularVelocity): AngularVelocity = AngularVelocity(rotPerSec - other.rotPerSec)
        operator fun times(factor: Double): AngularVelocity         = AngularVelocity(rotPerSec * factor)
        operator fun div(factor: Double): AngularVelocity           = AngularVelocity(rotPerSec / factor)
        override fun compareTo(other: AngularVelocity)              : Int { return rps.compareTo(other.rps) }
    }

    // -------------------- ACCELERATION --------------------
    @JvmInline
    value class AngularAcceleration(val rotPerSecPerSec: Double): Comparable<AngularAcceleration> {
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

        val radPerSecPerSec : Double get() = rotPerSecPerSec * 2.0 * PI
        val degPerSecPerSec : Double get() = rotPerSecPerSec * 360.0
        val rpmPerSec       : Double get() = rotPerSecPerSec * 60.0

        operator fun plus(other: AngularAcceleration): AngularAcceleration      = AngularAcceleration(rotPerSecPerSec + other.rotPerSecPerSec)
        operator fun minus(other: AngularAcceleration): AngularAcceleration     = AngularAcceleration(rotPerSecPerSec - other.rotPerSecPerSec)
        operator fun times(factor: Double): AngularAcceleration                 = AngularAcceleration(rotPerSecPerSec * factor)
        operator fun div(factor: Double): AngularAcceleration                   = AngularAcceleration(rotPerSecPerSec / factor)
        override fun compareTo(other: AngularAcceleration)                      : Int { return rotPerSecPerSec.compareTo(other.rotPerSecPerSec) }
    }

    // -------------------- MASS --------------------
    @JvmInline
    value class Mass(val kilograms: Double): Comparable<Mass> {
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

        operator fun plus(other: Mass): Mass                    = Mass(kilograms + other.kilograms)
        operator fun minus(other: Mass): Mass                   = Mass(kilograms - other.kilograms)
        operator fun times(factor: Double): Mass                = Mass(kilograms * factor)
        operator fun div(factor: Double): Mass                  = Mass(kilograms / factor)
        override fun compareTo(other: Mass)                     : Int { return kilograms.compareTo(other.kilograms) }
    }

    // -------------------- VOLTAGE --------------------

    @JvmInline
    value class Voltage(val volts: Double): Comparable<Voltage> {

        companion object {
            @JvmStatic
            fun fromVolts(volts: Double)                        : Voltage               = Voltage(volts)
        }

        operator fun plus(other: Voltage)               = Voltage(volts + other.volts)
        operator fun minus(other: Voltage)              = Voltage(volts - other.volts)
        operator fun times(factor: Double)              = Voltage(volts * factor)
        operator fun div(factor: Double)                = Voltage(volts / factor)
        override fun compareTo(other: Voltage)          : Int { return volts.compareTo(other.volts) }
    }