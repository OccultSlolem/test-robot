package frc.team1234.robot.automodes;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team1234.robot.RobotMap;
import frc.team1234.robot.commands.setAllDriveMotorSpeed;

public class DriveForwardAuto extends CommandGroup {
    public DriveForwardAuto() {
        addSequential(new setAllDriveMotorSpeed(100));
    }
}
