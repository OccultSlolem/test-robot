package frc.team1234.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.team1234.robot.automodes.DoNothingAuto;
import frc.team1234.robot.automodes.DriveForwardAuto;
import frc.team1234.robot.commands.ArcadeDrive;
import frc.team1234.robot.commands.MoveElevator;
import frc.team1234.robot.commands.TankDrive;
import frc.team1234.robot.subsystems.DriveTrain;
import frc.team1234.robot.subsystems.DriveType;
import frc.team1234.robot.subsystems.AutoMode;
import frc.team1234.robot.subsystems.Elevator;
import frc.team1234.robot.subsystems.OI;

//The first stop for the robot

public class Robot extends IterativeRobot {
    //private Command driveforwardauto;
    public DriveTrain driveTrain;
    public Elevator elevator;
    public OI oi;
    public TankDrive tankDrive;
    public ArcadeDrive arcadeDrive;
    public MoveElevator moveElevator;
    public DoNothingAuto doNothingAuto;
    public DriveForwardAuto driveForwardAuto;

    public static final Robot instance = new Robot();

    //Drivetrain selector
    public SendableChooser<DriveType> driveTypeSelector = new SendableChooser<>();

    //Auto Selector
    public SendableChooser<CommandGroup> autoModeSelector = new SendableChooser<>();


    public void robotInit() {
        driveTrain = new DriveTrain();
        elevator = new Elevator();
        oi = new OI();

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
        autoModeSelector.getSelected().start();
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
