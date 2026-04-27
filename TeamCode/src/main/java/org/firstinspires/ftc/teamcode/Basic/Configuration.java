// Universal Configuration!

package org.firstinspires.ftc.teamcode.Basic;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Configuration {

    // Reference to OpMode class (Can also use LinearOpMode)
    private final OpMode opMode;

    // Class Constructor
    public Configuration(OpMode opMode) {
        this.opMode = opMode;
    }

    // Configurable Values
    private final double
            servoActivePosition = 0.8,
            servoInactivePosition = 0.5,
            motorOnPower = 0.5,
            motorIdlePower = 0;
    // Note: Android Studio is highlighting these as they CAN BE local variables,
    //  but we keep them here so we can easily find and change them when needed.

    // Define Motor(s)
    public DcMotorEx motor;
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;
    // Note: DcMotorEx is the extended version of DcMotor, it allows the use velocity control when
    //  using encoders.

    // Define Servo(s)
    public Servo servo;

    /// Initialization Method
    public void init() {

        // HardwareMap Reference
        HardwareMap hwMap = opMode.hardwareMap;

        // Initialize Motors
        motor = hwMap.get(DcMotorEx.class, "motor");
        motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);

        fl = hwMap.get(DcMotor.class, "fl");
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fr = hwMap.get(DcMotor.class, "fr");
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        bl = hwMap.get(DcMotor.class, "bl");
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        br = hwMap.get(DcMotor.class, "br");
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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

    // TIP: You can hover over variables and classes to see their
    //  values or documentation. Try hovering over items like,
    //  RUN_WITHOUT_ENCODER, DcMotorEx, and motorOnPower.

}
