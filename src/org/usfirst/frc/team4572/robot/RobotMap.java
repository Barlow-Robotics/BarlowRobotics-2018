/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4572.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//MOTORS
	public final static int FRONT_LEFT_MOTOR_PORT = 0;
	public final static int FRONT_RIGHT_MOTOR_PORT = 2;
	public final static int BACK_LEFT_MOTOR_PORT = 1;
	public final static int BACK_RIGHT_MOTOR_PORT = 3;
	
	//CONTROLLER
	public static int PLAYSTATION_PORT = 0;
	
	//IO
	public static int GYROSCOPE_PORT = 0;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
