package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="Steven Test Tank Mode")
public class StevensRunMode extends LinearOpMode {

    HardwareRobob        robot   = new HardwareRobob();   // Use a Robob's hardware
    private ElapsedTime  runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        robot.latchArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.latchArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Robob Ready to run.",  "Latch Starting at %7d", robot.latchArm.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        double latchPower;
        while (opModeIsActive()) {
            robot.leftDrive.setPower(this.gamepad1.left_stick_y);
            robot.rightDrive.setPower(this.gamepad1.right_stick_y);


            // Handle the Latch Arm
            latchPower = 0.5;
            if (robot.latchArm.getCurrentPosition() > 250 && robot.latchArm.getCurrentPosition() < 8250) {
                latchPower = 1.0;
            }
            if (this.gamepad1.left_bumper) {
                robot.latchArm.setPower(latchPower);
            } else {
                if (this.gamepad1.right_bumper) {
                    robot.latchArm.setPower(-latchPower);
                } else {
                    robot.latchArm.setPower(0);
                }
            }
            telemetry.addData("Latch Arm",  "%7d", robot.latchArm.getCurrentPosition());
            telemetry.update();


        }
        telemetry.addData("Final Latch",  "Latch at %7d", robot.latchArm.getCurrentPosition());
        telemetry.update();

    }
}