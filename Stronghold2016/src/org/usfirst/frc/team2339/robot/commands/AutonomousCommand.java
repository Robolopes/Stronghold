package org.usfirst.frc.team2339.robot.commands;

import org.usfirst.frc.team2339.robot.subsystems.BoulderHandler;
import org.usfirst.frc.team2339.robot.subsystems.Scimitar;
import org.usfirst.frc.team2339.robot.subsystems.WesternDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Commands to run in autonomous mode
 */
public class AutonomousCommand extends CommandGroup {
    
    public  AutonomousCommand(WesternDrive robotDrive, Scimitar scimitar, BoulderHandler boulderHandler) {
    	super("Autonomous Commands");
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	//addSequential(new MoveScimitar("Auto lower scimitar", scimitar, false));
    	addSequential(new TimedDrive("Drive to auto zone", robotDrive, 
    			//SmartDashboard.getNumber("Auto drive time ", 1.50),
    			3.0, 
    			SmartDashboard.getNumber("Auto drive speed ", 1.0)));
    }
}
