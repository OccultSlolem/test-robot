package frc.team1234.robot.subsystems;

import edu.wpi.first.wpilibj.GamepadBase;
import edu.wpi.first.wpilibj.Joystick;
//import se.albin.steamcontroller.SteamController;
//import se.albin.steamcontroller.SteamControllerListener;
//import se.albin.steamcontroller.SteamControllerSubscriber;

import java.util.List;


public class OI {
    //Initialize Steam controller EDIT: I don't actually need this class. Commenting out...

    /*
    List<SteamController> controllers = SteamController.getConnectedControllers();
    SteamControllerListener listener = new SteamControllerListener();
    listener.open();
    SteamControllerSubscriber subscriber = new SteamControllerSubscriber();
    listener.addSubscriber(subscriber);
    */

    //Initialize joystick
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

    //Initialize Logitech Controller
    Joystick manipulator = new Joystick(1);

    public double getManipulatorRightY() {
        return manipulator.getRawAxis(5);
    }

    //@Override
    //public void update(SteamController steamController, SteamController steamController1) {
    //
    //}
}
