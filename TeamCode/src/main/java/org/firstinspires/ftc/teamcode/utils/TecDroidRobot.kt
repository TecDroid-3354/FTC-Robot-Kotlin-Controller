package org.firstinspires.ftc.teamcode.utils

import com.qualcomm.hardware.lynx.LynxModule
import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.CommandScheduler
import com.seattlesolvers.solverslib.command.Robot
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.utils.constants.RobotConstants.Telemetry.pTelemetry

abstract class TecDroidRobot(private val telemetry: Telemetry, private val hardwareMap: HardwareMap): Robot() {

    init {
        initBulkReadings()
        subsystemInitialization()
    }

    protected abstract fun subsystemInitialization()

    abstract fun initTeleOp()

    abstract fun initAuto()

    private fun initBulkReadings() {
        super.setBulkReading(hardwareMap, LynxModule.BulkCachingMode.MANUAL)
    }

    protected abstract fun updateTelemetry()

    override fun run() {
        CommandScheduler.getInstance().run()
        updateTelemetry()
        pTelemetry.update(telemetry)
    }

    abstract fun onEnd()
}