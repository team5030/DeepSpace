/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


//Import all commands for binding
import frc.robot.commands.*;
import frc.robot.commands.Climber.ExtendLifters;
import frc.robot.commands.Climber.RetractBackLifters;
import frc.robot.commands.Climber.ClimberOff;
import frc.robot.commands.Climber.ExtendBackLifters;
import frc.robot.commands.Climber.ExtendFrontLifters;
import frc.robot.commands.Climber.ExtendLifters;
import frc.robot.commands.Climber.RetractLifters;
import frc.robot.commands.Climber.RetractFrontLifters;
import frc.robot.commands.Intake.*;

//Import all subsytems
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;


public class OI 
{

	private final int LT = 2;
	private final int RT = 3;
	
	//Instantiate, create, and name Driver Joystick
	public static Joystick driver = new Joystick(0);
	public Button driverClimbAll = new JoystickButton(driver, 1);
	public Button driverRetractAll = new JoystickButton(driver, 4);
	public Button driverClimbFront = new JoystickButton(driver, 5); //LB
	public Button driverClimbBack = new JoystickButton(driver, 6); //RB
	public Button driverRetractFront = new JoystickButton(driver, 3); 
	public Button driverRetractBack = new JoystickButton(driver, 2);
	public Button driverDPadUp = new Button(){
		@Override
		public boolean get() {
			return driver.getPOV() == 0;
		}
	};
	public Button driverDPadDown = new Button(){
		@Override
		public boolean get() {
			return driver.getPOV() == 180;
		}
	};
	
	//Instantiate, create, and name Operator Joystick
  	public static Joystick operator = new Joystick(1);
    public JoystickButton piston = new JoystickButton(operator, 6);
    public JoystickButton operatorLB = new JoystickButton(operator, 5);
    public JoystickButton climb = new JoystickButton(operator, 1);
    public JoystickButton stopClimb = new JoystickButton(operator, 2);
    public JoystickButton operatorX = new JoystickButton(operator, 3);
    public JoystickButton operatorY = new JoystickButton(operator, 4);
    public Button ballIn = new TriggerButton(operator, this.RT, .2);
	public Button ballOut = new TriggerButton(operator, this.LT, .2);
    
	
		

	public OI()
	{
		
		//Intake
		ballIn.whenPressed(new IntakeBall());
		ballOut.whenPressed(new ReleaseBall());		
		piston.whileHeld(new IntakeHatch());

		//Climber
		driverClimbAll.whenPressed(new ExtendLifters(driver));
		driverRetractAll.whenPressed(new RetractLifters(driver));
		driverClimbFront.whenPressed(new ExtendFrontLifters(driver));
		driverClimbBack.whenPressed(new ExtendBackLifters(driver));
		driverRetractFront.whenPressed(new RetractFrontLifters(driver));
		driverRetractBack.whenPressed(new RetractBackLifters(driver));
			
	}
	
}
