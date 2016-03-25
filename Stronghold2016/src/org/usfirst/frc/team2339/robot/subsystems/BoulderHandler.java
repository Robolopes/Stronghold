package org.usfirst.frc.team2339.robot.subsystems;

import org.usfirst.frc.team2339.robot.OI;
import org.usfirst.frc.team2339.robot.components.OperatorJoystick;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BoulderHandler extends Subsystem {

    private final Talon conveyorMotor;
    private final Talon shooterMotor0;
    private final Talon shooterMotor1;
    private final Solenoid flapSolenoid;
    
    private Double timeShooterButtonFirstPushed = null;

	public BoulderHandler(int conveyorMotorNumber, int shooterMotorNumber0, int shooterMotorNumber1, 
			int flapSolenoidChannel) {
		this.conveyorMotor = new Talon(conveyorMotorNumber);
		this.shooterMotor0 = new Talon(shooterMotorNumber0);
		this.shooterMotor1 = new Talon(shooterMotorNumber1);
		this.flapSolenoid = new Solenoid(flapSolenoidChannel);
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Set the conveyor motor
     * 
     * @param value motor speed
     */
    public void setConveyorMotor(double value) {
        SmartDashboard.putString("DB/String 2", String.format("Conveyor motor value %.2f", value));
    	conveyorMotor.set(value);
    }
    
    /**
     * Stop the conveyor motor
     * 
     */
    public void stopConveyorMotor() {
        setConveyorMotor(0.0);
    }
    
    /**
     * Set the shooter motors
     * 
     * @param value motor speed
     */
    public void setShooterMotors(double value) {
        SmartDashboard.putNumber("Shooter motor value ", value);
    	shooterMotor0.set(-value);
    	shooterMotor1.set(value);
    }
    
    /**
     * Stop the shooter motors
     * 
     */
    public void stopShooterMotors() {
        setShooterMotors(0.0);
    }
    
    public void shoot(double currentTimeSeconds) {
		setShooterMotors(OI.SHOOTER_SPEED);
		if (timeShooterButtonFirstPushed == null) {
			timeShooterButtonFirstPushed = currentTimeSeconds;
			flapSolenoid.set(true);
		} else {
			if (currentTimeSeconds - timeShooterButtonFirstPushed < OI.SHOOTER_TIME_TO_SPIN_UP_SECONDS) {
				// Don't feed balls at first, give shooter motors time to come up to speed
				//stopConveyorMotor();
			} else {
				// Start feeding balls after alloted wait time.
				//setConveyorMotor(OI.CONVERYOR_INTAKE_SPEED);
			}
		}
    }
    
    public void stopShooting() {
		stopShooterMotors();
		flapSolenoid.set(false);
		timeShooterButtonFirstPushed = null;
    }
    
    /**
     * Set conveyor motor based on joystick input
     * 
     * @param stick
     */
    private void takeJoystickConveyor(OperatorJoystick stick) {
    	if (stick.getRawButton(OI.OPERATOR_BUTTON_CONVERYOR_INTAKE)) {
    		setConveyorMotor(OI.CONVERYOR_INTAKE_SPEED);
    	} else if (stick.getRawButton(OI.OPERATOR_BUTTON_CONVERYOR_REVERSE)) {
    		setConveyorMotor(OI.CONVERYOR_REVERSE_SPEED);
    	} else {
    		stopConveyorMotor();
    	}
    }
    
    /**
     * Set shooter motors based on joystick input.
     * 
     * @param stick
     */
    private void takeJoystickShooter(OperatorJoystick stick, double currentTimeSeconds) {
    	
    	if (stick.getRawButton(OI.OPERATOR_BUTTON_SHOOT)) {
    		shoot(currentTimeSeconds);
    	} else {
    		stopShooting();
    	}

    }
    
    /**
     * Accepts operator stick and time to control boulder handling
     * (time helps with shooter motor vs. conveyor motor timing)
     * 
     * @param stick
     * @param currentTimeSeconds
     */
    public void takeJoystickInput(OperatorJoystick stick, double currentTimeSeconds) {
    	takeJoystickShooter(stick, currentTimeSeconds);
    	takeJoystickConveyor(stick);
    }
    
}

