package org.firstinspires.ftc.teamcode.autos;

import java.util.Arrays;

import org.firstinspires.ftc.teamcode.robot.DriveTrain;
import org.firstinspires.ftc.teamcode.utils.Constants;
import org.firstinspires.ftc.teamcode.utils.Spline;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "splineTesting")
public class Auto extends LinearOpMode implements Constants {

    @Override
    public void runOpMode() {

        DriveTrain dt = new DriveTrain(hardwareMap, 34.5, -62.6, 0);

        ElapsedTime loopTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        ElapsedTime sample = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

        double[][] points = new double[][]{{34.5, -62.6}, {30.8, -37.1}, {35, -27.2}, {62.3, -35.8}};
      
        Spline spline = new Spline(points, 10, dt, 100);

        waitForStart();

            while(spline.desiredT() < 0.9){
                loopTimer.reset();
                dt.drive(0.8, spline.angle(), 0, false);
                if(sample.time() > 30){
                    spline.update();
                    sample.reset();
                }
                telemetry.addData("loops",loopTimer.time());
                telemetry.addData("DESIDERD TTTTT", spline.desiredT());
                telemetry.addData("angle", spline.angle());
                telemetry.addData("bobot pos", Arrays.toString(spline.robotPosition));
                telemetry.update();
            }
            dt.drive(0, 0, 0, false);
        
    }

}