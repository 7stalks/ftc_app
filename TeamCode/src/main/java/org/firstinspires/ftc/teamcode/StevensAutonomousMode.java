
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="Steven De-LatchAutonomous", group="Pushbot")
public class StevensAutonomousMode extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareRobob        robot   = new HardwareRobob();   // Use a Robob's hardware
    private ElapsedTime  runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);


    static final double     FORWARD_SPEED = 0.6;
    static final double     LATCH_SPEED    = 0.5;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Robob Ready to run");    //
        telemetry.update();

        robot.latchArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.latchArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Robob Ready to run.",  "Latch Starting at %7d", robot.latchArm.getCurrentPosition());
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        // Step 1:  Drop Latch Arm
        latchMove(LATCH_SPEED,  48,  5.0);  // S1: Forward 47 Inches with 5 Sec timeout



        robot.latchArm.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.2)) {
            telemetry.addData("Arm", "Arm: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }


//        robot.leftDrive.setPower(FORWARD_SPEED);
//        robot.rightDrive.setPower(FORWARD_SPEED);
//        runtime.reset();
//        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
//            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
//            telemetry.update();
//        }
//
//        // Step 2:  Spin right for 1.3 seconds
//        robot.leftDrive.setPower(TURN_SPEED);
//        robot.rightDrive.setPower(-TURN_SPEED);
//        runtime.reset();
//        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
//            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
//            telemetry.update();
//        }
//
//        // Step 3:  Drive Backwards for 1 Second
//        robot.leftDrive.setPower(-FORWARD_SPEED);
//        robot.rightDrive.setPower(-FORWARD_SPEED);
//        runtime.reset();
//        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
//            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
//            telemetry.update();
//        }



        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }


    public void latchMove(double speed, double degrees, double timeoutSecs) {

        if (opModeIsActive()) {

        }
    }
}
