package frc.team1234.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team1234.robot.RobotMap;

//Initializes various functions for the drivetrain. Includes gyro, encoder, and motor controller commands

public class DriveTrain extends Subsystem {
    VictorSP leftFront = new VictorSP(RobotMap.leftFrontMotor); //NOTE: KOP actually includes Sparks, not Victor SPs.
    VictorSP leftRear = new VictorSP(RobotMap.leftRearMotor);
    VictorSP rightFront = new VictorSP(RobotMap.rightFrontMotor);
    VictorSP rightRear = new VictorSP(RobotMap.rightRearMotor);

    Encoder rightEncoder = new Encoder(RobotMap.rightEncoderA,RobotMap.rightEncoderB);
    Encoder leftEncoder = new Encoder(RobotMap.leftEncoderA,RobotMap.leftEncoderB);

    double ticksPerRotation = 100; //Amount of encoder ticks per one rotation of a wheel

    public DriveTrain(){
    }

    @Override
    protected void initDefaultCommand() {
        //initialize default command here
    }

    public void initGyro() {
        initGyro();
    }

    public void resetGyro() {
        resetGyro();
    }

    public void setAllDriveMotorSpeed(double speed) {
        leftFront.set(speed);
        leftRear.set(speed);
        rightFront.set(speed);
        rightRear.set(speed);

    }

    public void setLeftMotorSpeed(double speed) {
        leftFront.set(speed);
        leftRear.set(speed);
    }

    public void setRightMotorSpeed(double speed) {
        leftFront.set(speed);
        leftRear.set(speed);
    }

    public void setEachLeftMotorSpeed(double frontSpeed, double rearSpeed) {
        leftFront.set(frontSpeed);
        leftRear.set(rearSpeed);
    }

    public void setEachRightMotorSpeed(double frontSpeed, double rearSpeed) {
        rightFront.set(frontSpeed);
        rightRear.set(rearSpeed);
    }

    public double getLeftEncoder() {
        return Math.abs(rightEncoder.get());
    }

    public double getRightEncoder() {
        return Math.abs(leftEncoder.get());
    }

    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }
}


