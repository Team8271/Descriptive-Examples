/* Universal Configuration!
 * This configuration file defines values that you can use
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Configuration {

    // Configurable Values
    private final double
            servoActivePosition = 0.8,
            servoInactivePosition = 0.5,
            motorOnPower = 0.5,
            motorIdlePower = 0;
    // Note: Android Studio is highlighting these as they CAN BE local variables,
    //  but we keep them here so we can easily find and change them when needed.

    // Reference to LinearOpMode class (Can also use OpMode)
    private final LinearOpMode linearOpMode;

    // Define Motor(s)
    public DcMotor motor;

    // Define Servo(s)
    public Servo servo;

    // Class Constructor
    public Configuration(LinearOpMode linearOpMode) {
        this.linearOpMode = linearOpMode;
    }

    /// Initialization Method
    public void init() {

        // HardwareMap Reference
        HardwareMap hwMap = linearOpMode.hardwareMap;

        // Initialize Motor
        motor = hwMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // Initialize Servo
        servo = hwMap.get(Servo.class, "servo");

    }

    /// Run Motor
    public void runMotor() {
        motor.setPower(motorOnPower);
    }

    /// Idle Motor
    public void idleMotor() {
        motor.setPower(motorIdlePower);
    }

    /// Servo Actuation Method
    public void activateServo() {
        servo.setPosition(servoActivePosition);
    }

    /// Servo Actuation Method
    public void deactivateServo() {
        servo.setPosition(servoInactivePosition);
    }

    // TIP: Use Shift+F6 to refactor a variable or method everywhere!
    //  Just highlight the method or variable name and press Shift+F6,
    //  now type what you want to rename it to, and it will change it
    //  everywhere!

}
