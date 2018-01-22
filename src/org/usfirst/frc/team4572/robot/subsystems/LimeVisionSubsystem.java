package org.usfirst.frc.team4572.robot.subsystems;

import org.usfirst.frc.team4572.robot.commands.LimeVisionCommand;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class LimeVisionSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	//Create network table
	NetworkTable table;
	
	//Create variables
	double targetD;
	boolean hasTarget;
	double xOffset;
	double yOffset;
	double area;
	double skew;
	double LEDMode;
	double camMode;
	double pipeline;

	public LimeVisionSubsystem() {
		table = NetworkTableInstance.getDefault().getTable("limelight");
	}
	
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new LimeVisionCommand());
	}
	
	//Does the camera proccessor have a target?
	public boolean getHasTarget() {
		targetD = table.getEntry("tv").getDouble(0); 
		if(targetD == 0) {
			hasTarget = false;
		}else if(targetD == 1) {
			hasTarget = true;
		}
		return hasTarget;
	}
	
	// Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
	public double getXOffset() {
		xOffset = table.getEntry("tx").getDouble(0);
		return xOffset;
	}
	
	//Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
	public double getYOffset() {
		yOffset = table.getEntry("ty").getDouble(0);
		return yOffset;
	}
	
	//Target Area (0% of image to 100% of image)
	public double getArea() {
		area = table.getEntry("ta").getDouble(0);
		return area;
	}
	
	//Skew or rotation (-90 degrees to 0 degrees)
	public double getSkew() {
		skew = table.getEntry("ts").getDouble(0);
		return skew;
	}

	//Limelight LED state
	public double getLEDMode() {
		LEDMode = table.getEntry("ledMode").getDouble(1);
		return LEDMode;
	}
	
	//Limelight Camera state
	public double getCamMode() {
		camMode = table.getEntry("camMode").getDouble(0);
		return camMode;
	}
	
	//get current pipeline that is being used
	public double getPipeline() {
		pipeline = table.getEntry("pipeline").getDouble(0);
		return pipeline;
	}
	
	//Set the LED mode of the limelight
	public void switchLED() {
		if(getLEDMode() == 0) {
			table.getEntry("ledMode").setDouble(1);
			SmartDashboard.putString("LED Mode", "Off");
		}else if(getLEDMode() == 1) {
			table.getEntry("ledMode").setDouble(0);
			SmartDashboard.putString("LED Mode", "On");
		}else if(getLEDMode() == 2) {
			table.getEntry("ledMode").setDouble(1);
			SmartDashboard.putString("LED Mode", "Off");
		}
	}
	
	//Set the camera mode
	public void switchCamera() {
		if(getCamMode() == 0) {
			table.getEntry("camMode").setDouble(1);
			SmartDashboard.putString("Camera Mode", "Camera");
		}else if(getCamMode() == 1) {
			table.getEntry("camMode").setDouble(0);
			SmartDashboard.putString("Camera Mode", "Vision");
		}
	}
	
	
	//Set the pipeline
	public void setPipeline(double pipeline) {
		table.getEntry("pipeline").setDouble(pipeline);
		SmartDashboard.putNumber("Camera Mode", pipeline);
	}
}