

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="ARM PID")
public class HAMZA_arm_control extends LinearOpMode {
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor arm;
    @Override
    public void runOpMode() throws RuntimeException{
        leftMotor = hardwareMap.get(DcMotor.class,"left");
        rightMotor = hardwareMap.get(DcMotor.class,"right");
        arm = hardwareMap.get(DcMotor.class,"elevator");
        waitForStart();
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int target= 300;
        double kp = 0.01;
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while(timer.milliseconds()<10000){
            int error = target - arm.getCurrentPosition();
            double power  = error*kp;
            arm.setPower(power);
        }

    }
}
