package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class GerritRunMode extends LinearOpMode {
    private Gyroscope imu;
    private DcMotor motorTest;
    private DcMotor motorTest2;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;

    @Override
    public void runOpMode() {
        imu = hardwareMap.get(Gyroscope.class, "imu");
        motorTest = hardwareMap.get(DcMotor.class, "motorTest");
        motorTest2 = hardwareMap.get(DcMotor.class, "motorTest2");
        digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        servoTest = hardwareMap.get(Servo.class, "servoTest");
        telemetry.addData("servo", "Initialized"); telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        double tgtPower = 0;
        double tgtPower2 = 0;
        while (opModeIsActive()) {
            tgtPower = this.gamepad1.left_stick_y;
            tgtPower2 = -this.gamepad1.left_stick_x;

            if (tgtPower!=0 || tgtPower2!=0) {
                if (tgtPower>0) {
                    telemetry.addData("tgtPower>0", tgtPower);
                    motorTest.setPower(-((tgtPower+tgtPower2)/2));
                    motorTest2.setPower(((tgtPower+tgtPower2)/2));
                }
                if (tgtPower<0) {
                    telemetry.addData("tgtPower<0", tgtPower);
                    motorTest.setPower(-((tgtPower+tgtPower)/2));
                    motorTest2.setPower(((tgtPower+tgtPower2)/2));
                }
                if (tgtPower2>0) {
                    telemetry.addData("tgtPower2>0", tgtPower2);
                    motorTest.setPower((tgtPower+tgtPower2)/2);
                    motorTest2.setPower((tgtPower+tgtPower2)/2);
                }
                if (tgtPower2<0) {
                    telemetry.addData("tgtPower2<0", tgtPower2);
                    motorTest.setPower((tgtPower+tgtPower2)/2);
                    motorTest2.setPower((tgtPower+tgtPower2)/2);
                }
                telemetry.update();
            } else {
                telemetry.addData("tgtPower=0", tgtPower);
                telemetry.addData("tgtPower2=0", tgtPower2);
                motorTest.setPower(0);
                motorTest2.setPower(0);
            }

            if (gamepad1.y) {
                servoTest.setPosition(0);
            } else if (gamepad1.x || gamepad1.b) {
                servoTest.setPosition(0.5);
            } else if (gamepad1.a) {
                servoTest.setPosition(1);
            }
            telemetry.addData("tgtPower>0", tgtPower);
            telemetry.addData("tgtPower<0", tgtPower);
            telemetry.addData("tgtPower2>0", tgtPower2);
            telemetry.addData("tgtPower2<0", tgtPower2);
            telemetry.addData("tgtPower=0", tgtPower);
            telemetry.addData("tgtPower2=0", tgtPower2);
            telemetry.addData("Servo Position", servoTest.getPosition());
            telemetry.addData("Distance (cm)", sensorColorRange.getDistance (DistanceUnit.CM));
            telemetry.addData("status", "Running");
            telemetry.update();
        }
    }
}