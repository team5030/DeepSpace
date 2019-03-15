/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.SplineDrive;

public class AUTO_LeftRocket extends Command {

  public double encoderDrivingDistance = 13;
  public boolean done = false;

  public AUTO_LeftRocket() {
    requires(Robot.drivetrainSubsystem);
    requires(Robot.intakeSubsytem);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrainSubsystem.EncReset();
    Robot.drivetrainSubsystem.configIMU();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.drivetrainSubsystem.CurrentEncoderPositionAverage() < encoderDrivingDistance) {
      Robot.drivetrainSubsystem.ArcadeDrive(0.75, 0.0);
    }
    else {
      Robot.drivetrainSubsystem.AllStop();
    }
    
   // Robot.intakeSubsytem.PistonsExtended();
   // Robot.intakeSubsytem.PistonsRetracted();
    done = true;

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return done;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrainSubsystem.AllStop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
