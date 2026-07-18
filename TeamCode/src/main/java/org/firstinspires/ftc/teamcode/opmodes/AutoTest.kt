package org.firstinspires.ftc.teamcode.opmodes

import com.pedropathing.follower.Follower
import com.pedropathing.geometry.BezierLine
import com.pedropathing.geometry.Pose
import com.pedropathing.paths.PathChain
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode


@Autonomous(name = "Example Auto", group = "Autos")
class AutoTest(): LinearOpMode() {
    private lateinit var follower: Follower
    private val startPoint: Pose = Pose(70.058, 70.040, Math.toRadians(90.0))
    private val endPoint: Pose = Pose(70.364, 104.763, Math.toRadians(90.0))

    private lateinit var path1: PathChain

    fun buildPaths() {
        path1 = follower.pathBuilder()
            .addPath(BezierLine(startPoint, endPoint))
            .setLinearHeadingInterpolation(startPoint.heading, endPoint.heading)
            .build()


    }

    override fun runOpMode() {

    }
}