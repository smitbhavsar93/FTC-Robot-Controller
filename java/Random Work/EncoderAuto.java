

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Encoder Auto")
public class EncoderAuto extends LinearOpMode {
    private DcMotor rightMotor;
    private  DcMotor leftMotor;
    @Override
    public void runOpMode(){
        rightMotor = hardwareMap.get(DcMotor.class,"right");
        leftMotor = hardwareMap.get(DcMotor.class,"left");
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //MotorControl driveTrain = new MotorControl();
        telemetry.addData("Status","Initialized");
        telemetry.update();
        waitForStart();
        //driveTrain.driveStraight(1000,0.9,rightMotor,leftMotor);
        driveStraight(1000,0.7);
        sleep(2000);//wait before exiting
    }

    public void driveStraight(int target,double power){
        //reset motor encoders to zero
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //set encoder target to 1000 ticks
        rightMotor.setTargetPosition(target);
        leftMotor.setTargetPosition(target);
        //setup the run mode so that the motor goes to target
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //set power to the motor
        //this sets the maximum power the motor will run at but is not constant
        rightMotor.setPower(power);
        leftMotor.setPower(power);
        while(leftMotor.isBusy()&&rightMotor.isBusy()){
            //wait until encoder reaches target ticks
        }
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }
}
