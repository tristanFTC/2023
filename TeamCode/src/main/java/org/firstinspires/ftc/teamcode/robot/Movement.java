package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Movement {
    DcMotor RF_motor, RB_motor, LF_motor, LB_motor;
    Telemetry telemetry;
    double front = 0;
    double turn = 0;
    public Movement(HardwareMap hardwaremap, Telemetry globalTelemetry) {
        RF_motor = hardwaremap.get(DcMotor.class, "rf_motor"); RF_motor.setDirection(DcMotor.Direction.REVERSE);
        RB_motor = hardwaremap.get(DcMotor.class, "rf_motor"); RB_motor.setDirection(DcMotor.Direction.REVERSE);
        LF_motor = hardwaremap.get(DcMotor.class, "rf_motor"); LF_motor.setDirection(DcMotor.Direction.FORWARD);
        LB_motor = hardwaremap.get(DcMotor.class, "rf_motor"); LB_motor.setDirection(DcMotor.Direction.FORWARD);
        telemetry = globalTelemetry;
    }
    public void check_gamepad(Gamepad gamepad){
        front = -gamepad.left_stick_y * .5;
        turn = gamepad.left_stick_x * .5;
    }
    public void apply(){
        RF_motor.setPower(front-turn);
        RB_motor.setPower(front-turn);
        LF_motor.setPower(front+turn);
        LB_motor.setPower(front+turn);

    }
}
