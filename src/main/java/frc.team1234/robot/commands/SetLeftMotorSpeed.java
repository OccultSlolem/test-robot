package frc.team1234.robot.commands;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import frc.team1234.robot.RobotMap;

public class SetLeftMotorSpeed extends Command {
    VictorSP leftFront = new VictorSP(RobotMap.leftFrontMotor);
    VictorSP leftRear = new VictorSP(RobotMap.leftRearMotor);
    double speed;

    public SetLeftMotorSpeed(double speed) { this.speed = speed;}

    public void execute() {

    }

    public void initialize() {
        leftFront.set(speed);
        leftRear.set(speed);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
