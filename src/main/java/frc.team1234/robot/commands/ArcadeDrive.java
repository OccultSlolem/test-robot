package frc.team1234.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1234.robot.Robot;

//Sets up the controller for if we decide to use arcade drive.

public class ArcadeDrive extends Command {
    public ArcadeDrive() {
        requires(Robot.instance.driveTrain);
    }

    protected void initialize() {

    }

    public void execute() {
        double throttle = 10*(Robot.instance.oi.getDriverLeftY()-.5);
        double turn = 10*(Robot.instance.oi.getDriverRightX()-.5);

        Robot.instance.driveTrain.setLeftMotorSpeed(throttle+turn);
        Robot.instance.driveTrain.setRightMotorSpeed(throttle-turn);

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
