package org.usfirst.frc.team2339.robot.components;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 * PID controller for scimitar
 * 
 * @author emiller
 *
 */
public class ScimitarPidController extends PIDController {

	private final Potentiometer scimitarPotentiometer;
	
	public ScimitarPidController(double Kp, double Ki, double Kd,
			Potentiometer scimitarPotentiometer,
			double scimitarMaxAngleDegrees, 
			SpeedController scimitarController) {
		super(Kp, Ki, Kd, scimitarPotentiometer, scimitarController);
		this.scimitarPotentiometer = scimitarPotentiometer;
        this.setInputRange(0, scimitarMaxAngleDegrees);
        this.setContinuous(false);
	}
	
	public double getScimitarAngle() {
		// TODO: Is this correct? Or use get() and we need to do distance calc ourselves.
		return scimitarPotentiometer.pidGet();
	}

}
