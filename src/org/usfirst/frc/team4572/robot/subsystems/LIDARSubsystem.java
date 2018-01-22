package org.usfirst.frc.team4572.robot.subsystems;

import org.usfirst.frc.team4572.robot.commands.LIDARCommand;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LIDARSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private static I2C i2c;
    public static byte[] distance;
    private final int LIDAR_ADDR = 0x62;
    private final static int LIDAR_CONFIG_REGISTER = 0x00;
    private final static int LIDAR_DISTANCE_REGISTER = 0x8f;
	
    public static boolean enabled = true;
    
    public void toggleEnable() {
    	enabled = !enabled;
    }
    
    
    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        setDefaultCommand(new LIDARCommand());
	
    }
    
    public LIDARSubsystem() {
    	//Get I2C port from the MXP connector on the RoboRIO
        i2c = new I2C(Port.kMXP, LIDAR_ADDR);
        distance = new byte[2];
    }
    



    /**
     * Internally return Distance in cm
     * 
     * @return distance in cm
     */
    public static int getDistanceCm() {
                                
        return (int) Integer.toUnsignedLong(distance[0] << 8) + Byte.toUnsignedInt(distance[1]);
    }

    /**
     * Return Distance in Inches
     * 
     * @return distance in inches
     */
    public static double getDistanceIn() { // I made this function better. It used to be part of a PID
                                    // system. We didn't need a PID system.
        return (double) getDistanceCm() * 0.393701; // inches cuz Merica.
    }


    /**
     * Read from the sensor and update the internal "distance" variable with the result.
     */
    public static void updateLIDAR() {
        i2c.write(LIDAR_CONFIG_REGISTER, 0x04); // Initiate measurement
        //System.out.println("test");
        try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Delay for measurement to be taken
        i2c.read(LIDAR_DISTANCE_REGISTER, 2, distance); // Read in measurement
        
    }

    
    
}

