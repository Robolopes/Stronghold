package org.usfirst.frc.team2339.robot;

import org.usfirst.frc.team2339.robot.subsystems.Lift;
import org.usfirst.frc.team2339.robot.subsystems.WesternDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Solenoid;

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

	
	public static class Solenoids {
		public static final int SUPER_SHIFTER = 1;
	};
	
	public static class PWM {
		public static final int DRIVE_LEFT_0 = 1;
		public static final int DRIVE_LEFT_1 = 2;
		public static final int DRIVE_RIGHT_0 = 3;
		public static final int DRIVE_RIGHT_1 = 4;
		public static final int LIFT_WINCH = 5;
	};
	
	public static class DIO {
		public static final int LIFT_LOWER_LIMIT_SWITCH = 1;
	};
	
	public static class Subsystem {
	    public static WesternDrive robotDrive; 
	    public static Lift lift;
	};
    
	/**
	 * Initialize subsystems and components based on RobotMap values
	 */
    public static void init() {
    	
    	/*
    	 * Initialize robot drive subsystem
    	 */
    	RobotDrive baseRobotDrive = new RobotDrive(PWM.DRIVE_LEFT_0, PWM.DRIVE_LEFT_1, PWM.DRIVE_RIGHT_0, PWM.DRIVE_RIGHT_1);
    	/*
    	 * Invert motors because of Western Drive.
    	 * Thus should be necessary if all motors are wired in the same direction
    	 * WARNING: Carefully test before applying power to all motors to make sure they turn the same way.
    	 *          Always test one motor per side before wiring both to avoid destroying the transmission.
    	 */
    	baseRobotDrive.setInvertedMotor(MotorType.kRearLeft, true);
    	baseRobotDrive.setInvertedMotor(MotorType.kRearRight, true);
        Subsystem.robotDrive = new WesternDrive(
        		baseRobotDrive, 
        		new Solenoid(Solenoids.SUPER_SHIFTER));

        /*
         * Initialize lift subsystem
         */
        Subsystem.lift = new Lift(PWM.LIFT_WINCH, DIO.LIFT_LOWER_LIMIT_SWITCH);
        
    }

}
