package org.usfirst.frc.team2339.robot;

import org.usfirst.frc.team2339.robot.commands.TeleopDrive;
import org.usfirst.frc.team2339.robot.commands.TeleopLift;
import org.usfirst.frc.team2339.robot.components.DriveJoystick;
import org.usfirst.frc.team2339.robot.components.OperatorJoystick;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

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

    private DriveJoystick joystickDriveLeft;
    private DriveJoystick joystickDriveRight;
    
    private OperatorJoystick joystickOperator;
    
    private TeleopDrive teleopDrive;
    private TeleopLift teleopLift;
    
	/**
	 * 
	 */
	public OI() {
        setJoystickOperator(new OperatorJoystick(1));
        setJoystickDriveLeft(new DriveJoystick(0, true));
        setJoystickDriveRight(new DriveJoystick(1, false));
        setTeleopDrive(new TeleopDrive("Teleop drive", RobotMap.Subsystem.robotDrive, getJoystickDriveLeft(), getJoystickDriveRight()));
        setTeleopLift(new TeleopLift("Teleop lift", RobotMap.Subsystem.lift, getJoystickOperator()));
        
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

