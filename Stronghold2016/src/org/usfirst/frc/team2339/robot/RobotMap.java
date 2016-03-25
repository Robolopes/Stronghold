package org.usfirst.frc.team2339.robot;

import org.usfirst.frc.team2339.robot.subsystems.BoulderHandler;
import org.usfirst.frc.team2339.robot.subsystems.CameraSystem;
import org.usfirst.frc.team2339.robot.subsystems.Climber;
import org.usfirst.frc.team2339.robot.subsystems.Scimitar;
import org.usfirst.frc.team2339.robot.subsystems.WesternDrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;

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
		
	};
	
	public static class Solenoids {
		public static final int SUPER_SHIFTER = 0;
		public static final int SCIMITAR_UP = 3;
		public static final int SCIMITAR_DOWN = 4;
		// No climber
		// WARNING: Shooter flap uses same solenoids. Do not enable climber unless that is changed.
		//public static final int CLIMBER_UP = 2;
		//public static final int CLIMBER_DOWN = 5;
		public static final int SHOOTER_FLAP = 1;
		//public static final int SHOOTER_FLAP_DOWN = 5;
	};
	
	public static class Analog {
		public static final BuiltInAccelerometer ACCELEROMETER = new BuiltInAccelerometer();
		public static final int GYRO_CHANNEL = 0;
	};
	
	public static class PWM {
		public static final int DRIVE_LEFT_0 = 2;
		public static final int DRIVE_LEFT_1 = 3;
		public static final int DRIVE_RIGHT_0 = 4;
		public static final int DRIVE_RIGHT_1 = 5;
		public static final int CONVEYOR = 0;
		public static final int SHOOTING_WHEEL_0 = 1;
		public static final int SHOOTING_WHEEL_1 = 6;
		public static final int CLIMBER_WINCH = 8;
	};
	
	public static class DIO {
		public static final int[] DRIVE_ENCODER_LEFT = {0, 1};
		public static final int[] DRIVE_ENCODER_RIGHT = {2, 3};
	};
	
	public static class Component {
		public static final Gyro GYRO = new AnalogGyro(Analog.GYRO_CHANNEL);
	};
    
	public static class Camera {
		// The camera name (ex "cam0") can be found through the roborio web interface
		public static final String NAME = "cam0";
	}
	
	public static class Subsystem {
	    public static WesternDrive robotDrive; 
	    public static Scimitar scimitar;
	    public static BoulderHandler boulderHandler;
	    public static Climber climber;
		public static CameraSystem cameraSystem;
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
         * Initialize boulder handler subsystem
         */
        Subsystem.boulderHandler = new BoulderHandler(PWM.CONVEYOR, PWM.SHOOTING_WHEEL_0, PWM.SHOOTING_WHEEL_1, 
        		Solenoids.SHOOTER_FLAP);
        
        /*
         * Initialize climber subsystem
         */
        Subsystem.scimitar = new Scimitar(Solenoids.SCIMITAR_UP, Solenoids.SCIMITAR_DOWN);
        
        /*
         * Initialize climber subsystem
         */
        //Subsystem.climber = new Climber(PWM.CLIMBER_WINCH, Solenoids.CLIMBER_UP, Solenoids.CLIMBER_DOWN);
        // No climber
        Subsystem.climber = null;
        
		Subsystem.cameraSystem = new CameraSystem(Camera.NAME);
    }

}
