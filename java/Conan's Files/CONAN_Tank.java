import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

@TeleOp(name="Pushbot: Teleop org.firstinspires.ftc.teamcode.Tank", group="Pushbot")
@Disabled
public class CONAN_Tank extends OpMode {
    HardwarePushbot robot = new HardwarePushbot();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }
    @Override
    public void init_loop() {
    }
    @Override
    public void loop() {
        double left;
        double right;
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;

        robot.leftDrive.setPower(left);
        robot.rightDrive.setPower(right);
    }
    @Override
    public void stop() {
        telemetry.addData("Say", "Plz don't drive me again");    //

    }

}