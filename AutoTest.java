package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name=" AutoTest1 ", group="Pushbot")

public class AutoTest extends LinearOpMode {

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
        telemetry.addData("Say", "Aunt Booty");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until 30 seconds ends (driver presses STOP)

        // Turn right for 1.5 seconds

        //Starting wait
        sleep(1500);

        //Forward #1
        Hardware.Forward(500);

        Hardware.Rest();

        //Turn Right #1
        Hardware.turnRight(1350);

        Hardware.Rest();

        Hardware.Forward(375);

        Hardware.turnLeft(1350);

        Hardware.Forward(200);



    }
}
