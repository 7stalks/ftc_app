package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

// Created by Steve

@TeleOp(name="Steven Test Tank Mode")
public class StevensRunMode extends LinearOpMode {

    private Gyroscope imu;
    private DcMotor LeftMotor;
    private DcMotor RightMotor;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo ServoTest;

    @Override
    public void runOpMode() {
        imu = hardwareMap.get(Gyroscope.class, "imu");
        LeftMotor = hardwareMap.get(DcMotor.class, "leftDrive");
        RightMotor = hardwareMap.get(DcMotor.class, "rightDrive");
//        digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
//        sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
//        ServoTest = hardwareMap.get(Servo.class, "ServoTest");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        double tgtPowerL;
        double tgtPowerR;
        while (opModeIsActive()) {
            tgtPowerL = -this.gamepad1.left_stick_y;
            tgtPowerR = this.gamepad1.right_stick_y;
            LeftMotor.setPower(tgtPowerL);
            RightMotor.setPower(tgtPowerR);
            telemetry.addData("Left Target Power", tgtPowerL);
            telemetry.addData("Right Target Power", tgtPowerR);
            telemetry.addData("Left Motor Power", LeftMotor.getPower());
            telemetry.addData("Right Motor Power", RightMotor.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}