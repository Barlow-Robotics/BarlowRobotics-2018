package org.usfirst.frc.team4572.robot.subsystems;

import org.usfirst.frc.team4572.robot.OI;
import org.usfirst.frc.team4572.robot.RobotMap;
import org.usfirst.frc.team4572.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

	//Speed Controllers
	public static Spark frontLeftMotor = new Spark(RobotMap.FRONT_LEFT_MOTOR_PORT);
	public static Spark frontRightMotor = new Spark(RobotMap.FRONT_RIGHT_MOTOR_PORT);
	public static Spark backLeftMotor = new Spark(RobotMap.BACK_LEFT_MOTOR_PORT);
	public static Spark backRightMotor = new Spark(RobotMap.BACK_RIGHT_MOTOR_PORT);
	
	//Mecanum Drive Variable... Used to move the robot
	public static MecanumDrive m_robotDrive = new MecanumDrive(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveCommand());
	}
	
	public Spark getFrontLeftMotor() {
		return frontLeftMotor;
	}
	public Spark getFrontRightMotor(){
		return frontRightMotor;
	}
	public Spark getBackLeftMotor(){
		return backLeftMotor;
	}
	public Spark getBackRightMotor(){
		return backRightMotor;
	}
    
	//Main Drive Function(called by DriveCommand)
    public static void drive() {
    	
    //Tell Robot to drive
    m_robotDrive.driveCartesian(OI.getDriveJoystickYAxis(), OI.getDriveJoystickXAxis(), OI.getDriveJoystickZAxis(), 0);
    }
    
}

