package frc.team1234.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import frc.team1234.robot.RobotMap;

public class Elevator {
    VictorSP elevator = new VictorSP(RobotMap.elevatorMotor);
    Encoder elevatorEncoder = new Encoder(RobotMap.elevatorEncoderA,RobotMap.elevatorEncoderB);

    double ticksPerRotation = 100;

    public Elevator() {
    }

    protected void initDefaultCommand() {
        //Put default command here
    }

    public void resetElevatorEncoder() {
        elevatorEncoder.reset();
    }

    public void getElevatorEncoder() {
        elevatorEncoder.get();
    }

    public void setElevatorSpeed(double speed) {
        elevator.set(speed);
    }

    public void setElevatorPos(double pos) {
        //1 = top
        //0 = bottom
        if(pos<0 || pos > 1) {
            return;
        }
        else if(pos >= 0 && pos <= 1) {
            return; //TODO: Finish section
        }
    }
}


