

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.util.concurrent.TimeUnit;

@Autonomous(name="Smit Auto2Encoder", group="Linear Opmode")
public class ANDY_Auto2Encoder extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor lf = null;
    private DcMotor rf = null;
    private DcMotor lr = null;
    private DcMotor rr = null;

    private DcMotor roller = null;
    private DcMotor flyingwheel = null;
    private DcMotor lowintake = null;

    private boolean flyon = false;
    private double currenttime = 0;
    private double flyPower = 0;
    static final int ticks_1meter = 800;

    public void driveForwardInitalize(double distance) {

        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lf.setTargetPosition((int) -distance * ticks_1meter);
        rf.setTargetPosition((int) -distance * ticks_1meter);
        lr.setTargetPosition((int) -distance * ticks_1meter);
        rr.setTargetPosition((int) -distance * ticks_1meter);

        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void driveForwardMove(double distance){
        int direction = (int)(distance/(Math.abs(distance)));
        lf.setPower(-0.3*direction);
        rf.setPower(-0.3*direction);
        lr.setPower(-0.3*direction);
        rr.setPower(-0.3*direction);

        while (opModeIsActive() && lf.isBusy() && rf.isBusy() && lr.isBusy() && rr.isBusy())
        {
            telemetry.addData("lf: ", lf.getCurrentPosition() + "  busy=" + lf.isBusy());
            telemetry.addData("rf: ", rf.getCurrentPosition() + "  busy=" + rf.isBusy());
            telemetry.addData("lr: ", lr.getCurrentPosition() + "  busy=" + lr.isBusy());
            telemetry.addData("rr: ", rr.getCurrentPosition() + "  busy=" + rr.isBusy());
            telemetry.update();
            idle();
        }


        lf.setPower(0);
        rf.setPower(0);
        lr.setPower(0);
        rr.setPower(0);

        resetStartTime();

        while (opModeIsActive() && getRuntime() < 2)
        {
            telemetry.addData("encoder-fwd-left-end", lf.getCurrentPosition());
            telemetry.addData("encoder-fwd-right-end", rf.getCurrentPosition());
            telemetry.addData("encoder-fwd-left-end", lr.getCurrentPosition());
            telemetry.addData("encoder-fwd-right-end", rr.getCurrentPosition());
            telemetry.update();
            idle();
        }
    }

    public void driveForward(double distance){
        driveForwardInitalize(distance);
        start();
        driveForwardMove(distance);
    }

    public void drivex(float speed, int duration){
        lf.setPower(speed);
        rf.setPower(-speed);
        lr.setPower(-speed);
        rr.setPower(speed);
        sleep(duration);
        lf.setPower(0);
        rf.setPower(0);
        lr.setPower(0);
        rr.setPower(0);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

    }

    public void turn(float speed, int duration){
        lf.setPower(speed);
        rf.setPower(-speed);
        lr.setPower(speed);
        rr.setPower(-speed);
        sleep(duration);
        lf.setPower(0);
        rf.setPower(0);
        lr.setPower(0);
        rr.setPower(0);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    public void intake(int duration){
        lowintake.setPower(1);
        roller.setPower(0.9);
        sleep(duration);
        lowintake.setPower(0);
        roller.setPower(0);
    }

    public void shoot(int duration, float flyp){
        flyingwheel.setPower(-flyp);
        sleep(900);
        roller.setPower(1);
        sleep(duration);
        roller.setPower(0);
        flyingwheel.setPower(0);
    }

    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        lf = hardwareMap.get(DcMotor.class, "lf");
        rf = hardwareMap.get(DcMotor.class, "rf");
        lr = hardwareMap.get(DcMotor.class, "lr");
        rr = hardwareMap.get(DcMotor.class, "rr");

        roller = hardwareMap.get(DcMotor.class, "roller");
        flyingwheel = hardwareMap.get(DcMotor.class, "flyingWheel");
        lowintake = hardwareMap.get(DcMotor.class, "lowIntake");

        lf.setDirection(DcMotor.Direction.FORWARD);
        rf.setDirection(DcMotor.Direction.REVERSE);
        lr.setDirection(DcMotor.Direction.FORWARD);
        rr.setDirection(DcMotor.Direction.REVERSE);
        roller.setDirection(DcMotor.Direction.REVERSE);
        flyingwheel.setDirection(DcMotor.Direction.FORWARD);
        lowintake.setDirection(DcMotor.Direction.FORWARD);
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        driveForward(1);
        driveForward(-1);


    }

}