package frc.team3256.robot.subsystems;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;
import frc.team3256.lib.DrivePower;
import frc.team3256.lib.Loop;
import frc.team3256.lib.hardware.ADXRS453_Gyro;
import frc.team3256.lib.hardware.TalonUtil;
import frc.team3256.lib.math.Rotation;
import frc.team3256.lib.trajectory.Trajectory;
import frc.team3256.lib.trajectory.TrajectoryCurveGenerator;
import frc.team3256.lib.trajectory.TrajectoryFollower;
import frc.team3256.lib.trajectory.TrajectoryGenerator;
import frc.team3256.robot.Constants;

public class DriveTrain extends SubsystemBase implements Loop {

    private static DriveTrain instance;
    private TalonSRX leftMaster, rightMaster, leftSlave, rightSlave;
    private ADXRS453_Gyro gyro;
    private DriveControlMode controlMode;
    private Trajectory trajectory;
    private Trajectory trajectoryCurveLead;
    private Trajectory trajectoryCurveFollow;

    public static DriveTrain getInstance() {
        return instance == null ? instance = new DriveTrain() : instance;
    }

    @Override
    public void outputToDashboard() {

    }

    @Override
    public void selfTest() {

    }

    @Override
    public void zeroSensors() {

    }

    public enum DriveControlMode {
        OPEN_LOOP,
        VELOCITY,
        FOLLOW_TRAJECTORY,
        TURN_TO_ANGLE,
        DRIVE_TO_DISTANCE
    }

    @Override
    public void init(double timestamp) {
        setOpenLoop(0, 0);
        leftMaster.setSelectedSensorPosition(0, 0,0);
        rightMaster.setSelectedSensorPosition(0, 0,0);
        gyro.reset();
    }

    @Override
    public void update(double timestamp) {
        System.out.println(getMode());
        switch (controlMode) {
            case OPEN_LOOP:
                //Fall through, all driver/manipulator input is handled in TeleopPeriodic
                break;
            case VELOCITY:
                /*
                double left = ControlsInterface.getTankLeft();
                double right = ControlsInterface.getTankRight();
                DrivePower power = TeleopDriveController.tankDrive(left, right);
                //System.out.println("MOTOR OUTPUT" + leftMaster.getOutputVoltage()/leftMaster.getBusVoltage());
                //System.out.println("MOTOR SPEED " + leftMaster.getSpeed());
                System.out.println("LEFT ERROR: " + getLeftVelocityError());
                System.out.println("RIGHT ERROR" + getRightVelocityError());
                SmartDashboard.putNumber("LEFT ERROR", getLeftVelocityError());
                SmartDashboard.putNumber("RIGHT ERROR", getRightVelocityError());
                updateVelocitySetpoint(power.getLeft()*Constants.kMaxVelocityHighGearInPerSec
                                , power.getRight()*Constants.kMaxVelocityHighGearInPerSec);
                */
                break;
            case FOLLOW_TRAJECTORY:
                break;
            /*
            case TURN_TO_ANGLE:
                updateTurnToAngle();
                break;
            case DRIVE_TO_DISTANCE:
                updateDriveToDistance();
                break;
            */
}
    }

    @Override
    public void end(double timestamp) {
        setOpenLoop(0, 0);
    }

    public DriveTrain() {
        controlMode = DriveControlMode.OPEN_LOOP;
        // create talon objects
        // master talons are set to a 5 period control frame rate
        leftMaster = TalonUtil.generateGenericTalon(Constants.kLeftDriveMaster);
        leftSlave = TalonUtil.generateSlaveTalon(Constants.kLeftDriveSlave, Constants.kLeftDriveMaster);
        rightMaster = TalonUtil.generateGenericTalon(Constants.kRightDriveMaster);
        rightSlave = TalonUtil.generateSlaveTalon(Constants.kRightDriveSlave, Constants.kRightDriveMaster);

        // setup encoders
        if (leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0) != ErrorCode.OK){
            DriverStation.reportError("Mag Encoder on Left Master not detected!!!", false);
        }
        if (rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0) != ErrorCode.OK){
            DriverStation.reportError("Mag Encoder on Right Master not detected!!!", false);
        }
        gyro = new ADXRS453_Gyro();

        /*
        We haven't tested this yet
        leftMaster.setPID(Constants.kDriveMotionMagicP, Constants.kDriveMotionMagicI, Constants.kDriveMotionMagicD,
                Constants.kDriveMotionMagicF, Constants.kDriveMotionMagicIZone, Constants.kDriveMotionMagicCloseLoopRampRate,
                Constants.kDriveMotionMagicProfile);
        rightMaster.setPID(Constants.kDriveMotionMagicP, Constants.kDriveMotionMagicI, Constants.kDriveMotionMagicD,
                Constants.kDriveMotionMagicF, Constants.kDriveMotionMagicIZone, Constants.kDriveMotionMagicCloseLoopRampRate,
                Constants.kDriveMotionMagicProfile);
        leftMaster.setMotionMagicAcceleration(Constants.kDriveMotionMagicAcceleration);
        rightMaster.setMotionMagicAcceleration((Constants.kDriveMotionMagicAcceleration));
        leftMaster.setMotionMagicCruiseVelocity(Constants.kDriveMotionMagicCruiseVelocity);
        rightMaster.setMotionMagicCruiseVelocity(Constants.kDriveMotionMagicCruiseVelocity);
        */

        leftMaster.config_kP(Constants.kDriveVelocityProfile, Constants.kLeftDriveVelocityP, 0);
        leftMaster.config_kI(Constants.kDriveVelocityProfile, Constants.kLeftDriveVelocityI, 0);
        leftMaster.config_kD(Constants.kDriveVelocityProfile, Constants.kLeftDriveVelocityD, 0);
        leftMaster.config_kF(Constants.kDriveVelocityProfile, Constants.kLeftDriveVelocityF, 0);
        leftMaster.config_IntegralZone(Constants.kDriveVelocityProfile, Constants.kLeftDriveVelocityIZone, 0);
        rightMaster.config_kP(Constants.kDriveVelocityProfile, Constants.kRightDriveVelocityP, 0);
        rightMaster.config_kI(Constants.kDriveVelocityProfile, Constants.kRightDriveVelocityI, 0);
        rightMaster.config_kD(Constants.kDriveVelocityProfile, Constants.kRightDriveVelocityD, 0);
        rightMaster.config_kF(Constants.kDriveVelocityProfile, Constants.kRightDriveVelocityF, 0);
        rightMaster.config_IntegralZone(Constants.kDriveVelocityProfile, Constants.kRightDriveVelocityIZone, 0);

        leftMaster.setSensorPhase(true);
        rightMaster.setSensorPhase(true);
    }

    public double getLeftDistance() {
        return rotToInches(leftMaster.getSelectedSensorPosition(0));
    }

    public double getRightDistance() {
        return rotToInches(rightMaster.getSelectedSensorPosition(0));
    }

    /**
     * @return left velocity in in/sec
     */
    public double getLeftVelocity(){
        return rpmToInchesPerSec(leftMaster.getSelectedSensorVelocity(0));
    }

    /**
     * @return right velocity in in/sec
     */
    public double getRightVelocity(){
        return rpmToInchesPerSec(rightMaster.getSelectedSensorVelocity(0));
    }

    //TODO: test these methods
    public double getLeftVelocityError(){
        if (controlMode!=DriveControlMode.VELOCITY){
            System.out.println("ERROR: WE ARE NOT IN VELOCITY CONTROL MODE");
            return 0;
        }
        //Returns in in/sec
        return Constants.kWheelDiameter*Math.PI/leftMaster.getClosedLoopError(0)*100;
    }

    public double getRightVelocityError(){
        if (controlMode != DriveControlMode.VELOCITY){
            System.out.println("ERROR: WE ARE NOT IN VELOCITY CONTROL MODE");
            return 0;
        }
        //Returns in in/sec
        return Constants.kWheelDiameter*Math.PI/rightMaster.getClosedLoopError(0)*100;
    }

    public double rotToInches(double rot){
        return rot*4*Math.PI;
    }

    public double inchesToRot(double inches){
        return inches / (4*Math.PI);
    }

    public double rpmToInchesPerSec(double rpm){
        return rotToInches(rpm)/60;
    }

    public double inchesPerSecToRpm(double inchesPerSec){
        return inchesToRot(inchesPerSec)*60;
    }

    public double rpmToNativeUnitsPerHundredMs(double rpm){
        return (rpm*4096)/600;
    }

    public void configureDistanceTrajectory(double startVel, double endVel, double distance){
        TrajectoryGenerator trajectoryGenerator = new TrajectoryGenerator();
        trajectory = trajectoryGenerator.generateTrajectory(startVel, endVel, distance);
        trajectoryCurveLead = null;
        trajectoryCurveFollow = null;
    }

    public void configureArcTrajectory(double startVel, double endVel, double degrees, double turnRadius) {
        TrajectoryCurveGenerator trajectoryCurveGenerator = new TrajectoryCurveGenerator();
        trajectoryCurveGenerator.generateTrajectoryCurve(startVel, endVel, degrees, turnRadius);
        trajectoryCurveLead = trajectoryCurveGenerator.getLeadPath();
        trajectoryCurveFollow = trajectoryCurveGenerator.getFollowPath();
        trajectory = null;
    }

    /*
    We haven't started testing these yet
    public void configureTalonsForDistance() {
        if (controlMode != DriveControlMode.DRIVE_TO_DISTANCE)
            controlMode = DriveControlMode.DRIVE_TO_DISTANCE;
        leftMaster.changeControlMode(CANTalon.TalonControlMode.MotionMagic);
        rightMaster.changeControlMode(CANTalon.TalonControlMode.MotionMagic);
    }

    public void configureTalonsForAngle() {
        if (controlMode != DriveControlMode.TURN_TO_ANGLE)
            controlMode = DriveControlMode.TURN_TO_ANGLE;
        leftMaster.changeControlMode(CANTalon.TalonControlMode.MotionMagic);
        rightMaster.changeControlMode(CANTalon.TalonControlMode.MotionMagic);
    }
    */

    /**
     * Changes the mode into Velocity mode, and sets the setpoints for the talons
     * @param setpoint_left left velocity setpoint in inches/sec
     * @param setpoint_right right velocity setpoint in inches/sec
     */
    public void setVelocitySetpoint(double setpoint_left, double setpoint_right){
        if (controlMode != DriveControlMode.VELOCITY){
            controlMode = DriveControlMode.VELOCITY;
        }
        updateVelocitySetpoint(setpoint_left, setpoint_right);
    }

    /**
     * Updates the Talons with a new velocity setpoint
     * @param left_velocity left velocity setpoint in inches/sec
     * @param right_velocity right velocity setpoint in inches/sec
     */
    public void updateVelocitySetpoint(double left_velocity, double right_velocity){
        //if we aren't in the velocity control mode, then something is messed up, so set motors to 0 for safety
        if (controlMode != DriveControlMode.VELOCITY){
            System.out.println("ERROR: We should be in the velocity mode");
            leftMaster.set(ControlMode.PercentOutput, 0);
            rightMaster.set(ControlMode.PercentOutput, 0);
            return;
        }
        if (Math.abs(left_velocity) <= 24){
            left_velocity = 0;
        }
        else if (Math.abs(right_velocity) <= 24){
            right_velocity = 0;
        }
        //otherwise, update the talons with the new velocity setpoint
        leftMaster.set(ControlMode.Velocity, inchesPerSecToRpm(left_velocity));
        rightMaster.set(ControlMode.Velocity, inchesPerSecToRpm(right_velocity));
    }

    /*
    We haven't started testing these yet
    public void setAngleSetpoint(double angle) {
        configureTalonsForAngle();
        leftMaster.set(degreesToInches(angle));
        rightMaster.set(degreesToInches(angle));
        this.angle = angle;
    }

    public void setDistanceSetpoint(double distance) {
        configureTalonsForDistance();
        leftMaster.set(distance);
        rightMaster.set(distance);
        this.distance = distance;
    }
    */

    /**
     * Set the mode into open-loop mode (PercentVBus on the talons, and set the corresponding power
     * @param leftPower
     * @param rightPower
     */
    public void setOpenLoop(double leftPower, double rightPower) {
        if (controlMode != DriveControlMode.OPEN_LOOP){
            controlMode = DriveControlMode.OPEN_LOOP;
        }
        //TALON reverseOutput doesn't work in PercentVBus (open loop)
        leftMaster.set(ControlMode.PercentOutput, -1.0*leftPower);
        rightMaster.set(ControlMode.PercentOutput, rightPower);
    }

    public void setOpenLoop(DrivePower power){
        setOpenLoop(power.getLeft(), power.getRight());
    }

    /*
    We haven't started testing these yet
    public void updateTurnToAngle() {
        //TODO: Take in gyro heading and set a new setpoint based on the gyro heading. This accounts for wheels slipping.
        leftMaster.set(degreesToInches(angle));
        rightMaster.set(degreesToInches(angle));
    }

    public void updateDriveToDistance() {
        //TODO: Take in gyro heading and adjust the left and right talon setpoints to make sure we are driving straight
        leftMaster.set(distance);
        rightMaster.set(distance);
    }
    */

    public ADXRS453_Gyro getGyro(){
        return gyro;
    }

    public Rotation getAngle(){
        return Rotation.fromDegrees(gyro.getAngle());
    }

    public double degreesToInches(double degrees) {
        return Constants.kRobotTrack * Math.PI * degrees / 360;
    }

    public DriveControlMode getMode(){
        return controlMode;
    }
}
