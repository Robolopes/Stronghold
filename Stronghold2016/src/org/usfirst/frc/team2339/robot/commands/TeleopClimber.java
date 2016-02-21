package org.usfirst.frc.team2339.robot.commands;

import org.usfirst.frc.team2339.robot.OI;
import org.usfirst.frc.team2339.robot.components.OperatorJoystick;
import org.usfirst.frc.team2339.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopClimber extends Command {
	/**
	 * Runs lift based on operator input
	 */
	
	private final Climber climber;
	private final OperatorJoystick climberStick;

	/**
	 * 
	 * @param name Name of command
	 * @param climber climber subsystem
	 */
	public TeleopClimber(String name, Climber climber, OperatorJoystick climberStick) {
		super(name);
        requires(climber);
        this.climber = climber;
        this.climberStick = climberStick;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		// Don't send commands to climber until late in match
		if (timeSinceInitialized() > OI.CLIMBER_TELEOP_TIME_DISABLE_SECONDS) {
			climber.takeJoystickInput(climberStick);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		climber.stopClimberMotor();
	}

	@Override
	protected void interrupted() {
		climber.stopClimberMotor();
	}

}
