
package org.usfirst.frc.team2339.robot;

import org.usfirst.frc.team2339.robot.commands.AutonomousCommand;
import org.usfirst.frc.team2339.robot.swervemath.SwerveWheel.VelocityPolar;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;


/**
 * The JVM (Java Virtual Machine) on the roboRio is configured to automatically run this class, 
 * and to call the functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */

    int session;
    Image frame;

    // Control operator interface
    public static OI oi;

    // Commands
    private AutonomousCommand autonomousCommand;
    
      
    /**
     * This method is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {

    	   frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

           // the camera name (ex "cam0") can be found through the roborio web interface
           session = NIVision.IMAQdxOpenCamera("cam0",
                   NIVision.IMAQdxCameraControlMode.CameraControlModeController);
           NIVision.IMAQdxConfigureGrab(session);
           
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
            SmartDashboard.getNumber("Auto lift time ");
            SmartDashboard.getNumber("Auto lift speed ");
            SmartDashboard.getNumber("Auto drive time ");
            SmartDashboard.getNumber("Auto drive speed ");
            SmartDashboard.getNumber("Auto drive direction ");
        } catch (TableKeyNotDefinedException e) {
            SmartDashboard.putNumber("Auto lift time ", 1);
            SmartDashboard.putNumber("Auto lift speed ", 0.5);
            SmartDashboard.putNumber("Auto drive time ", 0.0);
            SmartDashboard.putNumber("Auto drive speed ", 0.5);
            SmartDashboard.putNumber("Auto drive direction ", 90);
        }
        
    }
    

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	// Zero wheels
       	RobotMap.Subsystem.robotDrive.swerveDriveRobot(new VelocityPolar(0.0, 0.0));
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This method is called at the beginning of autonomous period
     */
    public void autonomousInit() {
       	RobotMap.Analog.GYRO.reset();
       	//RobotMap.Subsystem.robotDrive.resetSteering();
       	RobotMap.Subsystem.robotDrive.enableSteering(true);
        
        // Autonomous command
        autonomousCommand = new AutonomousCommand(RobotMap.Subsystem.robotDrive, RobotMap.Subsystem.lift);
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This method is called at the beginning of operator control
     */
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //if (autonomousCommand != null) autonomousCommand.cancel();
        
    	RobotMap.Analog.GYRO.reset();
    	//RobotMap.Subsystem.robotDrive.resetSteering();
    	RobotMap.Subsystem.robotDrive.enableSteering(true);
        oi.getTeleopDrive().start();
        oi.getTeleopLift().start();
        
        NIVision.IMAQdxStartAcquisition(session);

        /**
         * grab an image, draw the circle, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */
        NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);

        while (isOperatorControl() && isEnabled()) {

            NIVision.IMAQdxGrab(session, frame, 1);
            NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                    DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
            
            CameraServer.getInstance().setImage(frame);

            /** robot code here! **/
            Timer.delay(0.005);		// wait for a motor update time
        }
        NIVision.IMAQdxStopAcquisition(session);
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
