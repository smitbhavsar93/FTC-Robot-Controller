// autonomous program that drives bot forward a set distance, stops then
// backs up to the starting point using encoders to measure the distance.

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Drive Encoder Smit", group="Exercises")
//@Disabled
public class DriveWithEncoder extends LinearOpMode
{
    DcMotor leftFrontMotor;
    DcMotor rightFrontMotor;
    DcMotor leftRearMotor;
    DcMotor rightRearMotor;

    @Override
    public void runOpMode() throws InterruptedException
    {
        leftFrontMotor = hardwareMap.dcMotor.get("lf");
        rightFrontMotor = hardwareMap.dcMotor.get("rf");
        leftRearMotor = hardwareMap.dcMotor.get("lr");
        rightRearMotor = hardwareMap.dcMotor.get("rr");

        // You will need to set this based on your robot's
        // gearing to get forward control input to result in
        // forward motion.
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftRearMotor.setDirection(DcMotor.Direction.REVERSE);

        // reset encoder counts kept by motors.
        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set motors to run forward for 5000 encoder counts.
        leftFrontMotor.setTargetPosition(2000);
        leftRearMotor.setTargetPosition(2000);
        rightFrontMotor.setTargetPosition(2000);
        rightRearMotor.setTargetPosition(2000);

        // set motors to run to target encoder position and stop with brakes on.
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set both motors to 25% power. Movement will start. Sign of power is
        // ignored as sign of target encoder position controls direction when
        // running to position.

        leftFrontMotor.setPower(0.25);
        leftRearMotor.setPower(0.25);
        rightFrontMotor.setPower(0.25);
        rightRearMotor.setPower(0.25);

        // wait while opmode is active and left motor is busy running to position.

        while (opModeIsActive() && leftFrontMotor.isBusy())   //leftMotor.getCurrentPosition() < leftMotor.getTargetPosition())
        {
            telemetry.addData("encoder-fwd-left", leftFrontMotor.getCurrentPosition() + "  busy=" + leftFrontMotor.isBusy());
            telemetry.addData("encoder-fwd-right", rightFrontMotor.getCurrentPosition() + "  busy=" + rightFrontMotor.isBusy());
            telemetry.update();
            idle();
        }

        // set motor power to zero to turn off motors. The motors stop on their own but
        // power is still applied so we turn off the power.

        leftFrontMotor.setPower(0.0);
        leftRearMotor.setPower(0.0);
        rightFrontMotor.setPower(0.0);
        rightRearMotor.setPower(0.0);

        // wait 5 sec to you can observe the final encoder position.

        resetStartTime();

        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-fwd-left-end", leftFrontMotor.getCurrentPosition());
            telemetry.addData("encoder-fwd-right-end", rightFrontMotor.getCurrentPosition());
            telemetry.update();
            idle();
        }

        // From current position back up to starting point. In this example instead of
        // having the motor monitor the encoder we will monitor the encoder ourselves.

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRearMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRearMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFrontMotor.setTargetPosition(0);
        leftRearMotor.setTargetPosition(0);
        rightFrontMotor.setTargetPosition(0);
        rightRearMotor.setTargetPosition(0);

        // Power sign matters again as we are running without encoder.
        leftFrontMotor.setPower(-0.25);
        leftRearMotor.setPower(-0.25);
        rightFrontMotor.setPower(-0.25);
        rightRearMotor.setPower(-0.25);

        while (opModeIsActive() && leftFrontMotor.getCurrentPosition() > leftFrontMotor.getTargetPosition())
        {
            telemetry.addData("encoder-back-left", leftFrontMotor.getCurrentPosition());
            telemetry.addData("encoder-back-right", rightFrontMotor.getCurrentPosition());
            telemetry.update();
            idle();
        }

        // set motor power to zero to stop motors.

        leftFrontMotor.setPower(0.0);
        leftRearMotor.setPower(0.0);
        rightFrontMotor.setPower(0.0);
        rightRearMotor.setPower(0.0);

        resetStartTime();

        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-back-left-end", leftFrontMotor.getCurrentPosition());
            telemetry.addData("encoder-back-right-end", rightFrontMotor.getCurrentPosition());
            telemetry.update();
            idle();
        }
    }
}