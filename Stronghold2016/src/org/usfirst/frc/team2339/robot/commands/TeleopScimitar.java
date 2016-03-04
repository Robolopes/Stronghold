package org.usfirst.frc.team2339.robot.commands;

import org.usfirst.frc.team2339.robot.components.OperatorJoystick;
import org.usfirst.frc.team2339.robot.subsystems.Scimitar;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopScimitar extends Command {
	/**
	 * Runs lift based on operator input
	 */
	
	private final Scimitar scimitar;
	private final OperatorJoystick scimitarStick;

	/**
	 * 
	 * @param name Name of command
	 * @param scimitar scimitar subsystem
	 */
	public TeleopScimitar(String name, Scimitar scimitar, OperatorJoystick scimitarStick) {
		super(name);
        requires(scimitar);
        this.scimitar = scimitar;
        this.scimitarStick = scimitarStick;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
    	scimitar.takeJoystickInput(scimitarStick);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
