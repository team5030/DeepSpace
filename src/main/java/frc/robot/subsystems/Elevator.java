package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.commands.*;
import frc.robot.commands.Elevator.ElevatorOperation;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

//TODO input top limit on operatorControl using Robot.robotmap.topHeight
//TODO change invert in constructor if necessary
//TODO Smarter Deadband
public class Elevator extends Subsystem {

	private int operatorSetpoint;
	private int parsedSetpoint;
	public static boolean overHeight;
	
	//public static final int StartingHeight = 1000;
	public static final int SwitchHeight = 7000;
	public static final int ScaleHeight = 3000;
	
	
	
	public void operatorControl(double speed)
	{
		System.out.println(speed);
		if(Math.abs(speed) > 0.1)
		{
			this.elevatorRelease();
			Robot.robotmap.elev.set(speed);
		}
		else
		{
			this.elevatorStop();
		}
		
		// if(!Robot.robotmap.ElevatorBottomSwitch.get())
		// {
		// 	this.elevatorEncoderReset();
		// }
	}

	public void overPosition(int operatorPOV)
	{
		
		//Receive POV value and turn it into number 0-3 or -1
		//NOTE: When DPAd is not pressed value = -1
		if(operatorSetpoint >= 0)
		{
			parsedSetpoint = operatorPOV / 90;
		}
		else
		{
			parsedSetpoint = operatorSetpoint;
		}
		
			switch(parsedSetpoint)
			{
				case 0:
					if(this.getPosition() < this.SwitchHeight)
					{
						this.operatorControl(-0.75);
					}
					else
					{
						this.operatorControl(0.0);
						overHeight = true;
					}
				break;
				
				case 1:
					if(this.getPosition() < this.ScaleHeight)
					{
						this.operatorControl(-1.0);
					}
					else
					{
						this.operatorControl(0.0);
						overHeight = true;
					}	
				break;
				
				case -1:
				default:
					this.operatorControl(0.0);
					overHeight = true;
				break;
				
			}
		}
						
	
	
	public double getPosition()
	{
		return Robot.robotmap.elev.getSelectedSensorPosition(0);
	}
	
	public void elevatorStop()
	{
		Robot.robotmap.elev.set(0.0);
		Robot.robotmap.elevatorSolenoid.set(Value.kForward);
	}
	
	public void elevatorEncoderReset()
	{
		Robot.robotmap.elev.setSelectedSensorPosition(0, 0, 5);
	}

	public void elevatorRelease()
	{
		Robot.robotmap.elevatorSolenoid.set(Value.kReverse);
	}
	
	public Elevator()
	{
		Robot.robotmap.elev.setInverted(false);
		
		
		Robot.robotmap.elev.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ElevatorOperation());
    }
}