package org.usfirst.frc.team2339.robot.components;

import edu.wpi.first.wpilibj.PIDController;

/**
 * PID controller for scimitar
 * 
 * @author emiller
 *
 */
public class DrivePidController extends PIDController {

	private final DriveEncoder driveEncoder;
	
	public DrivePidController(double Kp, double Ki, double Kd,
			DriveEncoder driveEncoder,
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
