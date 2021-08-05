
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Fully Autonomous FTC driving / shooting program WITH ENCODERS
 */

@Autonomous(name = "Smit's Encoder", group = "Linear Opmode")

public class Encoder extends LinearOpMode {

    // declare all variables necessary for all the DcMotors
    public DcMotor leftfront;
    public DcMotor leftrear;
    public DcMotor rightfront;
    public DcMotor rightrear;
    public DcMotor flyingWheel;
    public DcMotor lowIntake;
    public DcMotor roller;

    // method to drive the robot in a straight line for a certain # of rotations
    public void DriveStraight(int Rotations, double power, int duration) {

        // stop and reset resets the encoder count to 0 in case of loops
        rightfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightrear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftrear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set how many rotations the encoders need to count up to
        rightfront.setTargetPosition(Rotations);
        rightrear.setTargetPosition(Rotations);
        leftfront.setTargetPosition(Rotations);
        leftrear.setTargetPosition(Rotations);

        // tells it to run to actually count set / rotate up until the set amount of rotations
        rightfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightrear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftrear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // set the power (negative because I want the front to be the yellow intake)
        rightfront.setPower(-power);
        rightrear.setPower(-power);
        leftfront.setPower(-power);
        leftrear.setPower(-power);

        // pause for a certain duration
        sleep(duration);

        // stops all motors
        PowerSetStop();
    }

    // method to turn the robot for a certain # of rotations
    public void Turning(int Rotations, double power, int duration) {

        // stop and reset resets the encoder count to 0 in case of loops
        rightfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightrear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftrear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set how many rotations the encoders need to count upto
        rightfront.setTargetPosition(Rotations);
        rightrear.setTargetPosition(Rotations);
        leftfront.setTargetPosition(-Rotations);
        leftrear.setTargetPosition(-Rotations);

        // tells the robot to actually count / rotate up until the set amount of rotations
        rightfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightrear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftrear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // set the power (negative because you wanna turn across 2 motors)
        rightfront.setPower(power);
        rightrear.setPower(power);
        leftfront.setPower(-power);
        leftrear.setPower(-power);

        // pause for a certain duration
        sleep(duration);

        // stops all motors
        PowerSetStop();
    }

    // method to strafe the robot for a certain # of rotations
    public void Strafing(int Rotations, double power, int duration) {

        // stop and reset resets the encoder count to 0 in case of loops
        rightfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightrear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftrear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set how many rotations the encoders need to count upto
        rightfront.setTargetPosition(-Rotations);
        rightrear.setTargetPosition(Rotations);
        leftfront.setTargetPosition(Rotations);
        leftrear.setTargetPosition(-Rotations);

        // tells the robot to actually count / rotate up until the set amount of rotations
        rightfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightrear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftrear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // set the power (negative because you wanna strafe across 2 motors)
        rightfront.setPower(-power);
        rightrear.setPower(power);
        leftfront.setPower(power);
        leftrear.setPower(-power);

        // pause for a certain duration
        sleep(duration);

        // stops all motors
        PowerSetStop();
    }

    // method to intake the rings in the robot for a certain # of rotations
    public void Intaking(int Rotations, double power, int duration) {

        // stop and reset resets the encoder count to 0 in case of loops
        lowIntake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        roller.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set how many rotations the encoders need to count upto
        lowIntake.setTargetPosition(Rotations);
        roller.setTargetPosition(Rotations);

        // tells the robot to actually count / rotate up until the set amount of rotations
        lowIntake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        roller.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // set the power of the roller / intake
        lowIntake.setPower(power);
        roller.setPower(power);

        // pause for a certain duration
        sleep(duration);

        // stops all motors
        lowIntake.setPower(0);
        roller.setPower(0);
    }

    // method to shoot the rings out the robot for a certain # of rotations
    public void Shooting(int Rotations, int fyRotations, double power, double fyPower, int duration) {

        // stop and reset resets the encoder count to 0 in case of loops
        flyingWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        roller.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set how many rotations the encoders need to count upto
        flyingWheel.setTargetPosition(fyRotations);
        roller.setTargetPosition(Rotations);

        // tells the robot to actually count / rotate up until the set amount of rotations
        flyingWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        roller.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // set the power of the roller / flying wheel
        flyingWheel.setPower(fyPower);
        roller.setPower(power);

        // pause for a certain duration
        sleep(duration);

        // stops all motors
        flyingWheel.setPower(0);
        roller.setPower(0);
    }

    //stops all driving motors
    public void PowerSetStop(){
        leftfront.setPower(0);
        rightfront.setPower(0);
        leftrear.setPower(0);
        rightrear.setPower(0);
    }

    // method where we put all the driving / shooting functions inside of
    public void Main(){
        DriveStraight(5000, 0.35, 1000);
        sleep(1000);
        Turning(5000, 0.35, 1000);
        sleep(1000);
        Strafing(5000, 0.35,1000);
        sleep(1000);
        Intaking(2000, 0.4, 1000);
        sleep(1000);
        Shooting(2000, 5000, 0.4, 1, 2500);
    }

    @Override
    public void runOpMode() {
        ElapsedTime runtime = new ElapsedTime();

        // hardware map each motor to its respective counterpart
        leftfront = hardwareMap.get(DcMotor.class, "lf");
        leftrear = hardwareMap.get(DcMotor.class, "lr");
        rightfront = hardwareMap.get(DcMotor.class, "rf");
        rightrear = hardwareMap.get(DcMotor.class, "rr");
        flyingWheel = hardwareMap.get(DcMotor.class, "flyingWheel");
        lowIntake = hardwareMap.get(DcMotor.class, "lowIntake");
        roller = hardwareMap.get(DcMotor.class, "roller");

        // reverse the left motors to set the front as the yellow intake
        leftfront.setDirection(DcMotor.Direction.REVERSE);
        leftrear.setDirection(DcMotor.Direction.REVERSE);

        // reverse the flying wheel and roller because default is backwards
        flyingWheel.setDirection(DcMotor.Direction.REVERSE);
        roller.setDirection(DcMotor.Direction.REVERSE);

        // apply brakes to smoothly stop any processes
        rightfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightrear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftrear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // everything here is processed when "init" initialized
        waitForStart();
        //everything after is processed when "play" is hit

        // call the "Main" method to run all directed functions
        Main();
    }
}
