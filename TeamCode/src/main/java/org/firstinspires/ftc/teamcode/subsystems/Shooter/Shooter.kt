package org.firstinspires.ftc.teamcode.subsystems.Shooter

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.SubsystemBase
import com.seattlesolvers.solverslib.hardware.motors.MotorEx
import org.firstinspires.ftc.teamcode.utils.AngularVelocity
import org.firstinspires.ftc.teamcode.utils.extensions.setVelocityCoefficients
import org.firstinspires.ftc.teamcode.utils.extensions.setVelocityFeedforward

class Shooter(val hardwareMap : HardwareMap) : SubsystemBase() {
    private val shooterMotor: MotorEx

    private var shooterVelocity : AngularVelocity = AngularVelocity.fromRps(0.0)

    init {
        shooterMotor = MotorEx(hardwareMap, ShooterConstants.Identification.shooterMotorId)

        shooterMotor.setInverted(ShooterConstants.Configuration.isShooterMotorInverted)
        shooterMotor.setZeroPowerBehavior(ShooterConstants.Configuration.shooterMotorZeroBeheavor)
        shooterMotor.setRunMode(ShooterConstants.Configuration.shooterMotorMode)
        shooterMotor.setVelocityCoefficients(ShooterConstants.Tunables.pidCoefficients)
        shooterMotor.setVelocityFeedforward(ShooterConstants.Tunables.feedforward)
    }

    override fun periodic() {
        shooterMotor.setVelocityCoefficients(ShooterConstants.Tunables.pidCoefficients)
        shooterMotor.setVelocityFeedforward(ShooterConstants.Tunables.feedforward)

        shooterVelocity = AngularVelocity.fromRps(shooterMotor.velocity/28)
    }

    fun setShooterVelocity(velocity: AngularVelocity) {
        val clampedVelocity = velocity.rps.coerceIn(-100.0, 100.0)
        val velocityInTicksPerSecond = clampedVelocity * 28

        shooterMotor.velocity = velocityInTicksPerSecond
    }

    fun stopShooter() {
        shooterMotor.set(0.0)
    }

    fun getVelocity(): AngularVelocity {
        return shooterVelocity
    }
}