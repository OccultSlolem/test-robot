package frc.team1234.robot.automodes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team1234.robot.commands.SetAllDriveMotorSpeed;

//Right on the can. Does nothing during auto.

public class DoNothingAuto extends CommandGroup {
    public DoNothingAuto() {
        addSequential(new SetAllDriveMotorSpeed(0));
    }
}
