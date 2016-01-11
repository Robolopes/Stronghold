package org.usfirst.frc.team2339.robot.subsystems;

import org.usfirst.frc.team2339.robot.components.DriveJoystick;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class WesternDrive extends Subsystem {
	
	private RobotDrive drive;
    private final Solenoid superShifter;
    
    private boolean isSuperShifterLow;
    private boolean wasSuperShifterButtonJustPushed;
	
    public WesternDrive(RobotDrive drive, Solenoid superShifter) {
    	this.drive = drive;
    	this.superShifter = superShifter;
    	shift(true);
    	wasSuperShifterButtonJustPushed = false;
    }
    
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
     * Set shifter
     * @param isSuperShifterLow true: set shifter to low. false: set shifter to high
     */
    public void shift(boolean isSuperShifterLow) {
        superShifter.set(isSuperShifterLow);
        this.isSuperShifterLow = isSuperShifterLow;
        if (isSuperShifterLow) {
            SmartDashboard.putString("Super shifter ", "High");
        } else {
            SmartDashboard.putString("Super shifter ", "Low");
        }
    }
    
    /*
     * Change super shifter from high speed to low speed and back
     * 
     */
    public void changeSuperShifter() {
    	shift(!this.isSuperShifterLow);
    }

    /**
     * Shift based on joystick input.
     * 
     * @param left
     * @param right
     */
    private void takeJoystickShift(DriveJoystick left, DriveJoystick right) {
    	
    	boolean superShifterPushedNow = false;
    	if (left.getSpeedShift() || right.getSpeedShift()) {
    		superShifterPushedNow = true;
    	}

        /*
         * Shift with super shifter
         * Shifter button shifts between high and low and back again.
         * Push once, it shifts to high. Push again it shifts to low.
         * The changeSuperShifter method does the shifting.
         * The wasSuperShifterButtonJustPushed flag is used to keep from "bouncing"
         * It makes sure the button is released before asking the shifter to change again.
         * Without it the shifter would shift every 20ms while the button is held down.
         * For example, without the flag, if the button were pushed for 1/2 second
         * the shifter would shift 25 times.
         */
        if(!wasSuperShifterButtonJustPushed && superShifterPushedNow) {
            changeSuperShifter();
            wasSuperShifterButtonJustPushed = true;
        } else if (wasSuperShifterButtonJustPushed && !superShifterPushedNow) {
            wasSuperShifterButtonJustPushed = false;
        }
        
    }
    
    public void takeJoystickInput(DriveJoystick left, DriveJoystick right) {
    	takeJoystickShift(left, right);
    	drive.tankDrive(left, right);
    }
    
    public void stop() {
    	drive.tankDrive(0, 0);
    }
}

