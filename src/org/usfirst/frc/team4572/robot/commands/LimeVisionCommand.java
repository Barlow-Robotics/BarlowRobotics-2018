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
		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.logitech.getRawButtonReleased(8)) {
    		Robot.limeVisionSubsystem.setPipeline(1.0);
    		
    	}
    	if(OI.logitech.getRawButtonPressed(10)) {
    		Robot.limeVisionSubsystem.switchLED();
    	}
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
    }
}
