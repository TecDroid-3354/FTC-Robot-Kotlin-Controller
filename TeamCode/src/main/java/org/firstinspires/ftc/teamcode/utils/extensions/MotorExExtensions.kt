package org.firstinspires.ftc.teamcode.utils.extensions

import com.qualcomm.robotcore.hardware.PIDCoefficients
import com.seattlesolvers.solverslib.controller.wpilibcontroller.SimpleMotorFeedforward
import com.seattlesolvers.solverslib.hardware.motors.MotorEx
import org.firstinspires.ftc.teamcode.utils.configurations.SolversMotorConfiguration

private fun MotorEx.setVelocityCoefficients(pidCoefficients: PIDCoefficients) {
    this.setVeloCoefficients(
        pidCoefficients.p,
        pidCoefficients.i,
        pidCoefficients.d
    )
}

private fun MotorEx.setVelocityFeedforward(feedforward: SimpleMotorFeedforward) {
    this.setFeedforwardCoefficients(
        feedforward.ks,
        feedforward.kv,
        feedforward.ka
    )
}

fun MotorEx.applyConfiguration(config: SolversMotorConfiguration) {
    this.inverted = config.inverted
    this.setRunMode(config.runMode)
    this.setZeroPowerBehavior(config.zeroPowerBehavior)
    this.setVelocityCoefficients(config.velocityCoefficients)
    this.setVelocityFeedforward(config.velocityFeedforward)
}