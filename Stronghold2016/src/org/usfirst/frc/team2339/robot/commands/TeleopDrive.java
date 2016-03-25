package org.usfirst.frc.team2339.robot.commands;

import org.usfirst.frc.team2339.robot.components.DriveJoystick;
import org.usfirst.frc.team2339.robot.subsystems.WesternDrive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopDrive extends Command {
	/**
	 * Drives based on joystick input
	 */
	
	private final WesternDrive robotDrive;
	private final DriveJoystick driveStickLeft;
	private final DriveJoystick driveStickRight;

	/**
	 * 
	 * @param name Name of command
	 * @param robotDrive Robot drive subsystem
	 * @param driveStick joystick used to drive robot in teleop
	 */
	public TeleopDrive(String name, WesternDrive robotDrive, DriveJoystick driveStickLeft, DriveJoystick driveStickRight) {
		super(name);
        requires(robotDrive);
        this.robotDrive = robotDrive;
        this.driveStickLeft = driveStickLeft;
        this.driveStickRight = driveStickRight;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		/*
		 * Print out significant changes in drive info
		 */
		SmartDashboard.putString("DB/String 0", String.format("Joystick left %.2f", driveStickLeft.getFrontBack()));
		SmartDashboard.putString("DB/String 1", String.format("Joystick right %.2f", driveStickRight.getFrontBack()));
          
        robotDrive.takeJoystickInput(driveStickLeft, driveStickRight);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		robotDrive.stop();
	}

	@Override
	protected void interrupted() {
		robotDrive.stop();
	}

}
