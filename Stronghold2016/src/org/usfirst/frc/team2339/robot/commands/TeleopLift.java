package org.usfirst.frc.team2339.robot.commands;

import org.usfirst.frc.team2339.robot.components.OperatorJoystick;
import org.usfirst.frc.team2339.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopLift extends Command {
	/**
	 * Runs lift based on operator input
	 */
	
	private final Climber lift;
	private final OperatorJoystick liftStick;

	/**
	 * 
	 * @param name Name of command
	 * @param lift lift subsystem
	 */
	public TeleopLift(String name, Climber lift, OperatorJoystick liftStick) {
		super(name);
        requires(lift);
        this.lift = lift;
        this.liftStick = liftStick;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
    	lift.setLiftMotor(liftStick.getLift());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		lift.stopLiftMotor();
	}

	@Override
	protected void interrupted() {
		lift.stopLiftMotor();
	}

}
