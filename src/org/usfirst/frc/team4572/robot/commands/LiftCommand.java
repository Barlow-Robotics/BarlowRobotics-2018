package org.usfirst.frc.team4572.robot.commands;

import org.usfirst.frc.team4572.robot.OI;
import org.usfirst.frc.team4572.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftCommand extends Command {
	private final double speed = 0.5;
	
	
    public LiftCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.liftSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(OI.logitech.getRawButton(6)) { //Move up if button 6 is pressed
    	Robot.liftSubsystem.activateLift(speed);
    	}
    	else if(OI.logitech.getRawButton(7)) { //Move down if button 7 pressed
        Robot.liftSubsystem.activateLift(-speed);
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
