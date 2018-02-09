package org.usfirst.frc.team4572.robot.subsystems;

import org.usfirst.frc.team4572.robot.RobotMap;
import org.usfirst.frc.team4572.robot.commands.ClawCommand;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class ClawSubsystem extends Subsystem {
	//public static Spark LeftClawMotor = new Spark(RobotMap.LEFT_CLAW_PORT);
	//public static Spark RightClawMotor = new Spark(RobotMap.RIGHT_CLAW_PORT);
	public Servo clawActuator1 = new Servo(RobotMap.PWM.CLAW_LEFT_PORT);
	public Servo clawActuator2 = new Servo(RobotMap.PWM.CLAW_RIGHT_PORT);

	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ClawCommand());
        
    }
    
    
    public void actuateClaw(double position) {
    	clawActuator1.set(position);
    	clawActuator2.set(position);
    }
}

