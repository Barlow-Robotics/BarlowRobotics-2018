package org.usfirst.frc.team4572.robot.subsystems;

import org.usfirst.frc.team4572.robot.RobotMap;
import org.usfirst.frc.team4572.robot.commands.LiftCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LiftSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public boolean PAUSE = false;
	public double count = 0;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LiftCommand());
    }
	public DigitalInput vertLimitSwitch = new DigitalInput(RobotMap.DIO.VERTICAL_LIMIT_SWITCH_PORT);

    
    public Spark liftMotors = new Spark(RobotMap.PWM.LIFT_MOTOR_PORT);
}


