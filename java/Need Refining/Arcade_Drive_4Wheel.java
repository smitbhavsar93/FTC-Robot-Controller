import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name="Arcade_Drive_TOUCH_Smit", group="Linear Opmode")
//@Disabled
public class Arcade_Drive_4Wheel extends LinearOpMode
{
    DcMotor lfMotor, lrMotor, rfMotor, rrMotor;
    float   leftPower, rightPower, xValue, yValue;
    TouchSensor touch;


    // called when init button is  pressed.
    @Override
    public void runOpMode() throws InterruptedException
    {
        lfMotor = hardwareMap.dcMotor.get("lf");
        lrMotor = hardwareMap.dcMotor.get("lf");
        rfMotor = hardwareMap.dcMotor.get("rf");
        rrMotor = hardwareMap.dcMotor.get("lf");

        touch = hardwareMap.touchSensor.get("touch_sensor");

        lfMotor.setDirection(DcMotor.Direction.REVERSE);
        lrMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        while (opModeIsActive() && !touch.isPressed())
        {
            yValue = gamepad1.right_stick_y * -1;
            xValue = gamepad1.right_stick_x * -1;

            leftPower =  yValue - xValue;
            rightPower = yValue + xValue;

            lfMotor.setPower(Range.clip(leftPower, -1.0, 1.0));
            lrMotor.setPower(Range.clip(leftPower, -1.0, 1.0));
            rfMotor.setPower(Range.clip(rightPower, -1.0, 1.0));
            rrMotor.setPower(Range.clip(rightPower, -1.0, 1.0));

            // turn the motors off.

            lfMotor.setPower(0);
            lrMotor.setPower(0);
            rfMotor.setPower(0);
            rrMotor.setPower(0);

            telemetry.addData("Mode", "running");
            telemetry.addData("stick", "  y=" + yValue + "  x=" + xValue);
            telemetry.addData("power", "  left=" + leftPower + "  right=" + rightPower);
            telemetry.update();

        }
    }
}