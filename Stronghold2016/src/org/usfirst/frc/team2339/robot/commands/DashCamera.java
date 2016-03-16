package org.usfirst.frc.team2339.robot.commands;

import org.usfirst.frc.team2339.robot.subsystems.CameraSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DashCamera extends Command {
	
	CameraSystem camera;

    public DashCamera(String name, CameraSystem camera) {
		super(name);
		this.camera = camera;
        requires(camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	camera.grabImage();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
