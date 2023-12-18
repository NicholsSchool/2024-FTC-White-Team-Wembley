package org.firstinspires.ftc.teamcode.teleops;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Arm;
import org.firstinspires.ftc.teamcode.robot.Hand;
import org.firstinspires.ftc.teamcode.utils.Constants;

/**
 * A teleop for tuning drive motors using
 * FTC Dashboard
 */
@Config
@TeleOp(name="[DASHBOARD] OperatorTuning")
public class OperatorTuning extends OpMode implements Constants {
    public Arm arm;
    public Hand hand;
    public static double leftClamp;
    public static double rightClamp;
    public static double fourbarPower;
    public static int fourbarTarget;
    public static double armPower;
    @Override
    public void init() {
        hand = new Hand(hardwareMap);
        arm = new Arm(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {

        double armPos = arm.armPos();
        double fourbarPos = arm.fourBarPos();
        double leftPos = hand.leftClamp.getPosition();
        double rightPos = hand.rightClamp.getPosition();

        hand.handTest(leftClamp, rightClamp);

        arm.setPower(armPower);

        arm.fourbarUpdate(fourbarPower);

        telemetry.addData("armPos", armPos);
        telemetry.addData("fourbarPos", fourbarPos);
        telemetry.addData("leftPos", leftPos);
        telemetry.addData("rightPos", rightPos);
        telemetry.update();
    }
}