package org.usfirst.frc.team4572.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;

/**
 *This is the Auto Code. Here will be the part that controls the robot during autonomous
 */
public class AutonomousSubsystem {
	
	public class Side {
		public static final int left = 1;
		public static final int middle = 0;
		public static final int right = -1;
	}
	
	public int robotSide = 0;
	public int switchSide;	
	
	public LimeVisionSubsystem limeLight;
	
	public int lines2Cross;
	
	/* 
	 * PROBLEMS TO WORK OUT:
	 * 
	 * 1. There is a big stack of cubes in the center of the switch
	 * 2. Will the mecanum wheels work correctly?
	 * 3. Detecting where to go.
	 * 
	 */
	
	boolean initVars = true;
	
	public AutonomousSubsystem(LimeVisionSubsystem limeLight) {
		this.limeLight = limeLight;
	}
	
	public void deposit() {
		if((robotSide + switchSide) == 0) {
			if(initVars) {
				//Do init Stuff
				lines2Cross = 2;
				
				initVars = false;
			}
			/* Big boy Cross
			 *		x
			 *		|
			 * ------
			 * |
			 * x 
			*/
			switch(robotSide) {
			case Side.left:
				
				if(lines2Cross != 0) {
					strafe(Side.left);
					
				}
				
				break;
			case Side.right:
				strafe(Side.right);
				break;
			
			}
			/*
			 * if(LINE_CROSSED){
			 * lines2Cross--;
			 * }
			 * 
			 * if(lines2Cross == 0){
			 * if(LIDAR.distanceinCM != front of bot)
			 * forward();
			 * }else{
			 * ClawCommand.activateClaw();
			 * }
			 */
			
			
		}else if(Math.abs((robotSide + switchSide)) == 2) {
			if(initVars) {
				//Do init Stuff
				
				
				initVars = false;
			}
			/* Straight
			 *		x
			 *		|
			 * 		|
			 *		x 
			*/
			switch(robotSide) {
			case Side.left:
				
				break;
			case Side.right:
			
				break;
			
			}
		}else {
			if(initVars) {
				//Do init Stuff
				lines2Cross = 1;
				
				initVars = false;
			}
			/*Little boy Cross
			 * x
			 * |
			 * ---
			 *   |
			 *	 x 
			*/
			switch(switchSide) {
			case Side.left:
				
				break;
			case Side.right:
			
				break;
			
			}
		}
	}
	
	
	/**
	 * Left    Middle    Right
	 *  | L      M      R |
	 *  | 1      0     -1 |
	 *  
	 * @return The side that the switch is on
	 */
	public int getSwitchSide() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L')
		{
		return Side.left;
		} else {
		return Side.right;
		}
	}
	
	
	public void turn(double angle) {
		
	}
	public void forward(double distance) {
		
		
	}
	
	public void strafe(int direction) {
		
		
	}
	
}

