import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name = "Wobblegoals Gabey The Baby ", group = "Iterative Opmode")

public class CONAN_WOBBLEGOALS extends LinearOpMode {
    ElapsedTime runtime = new ElapsedTime();
    public DcMotor left;
    public DcMotor right;

    public void Turn(int DegreeSpin, double power) {
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        left.setTargetPosition(DegreeSpin);
        right.setTargetPosition(DegreeSpin);

        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        right.setPower(-power);
        left.setPower(power);

        while (right.isBusy() && left.isBusy()) {

        }
        left.setPower(0);
        right.setPower(0);

    }


    public void Drive(int DegreeSpin, double power) {
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        left.setTargetPosition(DegreeSpin);
        right.setTargetPosition(DegreeSpin);

        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        right.setPower(power);
        left.setPower(power);

        while (right.isBusy() && left.isBusy()) {

        }
        left.setPower(0);
        right.setPower(0);

    }


    @Override
    public void runOpMode() {
        left = hardwareMap.get(DcMotor.class, "l");
        right = hardwareMap.get(DcMotor.class, "r");


        right.setDirection(DcMotor.Direction.REVERSE);
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ElapsedTime runtime = new ElapsedTime();

        //MotorControl driveTrain = new MotorControl();
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        Turn(-45, 0.35);
        sleep(1000);
        Drive(2160, 0.35);
        sleep(1000);
        Turn(45, 0.35);
        sleep(1000);

    }
}
