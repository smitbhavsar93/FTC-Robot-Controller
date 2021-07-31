/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

//package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Textbook_Computational_Smit", group="Linear Opmode")
public class Textbook_Computational_3WheelDrive extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    DcMotor left = null;
    DcMotor right = null;
    DcMotor front = null;

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        left  = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        front = hardwareMap.get(DcMotor.class, "front");

        left.setDirection(DcMotor.Direction.REVERSE);
        right.setDirection(DcMotor.Direction.REVERSE);
        front.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double drive = gamepad1.left_stick_y;
            double turn  =  -gamepad1.right_stick_x;
            double sideways = gamepad1.left_stick_x;

            double d = 0.14;
            double r = 0.045;
            int rpm = 223;

            double Motor1 = (-d * turn + 1 * sideways + 0) / r;
            telemetry.addData("Motor1", Motor1);

            double Motor2 = (-d * turn - 0.5 * sideways - Math.sin(Math.PI/3) * drive) / r;
            telemetry.addData("Motor 2", Motor2);

            double Motor3 = (-d * turn - 0.5 * sideways + Math.sin(Math.PI/3) * drive) / r;
            telemetry.addData("Motor 3", Motor3);

            Motor1/=(rpm / 60 * 2*Math.PI*r);
            Motor2/=(rpm / 60 * 2*Math.PI*r);
            Motor3/=(rpm / 60 * 2*Math.PI*r);

            Motor1 = Range.clip(Motor1, -1, 1);
            Motor2 = Range.clip(Motor2, -1, 1);
            Motor3 = Range.clip(Motor3, -1, 1);



            left.setPower(Motor1);
            telemetry.addData("Motor1", Motor1);
            right.setPower(Motor2);
            telemetry.addData("Motor2", Motor2);
            front.setPower(Motor3);
            telemetry.addData("Motor3", Motor3);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
