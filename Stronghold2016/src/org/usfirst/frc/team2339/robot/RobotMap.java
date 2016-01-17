package org.usfirst.frc.team2339.robot;

import org.usfirst.frc.team2339.robot.components.DriveEncoder;
import org.usfirst.frc.team2339.robot.components.DrivePidController;
import org.usfirst.frc.team2339.robot.components.DualTalonController;
import org.usfirst.frc.team2339.robot.subsystems.Lift;
import org.usfirst.frc.team2339.robot.subsystems.WesternDrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;

	
	public static class Constants {
		
		/*
		 * Test and set appropriate values
		 */
		public static final double DRIVE_INCHES_PER_PULSE = 0.1;
		public static final double DRIVE_PID_P = 0.02;
		public static final double DRIVE_PID_I = 0;
		public static final double DRIVE_PID_D = 0;
		
		/*
		 * Test and set appropriate values
		 */
		public static final double SCIMITAR_MAXIMUM_ANGLE_DEGREES = 135;
		public static final double SCIMITAR_PID_P = 0.02;
		public static final double SCIMITAR_PID_I = 0;
		public static final double SCIMITAR_PID_D = 0;
		
	};
	
	public static class Solenoids {
		public static final int SUPER_SHIFTER = 1;
	};
	
	public static class Analog {
		public static final int GYRO_CHANNEL = 0;
		public static final int SCIMITAR_POTENTIOMETER_CHANNEL = 1;
	};
	
	public static class PWM {
		public static final int DRIVE_LEFT_0 = 0;
		public static final int DRIVE_LEFT_1 = 1;
		public static final int DRIVE_RIGHT_0 = 2;
		public static final int DRIVE_RIGHT_1 = 3;
		public static final int SCIMITAR = 4;
		public static final int CONVEYOR = 5;
		public static final int SHOOTING_WHEEL = 6;
		public static final int LIFT_WINCH = 7;
	};
	
	public static class DIO {
		public static final int[] DRIVE_ENCODER_LEFT = {0, 1};
		public static final int[] DRIVE_ENCODER_RIGHT = {2, 3};
		/*
		 * These assume Ping-Response sensors like Devantech SRF04 or VEX Ultrasonic Rangefinder.
		 * If we use analog sensors attach to analog
		 * See http://wpilib.screenstepslive.com/s/3120/m/7912/l/85774-measuring-robot-distance-to-a-surface-using-ultrasonic-sensors
		 */
		public static final int[] ULTRASONIC_RIGHT = {4, 5};
		public static final int[] ULTRASONIC_LEFT = {6, 7};
		public static final int SCIMITAR_LOWER_LIMIT_SWITCH = 8;
	};
	
	public static class Component {
		public static final Gyro GYRO = new AnalogGyro(Analog.GYRO_CHANNEL);
		public static final Potentiometer SCIMITAR_POTENTIOMETER = 
				new AnalogPotentiometer(Analog.SCIMITAR_POTENTIOMETER_CHANNEL);
	};
    
	public static class Subsystem {
	    public static WesternDrive robotDrive; 
	    public static Lift lift;
	};
    
    /**
	 * Create a drive wheel controller
	 * 
     * @param driveEncoderChannelA First steering encoder DIO channel 
     * @param driveEncoderChannelB Second steering encoder DIO channel
     * @param driveMotorControllerPwm Steering motor controller PWM channel
     * @return new drive wheel controller
     */
    public static DrivePidController newDriveController(
    		int driveEncoderChannelA, 
    		int driveEncoderChannelB, 
    		DualTalonController driveMotorController) {
    	
    	return new DrivePidController(
    			Constants.DRIVE_PID_P, 
    			Constants.DRIVE_PID_I, 
    			Constants.DRIVE_PID_D, 
    			new DriveEncoder(driveEncoderChannelA, 
    					driveEncoderChannelB, 
    					Constants.DRIVE_INCHES_PER_PULSE), 
    			driveMotorController);
    }

	/**
	 * Initialize subsystems and components based on RobotMap values
	 */
    public static void init() {
    	
    	/*
    	 * Initialize robot drive subsystem
    	 */
		Talon driveLeft0 = new Talon(PWM.DRIVE_LEFT_0);
		Talon driveLeft1 = new Talon(PWM.DRIVE_LEFT_1);
		Talon driveRight0 = new Talon(PWM.DRIVE_RIGHT_0);
		Talon driveRight1 = new Talon(PWM.DRIVE_RIGHT_1);
    	RobotDrive baseRobotDrive = new RobotDrive(driveLeft0, driveLeft1, driveRight0, driveRight1);
    	/*
    	 * Invert motors because of Western Drive.
    	 * Thus should be necessary if all motors are wired in the same direction
    	 * WARNING: Carefully test before applying power to all motors to make sure they turn the same way.
    	 *          Always test one motor per side before wiring both to avoid destroying the transmission.
    	 */
    	baseRobotDrive.setInvertedMotor(MotorType.kRearLeft, true);
    	baseRobotDrive.setInvertedMotor(MotorType.kRearRight, true);
    	
    	DrivePidController driveControllerLeft = newDriveController(
    			DIO.DRIVE_ENCODER_LEFT[0], 
    			DIO.DRIVE_ENCODER_LEFT[1],
    			new DualTalonController(driveLeft0, driveLeft1));
    	DrivePidController driveControllerRight = newDriveController(
    			DIO.DRIVE_ENCODER_RIGHT[0], 
    			DIO.DRIVE_ENCODER_RIGHT[1],
    			new DualTalonController(driveRight0, driveRight1));
    	
        Subsystem.robotDrive = new WesternDrive(
        		baseRobotDrive, 
        		new Solenoid(Solenoids.SUPER_SHIFTER));

        /*
         * Initialize lift subsystem
         */
        Subsystem.lift = new Lift(PWM.LIFT_WINCH, DIO.SCIMITAR_LOWER_LIMIT_SWITCH);
        
    }

}
