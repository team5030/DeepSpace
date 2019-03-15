/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


//Import all commands for binding
import frc.robot.commands.*;
import frc.robot.commands.Climber.ClimberFirstPos;
import frc.robot.commands.Climber.ClimberOff;
import frc.robot.commands.Climber.ExtendLifters;
import frc.robot.commands.Climber.RetractLifters;
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
	public Button driveBackward = new TriggerButton(driver, this.LT, .2);
	public Button driveForward = new TriggerButton(driver, this.RT, .2);
	public Button driverA = new JoystickButton(driver, 1);
	public Button driverY = new JoystickButton(driver, 4);
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
		
		//climb
		// stopClimb.whenPressed(new ClimberOff());
		// climb.whenPressed(new ClimberFirstPos());

		// driverA.whileHeld(new RetractLifters(driver));
		// driverDPadDown.whileHeld(new RetractLifters(driver));

		// driverY.whileHeld(new ExtendLifters(driver));
		// driverDPadUp.whileHeld(new ExtendLifters(driver));		
	}
	
}
