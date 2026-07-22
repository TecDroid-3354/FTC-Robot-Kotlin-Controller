package org.firstinspires.ftc.teamcode.opmodes

import com.pedropathing.follower.Follower
import com.pedropathing.geometry.BezierLine
import com.pedropathing.geometry.Pose
import com.pedropathing.paths.PathChain
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

@Autonomous(name = "Example Auto", group = "Autos")
class AutoTest(): LinearOpMode() {
    private lateinit var  follower: Follower

    val startPose: Pose = Pose(56.0, 8.0,Math.toDegrees(90.0))
    val finalPose: Pose = Pose(56.0, 36.0,Math.toDegrees(90.0))

    private lateinit var path1: PathChain

    fun buildPaths() {
        path1 = follower.pathBuilder()
            .addPath(BezierLine(startPose, finalPose))
            .setLinearHeadingInterpolation (startPose.heading, finalPose.heading)
            .build()
    }

    override fun runOpMode() {
        TODO("Not yet implemented")
    }
}