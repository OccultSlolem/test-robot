package frc.team1234.robot.automodes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team1234.robot.commands.setAllDriveMotorSpeed;

public class DoNothingAuto extends CommandGroup {
    public DoNothingAuto() {
        addSequential(new setAllDriveMotorSpeed(0));
    }
}
