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

    private boolean isScimitarUp;
    private boolean wasScimitarButtonJustPushed;
    
    
	public Scimitar(int solenoidChannelUp, int solenoidChannelDown) {
		this.solenoidUp = new Solenoid(solenoidChannelUp);
		this.solenoidDown = new Solenoid(solenoidChannelDown);
		
    	setScimitar(true);
    	wasScimitarButtonJustPushed = false;
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Set scimitar up or down
     * @param isScimitarUp true: move scimitar to up position. false: move scimitar to down position
     */
    public void setScimitar(boolean isScimitarUp) {
        solenoidUp.set(isScimitarUp);
        solenoidDown.set(!isScimitarUp);
        this.isScimitarUp = isScimitarUp;
        if (isScimitarUp) {
            SmartDashboard.putString("Scimitar position ", "Up");
        } else {
            SmartDashboard.putString("Scimitar position ", "Down");
        }
    }
    
    /*
     * Change scimitar position from up to down and back
     * 
     */
    public void changeScimitarPosition() {
    	setScimitar(!this.isScimitarUp);
    }

    
    /**
     * Move scimitar based on joystick input.
     * 
     * @param stick
     */
    private void takeJoystickPosition(OperatorJoystick stick) {
    	
    	boolean scimitarPushedNow = false;
    	if (stick.getRawButton(OI.OPERATOR_BUTTON_SCIMITAR_UP_DOWN)) {
    		scimitarPushedNow = true;
    	}

        /*
         * Move scimitar up and down with button
         * Scimitar up-down button shifts between up and down and back again.
         * Push once, it moves scimitar up. Push again it moves scimitar down.
         * The changeScimitarPosition method does the moving.
         * The wasScimitarButtonJustPushed flag is used to keep from "bouncing"
         * It makes sure the button is released before asking the scimitar to move again.
         * Without it the scimitar would move every 20ms while the button is held down.
         * For example, without the flag, if the button were pushed for 1/2 second
         * the scimitar would try to move 25 times.
         */
        if(!wasScimitarButtonJustPushed && scimitarPushedNow) {
        	changeScimitarPosition();
            wasScimitarButtonJustPushed = true;
        } else if (wasScimitarButtonJustPushed && !scimitarPushedNow) {
            wasScimitarButtonJustPushed = false;
        }
        
    }
    
    public void takeJoystickInput(OperatorJoystick stick) {
    	takeJoystickPosition(stick);
    }
    
}

