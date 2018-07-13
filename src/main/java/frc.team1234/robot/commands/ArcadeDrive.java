package frc.team1234.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1234.robot.Robot;

public class ArcadeDrive extends Command {
    public ArcadeDrive() {
        requires(Robot.instance.driveTrain);
    }

    protected void initialize() {

    }

    public void execute() {
        double throttle = Robot.instance.oi.getDriverLeftY();
        double turn = Robot.instance.oi.getDriverRightX();

        Robot.instance.driveTrain.setLeftMotorSpeed(throttle+turn);
        Robot.instance.driveTrain.setRightMotorSpeed(throttle-turn);

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
