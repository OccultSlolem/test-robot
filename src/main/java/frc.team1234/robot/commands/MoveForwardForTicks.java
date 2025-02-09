package frc.team1234.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class MoveForwardForTicks extends Command {
    int errorL; // Left error
    int errorR; // Right error
    int lTicks; // Left encoder ticks travelled
    int rTicks; // Right encoder ticks travelled
    int ticksDistanceL; // Amount of ticks to travel on left drivetrain
    int ticksDistanceR; // Amount of ticks to travel on right
    private boolean done = false;

    private void setTicks(int ticksDistanceL, int ticksDistanceR) {
        this.ticksDistanceL = ticksDistanceL;
        this.ticksDistanceR = ticksDistanceR;
    }

    public void initialize() {
        setTicks(ticksDistanceL, ticksDistanceR);
        new ResetEncoders(true,false);
    }

    public MoveForwardForTicks(double leftSpeed, double rightSpeed, int ticks) {
        //errorR = rTicks - actual;
        if(errorR > 0 && errorL > 0) {
            new SetLeftMotorSpeed(leftSpeed);
            new SetRightMotorSpeed(rightSpeed);
            errorR = ticks - ticksDistanceR;
            errorL = ticks - ticksDistanceL;
        } else {
            new SetLeftMotorSpeed(0);
            new SetRightMotorSpeed(0);
            new ResetEncoders(true,false);
            done = true;
        }
    }

    public void execute() {
    }

    @Override
    protected boolean isFinished() {
        if(done) {
            done = false;
            return true;
        }
        return false;
    }
}
