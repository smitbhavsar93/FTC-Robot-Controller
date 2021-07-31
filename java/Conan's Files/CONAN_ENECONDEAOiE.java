
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "Encodas", group = "Linear Opmode")
@Disabled
public class CONAN_ENECONDEAOiE extends LinearOpMode {
    public DcMotor leftfront;
    public DcMotor leftrear;
    public DcMotor rightfront;
    public DcMotor rightrear;
    public DcMotor Thrower;
    public DcMotor Eater1;
    public DcMotor Eater2;

    public void STRAF(int DegreeSpin, double power) {
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
        rightrear.setPower(power);
        leftfront.setPower(power);
        leftrear.setPower(-power);

        while (rightfront.isBusy() && rightrear.isBusy() && leftfront.isBusy() && leftrear.isBusy()) {

        }
        rightfront.setPower(0);
        rightrear.setPower(0);
        leftfront.setPower(0);
        leftrear.setPower(0);

    }

    public void DriveStraight(int DegreeSpin, double power) {
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

        while (rightfront.isBusy() && rightrear.isBusy() && leftfront.isBusy() && leftrear.isBusy()) {

        }
        rightfront.setPower(0);
        rightrear.setPower(0);
        leftfront.setPower(0);
        leftrear.setPower(0);

    }

    @Override
    public void runOpMode() {
        ElapsedTime runtime = new ElapsedTime();


        leftfront = hardwareMap.get(DcMotor.class, "lf");
        leftrear = hardwareMap.get(DcMotor.class, "lr");
        rightfront = hardwareMap.get(DcMotor.class, "rf");
        rightrear = hardwareMap.get(DcMotor.class, "rr");
        Thrower = hardwareMap.get(DcMotor.class, "flyingWheel");
        Eater1 = hardwareMap.get(DcMotor.class, "lowIntake");
        Eater2 = hardwareMap.get(DcMotor.class, "roller");

        leftfront.setDirection(DcMotor.Direction.REVERSE);
        leftrear.setDirection(DcMotor.Direction.REVERSE);
        Thrower.setDirection(DcMotor.Direction.REVERSE);
        Eater2.setDirection(DcMotor.Direction.REVERSE);

        rightfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightrear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftrear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //MotorControl driveTrain = new MotorControl();
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

    //driveTrain.DriveStraight(1080, 0.35 , rightfront, rightrear, leftfront, leftrear);
    sleep(2000);
    //driveTrain.STRAF(1080, 0.35, rightfront, rightrear, leftfront, leftrear);

    }
}