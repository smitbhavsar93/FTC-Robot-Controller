

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Tiger")
public class HAMZA_TigerAuto extends LinearOpMode {
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    @Override
    public void runOpMode(){
        leftMotor = hardwareMap.get(DcMotor.class,"left");
        rightMotor = hardwareMap.get(DcMotor.class,"right");

        waitForStart();
        leftMotor.setPower(1);
        rightMotor.setPower(1);
        sleep(1000);
    }
}
