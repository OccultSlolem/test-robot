package frc.team1234.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;

//Initializes the Operator Interface, ie gives a bunch of commands to get driver and manipulator controller inputs

public class OI {
    //Initialize Pilot joystick
    static Joystick driver = new Joystick(0);

    public double getDriverLeftY() {
        return driver.getRawAxis(1);
    }

    public double getDriverRightY() {
        return driver.getRawAxis(5);
    }

    public double getDriverRightX() {
        return driver.getRawAxis(4);
    }

    //Initialize Manipulator Controller
    Joystick manipulator = new Joystick(1);

    public double getManipulatorRightY() {
        return manipulator.getRawAxis(5);
    }
}
