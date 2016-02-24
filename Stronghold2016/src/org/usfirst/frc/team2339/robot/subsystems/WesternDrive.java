package org.usfirst.frc.team2339.robot.subsystems;

import org.usfirst.frc.team2339.robot.OI;
import org.usfirst.frc.team2339.robot.RobotMap.Constants;
import org.usfirst.frc.team2339.robot.components.DriveJoystick;
import org.usfirst.frc.team2339.robot.components.DrivePidController;
import org.usfirst.frc.team2339.robot.components.DualTalonController;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class WesternDrive extends Subsystem {
	
	private final RobotDrive drive;
	//private final Encoder encoderLeft;
	//private final Encoder encoderRight;
	//private final DrivePidController controllerLeft;
	//private final DrivePidController controllerRight;
	private final double inchesPerPulseLow;
	private final double inchesPerPulseHigh;
    private final Solenoid superShifter;
    
    private boolean isSuperShifterLow;
    private boolean wasSuperShifterButtonJustPushed;
	
    public WesternDrive(int driveChannelLeft0, int driveChannelLeft1, 
    		int driveChannelRight0, int driveChannelRight1,
    		int[] driveEncoderChannelsLeft, int[] driveEncoderChannelsRight,
    		double inchesPerPulseLow, double inchesPerPulseHigh, 
    		int superShifterChannel) {

    	Talon driveLeft0 = new Talon(driveChannelLeft0);
    	Talon driveLeft1 = new Talon(driveChannelLeft1);
    	Talon driveRight0 = new Talon(driveChannelRight0);
    	Talon driveRight1 = new Talon(driveChannelRight1);
    	this.drive = new RobotDrive(driveLeft0, driveLeft1, driveRight0, driveRight1);
    	// Invert because one motor should be opposite
    	this.drive.setInvertedMotor(MotorType.kRearLeft, true);
    	this.drive.setInvertedMotor(MotorType.kRearRight, true);

    	this.inchesPerPulseLow = inchesPerPulseLow;
    	this.inchesPerPulseHigh = inchesPerPulseHigh;

    	/*
    	DualTalonController leftDual = new DualTalonController(driveLeft0, driveLeft1);
    	DualTalonController rightDual = new DualTalonController(driveRight0, driveRight1);
    	encoderLeft = new Encoder(driveEncoderChannelsLeft[0], driveEncoderChannelsLeft[1]);
    	encoderRight = new Encoder(driveEncoderChannelsRight[0], driveEncoderChannelsRight[1]);
    	controllerLeft = new DrivePidController(
    			Constants.DRIVE_PID_P, 
    			Constants.DRIVE_PID_I, 
    			Constants.DRIVE_PID_D, 
    			encoderLeft, 
    			leftDual);
    	controllerRight = new DrivePidController(
    			Constants.DRIVE_PID_P, 
    			Constants.DRIVE_PID_I, 
    			Constants.DRIVE_PID_D, 
    			encoderRight, 
    			rightDual);
    			*/

    	this.superShifter = new Solenoid(superShifterChannel);
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
/*            encoderLeft.setDistancePerPulse(inchesPerPulseLow);
            encoderRight.setDistancePerPulse(inchesPerPulseLow);
*/        } else {
            SmartDashboard.putString("Super shifter ", "Low");
/*            encoderLeft.setDistancePerPulse(inchesPerPulseHigh);
            encoderRight.setDistancePerPulse(inchesPerPulseHigh);
*/        }
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
    	if (left.getRawButton(OI.DRIVE_BUTTON_SPEED_SHIFT) || 
    			right.getRawButton(OI.DRIVE_BUTTON_SPEED_SHIFT)) {
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

    public void driveAtSpeed(double left, double right) {
/*    	controllerLeft.setSetpoint(left);
    	controllerRight.setSetpoint(right);
*/    }
    
    public void pidBrake() {
    	driveAtSpeed(0, 0);
    }
}

