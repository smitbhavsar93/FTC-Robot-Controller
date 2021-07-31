

import com.qualcomm.robotcore.eventloop.EventLoop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "Simple Auto")
public class HAMZA_SimpleAuton extends LinearOpMode{
    private DcMotor rightMotor;
    private  DcMotor leftMotor;
    @Override
    public void runOpMode(){
        rightMotor = hardwareMap.get(DcMotor.class,"right");
        leftMotor = hardwareMap.get(DcMotor.class,"left");
        ElapsedTime myTimer = new ElapsedTime();//create a timer object
        telemetry.addData("Status","Initialized");
        telemetry.update();
        waitForStart();
        moveTime(0.5,-0.5,2,myTimer);


    }
    public void moveTime(double l, double r, int time, ElapsedTime myTimer){
        int targetTime = time*1000;//convert to milliseconds
        myTimer.reset();
        leftMotor.setPower(l);
        rightMotor.setPower(r);
        while(myTimer.milliseconds()<targetTime){
            //dont do anything
        }
        stopDrive();

    }
    public void stopDrive(){
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}
