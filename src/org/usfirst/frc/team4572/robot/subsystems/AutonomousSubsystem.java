package org.usfirst.frc.team4572.robot.subsystems;

import org.usfirst.frc.team4572.robot.Robot;
import org.usfirst.frc.team4572.robot.commands.ClawCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

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
			stop();
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
	boolean DropCube = true;
	public void littleBoyCross() {
		/*Little boy Cross
		 * x
		 * |
		 * ---
		 *   |
		 *	 x 
		*/
		if(DropCube) {
		//Focus on the tape
		if(!focusTape()) return;
		
		//Move to the tape
		if(!tapeTooClose()) { forward(); return; };
		

		if(!Robot.liftSubsystem.vertLimitSwitch.get()) {Robot.liftSubsystem.liftMotors.set(.5);}
		//Move the claw out
		if(!Robot.clawSubsystem.limitSwitchExtend.get()) {Robot.clawSubsystem.extendClaw(.5); return;}
		//drop cube
		if(ClawCommand.extension != ClawCommand.maxExtension) {
		ClawCommand.extension = ClawCommand.maxExtension;
		Timer.delay(.5);
		}
		}
		DropCube = false;
		if(!Robot.clawSubsystem.limitSwitchRetract.get()) {Robot.clawSubsystem.extendClaw(-.5); return;}
		
		strafe(switchSide);
		Timer.delay(4);
		stop();
		forward();
		
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
	public void stop() {
		DriveSubsystem.frontLeftMotor.set(0.0);
		DriveSubsystem.backLeftMotor.set(0.0);
		DriveSubsystem.frontRightMotor.set(0.0);
		DriveSubsystem.frontLeftMotor.set(0.0);
	}
	
}

