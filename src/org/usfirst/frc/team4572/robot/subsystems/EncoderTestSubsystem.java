package org.usfirst.frc.team4572.robot.subsystems;

import org.usfirst.frc.team4572.robot.Robot;
import org.usfirst.frc.team4572.robot.commands.EncoderTestCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class EncoderTestSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public boolean PAUSE = false;
	public double count = 0;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new EncoderTestCommand());
    }
    
    
    public void initThread() {
    	Thread t = new Thread(new scheduler());
    	t.start();
    }
    
    public Spark testMotor = new Spark(5);
    
}


class scheduler implements Runnable{

	@Override
	public void run() {
		
	    Encoder testEncoder = new Encoder(4, 3);
		while(true) {
			if(!Robot.encoderTestSubsystem.PAUSE) {
				Robot.encoderTestSubsystem.count = testEncoder.getDistance()/4;
			}
		}
	}
	
}

