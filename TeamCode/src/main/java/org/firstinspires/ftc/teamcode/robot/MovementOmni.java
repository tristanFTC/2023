package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mathaddons.Vector2D;


public class MovementOmni {

    Vector2D direction = new Vector2D(0,0);
    double turn = 0;


    private class Omni{
        DcMotor motor;
        Vector2D wheel_direction;
        double power = 0;
        static final double move_ratio = .75;
        static final double turn_ratio = .25;
        public Omni(DcMotor dcmotor, Vector2D direct){
            motor = dcmotor;
            wheel_direction = direct;
        }
        public void calculateMovePower(){
            power = direction.x * wheel_direction.x + direction.y * wheel_direction.y;
        }
        public double getPower(){ return power; }
        public void multiplyPower(double n){ power*=n; }
        public void apply() {
            motor.setPower(power*Omni.move_ratio + turn*Omni.turn_ratio);
        }
        public void stop(){
            motor.setPower(0);
        }
    }

    Omni omni_1, omni_2, omni_3, omni_4;
    DcMotor motor_1, motor_2, motor_3, motor_4;
    Telemetry telemetry;
    AccelPositioning gyroscope;
    /*
         1 4
         2 3
     */

    public MovementOmni(HardwareMap hardwareMap, Telemetry globalTelemetry){
        motor_1 = hardwareMap.get(DcMotor.class, "omni_1"); motor_1.setDirection(DcMotorSimple.Direction.FORWARD);
        motor_2 = hardwareMap.get(DcMotor.class, "omni_2"); motor_2.setDirection(DcMotorSimple.Direction.FORWARD);
        motor_3 = hardwareMap.get(DcMotor.class, "omni_3"); motor_3.setDirection(DcMotorSimple.Direction.FORWARD);
        motor_4 = hardwareMap.get(DcMotor.class, "omni_4"); motor_4.setDirection(DcMotorSimple.Direction.FORWARD);
        double a = Math.sqrt(2)*.5;
        omni_1 = new Omni(motor_1, new Vector2D(a,a));
        omni_2 = new Omni(motor_2, new Vector2D(a,-a));
        omni_3 = new Omni(motor_3, new Vector2D(-a,-a));
        omni_4 = new Omni(motor_4, new Vector2D(-a,a));
        telemetry = globalTelemetry;
        gyroscope = new AccelPositioning(hardwareMap,globalTelemetry);
    }

    public void useGamepad(Gamepad gamepad){
        setDirection(gamepad.left_stick_x, gamepad.left_stick_y);
    }

    public void setDirection(double x, double y){ direction.setValue(x,y); }

    public void restrictOrientationGyro() {
        turn = -gyroscope.getAngle()*.1;
    }

    public void apply(){
        omni_1.calculateMovePower();
        omni_2.calculateMovePower();
        omni_3.calculateMovePower();
        omni_4.calculateMovePower();
        double max_power = Math.max(
                Math.max(
                        Math.max(
                                Math.abs(omni_1.getPower()),
                                Math.abs(omni_2.getPower())
                        ),
                        Math.abs(omni_3.getPower())
                ),
                Math.abs(omni_4.getPower())
        );
        double norm = direction.norm();
        if (max_power!=0){
            omni_1.multiplyPower(norm/max_power); // pour ne pas limiter la vitesse tout droit ou sur les côtés
        }
    }

}
