package org.usfirst.frc.team2339.robot.components;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;

/**
 * PID controller for scimitar
 * 
 * @author emiller
 *
 */
public class DrivePidController extends PIDController {

	private final Encoder driveEncoder;
	
	public DrivePidController(double Kp, double Ki, double Kd,
			Encoder driveEncoder,
			DualTalonController driveMotorController) {
		super(Kp, Ki, Kd, driveEncoder, driveMotorController);
		this.driveEncoder = driveEncoder;
        this.setInputRange(-1, 1);
        this.setContinuous(false);
	}
	
	public double getDriveDistance() {
		return driveEncoder.getDistance();
	}
	
}
