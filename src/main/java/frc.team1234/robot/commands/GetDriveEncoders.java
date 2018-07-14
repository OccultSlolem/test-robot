package frc.team1234.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import frc.team1234.robot.RobotMap;

public class GetDriveEncoders extends Command {
    Encoder rightEncoder = new Encoder(RobotMap.rightEncoderA,RobotMap.rightEncoderB);
    Encoder leftEncoder = new Encoder(RobotMap.leftEncoderA,RobotMap.leftEncoderB);

    public GetDriveEncoders() {

    }

    public int initialize(boolean getLeft) {
        if(getLeft == true) {
            return leftEncoder.get();
        } else {
            return rightEncoder.get();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
