package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name=" Gerrit Drive Arcade", group="Exercises")
public class GerritRunMode extends LinearOpMode {
    DcMotor leftDrive, rightDrive, chainDrive;
    Servo sArm;
    float   leftPower, rightPower, xValue, yValue, ryValue, rxValue, rxStick, yToggle, aToggle;
    double maxPower=0.5, minPower=0.2, togglePower=0.5;

    // init button is  pressed.
    @Override
    public void runOpMode() throws InterruptedException {
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        chainDrive = hardwareMap.dcMotor.get("chainDrive");
        sArm = hardwareMap.servo.get("clawR");
        leftDrive.setDirection(DcMotor.Direction.REVERSE);

//        chainDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button. && and || or

        waitForStart();

        int intPosition, Position;
        intPosition = chainDrive.getCurrentPosition();

        while (opModeIsActive()) {
            yValue = gamepad1.left_stick_y;
            xValue = gamepad1.left_stick_x;
            ryValue = gamepad1.right_stick_y;
            rxValue = gamepad1.right_stick_x;

            rightPower = yValue - xValue;
            leftPower = yValue + xValue;
            if (gamepad1.y) {
                if (togglePower == minPower) {
                    togglePower = maxPower;
                } else {
                    togglePower = minPower;
                }
            }
            if (gamepad1.a) {
                if (togglePower == maxPower) {
                    togglePower = minPower;
                } else {
                    togglePower = maxPower;
                }
            }


            if (gamepad1.x) {
                sArm.setPosition(0);
            }
            if (gamepad1.b) {
                sArm.setPosition(0.6);
            }

            leftDrive.setPower(Range.clip(leftPower, -togglePower, togglePower));
            rightDrive.setPower(Range.clip(rightPower, -togglePower, togglePower));


            chainDrive.setPower(ryValue);


            telemetry.addData("Mode", "running");
            telemetry.addData("stick", "  y=" + yValue + "  x=" + xValue);
            telemetry.addData("power", "  left=" + leftDrive + "  right=" + rightDrive);
            telemetry.addData("start position", intPosition);
            telemetry.addData("rxValue", rxValue);
            telemetry.update();

            idle();
        }

    }
}