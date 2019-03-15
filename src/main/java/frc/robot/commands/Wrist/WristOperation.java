package frc.robot.commands.Wrist;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class WristOperation extends Command {

	private double speed;
	
    public WristOperation() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.wristSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        speed = Robot.oi.operator.getRawAxis(5); //Might be the wrong axis
    	
    	Robot.wristSubsystem.operatorWristControl(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}
}