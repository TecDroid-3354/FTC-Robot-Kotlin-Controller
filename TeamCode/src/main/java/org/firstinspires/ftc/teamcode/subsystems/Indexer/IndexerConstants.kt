package org.firstinspires.ftc.teamcode.subsystems.Indexer

import com.seattlesolvers.solverslib.hardware.motors.CRServoEx

object IndexerConstants {

    object  Identification {
        val leftCenteringServoId = "leftCenteringServo"

        val rightCenteringServoId = "rightCenteringServo"

        val indexerServoId = "indexerServo"
    }

    object Configuration {
        val leftCenteringServoRunMode = CRServoEx.RunMode.RawPower

        val rightCenteringServoRunMode = CRServoEx.RunMode.RawPower

        val indexerServoRunMode = CRServoEx.RunMode.RawPower

    }
}