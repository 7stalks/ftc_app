package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name=" Gerrit Drive Arcade", group="Exercises")
public class GerritRunMode extends LinearOpMode {
    DcMotor leftDrive, rightDrive, chainDrive;
    float   leftPower, rightPower, xValue, yValue, rxValue;

    // init button is  pressed.
    @Override
    public void runOpMode() throws InterruptedException
    {
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        chainDrive = hardwareMap.dcMotor.get("chainDrive");
        leftDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        while (opModeIsActive())
        {
            yValue = gamepad1.left_stick_y;
            xValue = gamepad1.left_stick_x;
            rxValue = gamepad1.right_stick_y;

            rightPower =  yValue - xValue;
            leftPower = yValue + xValue;

            leftDrive.setPower(Range.clip(leftPower, -1.0, 1.0));
            rightDrive.setPower(Range.clip(rightPower, -1.0, 1.0));

            chainDrive.setPower(rxValue);

            telemetry.addData("Mode", "running");
            telemetry.addData("stick", "  y=" + yValue + "  x=" + xValue);
            telemetry.addData("power", "  left=" + leftDrive + "  right=" + rightDrive);
            telemetry.update();

            idle();
        }
    }
}