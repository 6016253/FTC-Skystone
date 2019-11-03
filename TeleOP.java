package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name=" TeleOP ", group="Pushbot")

public class TeleOP extends LinearOpMode {

    /* Declare OpMode members. */

    Hardware robot           = new Hardware();  // Use a Pushbots hardware
    double          clawOffset      = 0;                      // Gripper mid position
    double          rotateOffSet    = 0;                      // Arm Rotation mid position
    final double    CLAW_SPEED      = 0.08;                   // sets rate to move servo
    final double    ROTATE_SPEED    = 0.04;                   // Same as above

    @Override
    public void runOpMode() {
        double left;
        double right;
        double drive;
        double turn;
        double max;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Drive stuff
            drive = -gamepad1.left_stick_y;
            turn  =  gamepad1.right_stick_x;

            // Combine drive and turn for blended motion.
            left  = drive + turn;
            right = drive - turn;

            // Normalize the values so neither exceed +/- 1.0
            max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0)
            {
                left /= max;
                right /= max;
            }

            // Output values to the drivetrain
            robot.leftDrive.setPower(left);
            robot.rightDrive.setPower(right);


            // Use gamepad left & right Bumpers to open and close the claw
            if (gamepad2.right_bumper)
                clawOffset += CLAW_SPEED;
            else if (gamepad2.left_bumper)
                clawOffset -= CLAW_SPEED;

            // Move Claw to new Position
            clawOffset = Range.clip(clawOffset, -0.5, 0.5);
            robot.Claw.setPosition(robot.MID_SERVO + clawOffset);


            // Use gamepad left & right (Whatever i decide on) to rotate the Claw

            if (-gamepad2.right_stick_y <=1 && -gamepad2.right_stick_y > 0)
                rotateOffSet += ROTATE_SPEED;
            else if (-gamepad2.right_stick_y >= -1 && -gamepad2.right_stick_y < 0)
                rotateOffSet -= ROTATE_SPEED;

            // Rotate the arm to a new position
            rotateOffSet = Range.clip(rotateOffSet, -0.5, 0.5);
            robot.Rotate.setPosition(robot.MID_SERVO + rotateOffSet);


            //Fuck this nobody's going to read my comments

            if (-gamepad2.left_stick_y <=1 && -gamepad2.left_stick_y > 0)
                robot.robotArm.setPower(robot.ARM_UP_POWER);
            else if (-gamepad2.left_stick_y >= -1 && -gamepad2.left_stick_y < 0)
                robot.robotArm.setPower(robot.ARM_DOWN_POWER);
                else
                    robot.robotArm.setPower(0.0);

            // Send telemetry message to signify robot running;
            telemetry.addData("claw",  "Offset = %.2f", clawOffset);
            telemetry.addData("claw rotation",  "Offset = %.2f", rotateOffSet);
            telemetry.addData("left drive",  "%.2f", left);
            telemetry.addData("right right", "%.2f", right);
            telemetry.update();

            // Pace this loop so jaw action is reasonable speed.
            sleep(50);
        }
    }
}
