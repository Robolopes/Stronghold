package org.usfirst.frc.team2339.robot;

import org.usfirst.frc.team2339.robot.commands.TeleopBoulderHandler;
import org.usfirst.frc.team2339.robot.commands.TeleopClimber;
import org.usfirst.frc.team2339.robot.commands.TeleopDrive;
import org.usfirst.frc.team2339.robot.commands.TeleopScimitar;
import org.usfirst.frc.team2339.robot.components.DriveJoystick;
import org.usfirst.frc.team2339.robot.components.OperatorJoystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

	public static final int DRIVE_BUTTON_SPEED_SHIFT = 1;
	public static final int OPERATOR_BUTTON_SHOOT = 1;
	public static final double SHOOTER_SPEED = 1.0;
	// Number of seconds to allow shooter to spin up before feeding balls
	public static final double SHOOTER_TIME_TO_SPIN_UP_SECONDS = 0.1;
	public static final int OPERATOR_BUTTON_SCIMITAR_UP_DOWN = 2;
	public static final int OPERATOR_BUTTON_SCIMITAR_ROLLER_INTAKE = 3;
	public static final double SCIMITAR_ROLLER_INTAKE_SPEED = 0.5;
	public static final int OPERATOR_BUTTON_SCIMITAR_ROLLER_REVERSE = 4;
	public static final double SCIMITAR_ROLLER_REVERSE_SPEED = -0.5;
	public static final int OPERATOR_BUTTON_CONVERYOR_INTAKE = 5;
	public static final double CONVERYOR_INTAKE_SPEED = 0.5;
	public static final int OPERATOR_BUTTON_CONVERYOR_REVERSE = 6;
	public static final double CONVERYOR_REVERSE_SPEED = -0.5;
	public static final int OPERATOR_BUTTON_CLIMBER_UP_DOWN = 7;
	// Number of seconds before climber is allowed to operate
	// 115 is 1 minute 55 seconds, which should be 20 seconds before the end of a 2:15 teleop period.
	public static final double CLIMBER_TELEOP_TIME_DISABLE_SECONDS = 115;

    private DriveJoystick joystickDriveLeft;
    private DriveJoystick joystickDriveRight;
    
    private OperatorJoystick joystickOperator;
    
    private TeleopDrive teleopDrive;
    private TeleopScimitar teleopScimitar;
    private TeleopBoulderHandler teleopBoulderHandler;
    private TeleopClimber teleopClimber;
    
	/**
	 * 
	 */
	public OI() {
        setJoystickOperator(new OperatorJoystick(2));
        setJoystickDriveLeft(new DriveJoystick(0, true));
        setJoystickDriveRight(new DriveJoystick(1, false));
        setTeleopDrive(new TeleopDrive("Teleop drive", RobotMap.Subsystem.robotDrive, getJoystickDriveLeft(), getJoystickDriveRight()));
        setTeleopScimitar(new TeleopScimitar("Teleop lift", RobotMap.Subsystem.scimitar, getJoystickOperator()));
        setTeleopBoulderHandler(new TeleopBoulderHandler("Teleop lift", RobotMap.Subsystem.boulderHandler, getJoystickOperator()));
        setTeleopClimber(new TeleopClimber("Teleop lift", RobotMap.Subsystem.climber, getJoystickOperator()));
	}

    
	/**
	 * @return the left joystickDrive
	 */
	public DriveJoystick getJoystickDriveLeft() {
		return joystickDriveLeft;
	}

	/**
	 * @param joystickDriveLeft the joystick to use for the left side of the drive
	 */
	protected void setJoystickDriveLeft(DriveJoystick joystickDriveLeft) {
		this.joystickDriveLeft = joystickDriveLeft;
	}

	/**
	 * @return the right joystickDrive
	 */
	public DriveJoystick getJoystickDriveRight() {
		return joystickDriveRight;
	}

	/**
	 * @param joystickDriveRight the joystick to use for the right side of the drive
	 */
	protected void setJoystickDriveRight(DriveJoystick joystickDriveRight) {
		this.joystickDriveRight = joystickDriveRight;
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


	public TeleopScimitar getTeleopScimitar() {
		return teleopScimitar;
	}


	private void setTeleopScimitar(TeleopScimitar teleopScimitar) {
		this.teleopScimitar = teleopScimitar;
	}

	public TeleopBoulderHandler getTeleopBoulderHandler() {
		return teleopBoulderHandler;
	}


	private void setTeleopBoulderHandler(TeleopBoulderHandler teleopBoulderHandler) {
		this.teleopBoulderHandler = teleopBoulderHandler;
	}


	public TeleopClimber getTeleopClimber() {
		return teleopClimber;
	}


	private void setTeleopClimber(TeleopClimber teleopClimber) {
		this.teleopClimber = teleopClimber;
	}


}

