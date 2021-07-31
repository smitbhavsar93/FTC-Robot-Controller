import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="stacys a witch", group="Linear Opmode")
public class STACY_gun extends LinearOpMode {

    public DcMotor leftfront;
    public DcMotor leftback;
    public DcMotor rightfront;
    public DcMotor rightback;
    public DcMotor roller;
    public DcMotor flyingwheel;
    public DcMotor lowintake;

    public void DriveStraight(double speed, int time){

        leftfront.setPower(speed);
        leftback.setPower(speed);
        rightfront.setPower(speed);
        rightback.setPower(speed);

        sleep(time);
        leftfront.setPower(0);
        leftback.setPower(0);
        rightfront.setPower(0);
        rightback.setPower(0);

    }

    public void strafe (double speed, int time){
        leftfront.setPower(-speed);
        leftback.setPower(+speed);
        rightfront.setPower(+speed);
        rightback.setPower(-speed);

        sleep(time);
        leftfront.setPower(0);
        leftback.setPower(0);
        rightfront.setPower(0);
        rightback.setPower(0);
    }

    public void intake (int time){
        lowintake.setPower(1);
        roller.setPower(-1);
        sleep(time);
        lowintake.setPower(0);
        roller.setPower(0);
    }

    public void shoot(int power,int time){
        flyingwheel.setPower(power);
        //sleep(time);
        roller.setPower(-1);
        sleep(time);
        roller.setPower(0);
        flyingwheel.setPower(0);
    }

    @Override
    public void runOpMode() {
        leftfront  = hardwareMap.get(DcMotor.class, "lf");
        leftback = hardwareMap.get(DcMotor.class, "rf");
        rightfront  = hardwareMap.get(DcMotor.class, "lr");
        rightback = hardwareMap.get(DcMotor.class, "rr");

        roller  = hardwareMap.get(DcMotor.class, "roller");
        flyingwheel  = hardwareMap.get(DcMotor.class, "flyingWheel");
        lowintake  = hardwareMap.get(DcMotor.class, "lowIntake");

        leftfront.setDirection(DcMotor.Direction.REVERSE);
        leftback.setDirection(DcMotor.Direction.FORWARD);
        rightfront.setDirection(DcMotor.Direction.REVERSE);
        rightback.setDirection(DcMotor.Direction.FORWARD);
        flyingwheel.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        DriveStraight(0.6,700);
        intake(2000);
        shoot(1000, 1200);
        DriveStraight(0.6,700);
        intake(2000);
        shoot(700, 1200);
        DriveStraight(0.6, 700);
        intake(2000);
        shoot(500, 1200);

        /*
        strafe(0.4,2020);
        DriveStraight(0.6,200);
        intake(2000);
        shoot(1000, 3000);
        */

    }

}