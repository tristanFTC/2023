package org.firstinspires.ftc.teamcode.manual;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Movement;

@TeleOp(name="Mode Manuel", group="0")
public class ManualOpMode extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private Movement movement;

    @Override
    public void init() {
        movement = new Movement(hardwareMap, telemetry);
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    runtime.reset();
    }

    @Override
    public void loop() {
        movement.check_gamepad(gamepad1);
        movement.apply();
    }

    @Override
    public void stop() {
    }
}
