/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.TriggerButton;

public class RetractFrontLifters extends Command {

  private Joystick stick;

  public RetractFrontLifters(Joystick stick) {
    requires(Robot.climberSubsytem);
    this.stick = stick;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(stick.getRawButton(2)) {
      Robot.climberSubsytem.retractFrontLifter();
      System.out.println("Retract Back");
    } else {
        Robot.climberSubsytem.stopFrontLifter();
    }
}

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !stick.getRawButton(2);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.climberSubsytem.stopBackLifter();
  }
}
