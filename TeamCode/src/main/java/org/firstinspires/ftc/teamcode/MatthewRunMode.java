package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name="Matthew is great at FortNite")
public class MatthewRunMode extends LinearOpMode {
    private Gyroscope imu;
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor chainDrive;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;

    @Override
    public void runOpMode() {
        imu = hardwareMap.get(Gyroscope.class, "imu");
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        chainDrive = hardwareMap. get(DcMotor.class, "chaindrive");
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        //servoTest = hardwareMap.get(Servo.class, "servoTest");
        telemetry.addData("Status", "Initialized"); telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        double tgtPowerY;
        double tgtPowerX;
        double tgtchain;
        while (opModeIsActive()) {
            tgtPowerY = -this.gamepad1.left_stick_y;
            tgtPowerX = this.gamepad1.left_stick_x;
            leftDrive.setPower(tgtPowerY);
            rightDrive.setPower(tgtPowerX);
            tgtchain = this.gamepad1.right_stick_y;
//            if(gamepad1.y) {
//                // move to 0 degrees.
//                servoTest.setPosition(0);
//            } else if (gamepad1.x || gamepad1.b) {
//                // move to 90 degrees.
//                servoTest.setPosition(0.5);
//            } else if (gamepad1.a) {
//                // move to 180 degrees.
//                servoTest.setPosition(1);
//            }
            telemetry.addData("Target PowerY", tgtPowerY);
            telemetry.addData("Motor PowerY", leftDrive.getPower());
            telemetry.addData("Target PowerX", tgtPowerX);
            telemetry.addData("Motor PowerX", rightDrive.getPower());
            telemetry.addData( "Target PowerY, tgtChain", chainDrive.getPower());
            telemetry.addData( "Motor PowerY", chainDrive.getPower());
//            telemetry.addData("Distance (cm)", sensorColorRange.getDistance(DistanceUnit.CM));
            telemetry.update();


        }
    }
}
