package frc.team1234.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class MoveForwardForTicks extends Command {
    int error;
    int ticks;
    int actual;
    int leftMotor;
    int rightMotor;

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public void setMotorSpeeds(int leftMotor, int rightMotor) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
    }

    public MoveForwardForTicks(int leftSpeed, int rightSpeed, int ticks) {
        setMotorSpeeds(leftSpeed,rightSpeed);
        setTicks(ticks);

        error = ticks - actual;
        if(error > 0) {
            new SetLeftMotorSpeed(leftMotor);
            new SetRightMotorSpeed(rightMotor);
            error = ticks - actual;
        } else {
            new SetLeftMotorSpeed(0);
            new SetRightMotorSpeed(0);
        }
    }

    public void execute(int leftSpeed, int rightSpeed, int setpoint) {
        new MoveForwardForTicks(leftSpeed,rightSpeed,setpoint);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
