package org.usfirst.frc.team2339.robot.commands;

import org.usfirst.frc.team2339.robot.subsystems.Scimitar;

import edu.wpi.first.wpilibj.command.Command;

public class MoveScimitar extends Command {
	/**
	 * Runs lift based on operator input
	 */
	
	private final Scimitar scimitar;
	private final boolean moveUp;
	private boolean moveDone;

	/**
	 * 
	 * @param name Name of command
	 * @param scimitar scimitar subsystem
	 */
	public MoveScimitar(String name, Scimitar scimitar, boolean moveUp) {
		super(name);
        requires(scimitar);
        this.scimitar = scimitar;
        this.moveUp = moveUp;
        this.moveDone = false;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (moveUp) {
			scimitar.moveScimitarUp();
		} else {
			scimitar.moveScimitarDown();
		}
        moveDone = true;
	}

	@Override
	protected boolean isFinished() {
		return moveDone;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
