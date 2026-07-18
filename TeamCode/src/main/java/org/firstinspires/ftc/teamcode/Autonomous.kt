package org.firstinspires.ftc.teamcode

import com.bylazar.field.Style
import com.pedropathing.follower.Follower
import com.pedropathing.geometry.BezierLine
import com.pedropathing.geometry.Pose
import com.pedropathing.ivy.Command
import com.pedropathing.ivy.Scheduler
import com.pedropathing.ivy.Scheduler.schedule
import com.pedropathing.ivy.groups.Groups.sequential
import com.pedropathing.ivy.pedro.PedroCommands.follow
import com.pedropathing.paths.PathChain
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.pedroPathing.Constants
import org.firstinspires.ftc.teamcode.pedroPathing.Tuning


@Autonomous(name = "Test Auto", group = "Examples")
class ExampleAuto(): LinearOpMode() {
    // Initialize follower
    private lateinit var follower: Follower

    // Initialize poses
    private val startPose = Pose(72.000, 72.000, Math.toRadians(90.0))

    private val endPose = Pose(60.0, 105.0, Math.toRadians(90.0)) // Final Pose of our robot, off the starting line

    // Initialize paths

    private lateinit var path1: PathChain

    // Initialize commands
    private val command1 = Command.build()
        .setStart({
        // executed on start
        })
        .setExecute({
            // executed on execute
        })
        .setEnd({
            // executed on end
        })

    // Build paths
    private fun buildPaths() {
        path1 = follower.pathBuilder()
            .addPath(BezierLine(startPose, endPose))
            .setLinearHeadingInterpolation(startPose.heading, endPose.heading)
            .build()
    }

    // Define command auto routine
    private fun autoRoutine(): Command {
        return sequential(
            follow(follower, path1),
            //command1
        )
    }

    override fun runOpMode() {
        //These will run when the OpMode is initiated
        Scheduler.reset()
        follower = Constants.createFollower(hardwareMap)
        buildPaths()
        follower.setStartingPose(startPose)

        waitForStart()
        //We schedule all our commands when we start the OpMode
        schedule(autoRoutine())
        while (opModeIsActive()) {
            //Update the follower and execute the scheduler every loop
            follower.update()
            Scheduler.execute()

            // Draw robot in panels
            Tuning.drawCurrent(follower)

            // Feedback to Driver Hub for debugging
            telemetry.addData("x", follower.getPose().getX())
            telemetry.addData("y", follower.getPose().getY())
            telemetry.addData("heading", follower.getPose().getHeading())
            telemetry.update()
        }
    }
}