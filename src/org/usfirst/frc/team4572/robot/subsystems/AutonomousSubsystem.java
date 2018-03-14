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
		
	public double time;
	
	/* 
	 * PROBLEMS TO WORK OUT:
	 * 
	 * 1. Will the mecanum wheels work correctly?
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
	
	long startTime;
	public void deposit() {

		
		
		if((robotSide + switchSide) == 0) {
			if(initVars) {
				startTime = System.currentTimeMillis();
				initVars = false;
			}
			//Do nothing because can't reach
			if(System.currentTimeMillis() - startTime < 5000) {
				Robot.driveSubsystem.getBackLeftMotor().set(-0.3);
				Robot.driveSubsystem.getBackRightMotor().set(0.3);
				Robot.driveSubsystem.getFrontLeftMotor().set(-0.3);
				Robot.driveSubsystem.getFrontRightMotor().set(0.3);
				}
				else {
				Robot.driveSubsystem.getBackLeftMotor().set(0);
				Robot.driveSubsystem.getBackRightMotor().set(0);
				Robot.driveSubsystem.getFrontLeftMotor().set(0);
				Robot.driveSubsystem.getFrontRightMotor().set(0);
				}
			
		}else if(Math.abs((robotSide + switchSide)) == 2) {
			if(initVars) {
				initVars = false;
			}
			/* Straight
			 *		x
			 *		|
			 * 		|
			 *		x 
			*/
			if(System.currentTimeMillis() - startTime < 5000) {
				Robot.driveSubsystem.getBackLeftMotor().set(-0.3);
				Robot.driveSubsystem.getBackRightMotor().set(0.3);
				Robot.driveSubsystem.getFrontLeftMotor().set(-0.3);
				Robot.driveSubsystem.getFrontRightMotor().set(0.3);
				}
				else {
					//Done moving forward
				Robot.driveSubsystem.getBackLeftMotor().set(0);
				Robot.driveSubsystem.getBackRightMotor().set(0);
				Robot.driveSubsystem.getFrontLeftMotor().set(0);
				Robot.driveSubsystem.getFrontRightMotor().set(0);
				if(!Robot.clawSubsystem.limitSwitchExtend.get()) {Robot.clawSubsystem.extendClaw(.5); return;}
				//drop cube
				}
				
			
		}else {
			if(initVars) {
				//Do init Stuff				
				initVars = false;
			}
			if(startTime - System.currentTimeMillis() < 1500) {
				strafe(switchSide);
			}
			middleDeposite();
		}
	}

	boolean DropCube = true;
	long doneFocusTime = 0;
	public void middleDeposite() {
		/*
		 * x
		 * |
		 * ---
		 *   |
		 *	 x 
		*/
		if(DropCube) {
		//Focus on the tape
		if(!focusTape()) {
		return;
		}else {
			if(doneFocusTime == 0)
			doneFocusTime = System.currentTimeMillis();
		}
		
	
		//Move to the tape
		
		if(System.currentTimeMillis() - doneFocusTime < 3000) { forward(); return; }
		else stop();

		
		//Move the claw out
		if(!Robot.clawSubsystem.limitSwitchExtend.get()) {Robot.clawSubsystem.extendClaw(.5); return;}
		else Robot.clawSubsystem.extendClaw(0);
		}
		DropCube = false;
		
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
	
	public void forward() {
		DriveSubsystem.frontLeftMotor.set(-0.3);
		DriveSubsystem.backLeftMotor.set(-0.3);
		DriveSubsystem.frontRightMotor.set(0.3);
		DriveSubsystem.frontLeftMotor.set(0.3);
	}
	public void backwards() {
		DriveSubsystem.frontLeftMotor.set(1.0);
		DriveSubsystem.backLeftMotor.set(1.0);
		DriveSubsystem.frontRightMotor.set(1.0);
		DriveSubsystem.backRightMotor.set(1.0);
	}
	
	public void strafe(int direction) {
		if(direction == Side.left) {
			DriveSubsystem.frontLeftMotor.set(0.3);
			DriveSubsystem.backLeftMotor.set(-0.3);
			DriveSubsystem.frontRightMotor.set(-0.3);
			DriveSubsystem.backRightMotor.set(0.3);
		}
		else if(direction == Side.right) {
			DriveSubsystem.frontLeftMotor.set(-0.3);
			DriveSubsystem.backLeftMotor.set(0.3);
			DriveSubsystem.frontRightMotor.set(0.3);
			DriveSubsystem.backRightMotor.set(-0.3);
		}
		
	}
	public void stop() {
		DriveSubsystem.frontLeftMotor.set(0.0);
		DriveSubsystem.backLeftMotor.set(0.0);
		DriveSubsystem.frontRightMotor.set(0.0);
		DriveSubsystem.frontLeftMotor.set(0.0);
	}
	
}

