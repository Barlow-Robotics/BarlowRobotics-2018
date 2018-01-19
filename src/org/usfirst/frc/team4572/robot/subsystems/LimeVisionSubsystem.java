package org.usfirst.frc.team4572.robot.subsystems;

import org.usfirst.frc.team4572.robot.commands.LimeVisionCommand;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LimeVisionSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	NetworkTable table = NetworkTable.getTable("limelight");
	double targetOffsetAngle_Horizontal = table.getNumber("tx", 0);
	double targetOffsetAngle_Vertical = table.getNumber("ty", 0);
	double targetArea = table.getNumber("ta", 0);
	double targetSkew = table.getNumber("ts", 0);
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LimeVisionCommand());
    }
    
    public void retrieveVision() {
    	
    	targetOffsetAngle_Horizontal = table.getNumber("tx", 0);
    	targetOffsetAngle_Vertical = table.getNumber("ty", 0);
    	targetArea = table.getNumber("ta", 0);
    	targetSkew = table.getNumber("ts", 0);
    }
    
}

