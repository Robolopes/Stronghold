package org.usfirst.frc.team2339.robot;

import org.usfirst.frc.team2339.robot.commands.DashCamera;
import org.usfirst.frc.team2339.robot.commands.GyroReset;
import org.usfirst.frc.team2339.robot.commands.SetSwervePivotPoint;
import org.usfirst.frc.team2339.robot.commands.TeleopDrive;
import org.usfirst.frc.team2339.robot.commands.TeleopLift;
import org.usfirst.frc.team2339.robot.components.OperatorJoystick;
import org.usfirst.frc.team2339.robot.components.SwerveJoystick;
import org.usfirst.frc.team2339.robot.swervemath.SwerveWheel.RectangularCoordinates;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * @author emiller
 */
public class OI {

	public static final double DRIVE_STICK_DEAD_BAND = 0.15;
	public static final int DRIVE_BUTTON_SPEED_SHIFT = 1;
	public static final int DRIVE_BUTTON_ABSOLUTE_GYRO_MODE = 2;
	private static final int DRIVE_BUTTON_ROTATE_AROUND_CONTAINER = 7;
	private static final int GYRO_BUTTON_RESET = 1;
    
    private SwerveJoystick joystickDrive;
    private JoystickButton containerPivotButton;
    
    private OperatorJoystick joystickOperator;
    private JoystickButton gyroResetButton;
    
    private TeleopDrive teleopDrive;
    private DashCamera dashCamera;
    private TeleopLift teleopLift;

	/**
	 * 
	 */
	public OI() {
        setJoystickOperator(new OperatorJoystick(1));
        setJoystickDrive(new SwerveJoystick(0));
        setTeleopDrive(new TeleopDrive("Teleop drive", RobotMap.Subsystem.robotDrive, getJoystickDrive(), RobotMap.Analog.GYRO));
        setDashCamera(new DashCamera("Dash camera", RobotMap.Subsystem.cameraSystem));
        setTeleopLift(new TeleopLift("Teleop lift", RobotMap.Subsystem.lift, getJoystickOperator()));
        
        containerPivotButton = new JoystickButton(getJoystickDrive(), DRIVE_BUTTON_ROTATE_AROUND_CONTAINER);
        containerPivotButton.whenPressed(new SetSwervePivotPoint("Container Pivot", RobotMap.Subsystem.robotDrive, 
        		new RectangularCoordinates(0.0, 
        				RobotMap.Constants.CONTAINER_CENTER_DISTANCE_FORWARD + 
        				0.5 * RobotMap.Constants.WHEEL_BASE_LENGTH)));
        containerPivotButton.whenReleased(new SetSwervePivotPoint("Container Pivot", RobotMap.Subsystem.robotDrive, 
        		new RectangularCoordinates(0.0, 0.0)));
        
        gyroResetButton = new JoystickButton(getJoystickOperator(), GYRO_BUTTON_RESET);
        gyroResetButton.whenPressed(new GyroReset(RobotMap.Analog.GYRO));
        
	}

	/**
	 * @return the joystickDrive
	 */
	public SwerveJoystick getJoystickDrive() {
		return joystickDrive;
	}

	/**
	 * @param joystickDrive the joystickDrive to set
	 */
	protected void setJoystickDrive(SwerveJoystick joystickDrive) {
		this.joystickDrive = joystickDrive;
	}

	/**
	 * @return the joystickOperator
	 */
	public OperatorJoystick getJoystickOperator() {
		return joystickOperator;
	}

	/**
	 * @param joystickOperator the joystickOperator to set
	 */
	protected void setJoystickOperator(OperatorJoystick joystickOperator) {
		this.joystickOperator = joystickOperator;
	}

	/**
	 * @return the teleopDrive
	 */
	public TeleopDrive getTeleopDrive() {
		return teleopDrive;
	}

	/**
	 * @param teleopDrive the teleopDrive to set
	 */
	private void setTeleopDrive(TeleopDrive teleopDrive) {
		this.teleopDrive = teleopDrive;
	}

	public DashCamera getDashCamera() {
		return dashCamera;
	}

	public void setDashCamera(DashCamera dashCamera) {
		this.dashCamera = dashCamera;
	}

	/**
	 * @return the teleopLift
	 */
	public TeleopLift getTeleopLift() {
		return teleopLift;
	}

	/**
	 * @param teleopLift the teleopLift to set
	 */
	private void setTeleopLift(TeleopLift teleopLift) {
		this.teleopLift = teleopLift;
	}

}
