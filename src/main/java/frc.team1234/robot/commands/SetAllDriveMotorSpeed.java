package frc.team1234.robot.commands;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import frc.team1234.robot.RobotMap;

//Sets all drivetrain motors to one speed. Used mostly for autonomous and arcade drive

public class SetAllDriveMotorSpeed extends Command {
    VictorSP leftFront = new VictorSP(RobotMap.leftFrontMotor);
    VictorSP leftRear = new VictorSP(RobotMap.leftRearMotor);
    VictorSP rightFront = new VictorSP(RobotMap.rightFrontMotor);
    VictorSP rightRear = new VictorSP(RobotMap.rightRearMotor);
    double speed;

    public SetAllDriveMotorSpeed(double speed) {
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
