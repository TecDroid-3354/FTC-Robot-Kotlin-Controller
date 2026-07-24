package org.firstinspires.ftc.teamcode.subsystems.hood

import androidx.core.math.MathUtils
import com.bylazar.telemetry.PanelsTelemetry
import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.hardware.motors.MotorEx
import com.seattlesolvers.solverslib.hardware.servos.ServoEx
import com.seattlesolvers.solverslib.util.InterpLUT
import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.utils.Distance
import org.firstinspires.ftc.teamcode.utils.constants.RobotConstants

class Hood(hardwareMap: HardwareMap) {

    /**
     * Declare your variables here
     * */

    /**
     * Initialize your servo here
     * */
    private var hoodServo: ServoEx = ServoEx(hardwareMap, HoodConstants.Identification.hoodServoId)
    val interpolation = InterpLUT()
    var distance = 10.0


    init {

        /**Apply your configuration here**/

        hoodServo.setInverted(HoodConstants.Configuration.isHoodServoInverted)


        /**
         * Interpolations LookUp Table
         *
         * First value is the distance, the second is the hood angle
         * */

        interpolation.add(0.0,5.0)
        interpolation.add(1.0,6.0)
        interpolation.add(2.0,7.0)
        interpolation.add(3.0,8.0)
        interpolation.add(4.0,9.0)
        interpolation.add(5.0,10.0)
        interpolation.add(6.0,11.0)
        interpolation.add(7.0,12.0)
        interpolation.add(8.0,13.0)
        interpolation.add(9.0,14.0)
        interpolation.add(10.0,60.0)

        interpolation.createLUT()

    }


    fun moveHoodToPosition() {
        distance = 10.0 //This is the value of the distance you are going to use, usually you get it from your camera and odometry, it is being reassigned just for clarity in the example

        var targetPosition = interpolation.get(distance)

        /**
         * Multiply your target position by the tics and gear ratio
         * */
        targetPosition = targetPosition * HoodConstants.PhysicalLimits.gearRatio

        /**This function ensures your target position is within the min and max angle**/
        targetPosition = MathUtils.clamp(targetPosition, HoodConstants.PhysicalLimits.minAngle,
            HoodConstants.PhysicalLimits.maxAngle)

        hoodServo.set(targetPosition)



    }

    fun setServo(Angle: Double){
      hoodServo.set(Angle)
    }

    fun moveHoodDownPosition(){
        hoodServo.set(HoodConstants.PhysicalLimits.minAngle)

    }



}