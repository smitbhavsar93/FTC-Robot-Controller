

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;


@Autonomous(name = "Button Auto")
public class HAMZA_ButtonAuton extends LinearOpMode{
    private DcMotor rightMotor;
    private  DcMotor leftMotor;
    private TouchSensor button;

    @Override
    public void runOpMode(){
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor = hardwareMap.get(DcMotor.class,"right");
        leftMotor = hardwareMap.get(DcMotor.class,"left");
        button= hardwareMap.get(TouchSensor.class,"button");

        telemetry.addData("Status","Initialized");
        telemetry.update();
        waitForStart();

        leftMotor.setPower(1);
        rightMotor.setPower(1);
        //wait until button is pressed then stop

        while(!button.isPressed()){
            idle();
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}
