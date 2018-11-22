package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name=" Gerrit Drive Arcade", group="Exercises")
public class GerritRunMode extends LinearOpMode {
    DcMotor leftDrive, rightDrive, chainDrive;
    Servo clawL, clawR;
    float   leftPower, rightPower, xValue, yValue, ryValue, rxValue, rxStick, yToggle, aToggle;
    double maxPower=0.5, minPower=0.2, togglePower=0.5;

    // init button is  pressed.
    @Override
    public void runOpMode() throws InterruptedException
    {
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        chainDrive = hardwareMap.dcMotor.get("chainDrive");
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        clawL = hardwareMap.servo.get("clawL");
        clawR = hardwareMap.servo.get("clawR");
        clawR.setDirection(Servo.Direction.REVERSE);

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
            rxValue = gamepad1. right_stick_x;

            rightPower = yValue - xValue;
            leftPower = yValue + xValue;
            if (gamepad1.y){
                if (togglePower == minPower){
                    togglePower = maxPower;
                } else {
                    togglePower = minPower;
                }
            }
            if (gamepad1.a){
                if (togglePower == maxPower){
                    togglePower = minPower;
                } else {
                    togglePower = maxPower;
                }
            }
            leftDrive.setPower(Range.clip(leftPower, -togglePower, togglePower));
            rightDrive.setPower(Range.clip(rightPower, -togglePower, togglePower));


           chainDrive.setPower(ryValue);

            if (rxValue <= .5) {
                clawR.setPosition(1);
                clawL.setPosition(1);
            } else if (rxValue > .5) {
                clawR.setPosition(.3);
                clawL.setPosition(.3);
            }







            telemetry.addData("Mode", "running");
            telemetry.addData("stick", "  y=" + yValue + "  x=" + xValue);
            telemetry.addData("power", "  left=" + leftDrive + "  right=" + rightDrive);
            telemetry.addData( "start position",intPosition);
            telemetry.addData( "rxValue",rxValue);
            telemetry.update();

            idle();
        }
    }
}