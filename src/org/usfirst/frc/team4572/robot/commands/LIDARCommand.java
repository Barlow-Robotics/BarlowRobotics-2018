package org.usfirst.frc.team4572.robot.commands;

import org.usfirst.frc.team4572.robot.Robot;
import org.usfirst.frc.team4572.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LIDARCommand extends Command{

    public LIDARCommand() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.lidarSubsystem);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lidarSubsystem.initLIDAR(new DigitalInput(RobotMap.LIDAR_PORT));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(Robot.lidarSubsystem.getDistanceIn(true));

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
