package org.usfirst.frc.team2339.robot.subsystems;

import org.usfirst.frc.team2339.robot.OI;
import org.usfirst.frc.team2339.robot.components.OperatorJoystick;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Climber extends Subsystem {

    private final Victor climberMotor;
    private final Solenoid solenoidUp;
    private final Solenoid solenoidDown;

    private boolean isClimberUp;
    private boolean wasClimberButtonJustPushed;
    
    
	public Climber(int climberMotorNumber, int solenoidChannelUp, int solenoidChannelDown) {
		this.climberMotor = new Victor(climberMotorNumber);
		this.solenoidUp = new Solenoid(solenoidChannelUp);
		this.solenoidDown = new Solenoid(solenoidChannelDown);
		
    	setClimber(true);
    	wasClimberButtonJustPushed = false;
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Set the intake motor
     * 
     * @param value motor speed
     */
    public void setClimberMotor(double value) {
        SmartDashboard.putNumber("Lift motor value ", value);
    	climberMotor.set(value);
    }
    
    /**
     * Stop the intake motor
     * 
     */
    public void stopClimberMotor() {
        setClimberMotor(0.0);
    }
    
    /**
     * Set climber up or down
     * @param isClimberUp true: move climber to up position. false: move climber to down position
     */
    public void setClimber(boolean isClimberUp) {
        solenoidUp.set(isClimberUp);
        solenoidDown.set(!isClimberUp);
        this.isClimberUp = isClimberUp;
        if (isClimberUp) {
            SmartDashboard.putString("Climber position ", "Up");
        } else {
            SmartDashboard.putString("Climber position ", "Down");
        }
    }
    
    /*
     * Change climber position from up to down and back
     * 
     */
    public void changeClimberPosition() {
    	setClimber(!this.isClimberUp);
    }

    
    /**
     * Move climber based on joystick input.
     * 
     * @param stick
     */
    private void takeJoystickPosition(OperatorJoystick stick) {
    	
    	boolean climberPushedNow = false;
    	if (stick.getRawButton(OI.OPERATOR_BUTTON_CLIMBER_UP_DOWN)) {
    		climberPushedNow = true;
    	}

        /*
         * Move climber up and down with button
         * Climber up-down button shifts between up and down and back again.
         * Push once, it moves climber up. Push again it moves climber down.
         * The changeClimberPosition method does the moving.
         * The wasClimberButtonJustPushed flag is used to keep from "bouncing"
         * It makes sure the button is released before asking the climber to move again.
         * Without it the climber would move every 20ms while the button is held down.
         * For example, without the flag, if the button were pushed for 1/2 second
         * the climber would try to move 25 times.
         */
        if(!wasClimberButtonJustPushed && climberPushedNow) {
        	changeClimberPosition();
            wasClimberButtonJustPushed = true;
        } else if (wasClimberButtonJustPushed && !climberPushedNow) {
            wasClimberButtonJustPushed = false;
        }
        
    }
    
    /**
     * Set climber motor based on joystick input.
     * 
     * @param stick
     */
    private void takeJoystickMotor(OperatorJoystick stick) {
    	/*
    	 * Run climber motor by moving operator stick front to back.
    	 */
    	setClimberMotor(stick.getClimberSpeed());
    }
    
    public void takeJoystickInput(OperatorJoystick stick) {
    	takeJoystickPosition(stick);
    	takeJoystickMotor(stick);
    }
    
}

