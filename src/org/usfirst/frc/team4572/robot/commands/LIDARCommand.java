package org.usfirst.frc.team4572.robot.commands;

import org.usfirst.frc.team4572.robot.Robot;
import org.usfirst.frc.team4572.robot.subsystems.LIDARSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LIDARCommand extends Command {

	
    public LIDARCommand() {
        // Use requires() here to declare subsystem dependencies
       requires(Robot.lidarSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	LIDARSubsystem.updateLIDAR();
    	System.out.println(LIDARSubsystem.getDistanceCm());
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
