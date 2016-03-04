package org.usfirst.frc.team2339.robot.subsystems;

import org.usfirst.frc.team2339.robot.OI;
import org.usfirst.frc.team2339.robot.components.OperatorJoystick;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Scimitar extends Subsystem {

    private final Solenoid solenoidUp;
    private final Solenoid solenoidDown;

	public Scimitar(int solenoidChannelUp, int solenoidChannelDown) {
		this.solenoidUp = new Solenoid(solenoidChannelUp);
		this.solenoidDown = new Solenoid(solenoidChannelDown);
		
		moveScimitarUp();
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void moveScimitarUp() {
        solenoidUp.set(true);
        solenoidDown.set(false);
        SmartDashboard.putString("Scimitar action ", "Up");
    }
    
    public void moveScimitarDown() {
        solenoidUp.set(false);
        solenoidDown.set(true);
        SmartDashboard.putString("Scimitar action ", "Down");
    }
    
    public void stopScimitar() {
        solenoidUp.set(true);
        solenoidDown.set(true);
        SmartDashboard.putString("Scimitar action ", "Stop");
    }
    
    public void releaseScimitar() {
        solenoidUp.set(false);
        solenoidDown.set(false);
        SmartDashboard.putString("Scimitar action ", "Stop");
    }
    
    /**
     * Move scimitar based on joystick button input.
     * 
     * @param stick
     */
    private void takeJoystickPosition(OperatorJoystick stick) {
    	if (stick.getRawButton(OI.OPERATOR_BUTTON_SCIMITAR_UP)) {
    		moveScimitarUp();
    	} else if (stick.getRawButton(OI.OPERATOR_BUTTON_SCIMITAR_DOWN)) {
    		moveScimitarDown();
    	} else {
    		releaseScimitar();
    	}
    }
    
    public void takeJoystickInput(OperatorJoystick stick) {
    	takeJoystickPosition(stick);
    }
    
}

