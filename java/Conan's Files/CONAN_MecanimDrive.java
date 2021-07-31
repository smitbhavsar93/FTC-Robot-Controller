import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Conan Mecanim Driver", group = "Linear Opmode")


public class CONAN_MecanimDrive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor leftfront = hardwareMap.dcMotor.get("lf");
        DcMotor leftrear = hardwareMap.dcMotor.get("lr");
        DcMotor rightfront = hardwareMap.dcMotor.get("rf");
        DcMotor rightrear = hardwareMap.dcMotor.get("rr");


        rightfront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightrear.setDirection(DcMotorSimple.Direction.REVERSE);


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



            leftfront.setPower(frontLeftPower);
            leftrear.setPower(backLeftPower);
            rightfront.setPower(frontRightPower);
            rightrear.setPower(backRightPower);
        }
    }
}
