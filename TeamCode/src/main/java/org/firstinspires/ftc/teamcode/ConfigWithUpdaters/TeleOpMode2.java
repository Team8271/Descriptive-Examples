package org.firstinspires.ftc.teamcode.ConfigWithUpdaters;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class TeleOpMode2 extends OpMode {
    // Use Configuration class
    Configuration2 robot;

    @Override
    public void init() {
        robot = new Configuration2(this);
        robot.init();
    }

    @Override
    public void loop() {
        driverControl();

        if(gamepad1.a) {
            robot.timedMotorBlips();
        }
        if(gamepad1.y) {
            robot.cancelMotorBlips();
        }
        // Continue processing on a time-based method
        if(robot.getMotorBlipsIsRunning()) {
            robot.timedMotorBlips();
        }

        if(gamepad1.b) {
            robot.activateServo();
        }

        telemetry.update();
    }

    private void driverControl() {
        double axialControl = -gamepad1.left_stick_y;  // Y axis
        double lateralControl = gamepad1.left_stick_x; // X axis
        double yawControl = gamepad1.right_stick_x;    // Z axis
        double throttle = .3 + (gamepad1.right_trigger * 0.7); // Throttle

        double leftFrontPower = (axialControl + lateralControl + yawControl)*throttle;
        double rightFrontPower = (axialControl - lateralControl - yawControl)*throttle;
        double leftBackPower = (axialControl - lateralControl + yawControl)*throttle;
        double rightBackPower = (axialControl + lateralControl - yawControl)*throttle;

        double max = Math.max(1.0,
                    Math.max(Math.abs(leftFrontPower),
                    Math.max(Math.abs(rightFrontPower),
                    Math.max(Math.abs(leftBackPower),
                    Math.abs(rightBackPower)))));

        robot.fl.setPower(leftFrontPower / max);
        robot.fr.setPower(rightFrontPower / max);
        robot.bl.setPower(leftBackPower / max);
        robot.br.setPower(rightBackPower / max);
    }

}
