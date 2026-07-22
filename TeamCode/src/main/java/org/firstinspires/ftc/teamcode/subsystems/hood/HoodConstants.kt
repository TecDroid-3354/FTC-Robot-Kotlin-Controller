package org.firstinspires.ftc.teamcode.subsystems.hood

class HoodConstants {
    object Identification {
        val hoodServoId = "hoodServo"
    }

    object PhysicalLimits {
        val gearRatio = 1/2
        val minAngle = 5.0 * gearRatio
        val maxAngle = 30.0 * gearRatio
    }
    object Configuration {
        val isHoodServoInverted = false
    }
}