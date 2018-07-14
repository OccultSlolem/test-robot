package frc.team1234.robot.automodes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team1234.robot.commands.MoveForwardForTicks;
import frc.team1234.robot.commands.ResetEncoders;

//Moves forward a bit during auto.

public class DriveForwardAuto extends CommandGroup {
    public DriveForwardAuto() {
        addSequential(new MoveForwardForTicks(100,100,500));
        addSequential(new ResetEncoders(true,false));
    }
}
