
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Fully Autonomous and does exactly what you want it to do W/O ENCODERS
 * Smit Bhavsar
 */

@Autonomous(name="Smit's Firing Auto", group="Linear Opmode")
public class Load_Shoot_FIRE_AUTONOMOUS extends LinearOpMode {

    // declare all variables for motors
    public DcMotor leftfront;
    public DcMotor leftback;
    public DcMotor rightfront;
    public DcMotor rightback;

    //declare all variables that will shoot rings at opposition
    public DcMotor roller;
    public DcMotor flyingwheel;
    public DcMotor lowintake;

    //stops all driving motors
    public void PowerSetStop(){
        leftfront.setPower(0);
        rightfront.setPower(0);
        leftback.setPower(0);
        rightback.setPower(0);
    }

    //drives all motors straight at certain % speed for certain amt of time
    public void DriveStraight(double speed, int duration){
        leftfront.setPower(speed);
        leftback.setPower(speed);
        rightfront.setPower(speed);
        rightback.setPower(speed);

        sleep(duration);
        PowerSetStop();

    }

    //turns all motors to left/right at certain % speed for certain amt of time
    public void turn (double speed, int duration){
        leftfront.setPower(-speed);
        leftback.setPower(-speed);
        rightfront.setPower(speed);
        rightback.setPower(speed);

        sleep(duration);
        PowerSetStop();
    }

    //strafes all motors to left/right at certain % speed for certain amt of time
    public void strafe (double speed, int duration){
        leftfront.setPower(-speed);
        leftback.setPower(speed);
        rightfront.setPower(speed);
        rightback.setPower(-speed);

        sleep(duration);
        PowerSetStop();
    }

    //sucks in rings from the bottom intake
    public void intake (int duration){
        lowintake.setPower(1);
        roller.setPower(-1);
        sleep(duration);
        lowintake.setPower(0);
        roller.setPower(0);
    }

    //fires rings out of flyingwheel into targets
    public void shoot(int power,int duration){
        flyingwheel.setPower(power);
        roller.setPower(-1);
        sleep(duration);
        roller.setPower(0);
        flyingwheel.setPower(0);
    }

    //aka the "Main" method in java
    @Override
    public void runOpMode() {

        //mapping all motor variables to its device counterpart
        leftfront  = hardwareMap.get(DcMotor.class, "lf");
        leftback = hardwareMap.get(DcMotor.class, "rf");
        rightfront  = hardwareMap.get(DcMotor.class, "lr");
        rightback = hardwareMap.get(DcMotor.class, "rr");

        //mapping all shooter variables to its device counterpart
        roller  = hardwareMap.get(DcMotor.class, "roller");
        flyingwheel  = hardwareMap.get(DcMotor.class, "flyingWheel");
        lowintake  = hardwareMap.get(DcMotor.class, "lowIntake");

        //setting the directions of certain digital devices
        leftfront.setDirection(DcMotor.Direction.REVERSE);
        leftback.setDirection(DcMotor.Direction.FORWARD);
        rightfront.setDirection(DcMotor.Direction.REVERSE);
        rightback.setDirection(DcMotor.Direction.FORWARD);
        flyingwheel.setDirection(DcMotor.Direction.REVERSE);

        //everything here is processed when "init" initialized
        waitForStart();
        //everything after is processed when "play" is hit

        //actual function calls to move/do cool things
        DriveStraight(0.6,700);
        intake(2000);
        shoot(1000, 1200);
        DriveStraight(0.6,700);
        intake(2000);
        shoot(700, 1200);
        DriveStraight(0.6, 700);
        intake(2000);
        shoot(500, 1200);
    }
}