package frc.team1234.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.team1234.robot.automodes.DoNothingAuto;
import frc.team1234.robot.automodes.DriveForwardAuto;
import frc.team1234.robot.commands.ArcadeDrive;
import frc.team1234.robot.commands.MoveElevator;
import frc.team1234.robot.commands.TankDrive;
import frc.team1234.robot.subsystems.DriveTrain;
import frc.team1234.robot.subsystems.DriveType;
import frc.team1234.robot.subsystems.Elevator;
import frc.team1234.robot.subsystems.OI;

//The first stop for the robot code. Is executed by the roboRIO immediately on startup, and various at points
//including on robot startup, and as well as at the beginning of, and during, autonomous, teleop, and disabled modes

public class Robot extends TimedRobot {

    //Initialize Subsystems
    public DriveTrain driveTrain = new DriveTrain();
    public Elevator elevator = new Elevator();
    public OI oi = new OI();

    //Initialize Commands and Automodes
    public TankDrive tankDrive = new TankDrive();
    public ArcadeDrive arcadeDrive = new ArcadeDrive();
    public MoveElevator moveElevator = new MoveElevator();

    //Initialize robot instance
    public static final Robot instance = new Robot();

    //Drivetrain selector
    public SendableChooser<DriveType> driveTypeSelector = new SendableChooser<>();

    //Auto Selector
    public SendableChooser<CommandGroup> autoModeSelector = new SendableChooser<>();


    public void robotInit() {
        //Drive Type selector - Use a Sendable Chooser to choose either arcade or tank drive
        driveTypeSelector.addDefault("Arcade", DriveType.ARCADE);
        driveTypeSelector.addObject("Tank", DriveType.TANK);

        //Auto Mode selector - Choose which auto to execute
        autoModeSelector.addDefault("Go Forward", new DriveForwardAuto());
        autoModeSelector.addObject("Do Nothing", new DoNothingAuto());

        driveTrain.initGyro();
        driveTrain.resetEncoders();
    }

    public void autonomousInit() {
        driveTrain.resetGyro();
        driveTrain.resetEncoders();
        autoModeSelector.getSelected().start();
    }

    public void teleopInit() {
        driveTrain.resetEncoders();
    }

    public void teleopPeriodic() {
        switch(driveTypeSelector.getSelected()) {
            case TANK:
                tankDrive.execute();
            case ARCADE:
                arcadeDrive.execute();
        }
        moveElevator.execute();
    }
}
