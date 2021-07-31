import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "ENCODER", group = "Linear Opmode")
public class Encoders extends LinearOpMode{

    public DcMotor leftfront;
    public DcMotor leftrear;
    public DcMotor rightfront;
    public DcMotor rightrear;
    public DcMotor lowintake;
    public DcMotor roller;
    public DcMotor flyingwheel;

    public void DriveStraight(int DegreeSpin, double power, int duration) {
        rightfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightrear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftrear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightfront.setTargetPosition(DegreeSpin);
        rightrear.setTargetPosition(DegreeSpin);
        leftfront.setTargetPosition(DegreeSpin);
        leftrear.setTargetPosition(DegreeSpin);

        rightfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightrear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftrear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightfront.setPower(-power);
        rightrear.setPower(-power);
        leftfront.setPower(-power);
        leftrear.setPower(-power);

        sleep(duration);

        rightfront.setPower(0);
        rightrear.setPower(0);
        leftfront.setPower(0);
        leftrear.setPower(0);
    }

    public void Strafe(int DegreeSpin, double power, int duration) {
        rightfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightrear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftrear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightfront.setTargetPosition(DegreeSpin);
        rightrear.setTargetPosition(-DegreeSpin);
        leftfront.setTargetPosition(-DegreeSpin);
        leftrear.setTargetPosition(DegreeSpin);

        rightfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightrear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftrear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightfront.setPower(-power);
        rightrear.setPower(power);
        leftfront.setPower(power);
        leftrear.setPower(-power);

        sleep(duration);

        rightfront.setPower(0);
        rightrear.setPower(0);
        leftfront.setPower(0);
        leftrear.setPower(0);

    }

    @Override
    public void runOpMode() {

        leftfront = hardwareMap.get(DcMotor.class, "lf");
        leftrear = hardwareMap.get(DcMotor.class, "lr");
        rightfront = hardwareMap.get(DcMotor.class, "rf");
        rightrear = hardwareMap.get(DcMotor.class, "rr");
        flyingwheel = hardwareMap.get(DcMotor.class, "flyingWheel");
        lowintake = hardwareMap.get(DcMotor.class, "lowIntake");
        roller = hardwareMap.get(DcMotor.class, "roller");

        leftfront.setDirection(DcMotor.Direction.REVERSE);
        leftrear.setDirection(DcMotor.Direction.REVERSE);
        flyingwheel.setDirection(DcMotor.Direction.REVERSE);
        roller.setDirection(DcMotor.Direction.REVERSE);

        rightfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightrear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftrear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        DriveStraight(1080, 0.35, 2500);
        Strafe(1080, 0.35, 1250);

    }
}
