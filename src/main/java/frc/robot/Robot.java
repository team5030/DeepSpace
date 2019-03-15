/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.*;
import frc.robot.commands.*;
import frc.robot.commands.Autos.AUTO_Controller;
import frc.robot.commands.Autos.AUTO_Default;
import frc.robot.commands.Autos.AUTO_LeftCargo;
import frc.robot.commands.Autos.AUTO_LeftRocket;
import frc.robot.commands.Autos.AUTO_RightCargo;
import frc.robot.commands.Autos.AUTO_RightRocket;
import frc.robot.Enums.AutoMode;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
	
	public static double kEncoderConversion = ((6*Math.PI) / 4100);
	
	public static String receivedGameData;
	
	public static RobotMap robotmap;
	public static Intake intakeSubsytem;
	public static Drivetrain drivetrainSubsystem;
	public static Climber climberSubsytem;
    public static Elevator elevatorSubsystem; 
    public static Wrist wristSubsystem;
	public static OI oi;

	Command m_autonomousCommand;
	SendableChooser<AutoMode> m_chooser = new SendableChooser<>();


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */ 
	@Override
	public void robotInit() {
		robotmap = new RobotMap();
		intakeSubsytem = new Intake();
		drivetrainSubsystem = new Drivetrain();
		climberSubsytem = new Climber();
		elevatorSubsystem = new Elevator();
		wristSubsystem = new Wrist();
		oi = new OI();
		m_chooser.addDefault("Default Auto", AutoMode.DEFAULT);//new AUTO_Default());
		m_chooser.addObject("Controller", AutoMode.CONTROLLER);
		m_chooser.addObject("Left Side Rocket", AutoMode.LEFT_ROCKET);
		m_chooser.addObject("Left Side Cargo", AutoMode.LEFT_CARGO);
		m_chooser.addObject("Right Side Cargo", AutoMode.RIGHT_CARGO);
		m_chooser.addObject("Right Side Rocket", AutoMode.RIGHT_ROCKET);
		
		SmartDashboard.putData("Auto mode", m_chooser);
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setFPS(20);
		camera.setResolution(486, 440);

		Robot.drivetrainSubsystem.EncReset();
		Robot.drivetrainSubsystem.configIMU();
		Robot.drivetrainSubsystem.ConifgMagEncoder();
	
	} 

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		debug();
	}

	/**
	 * This autonomous (along with the m_chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * m_chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the m_chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * m_chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
		Robot.drivetrainSubsystem.EncReset();
		
		switch(m_chooser.getSelected()) {
			case CONTROLLER:
				m_autonomousCommand = new AUTO_Controller(); 
				break;
			
			case LEFT_ROCKET:
				m_autonomousCommand = new AUTO_LeftRocket();
				break;

			case LEFT_CARGO:
				m_autonomousCommand = new AUTO_LeftCargo();
				break;

			case RIGHT_CARGO:
				m_autonomousCommand = new AUTO_RightCargo();
				break;

			case RIGHT_ROCKET:
				m_autonomousCommand = new AUTO_RightRocket();
				break;

			case DEFAULT:
			default:
				m_autonomousCommand = new AUTO_Default();
				break;
		}
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}
 
	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		debug();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		
		Robot.drivetrainSubsystem.EncReset();
		Robot.drivetrainSubsystem.configIMU();
		Robot.elevatorSubsystem.elevatorEncoderReset();
	
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		// System.out.println("Heading " + Robot.drivetrainSubsystem.getGyroAngle());
		debug();
			
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {}
		
	public void debug() {
		SmartDashboard.putNumber("Right Encoder", robotmap.FR.getSelectedSensorPosition(0) * this.kEncoderConversion);
		SmartDashboard.putNumber("Left Encoder" , robotmap.FL.getSelectedSensorPosition(0) * this.kEncoderConversion);
		SmartDashboard.putNumber("Elevator Encoder", this.elevatorSubsystem.getPosition()); 
		SmartDashboard.putNumber("Yaw" , this.drivetrainSubsystem.getGyroAngle());
	
	}
}