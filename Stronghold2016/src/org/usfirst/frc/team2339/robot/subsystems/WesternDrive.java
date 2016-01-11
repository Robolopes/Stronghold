package org.usfirst.frc.team2339.robot.subsystems;

import org.usfirst.frc.team2339.robot.components.DriveJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class WesternDrive extends Subsystem {
	
	private RobotDrive drive;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * 
     * @param left speed of left side. Negative is backward.
     * @param right speed of right side. Negative is backward.
     */
    public void takeSpeedInput(double left, double right) {
    	drive.tankDrive(left, right);
    }
    
    /**
     * 
     * @param toHigh if true shift to high. Otherwise shift to low
     */
    public void shift(boolean toHigh) {
    	
    }
    
    public void takeJoystickInput(DriveJoystick left, DriveJoystick right) {
    	if (left.getSpeedShift() || right.getSpeedShift()) {
    		shift(true);
    	} else {
    		shift(false);
    	}
    	drive.tankDrive(left, right);
    }
    
    public void stop() {
    	drive.tankDrive(0, 0);
    }
}

