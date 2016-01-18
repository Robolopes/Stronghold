package org.usfirst.frc.team2339.robot;

import org.usfirst.frc.team2339.robot.subsystems.Lift;
import org.usfirst.frc.team2339.robot.subsystems.WesternDrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
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
		public static final double DRIVE_INCHES_PER_PULSE_LOW = 0.1;
		public static final double DRIVE_INCHES_PER_PULSE_HIGH = 0.2;
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
		public static final BuiltInAccelerometer ACCELEROMETER = new BuiltInAccelerometer();
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
	 * Initialize subsystems and components based on RobotMap values
	 */
    public static void init() {
    	
        Subsystem.robotDrive = new WesternDrive(
        		PWM.DRIVE_LEFT_0, PWM.DRIVE_LEFT_1, 
        		PWM.DRIVE_RIGHT_0, PWM.DRIVE_RIGHT_1, 
        		DIO.DRIVE_ENCODER_LEFT, DIO.DRIVE_ENCODER_RIGHT, 
        		Constants.DRIVE_INCHES_PER_PULSE_LOW, 
        		Constants.DRIVE_INCHES_PER_PULSE_HIGH, 
        		Solenoids.SUPER_SHIFTER);

        /*
         * Initialize lift subsystem
         */
        Subsystem.lift = new Lift(PWM.LIFT_WINCH, DIO.SCIMITAR_LOWER_LIMIT_SWITCH);
        
    }

}
