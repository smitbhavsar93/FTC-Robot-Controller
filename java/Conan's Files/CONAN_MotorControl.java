import com.qualcomm.robotcore.hardware.DcMotor;



public class CONAN_MotorControl {
    private int x = 0;
    int get_x(){
        return x;
    }
    public void DriveStraight(int DegreeSpin, double power, DcMotor rightfront, DcMotor rightrear, DcMotor leftfront, DcMotor leftrear) {
        rightfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightrear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftrear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightfront.setTargetPosition(DegreeSpin);
        rightrear.setTargetPosition(DegreeSpin);
        leftfront.setTargetPosition(DegreeSpin);
        leftrear.setTargetPosition(DegreeSpin);

        rightfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightrear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftrear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightfront.setPower(power);
        rightrear.setPower(power);
        leftfront.setPower(power);
        leftrear.setPower(power);

        while (rightfront.isBusy() && rightrear.isBusy() && leftfront.isBusy() && leftrear.isBusy()) {

        }
        rightfront.setPower(0);
        rightrear.setPower(0);
        leftfront.setPower(0);
        leftrear.setPower(0);

    }

    public void STRAF(int DegreeSpin, double power, DcMotor rightfront, DcMotor rightrear, DcMotor leftfront, DcMotor leftrear) {
        rightfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightrear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftrear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightfront.setTargetPosition(DegreeSpin);
        rightrear.setTargetPosition(DegreeSpin);
        leftfront.setTargetPosition(DegreeSpin);
        leftrear.setTargetPosition(DegreeSpin);

        rightfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightrear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftrear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightfront.setPower(-1*power);
        rightrear.setPower(-1*power);
        leftfront.setPower(power);
        leftrear.setPower(power);

        while (rightfront.isBusy() && rightrear.isBusy() && leftfront.isBusy() && leftrear.isBusy()) {

        }
        rightfront.setPower(0);
        rightrear.setPower(0);
        leftfront.setPower(0);
        leftrear.setPower(0);

    }




}
