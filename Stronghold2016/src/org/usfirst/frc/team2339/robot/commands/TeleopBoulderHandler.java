package org.usfirst.frc.team2339.robot.commands;

import org.usfirst.frc.team2339.robot.components.OperatorJoystick;
import org.usfirst.frc.team2339.robot.subsystems.BoulderHandler;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopBoulderHandler extends Command {
	/**
	 * Runs lift based on operator input
	 */
	
	private final BoulderHandler boulderHandler;
	private final OperatorJoystick boulderHandlerStick;

	/**
	 * 
	 * @param name Name of command
	 * @param boulderHandler boulder handler subsystem
	 */
	public TeleopBoulderHandler(String name, BoulderHandler boulderHandler, OperatorJoystick boulderHandlerStick) {
		super(name);
        requires(boulderHandler);
        this.boulderHandler = boulderHandler;
        this.boulderHandlerStick = boulderHandlerStick;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
    	boulderHandler.takeJoystickInput(boulderHandlerStick, timeSinceInitialized());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		boulderHandler.stopConveyorMotor();
		boulderHandler.stopShooterMotors();
	}

	@Override
	protected void interrupted() {
		boulderHandler.stopConveyorMotor();
		boulderHandler.stopShooterMotors();
	}

}
