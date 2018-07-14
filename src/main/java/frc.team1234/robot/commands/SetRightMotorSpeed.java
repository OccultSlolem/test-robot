package frc.team1234.robot.commands;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import frc.team1234.robot.RobotMap;

public class SetRightMotorSpeed extends Command {
    VictorSP rightFront = new VictorSP(RobotMap.leftFrontMotor);
    VictorSP rightRear = new VictorSP(RobotMap.leftRearMotor);
    double speed;

    public SetRightMotorSpeed(double speed) { this.speed = speed;}

    public void execute() {

    }

    public void initialize() {
        rightFront.set(speed);
        rightRear.set(speed);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}