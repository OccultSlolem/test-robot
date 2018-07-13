package frc.team1234.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1234.robot.Robot;
import frc.team1234.robot.subsystems.DriveTrain;
import frc.team1234.robot.subsystems.OI;

public class TankDrive extends Command {
    //public TankDrive() {
    ////requires(Robot.driveTrain);
    //}
    //

    protected void initialize() {

    }

    public void execute() {
        double leftY = Robot.instance.oi.getDriverLeftY();
        double rightY = Robot.instance.oi.getDriverRightY();
        Robot.instance.driveTrain.setLeftMotorSpeed(leftY);
        Robot.instance.driveTrain.setRightMotorSpeed(rightY);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
