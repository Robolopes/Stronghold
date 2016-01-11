/**
 * 
 */
package org.usfirst.frc.team2339.robot.components;

import org.usfirst.frc.team2339.robot.OI;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author emiller
 *
 */
public class DriveJoystick extends Joystick {
	
	// If true this is left stick. Otherwise this is right stick.
	private final boolean isLeft; 

	/**
	 * @param port
	 */
	public DriveJoystick(int port, boolean isLeft) {
		super(port);
		this.isLeft = isLeft;
	}

	public double getFrontBack() {
		return -getRawAxis(1);
	}

	public boolean getSpeedShift() {
		return getRawButton(OI.DRIVE_BUTTON_SPEED_SHIFT);
	}

	public boolean isLeft() {
		return isLeft;
	}

}
