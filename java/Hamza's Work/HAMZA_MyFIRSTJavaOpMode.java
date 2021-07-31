

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")

public class HAMZA_MyFIRSTJavaOpMode extends LinearOpMode {
    private DcMotor rightMotor;
    private  DcMotor leftMotor;
    private DcMotor arm;
    @Override
    public void runOpMode(){
        rightMotor = hardwareMap.get(DcMotor.class,"right");
        leftMotor = hardwareMap.get(DcMotor.class,"left");
        arm = hardwareMap.get(DcMotor.class,"elevator");
        telemetry.addData("Status","Initialized");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()){
            double left = this.gamepad1.left_stick_y;
            double right = -this.gamepad1.right_stick_y;
            leftMotor.setPower(left);
            rightMotor.setPower(right);
            if(this.gamepad1.a)
                arm.setPower(1);
            else if(this.gamepad1.b)
                arm.setPower(-1);
            else
                arm.setPower(0);
            telemetry.addData("Status","Running");
            telemetry.addData("Left Joystick",left);
            telemetry.addData("Right Joysticl",right);
            telemetry.update();

        }
    }

}
