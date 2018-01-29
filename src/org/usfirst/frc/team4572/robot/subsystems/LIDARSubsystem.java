package org.usfirst.frc.team4572.robot.subsystems;

import java.util.TimerTask;

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

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LIDARCommand());
    }
    
    static I2C mainI2C;
    static byte[] buffer = new byte[2];
    private final int LIDAR_ADDRESS = 0x62;
    private final int LIDAR_CONFIG = 0x00;
    private final int LIDAR_DIST_REG = 0x8f;
    java.util.Timer updater = new java.util.Timer();
    
    long lastUpdateTime = System.currentTimeMillis();
    
    public void lidarConfig(){
    	mainI2C = new I2C(Port.kMXP,LIDAR_ADDRESS);
    	mainI2C.write(0x02, 0x80);
    	mainI2C.write(0x04, 0x08);
    	mainI2C.write(0x1c, 0x00);
    	updater.scheduleAtFixedRate(new LIDARUpdater(), 0, 100);
    }
    public boolean getSuccess(){
    	mainI2C.write(0x00, 0x04);
    	try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return mainI2C.read(0x8f, 2, buffer);
    }
    public boolean updateDistance() throws InterruptedException {
    	if(System.currentTimeMillis() - lastUpdateTime >= 255) {
    		mainI2C.write(LIDAR_CONFIG, 0x04);
    		Thread.sleep(40);
    		mainI2C.read(LIDAR_DIST_REG, 2, buffer);
    		Thread.sleep(5);
    		mainI2C.write(LIDAR_DIST_REG, 0x00);
    		double temp = getDistanceCm();
    		if(temp != 1285)
    		System.out.println(temp);
    		lastUpdateTime = System.currentTimeMillis();
    		return true;
    	}
		return false;
    	
    }
    public int getDistanceCm(){
    	return (int) Integer.toUnsignedLong(buffer[0] << 8) + Byte.toUnsignedInt(buffer[1]);  
    }
    public double getDistanceIn() {
    	return getDistanceCm() * 0.393701; //TODO fix
    }
    private class LIDARUpdater extends TimerTask{

		@Override
		public void run() {
			while(true) {
				try {
					updateDistance();
					Thread.sleep(10);
					
				} catch(Exception e){
					e.printStackTrace();
					
				}
				
			}
		}
    	
    	
    }
}

