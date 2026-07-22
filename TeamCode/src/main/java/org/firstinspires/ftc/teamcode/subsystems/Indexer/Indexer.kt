package org.firstinspires.ftc.teamcode.subsystems.Indexer

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.SubsystemBase
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx

class Indexer(val hardwareMap: HardwareMap): SubsystemBase(){
    private lateinit var leftCenteringServo : CRServoEx
    private lateinit var rightCenteringServo : CRServoEx
    private lateinit var indexerServo : CRServoEx
     init {
         leftCenteringServo = CRServoEx(hardwareMap, IndexerConstants.Identification.leftCentringServoId)
         rightCenteringServo = CRServoEx(hardwareMap, IndexerConstants.Identification.rightCenteringServoId)
         indexerServo = CRServoEx(hardwareMap, IndexerConstants.Identification.indexerServoId)

         leftCenteringServo.setRunMode(IndexerConstants.Configuration.leftCenteringServoRunMode)
         rightCenteringServo.setRunMode(IndexerConstants.Configuration.rightCenteringServoRunMode)
         indexerServo.setRunMode(IndexerConstants.Configuration.indexerServoRunMode)
     }

    fun startCentering () {
        leftCenteringServo.set(1.0)
        rightCenteringServo.set(1.0)
    }

    fun stopCentering () {
        leftCenteringServo.set(0.0)
        rightCenteringServo.set(0.0)
    }

    fun startIndexer () {
        indexerServo.set(1.0)
    }

    fun stopIndexer () {
        indexerServo.set(0.0)
    }
}