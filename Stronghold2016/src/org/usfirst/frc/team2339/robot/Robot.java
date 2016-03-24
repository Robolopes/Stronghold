
package org.usfirst.frc.team2339.robot;

import org.usfirst.frc.team2339.robot.commands.AutonomousCommand;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */

    // Control operator interface
    public static OI oi;

    // Commands
    private AutonomousCommand autonomousCommand;

    Compressor compressor = new Compressor(0);
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        
    	/*
    	 * Initialize robot components and subsystems
    	 */
    	RobotMap.init();
    	
        // OI must be constructed after subsystems. If the OI creates Commands 
        //(which it very likely will), subsystems are not guaranteed to be 
        // constructed yet. Thus, their requires() statements may grab null 
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // Autonomous dashboard values
        try {
            SmartDashboard.getNumber("Auto drive time ");
            SmartDashboard.getNumber("Auto drive speed ");
        } catch (TableKeyNotDefinedException e) {
            SmartDashboard.putNumber("Auto drive time ", 3.0);
            SmartDashboard.putNumber("Auto drive speed ", 1.0);
        }

        /*
         * TODO: Figure out what a SendableChooser does
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new AutonomousCommand(RobotMap.Subsystem.robotDrive, RobotMap.Subsystem.lift));
        SmartDashboard.putData("Auto mode", chooser);
         */
        
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	compressor.setClosedLoopControl(false);
       	RobotMap.Subsystem.robotDrive.stop();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	
    	compressor.setClosedLoopControl(true);
    	
        // Autonomous command
        autonomousCommand = new AutonomousCommand(RobotMap.Subsystem.robotDrive, RobotMap.Subsystem.scimitar, 
        		RobotMap.Subsystem.boulderHandler);
    	
    	// schedule the autonomous command
        if (autonomousCommand != null) autonomousCommand.start();
        
        // Camera
        oi.getDashCamera().start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //if (autonomousCommand != null) autonomousCommand.cancel();
        
    	compressor.setClosedLoopControl(true);
    	RobotMap.Subsystem.robotDrive.shift(false);
        oi.getTeleopDrive().start();
        oi.getTeleopScimitar().start();
        oi.getTeleopBoulderHandler().start();
        // No climber
        //oi.getTeleopClimber().start();
        oi.getDashCamera().start();
        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
