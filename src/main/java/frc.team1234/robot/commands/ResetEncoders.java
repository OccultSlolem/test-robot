package frc.team1234.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import frc.team1234.robot.RobotMap;

public class ResetEncoders extends Command {
    Encoder rightEncoder = new Encoder(RobotMap.rightEncoderA,RobotMap.rightEncoderB);
    Encoder leftEncoder = new Encoder(RobotMap.leftEncoderA,RobotMap.leftEncoderB);
    Encoder elevatorEncoder = new Encoder(RobotMap.elevatorEncoderA,RobotMap.elevatorEncoderB);

    public ResetEncoders(boolean resetDriveEncoders, boolean resetElevatorEncoders) {
        initialize(resetDriveEncoders, resetElevatorEncoders);
    }

    public void execute() {

    }

    public void initialize(boolean resetDriveEncoders, boolean resetElevatorEncoders) {
        if(resetDriveEncoders == true) {
            rightEncoder.reset();
            leftEncoder.reset();
        }
        if(resetElevatorEncoders == true) {
            elevatorEncoder.reset();
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
