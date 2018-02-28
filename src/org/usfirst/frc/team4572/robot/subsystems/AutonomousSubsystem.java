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
	
	public int linesToCross;
	
	public double time;
	
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
		
		this.limeLight.setPipeline(2);
		
		
	}
	
	public void AutonomousEnd() {
		limeLight.setPipeline(0);
	}
	
	
	public static final int tolerance = 10;
	
	public boolean focusTape() {		
		if(limeLight.getXOffset() > tolerance || limeLight.getXOffset() < -tolerance) {
			//focused on target
			return true;
		}
		
		if((limeLight.getXOffset() > 0 && switchSide == Side.left) || (limeLight.getXOffset() < 0 && switchSide == Side.right)) {
			strafe(-switchSide);
		}else if((limeLight.getXOffset() > 0 && switchSide == Side.right) || (limeLight.getXOffset() < 0 && switchSide == Side.left)) {
			strafe(switchSide);
		}
		
		return false;
	}
	
	public boolean DetectLineCross() {
		if(!limeLight.hasTarget) { return false;
		}
		//limeLight.xOffset + limeLight.
		
		
		else {
		return true;
		}
	}
	
	public void deposit(double time) {
		this.time = time;
		if(time < 1.5) {
			strafe(switchSide);
		}
		
		
		if((robotSide + switchSide) == 0) {
			if(initVars) {
				//Do init Stuff
				linesToCross = 2;
				
				initVars = false;
			}
			
			bigBoyCross();
			
			
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
				linesToCross = 1;
				
				initVars = false;
			}
			littleBoyCross();
		}
	}
	
	public void bigBoyCross() {
		/* Big boy Cross
		 *		x
		 *		|
		 * ------
		 * |
		 * x 
		*/
		switch(robotSide) {
		case Side.left:
			
			if(linesToCross != 0) {
				strafe(Side.left);
				
			}
			
			break;
		case Side.right:
			strafe(Side.right);
			break;
		
		}
		/*
		 * if(LINE_CROSSED){
		 * linesToCross--;
		 * }
		 * 
		 * if(linesToCross == 0){
		 * if(LIDAR.distanceinCM != front of bot)
		 * forward();
		 * }else{
		 * ClawCommand.activateClaw();
		 * }
		 */
	}
	public void littleBoyCross() {
		/*Little boy Cross
		 * x
		 * |
		 * ---
		 *   |
		 *	 x 
		*/
		
		if(!focusTape()) return;
		
		if(!tapeTooClose()) { forward(); return; };
		
		//if(!make_claw_move_out_done()) return;
		//if(!make_claw_drop_cube_done()) return;
		//if(!make_claw_move_in_done()) return;
		//if(!move_to_side_done()) return;
		//if(!move_forward_for_2_seconds()) return;
		
	}
	
	public boolean tapeTooClose() {
		return false;
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
	
	/**
	 * takes input of a side
	 */
	public void turn(int direction) {
		if(direction == Side.right) {
		DriveSubsystem.frontLeftMotor.set(1.0);
		DriveSubsystem.backLeftMotor.set(1.0);
		DriveSubsystem.frontRightMotor.set(-1.0);
		DriveSubsystem.frontLeftMotor.set(-1.0);
		}else if(direction == Side.left){
			DriveSubsystem.frontLeftMotor.set(-1.0);
			DriveSubsystem.backLeftMotor.set(-1.0);
			DriveSubsystem.frontRightMotor.set(1.0);
			DriveSubsystem.frontLeftMotor.set(1.0);
		}
	}
	public void forward() {
		DriveSubsystem.frontLeftMotor.set(1.0);
		DriveSubsystem.backLeftMotor.set(1.0);
		DriveSubsystem.frontRightMotor.set(1.0);
		DriveSubsystem.frontLeftMotor.set(1.0);
	}
	public void backwards() {
		DriveSubsystem.frontLeftMotor.set(1.0);
		DriveSubsystem.backLeftMotor.set(1.0);
		DriveSubsystem.frontRightMotor.set(1.0);
		DriveSubsystem.frontLeftMotor.set(1.0);
	}
	
	public void strafe(int direction) {
		if(direction == Side.left) {
			DriveSubsystem.frontLeftMotor.set(-1.0);
			DriveSubsystem.backLeftMotor.set(1.0);
			DriveSubsystem.frontRightMotor.set(-1.0);
			DriveSubsystem.frontLeftMotor.set(1.0);
		}
		else if(direction == Side.right) {
			DriveSubsystem.frontLeftMotor.set(1.0);
			DriveSubsystem.backLeftMotor.set(-1.0);
			DriveSubsystem.frontRightMotor.set(1.0);
			DriveSubsystem.frontLeftMotor.set(-1.0);
		}
		
	}
	
}

