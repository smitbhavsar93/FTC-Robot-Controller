import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
@Disabled
public class MecanimDrive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor leftFront = hardwareMap.dcMotor.get("lf");
        DcMotor leftRear = hardwareMap.dcMotor.get("lr");
        DcMotor rightFront = hardwareMap.dcMotor.get("rf");
        DcMotor rightRear = hardwareMap.dcMotor.get("rr");


        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);


        if (isStopRequested()) return;
        
        while (opModeIsActive()) {

            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            double frontLeftPower = y + x + rx;
            double backLeftPower = y - x + rx;
            double frontRightPower = y - x - rx;
            double backRightPower = y + x - rx;

            if(Math.abs(frontLeftPower) > 1) // set max power
                frontLeftPower = 1;
            if(Math.abs(frontRightPower) > 1)
                frontRightPower = 1;
            if(Math.abs(backRightPower)>1)
                backRightPower = 1;
            if(Math.abs(backLeftPower)>1)
                frontRightPower = 1;

            leftFront.setPower(frontLeftPower);
            leftRear.setPower(backLeftPower);
            rightFront.setPower(frontRightPower);
            rightRear.setPower(backRightPower);
        }
    }
}
