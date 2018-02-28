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
    	extension = minExtension;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Be sure to set bounds this way in order to use the linear actuator properly!
		Robot.clawSubsystem.clawActuator1.setBounds(2.0, 2.0, 1.5, 1.0, 1.0);
		Robot.clawSubsystem.clawActuator2.setBounds(2.0, 2.0, 1.5, 1.0, 1.0);

    }
    double oldextension = 10000;
    double extension = 0;
    double minExtension = 0.0;
    double maxExtension = 0.9;
    double rate = 0.01;
    double speed = 1;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	extension = SmartDashboard.getNumber("Claw Extension", extension);
    	maxExtension = SmartDashboard.getNumber("Claw Max", maxExtension);
    	minExtension = SmartDashboard.getNumber("Claw Min", minExtension);
    	
    	if(Math.abs(OI.logitech.getY()) > 0.02) {
    		Robot.clawSubsystem.extendClaw(OI.logitech.getY() * 0.5);
    	} else {
    		Robot.clawSubsystem.extendClaw(0.0);
    	}

    	if(OI.logitech.getRawButton(3) && !(Robot.clawSubsystem.clawActuator1.getPosition() > maxExtension + 0.05)) { //Move up if trigger pressed
    		if(extension <= maxExtension)
    		extension += rate;
    	}
    	else if(OI.logitech.getRawButton(2)) { //Move back if button 2 pressed
    		if(extension >= minExtension)
    		extension -= rate;
    	}  
    	else if(Robot.lidarSubsystem.getDistanceIn(true)<14.0) {
    		Robot.clawSubsystem.actuateClaw(minExtension);
    		
    	}

    	
    	rate = SmartDashboard.getNumber("Claw Rate", rate);
    	putNumbers();
    	
//    	Robot.clawSubsystem.clawActuator.setSpeed(speed);

    	if(oldextension != extension) {
    	Robot.clawSubsystem.actuateClaw(extension);
    	oldextension = extension;
    	}
    }
    
    public void putNumbers() {
    	SmartDashboard.putNumber("Claw Rate", rate);
    	SmartDashboard.putNumber("Claw Max", maxExtension);
    	SmartDashboard.putNumber("Claw Min", minExtension);
    	SmartDashboard.putNumber("Claw Extension", extension);
    	SmartDashboard.putBoolean("Limit Switch", Robot.clawSubsystem.limitSwitch.get());
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
