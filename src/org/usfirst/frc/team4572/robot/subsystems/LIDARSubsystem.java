package org.usfirst.frc.team4572.robot.subsystems;

import org.usfirst.frc.team4572.robot.commands.LIDARCommand;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LIDARSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LIDARCommand());
    }
    static I2C mainI2C = new I2C(Port.kMXP,0x62);
    public static int getDistanceCm(){
    	byte[] buffer; 
    	buffer = new byte[2];
    	mainI2C.write(0x00, 0x04);
    	Timer.delay(0.04);
    	mainI2C.read(0x8f, 2, buffer); 
    	return (int)Integer.toUnsignedLong(buffer[0] << 8) + Byte.toUnsignedInt(buffer[1]);	   	
    }
    public static double getDistanceIn() {
    	return getDistanceCm() * 0.393701;
    }
}

