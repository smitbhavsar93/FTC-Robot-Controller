

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="sire", group="Linear Opmode")
public class more_teleop extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        DcMotor frontLeft;
        DcMotor frontRight;
        DcMotor backLeft;
        DcMotor backRight;
        DcMotor low;
        DcMotor mid;
        DcMotor fly;

        frontLeft = hardwareMap.get(DcMotor.class, "lf");
        frontRight = hardwareMap.get(DcMotor.class, "rf");
        backLeft = hardwareMap.get(DcMotor.class, "lr");
        backRight = hardwareMap.get(DcMotor.class, "rr");
        low = hardwareMap.get(DcMotor.class, "lowIntake");
        mid = hardwareMap.get(DcMotor.class, "roller");
        fly = hardwareMap.get(DcMotor.class, "flyingWheel");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        boolean fast = true;
        double modifier = 1;

        while (opModeIsActive()) {

            double fl;
            double fr;
            double bl;
            double br;

            double drive = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double turn  =  gamepad1.right_stick_x;

            double lt = gamepad1.left_trigger;
            double rt = gamepad1.right_trigger;

            if(gamepad1.a){
                fast = !fast;
            }

            if(fast){
                modifier = 1;
            } else {
                modifier = 0.333;
            }

            fl = Range.clip((-drive-strafe+turn), -0.7, 0.7);
            fr = Range.clip((-drive+strafe-turn), -0.7, 0.7);
            bl = Range.clip((-drive+strafe-turn), -0.7, 0.7);
            br = Range.clip((-drive-strafe+turn), -0.7, 0.7);


            frontLeft.setPower(modifier*fl);
            frontRight.setPower(modifier*fr);
            backLeft.setPower(modifier*bl);
            backRight.setPower(modifier*br);

            low.setPower(lt*(-10));
            mid.setPower(lt*10);
            fly.setPower(rt*10);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}