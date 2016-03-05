package org.usfirst.frc.team2339.robot.commands;

import org.usfirst.frc.team2339.robot.subsystems.WesternDrive;

import edu.wpi.first.wpilibj.command.Command;

public class TimedDrive extends Command {
	/**
	 * Drives for a given time in at a given speed and direction
	 */
	
	private final WesternDrive robotDrive;
	private final double time;
	private final double speed;
	private final boolean useHighGear;

	/**
	 * 
	 * @param name Name of command
	 * @param robotDrive Robot drive subsystem
	 * @param time Time to drive (in seconds)
	 * @param speed Speed to drive at [0, 1]
	 * @param useHighGear true: use high gear. false: use low gear.
	 * @param direction Direction to drive [-180, 180]. Zero is forward.
	 */
	public TimedDrive(String name, WesternDrive robotDrive, double time, double speed, boolean useHighGear) {
		super(name);
        requires(robotDrive);
        this.robotDrive = robotDrive;
		this.time = time;
		this.speed = speed;
		this.useHighGear = useHighGear;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		robotDrive.shift(useHighGear);
		robotDrive.takeSpeedInput(speed, speed);
	}

	@Override
	protected boolean isFinished() {
		return timeSinceInitialized() >= time;
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
