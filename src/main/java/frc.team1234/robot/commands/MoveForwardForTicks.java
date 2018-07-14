package frc.team1234.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class PIDMoveForward extends Command {
    int P = 1; // Motor Power
    int error;
    int setpoint;

    public void setSetpoint(int setpoint) {
        this.setpoint = setpoint;
    }

    public void PIDMoveForward() {
        error = setpoint - 
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
