package org.usfirst.frc.team4572.robot.subsystems;

import org.usfirst.frc.team4572.robot.RobotMap;
import org.usfirst.frc.team4572.robot.commands.ClawCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class ClawSubsystem extends Subsystem {
	//public static Spark LeftClawMotor = new Spark(RobotMap.LEFT_CLAW_PORT);
	//public static Spark RightClawMotor = new Spark(RobotMap.RIGHT_CLAW_PORT);
	public Servo clawActuatorLeft = new Servo(RobotMap.PWM.CLAW_LEFT_PORT);
	public Servo clawActuatorRight = new Servo(RobotMap.PWM.CLAW_RIGHT_PORT);
	public Spark extensionMotor = new Spark(RobotMap.PWM.CLAW_EXTEND_MOTOR_PORT);
	public DigitalInput limitSwitchExtend = new DigitalInput(RobotMap.DIO.EXTENSION_LIMIT_SWITCH_PORT);
	public DigitalInput limitSwitchRetract = new DigitalInput(RobotMap.DIO.RETRACTION_LIMIT_SWITCH_PORT);

	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ClawCommand());
        
    }
    
    public Spark getExtensionMotor() {
    	return extensionMotor;
    	
    }
    public void actuateClaw(double position) {
    	clawActuatorLeft.set(position);
    	clawActuatorRight.set(position);
    }

    
    public void extendClaw(double speed) {
    	if(speed > 0.0 && !limitSwitchExtend.get()) {
        	extensionMotor.set(speed);
    	}
    	if (speed < 0.0 && !limitSwitchRetract.get()) {
        	extensionMotor.set(speed);
    	}
    }
}

