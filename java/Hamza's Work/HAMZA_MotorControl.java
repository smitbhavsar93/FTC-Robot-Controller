package Hamza;

import com.qualcomm.robotcore.hardware.DcMotor;

public class HAMZA_MotorControl {
    private int x = 0;
    int get_x(){
        return x;
    }
    public void driveStraight(int target,double power,DcMotor rightMotor,DcMotor leftMotor){
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