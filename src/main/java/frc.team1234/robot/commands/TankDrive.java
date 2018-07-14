package frc.team1234.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1234.robot.Robot;

//Controls the robot should we decide to use tank drive

public class TankDrive extends Command {
    //public TankDrive() {
    ////requires(Robot.driveTrain);
    //}
    //

    protected void initialize() {

    }

    public void execute() {
        double leftY = 10*(Robot.instance.oi.getDriverLeftY()-.5);
        double rightY = 10*(Robot.instance.oi.getDriverRightY()-.5);
        Robot.instance.driveTrain.setLeftMotorSpeed(leftY);
        Robot.instance.driveTrain.setRightMotorSpeed(rightY);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
