package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.commands.*;
import frc.robot.commands.Wrist.*;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

//TODO Figure out Invert so spinning direction is positive
public class Wrist extends Subsystem {
	
	public void WristStop()
    {
        Robot.robotmap.wrist.set(0.0);
    }

    public void operatorWristControl(double speed)
	{
		if((Math.abs(speed) > 0.1))
		{
			Robot.robotmap.wrist.set(speed);
		}
		else
		{
			Robot.robotmap.wrist.set(0.0);
		}
		
		
	}
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new WristOperation());
	}
	
	public Wrist()
	{

	}
}