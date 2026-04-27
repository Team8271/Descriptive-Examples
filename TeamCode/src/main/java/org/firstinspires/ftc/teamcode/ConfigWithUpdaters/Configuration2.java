// Universal Configuration!

package org.firstinspires.ftc.teamcode.ConfigWithUpdaters;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * An expansion of Basic.Configuration that demonstrates effective time-driven methods.
 */
public class Configuration2 {

    // Reference to OpMode class (Can also use LinearOpMode)
    private final OpMode opMode;

    // Class Constructor
    public Configuration2(OpMode opMode) {
        this.opMode = opMode;
    }

    // Configurable Values
    private final double
            servoActivePosition = 0.8,
            servoInactivePosition = 0.5,
            motorOnPower = 0.5,
            motorIdlePower = 0;

    // Define Motor(s)
    public DcMotorEx motor;
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;

    // Define Servo(s)
    public Servo servo;

    // Define a runtime variable to allow time-driven methods (per method)
    private boolean motorBlipRunning = false;
    private double blipOffsetTime = 0;

    // Timer
    private ElapsedTime time = new ElapsedTime();

    /// Initialization Method
    public void init() {

        // HardwareMap Reference
        HardwareMap hwMap = opMode.hardwareMap;

        time = new ElapsedTime();

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

    /**
     * A method that should be called repeatedly in an opMode while getMotorBlipsIsRunning
     * returns true
     * <br><br>
     * Blips the motor in and out every second for 3 seconds
     */
    public void timedMotorBlips(){
        if(!motorBlipRunning) {
            blipOffsetTime = time.seconds(); // Reset the "loop"
            motorBlipRunning = true;
        }
        int loopTime = (int) (time.seconds()-blipOffsetTime);

        // If time is up or user canceled then stop
        if(loopTime>3 || !motorBlipRunning){
            motorBlipRunning = false;
            // Place a shutdown behavior here (if you call cancel and want it to end
            // a certain way).
            return;
        }
        if(loopTime%2==0) { // Even
            runMotor();
        }
        else { // Odd
            idleMotor();
        }
    }

    /**
     * Safely stops the timedMotorBlips method
     */
    public void cancelMotorBlips() {
        motorBlipRunning = false;
    }

    /**
     * Getter method that tells you if timedMotorBlips is looping
     * @return true if currently running, false if not running
     */
    public boolean getMotorBlipsIsRunning() {
        return motorBlipRunning;
    }

}
