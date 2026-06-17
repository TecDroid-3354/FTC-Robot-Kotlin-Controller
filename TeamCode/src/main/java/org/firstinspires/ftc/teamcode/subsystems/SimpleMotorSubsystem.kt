@file:Suppress("JoinDeclarationAndAssignment")

package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.hardware.motors.Motor
import com.seattlesolvers.solverslib.hardware.motors.MotorEx

class SimpleMotorSubsystem(private val hardwareMap: HardwareMap) {
    /* Declare your motor here */
    private var motor: MotorEx

    private lateinit var runMode: Motor.RunMode

    /* Initialize your motor here */
    init {
        motor = MotorEx(hardwareMap, "motor")
    }

    /* Turn on your motor here */
    fun enableMotor() {
        motor.set(1.0)
    }

    /* Turn off your motor here */
    fun disableMotor() {
        motor.set(0.0)
    }

    /* Set up your velocity motor */
    fun switchToVelocityMode() {
        runMode = Motor.RunMode.VelocityControl

    }

    /* Set up your position motor */
    fun switchToPositionMode() {
        runMode = Motor.RunMode.PositionControl

    }

    /* Set up your percentage motor */
    fun switchToRawPowerMode() {
        runMode = Motor.RunMode.RawPower
    }

    /* Write what to do in each case */
    fun runMotorAccordingMode() {
        when (runMode) {
            Motor.RunMode.RawPower -> {
                /* Write here your percentage logic */
                TODO()
            }

            Motor.RunMode.VelocityControl -> {
                /* Write here your velocity logic */
                TODO()
            }

            Motor.RunMode.PositionControl -> {
                /* Write here your position logic */
                TODO()
            }
        }
    }
}