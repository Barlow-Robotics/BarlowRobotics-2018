package org.usfirst.frc.team4572.robot.commands;

import org.usfirst.frc.team4572.robot.OI;
import org.usfirst.frc.team4572.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ClawCommand extends Command {

    public ClawCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.clawSubsystem);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Be sure to set bounds this way in order to use the linear actuator properly!
		Robot.clawSubsystem.clawActuator1.setBounds(2.0, 2.0, 1.5, 1.0, 1.0);
		Robot.clawSubsystem.clawActuator2.setBounds(2.0, 2.0, 1.5, 1.0, 1.0);

    }
    double oldextension = 10000;
    double extension = 0;
    double rate = 0.003;
    double speed = 1;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	extension = SmartDashboard.getNumber("Claw Extension", extension);
    	
    	if(OI.playstation.getRawButton(5)) { //Move up if button 5 is pressed
    		if(!(extension >= 1))
    		extension += rate;
    	}
    	else if(OI.playstation.getRawButton(6)) { //Move down if button 6 pressed
    		if(!(extension <= 0.11))
    		extension -= rate;
    	}  
	else if(OI.playstation.getRawButton(7)) { //Move down if button 6 pressed
		extension = 0.7;
	}   
    	rate = SmartDashboard.getNumber("Claw Rate", rate);
    	SmartDashboard.putNumber("Claw Rate", rate);
    	SmartDashboard.putNumber("Claw Extension", extension);

//    	Robot.clawSubsystem.clawActuator.setSpeed(speed);

    	if(oldextension != extension) {
    	Robot.clawSubsystem.actuateClaw(extension);
    	oldextension = extension;
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
