package org.usfirst.frc.team4572.robot.commands;

import org.usfirst.frc.team4572.robot.OI;
import org.usfirst.frc.team4572.robot.Robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LimeVisionCommand extends Command {

    public LimeVisionCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.limeVisionSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.limeVisionSubsystem.getLEDMode() == 1) {
    		Robot.limeVisionSubsystem.switchLED();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.logitech.getRawButtonPressed(8)) {
    		Robot.limeVisionSubsystem.setPipeline(1.0);
    		
    	}
    	if(OI.logitech.getRawButtonPressed(10)) {
    		Robot.limeVisionSubsystem.switchLED();
    	}
    	if(OI.logitech.getRawButtonPressed(11)) {
    		if(Robot.limeVisionSubsystem.getPipeline() == 0)
    		Robot.limeVisionSubsystem.setPipeline(1);
    		else {
        	Robot.limeVisionSubsystem.setPipeline(0);
    		}
    	}
    	//System.out.println(Robot.limeVisionSubsystem.getXOffset());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	if(Robot.limeVisionSubsystem.getLEDMode() == 0) {
    		Robot.limeVisionSubsystem.switchLED();
    	}
    }
}
