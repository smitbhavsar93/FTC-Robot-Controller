import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.util.concurrent.TimeUnit;


@TeleOp(name = "Gabe The Robot", group = "Linear Opmode")
public class CONAN_GABETHEROBOT extends LinearOpMode {

    ElapsedTime runtime = new ElapsedTime();
    DcMotor left = null;
    DcMotor right = null;
    TouchSensor touchytouchy = null;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");

        left = hardwareMap.get(DcMotor.class, "l");
        right = hardwareMap.get(DcMotor.class, "r");
        touchytouchy = hardwareMap.get(TouchSensor.class, "TOUCHYTOUCHY");


        left.setDirection(DcMotor.Direction.FORWARD);
        right.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");

        waitForStart();

        double leftPower;
        double rightPower;


        double drive = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;

        leftPower = Range.clip(drive - turn, -0.7, 0.7);
        rightPower = Range.clip(drive + turn, -0.7, 0.7);

        left.setPower(leftPower);
        right.setPower(rightPower);

        if(touchytouchy.isPressed()){
            left.setPower(-0.5);
            right.setPower(-0.5);
            sleep(2000);
            left.setPower(0);
            right.setPower(0);
        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

}

