package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static android.os.SystemClock.sleep;

public class Hardware
{
    /* Public OpMode members. */
    public static DcMotor  leftDrive   = null;
    public static DcMotor  rightDrive  = null;
    public DcMotor  robotArm     = null;

    public Servo    Claw    = null;
    public Servo    Rotate   = null;

    public static final double MID_SERVO       =  0.5 ;
    public static final double ARM_UP_POWER    =  0.40 ;
    public static final double ARM_DOWN_POWER  = -0.40 ;


    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public Hardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftDrive  = hwMap.get(DcMotor.class, "left_drive");
        rightDrive = hwMap.get(DcMotor.class, "right_drive");
        robotArm    = hwMap.get(DcMotor.class, "robot_arm");
        leftDrive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightDrive.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        robotArm.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robotArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        Claw  = hwMap.get(Servo.class, "claw");
        Rotate = hwMap.get(Servo.class, "claw_rotate");
        Claw.setPosition(MID_SERVO);
        Rotate.setPosition(MID_SERVO);



    }
    public static void Forward(int forwardTime) {
        leftDrive.setPower(1);
        rightDrive.setPower(1);
        sleep(forwardTime);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public static void Backwards(int backTime) {
        leftDrive.setPower(-1);
        rightDrive.setPower(-1);
        sleep(backTime);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public static void turnLeft(int leftTime) {
        leftDrive.setPower(-1);
        rightDrive.setPower(1);
        sleep(leftTime);
        leftDrive.setPower(0);
        rightDrive.setPower(0);

    }

    public static void Rest() {

        sleep(500);
    }


    public static void turnRight(int rightTime) {
        leftDrive.setPower(1);
        rightDrive.setPower(-1);
        sleep(rightTime);
        leftDrive.setPower(0);
        rightDrive.setPower(0);

    }
}

