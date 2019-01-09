package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="GerritAutonomous")
public class GerritAutonomous extends LinearOpMode {
    DcMotor leftDrive, rightDrive, chainDrive;
    Servo sArm;
    float leftPower, rightPower, xValue, yValue, ryValue, rxValue, rxStick, yToggle, aToggle;
    double maxPower = 0.5, minPower = 0.2, togglePower = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        chainDrive = hardwareMap.dcMotor.get("chainDrive");
        sArm = hardwareMap.servo.get("clawR");
        leftDrive.setDirection(DcMotor.Direction.REVERSE);

        sArm.setPosition(0.1);

        waitForStart();
        {
            chainDrive.setPower(.5);
            Thread.sleep(3500);

            chainDrive.setPower(0.0);
            Thread.sleep(1);

            sArm.setPosition(0.6);
            Thread.sleep(1000);

            chainDrive.setPower(-.5);
            Thread.sleep(1000);

            chainDrive.setPower(0.0);
            Thread.sleep(1);

            leftDrive.setPower(-.5);
            rightDrive.setPower(-.5);

            Thread.sleep(500);

            leftDrive.setPower(0);
            rightDrive.setPower(0);

            Thread.sleep(1);





            telemetry.update();

            idle();
        }

    }
}
