package org.usfirst.frc.team2339.robot.components;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Talon;

/**
 * Controller that drives dual motors, one forward and one reverse, such
 * as used by one side of Western drive.
 * 
 * @author emiller
 *
 */
public class DualTalonController implements PIDOutput {
	
	private final Talon motorForward;
	private final Talon motorBackward;

	public DualTalonController(Talon motorForward, Talon motorBackward) {
		super();
		this.motorForward = motorForward;
		this.motorBackward = motorBackward;
	}

	@Override
	public void pidWrite(double output) {
		motorForward.set(output);
		motorBackward.set(-output);
	}

}
