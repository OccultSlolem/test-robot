package frc.team1234.robot.commands;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import frc.team1234.robot.RobotMap;

public class setAllDriveMotorSpeed extends Command {
    VictorSP leftFront = new VictorSP(RobotMap.leftFrontMotor);
    VictorSP leftRear = new VictorSP(RobotMap.leftRearMotor);
    VictorSP rightFront = new VictorSP(RobotMap.rightFrontMotor);
    VictorSP rightRear = new VictorSP(RobotMap.rightRearMotor);
    double speed;

    public setAllDriveMotorSpeed(double speed) {
        this.speed = speed;
    }


    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    public void execute() {

    }

    @Override
    public void initialize() {
        leftFront.set(speed);
        leftRear.set(speed);
        rightFront.set(speed);
        rightRear.set(speed);
    }
}
