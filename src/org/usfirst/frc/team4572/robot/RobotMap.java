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
	//Drive
		public static final int FRONT_LEFT_MOTOR_PORT = 0;
		public static final int FRONT_RIGHT_MOTOR_PORT = 2;
		public static final int BACK_LEFT_MOTOR_PORT = 1;
		public static final int BACK_RIGHT_MOTOR_PORT = 3;
	//Claw
		public static final int LEFT_CLAW_PORT = 4;
		public static final int RIGHT_CLAW_PORT = 5;
	//Intake Wheels
		public static final int LEFT_INTAKE_WHEEL_PORT = 6;
		public static final int RIGHT_INTAKE_WHEEL_PORT = 7;
	//Lift System
		public static final int LEFT_LIFT_MOTOR_PORT = 8;
		public static final int RIGHT_LIFT_MOTOR_PORT = 9;
		
	
	
	
	//CONTROLLER
	public static int PLAYSTATION_PORT = 0;
	public static int LOGITECH_PORT = 1;
	
	//SENSORS
	public static int GYROSCOPE_PORT = 0;
	
	
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
