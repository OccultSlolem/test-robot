package frc.team1234.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1234.robot.Robot;

//Sets the speed of the elevator motor

public class MoveElevator extends Command {
    public MoveElevator() {
        //requires(Robot.instance);
    }

    public void execute() {
        Robot.instance.elevator.setElevatorSpeed(Robot.instance.oi.getManipulatorRightY());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
