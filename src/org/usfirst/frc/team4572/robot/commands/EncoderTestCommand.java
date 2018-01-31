package org.usfirst.frc.team4572.robot.commands;

import org.usfirst.frc.team4572.robot.OI;
import org.usfirst.frc.team4572.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncoderTestCommand extends Command {

    public EncoderTestCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.encoderTestSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.encoderTestSubsystem.testEncoder.reset();
    	Robot.encoderTestSubsystem.testMotor.setInverted(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double count = Robot.encoderTestSubsystem.testEncoder.getDistance();
    	
//    	if(Math.abs(count) == 360.0 || Math.abs(count) == 720 || Math.abs(count) == 1080)
//    	Robot.encoderTestSubsystem.testEncoder.reset();
    	System.out.println(count);
    	if(count < 360) {
    		Robot.encoderTestSubsystem.testMotor.set(0.25);
    	}else {
    		Robot.encoderTestSubsystem.testMotor.set(0);
    	}
    	
//    	if(OI.logitech.getRawButton(3)) {
//    		Robot.encoderTestSubsystem.testMotor.set(1.0);
//    		System.out.println(count);
//    	}
//    	else {
//    		Robot.encoderTestSubsystem.testMotor.set(0.0);
//    		
//    	}
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
