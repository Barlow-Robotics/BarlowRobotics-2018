package org.usfirst.frc.team4572.robot.subsystems;

import org.usfirst.frc.team4572.robot.RobotMap;
import org.usfirst.frc.team4572.robot.commands.LiftCommand;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LiftSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static Spark leftLiftMotor = new Spark(RobotMap.LEFT_LIFT_MOTOR_PORT);
	public static Spark rightLiftMotor = new Spark(RobotMap.RIGHT_LIFT_MOTOR_PORT);
	
	public Spark getLeftLiftMotor() {
		return leftLiftMotor;
	}
	
	public Spark getRightLiftMotor() {
		return rightLiftMotor;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LiftCommand());
    }
    public void activateLift(double speed) {
    	leftLiftMotor.set(speed);
    	rightLiftMotor.set(speed);
    }
}

