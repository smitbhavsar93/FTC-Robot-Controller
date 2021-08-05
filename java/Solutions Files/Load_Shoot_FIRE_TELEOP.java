
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 *
 * Fully Controllable and does exactly what you want it to do W/O ENCODERS
 * Smit Bhavsar
 *
 * Move back and forth with LEFT STICK (y)
 * Strafe side to side with LEFT STICK (x)
 * Turn left and right with RIGHT STICK (x)
 *
 * Load in rings with LEFT TRIGGER (lowintake and roller)
 * Shoot out rings with RIGHT TRIGGER (hold LEFT TRIGGER too for more power)
 *
 * Remember the front is the YELLOW-INTAKE
 * Middle gray gear/wheel is the ROLLER
 * Top black spinny wheel is the FLYING-WHEEL
 */

@TeleOp(name="Smit's Firing TeleOp", group="Linear Opmode")

public class Load_Shoot_FIRE_TELEOP extends LinearOpMode {

    // declare all variables for motors
    public DcMotor leftfront;
    public DcMotor leftback;
    public DcMotor rightfront;
    public DcMotor rightback;

    //declare all variables that will shoot rings at opposition
    public DcMotor roller;
    public DcMotor flyingwheel;
    public DcMotor lowintake;

    //declare all variables for arm and hand to move claw
    public DcMotor arm;
    public Servo hand;

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

        //mapping all claw/hand/arm variables to its device counterpart
        arm = hardwareMap.get(DcMotor.class, "arm");
        hand = hardwareMap.get(Servo.class, "hand");

        //setting the directions of all driving motors
        leftfront.setDirection(DcMotor.Direction.REVERSE);
        leftback.setDirection(DcMotor.Direction.FORWARD);
        rightfront.setDirection(DcMotor.Direction.REVERSE);
        rightback.setDirection(DcMotor.Direction.FORWARD);

        //setting the directions of all loading/shooter motors
        flyingwheel.setDirection(DcMotor.Direction.REVERSE);
        roller.setDirection(DcMotor.Direction.REVERSE);
        lowintake.setDirection(DcMotor.Direction.FORWARD);

        //everything here is processed when "init" initialized
        waitForStart();
        //everything after is processed when "play" is hit

        //actual function calls to move/do cool things
        while (opModeIsActive()) {
            //drive and strafe with left analog, turn using right analog
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            double strafe = -gamepad1.left_stick_x;

            //load the rings using left trigger, aim and shoot using right trigger
            double loader = gamepad1.left_trigger;
            double shooter = gamepad1.right_trigger;
            

            //set values
            leftfront.setPower(drive + turn - strafe);
            rightfront.setPower(drive + turn + strafe);
            leftback.setPower(drive - turn + strafe);
            rightback.setPower(drive - turn- strafe);

            roller.setPower(loader);
            lowintake.setPower(loader);
            flyingwheel.setPower(shooter);

        }
    }
}
