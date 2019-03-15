package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

//TODO Test all motors and correct imports
public class Intake extends Subsystem {
	
	//Create SpeedControllerGroup for gripper and arm motors
	private SpeedControllerGroup Claw_Group 
		= new SpeedControllerGroup(Robot.robotmap.clawIntakeLeft, Robot.robotmap.clawIntakeRight);
	
	private double Intake_Speed = 0.5;
	
	public Intake()
	{
        //TODO
        //Invert One of the gripper motors
		
		Robot.robotmap.clawIntakeLeft.setInverted(true);
		Robot.robotmap.clawIntakeRight.setInverted(false);
	} 
	
	//Turn off SpeedControllerGroup
    public void IntakeStop()
    {
    	this.Claw_Group.set(0.0);
    }
    
    //Bring in Ball
    public void IntakeIn()
    {
        Robot.robotmap.clawIntakeRight.set(-Intake_Speed);
        Robot.robotmap.clawIntakeLeft.set(-Intake_Speed);
    }

    //Intake outwards
    public void IntakeOut()
    {
    	Robot.robotmap.clawIntakeRight.set(Intake_Speed);
        Robot.robotmap.clawIntakeLeft.set(Intake_Speed);
    }
    
    //FullSpeed
    public void ShootBall()
    {
    	Robot.robotmap.clawIntakeLeft.set(1.0);
    	Robot.robotmap.clawIntakeRight.set(1.0);
    }
 
    public void IntakePosition()
    {
        Robot.robotmap.hatchSolenoid.set(Value.kForward);
        Robot.robotmap.clawSolenoid.set(Value.kForward);
    } 

    public void IntakeHatchPosition()
    {
        Robot.robotmap.hatchSolenoid.set(Value.kForward);
        Robot.robotmap.clawSolenoid.set(Value.kReverse);
    }

    public void ClampClaw()
    {
        Robot.robotmap.clawSolenoid.set(Value.kReverse);
        Robot.robotmap.hatchSolenoid.set(Value.kForward);
    }
    public void CloseC()
    {
        Robot.robotmap.hatchSolenoid.set(Value.kReverse);
        Robot.robotmap.clawSolenoid.set(Value.kReverse);
    }
    
    public void IntakeHatch()
    {
        Robot.robotmap.clawIntakeRight.set(-1.0);
        Robot.robotmap.clawIntakeLeft.set(1.0); //TOP
    }

    /* MIGHT NEED THIS!!??
    public boolean BallPresent()
    {
    	return Robot.robotmap.cubeSensor.get();
    }
    */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand();
    }
}