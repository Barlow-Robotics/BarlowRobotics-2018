package org.usfirst.frc.team4572.robot.subsystems;

import org.usfirst.frc.team4572.robot.RobotMap;
import org.usfirst.frc.team4572.robot.commands.IntakeCommand;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//public static Spark leftIntakeMotor = new Spark(RobotMap.PWM.LEFT_INTAKE_WHEEL_PORT);
	//public static Spark rightIntakeMotor = new Spark(RobotMap.PWM.RIGHT_INTAKE_WHEEL_PORT);
	
	//public Spark getLeftIntakeMotor() {
	//	return leftIntakeMotor;
	//}
	
//	public Spark getRightIntakeMotor() {
	//	return rightIntakeMotor;
//	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeCommand());
    }
//    public void activateIntake(double speed) {
//    	leftIntakeMotor.set(-speed);
//    	rightIntakeMotor.set(speed);
//    }
}

