package org.firstinspires.ftc.teamcode.utils.extensions

import com.qualcomm.robotcore.hardware.PIDCoefficients
import com.seattlesolvers.solverslib.controller.wpilibcontroller.SimpleMotorFeedforward
import com.seattlesolvers.solverslib.hardware.motors.MotorEx

fun MotorEx.setVelocityCoefficients(pidCoefficients: PIDCoefficients) {
    this.setVeloCoefficients(
        pidCoefficients.p,
        pidCoefficients.i,
        pidCoefficients.d
    )
}

fun MotorEx.setVelocityFeedforward(feedforward: SimpleMotorFeedforward) {
    this.setFeedforwardCoefficients(
        feedforward.ks,
        feedforward.kv,
        feedforward.ka
    )
}