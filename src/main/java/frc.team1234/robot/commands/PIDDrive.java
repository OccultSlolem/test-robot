package frc.team1234.robot.commands;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class PIDDrive {
    int P,I,D = 1;
    int integral, previous_error, setpoint = 0;
    Gyro gyro;
    DifferentialDrive robotDrive;
    private double rcw;

    public PIDDrive(Gyro gyro) {
        this.gyro = gyro;
    }

    public void setSetpoint(int setpoint) {
        this.setpoint = setpoint;
    }

    public void PID() {
        double error = setpoint - gyro.getAngle(); //Error = target - actual
        this.integral += (error*0.2); //Integral is increased by error * time (which is 0.2 seconds)
        double derivative = (error-this.previous_error) / 0.2;
        this.rcw = P*error + I*this.integral + D*derivative;
    }

    public void execute() {
        PID();
        robotDrive.arcadeDrive(0,rcw);
    }
}
