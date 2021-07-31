import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name = "TWOHOLESEXISTSHOOTTHERINGSINTOTHEHOLESORELSEYOUAREBADANDYOURCODEDOESNTWORKLLLLLLLL", group = "Linear Opmode")

public class CONAN_DOUBLEHOlESSHOT extends LinearOpMode {
    ElapsedTime runtime = new ElapsedTime();
    public DcMotor leftfront;
    public DcMotor leftrear;
    public DcMotor rightfront;
    public DcMotor rightrear;
    public DcMotor Thrower;
    public DcMotor Eater1;
    public DcMotor Eater2;

    public void DriveStraightToLine(float speed, int duration) {
        // -1 <= speed <= 1 and duration in ms
        try {

            //yellow spinny at bottom "lowIntake"
            //grey roller "roller"
            //black top flyingWheel"


            // set the speed
            leftfront.setPower(-speed);
            leftrear.setPower(-speed);
            rightfront.setPower(-speed);
            rightrear.setPower(-speed);

            // wait for the requested duration
            Thread.sleep(duration);

            // stop all the motors when done waiting
            leftfront.setPower(0);
            leftrear.setPower(0);
            rightfront.setPower(0);
            rightrear.setPower(0);

            Thread.sleep(100);

        } catch (Exception e) {
            telemetry.addData("Exception: ", e.toString());
        }

    }

    public void strafe(float speed, int duration) {
        // -1 <= speed <= 1 and duration in ms
        try {

            //yellow spinny at bottom "lowIntake"
            //grey roller "roller"
            //black top "intake"


            // set the speed
            leftfront.setPower(speed);
            leftrear.setPower(-speed);
            rightfront.setPower(-speed);
            rightrear.setPower(speed);

            // wait for the requested duration
            Thread.sleep(duration);

            // stop all the motors when done waiting
            leftfront.setPower(0);
            leftrear.setPower(0);
            rightfront.setPower(0);
            rightrear.setPower(0);

            Thread.sleep(500);

        } catch (Exception e) {
            telemetry.addData("Exception: ", e.toString());
        }

    }


    public void YEEETER(float speed, int duration) {
        // -1 <= speed <= 1 and duration in ms
        try {

            // set the speed
            Thrower.setPower(speed);
            Eater1.setPower(speed);
            Eater2.setPower(speed);
            // wait for the requested duration
            Thread.sleep(duration);

            // stop all the motors when done waiting
            Thrower.setPower(0);
            Eater1.setPower(0);
            Eater2.setPower(0);


        } catch (Exception e) {
            telemetry.addData("Exception: ", e.toString());
        }

    }

    public void load(float speed, int duration) {
        // -1 <= speed <= 1 and duration in ms
        try {

            // set the speed

            Eater1.setPower(speed);
            Eater2.setPower(speed);
            // wait for the requested duration
            Thread.sleep(duration);

            // stop all the motors when done waiting
            Eater1.setPower(0);
            Eater2.setPower(0);


        } catch (Exception e) {
            telemetry.addData("Exception: ", e.toString());
        }

    }


    public void turn(float speed, int duration) {
        // -1 <= speed <= 1 and duration in ms
        try {

            //yellow spinny at bottom "lowIntake"
            //grey roller "roller"
            //black top "intake"


            // set the speed
            leftfront.setPower(speed);
            leftrear.setPower(speed);
            rightfront.setPower(-speed);
            rightrear.setPower(-speed);

            // wait for the requested duration
            Thread.sleep(duration);

            // stop all the motors when done waiting
            leftfront.setPower(0);
            leftrear.setPower(0);
            rightfront.setPower(0);
            rightrear.setPower(0);

            Thread.sleep(500);

        } catch (Exception e) {
            telemetry.addData("Exception: ", e.toString());
        }

    }



    public void goback() {
        // -1 <= speed <= 1 and duration in ms
        try {
            turn(0.25f, 400);
            strafe(-0.35f, 5500);
            DriveStraightToLine(-0.35f, 1800);
            sleep(300);

        } catch (Exception e) {
            telemetry.addData("Exception: ", e.toString());
        }

    }

    @Override
    public void runOpMode() {

        leftfront = hardwareMap.get(DcMotor.class, "lf");
        leftrear = hardwareMap.get(DcMotor.class, "lr");
        rightfront = hardwareMap.get(DcMotor.class, "rf");
        rightrear = hardwareMap.get(DcMotor.class, "rr");
        Thrower = hardwareMap.get(DcMotor.class, "flyingWheel");
        Eater1 = hardwareMap.get(DcMotor.class, "lowIntake");
        Eater2 = hardwareMap.get(DcMotor.class, "roller");


        rightfront.setDirection(DcMotor.Direction.REVERSE);
        rightrear.setDirection(DcMotor.Direction.REVERSE);
        Thrower.setDirection(DcMotor.Direction.REVERSE);
        Eater2.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        runtime.reset();

        DriveStraightToLine(0.35f, 2100);
        YEEETER(1f, 5000);
        turn(-0.25f, 160);
        strafe(0.35f, 5500);
        DriveStraightToLine(0.25f, 350);
        YEEETER(1f, 5000);

        sleep(1000);

        goback();

    }
}