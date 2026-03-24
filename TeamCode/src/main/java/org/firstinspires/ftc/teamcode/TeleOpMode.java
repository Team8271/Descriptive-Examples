package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class TeleOpMode extends OpMode {
    // Use Configuration class
    Configuration robot;

    @Override
    public void init() {
        robot = new Configuration(this);
        robot.init();
    }

    @Override
    public void loop() {
        driverControl();
        updateMultiStepProcess();

        if(gamepad1.a) {
            startMultiStepProcess();
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

    int multiStepProcessStep = 0;
    boolean multiStepProcessActive = false;
    ElapsedTime processTimer = new ElapsedTime();
    double waitStartTime = 0;
    int targetMotorVelocity = 1000;

    private void startMultiStepProcess() {
        if (!multiStepProcessActive) {
            multiStepProcessActive = true;
            multiStepProcessStep = 0;
            processTimer.reset();
            waitStartTime = processTimer.seconds();
        }
    }

    private void updateMultiStepProcess() {
        if(!multiStepProcessActive) {
            telemetry.addLine("Process: Inactive");
            return;
        }
        switch (multiStepProcessStep) {
            case (0):
                telemetry.addLine("Process: 1");
                // Example Timer Sequence
                if(processTimer.seconds() > waitStartTime + 5) { // Wait five seconds
                    // Step Two
                    multiStepProcessStep = 1;
                }
                break;
            case (1):
                telemetry.addLine("Process: 2");
                // Example Motor Wait Sequence
                if(robot.motor.getVelocity() >= targetMotorVelocity) {
                    // Step Three
                    multiStepProcessActive = false;
                }
                break;
        }
    }

}
