//package.org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "ARmTHiNgG", group = "Linear Opmode")

public class CONAN_SOMETHINGIMSUPPOSEDTO extends LinearOpMode {
    public DcMotor arm;

    public void NinetyDegress(int DegreeSpin, double power) {
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setTargetPosition(DegreeSpin);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        arm.setPower(power);

        while (arm.isBusy()) {

        }
        arm.setPower(0);
    }



    @Override
    public void runOpMode() {
        ElapsedTime runtime = new ElapsedTime();


      arm = hardwareMap.get(DcMotor.class, "arm");


        arm.setDirection(DcMotor.Direction.REVERSE);


        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //MotorControl driveTrain = new MotorControl();
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        NinetyDegress(90,0.35);
        sleep(2000);
        NinetyDegress(90,0.35);
    }
}