package org.usfirst.frc.team2339.robot.subsystems;

import org.usfirst.frc.team2339.robot.OI;
import org.usfirst.frc.team2339.robot.components.OperatorJoystick;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Scimitar extends Subsystem {

    private final DoubleSolenoid solenoid;

	public Scimitar(int solenoidChannelUp, int solenoidChannelDown) {
		this.solenoid = new DoubleSolenoid(solenoidChannelUp, solenoidChannelDown);
		
		moveScimitarUp();
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void moveScimitarUp() {
        solenoid.set(Value.kForward);
        SmartDashboard.putString("Scimitar action ", "Up");
    }
    
    public void moveScimitarDown() {
        solenoid.set(Value.kReverse);
        SmartDashboard.putString("Scimitar action ", "Down");
    }
    
    public void stopScimitar() {
        solenoid.set(Value.kOff);
        SmartDashboard.putString("Scimitar action ", "Stop");
    }
    
    public void releaseScimitar() {
        solenoid.set(Value.kOff);
        SmartDashboard.putString("Scimitar action ", "Release");
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

