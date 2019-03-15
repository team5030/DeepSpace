/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class AUTO_Controller extends Command {
  public AUTO_Controller() {
      requires(Robot.drivetrainSubsystem);
      requires(Robot.elevatorSubsystem);
      requires(Robot.wristSubsystem);
      requires(Robot.climberSubsytem);
      requires(Robot.intakeSubsytem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrainSubsystem.EncReset();
		Robot.drivetrainSubsystem.configIMU();
		Robot.elevatorSubsystem.elevatorEncoderReset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Scheduler.getInstance().run();
		System.out.println("Heading " + Robot.drivetrainSubsystem.getGyroAngle());
		debug();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.robotmap.BL.set(0.0);
    Robot.robotmap.BR.set(0.0);
    Robot.robotmap.FL.set(0.0);
    Robot.robotmap.FR.set(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

  public void debug() {
		SmartDashboard.putNumber("Right Encoder", Robot.robotmap.FR.getSelectedSensorPosition(0) * Robot.kEncoderConversion);
		SmartDashboard.putNumber("Left Encoder" , Robot.robotmap.FL.getSelectedSensorPosition(0) * Robot.kEncoderConversion);
		SmartDashboard.putNumber("Elevator Encoder", Robot.elevatorSubsystem.getPosition()); 
		SmartDashboard.putNumber("Yaw" , Robot.drivetrainSubsystem.getGyroAngle());
	}

}
