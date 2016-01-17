package org.usfirst.frc.team2339.robot.components;

import edu.wpi.first.wpilibj.Encoder;

public class DriveEncoder extends Encoder {

	/**
	 * Construct a swerve steering encoder
	 * 
	 * @param aChannel Encoder first channel number
	 * @param bChannel Encoder second channel number
	 * @param inchesPerPulse Distance wheel edge travels between pulses in inches.
	 */
	public DriveEncoder(int aChannel, int bChannel, double inchesPerPulse) {
		super(aChannel, bChannel);
        this.setDistancePerPulse(inchesPerPulse);
	}
}
