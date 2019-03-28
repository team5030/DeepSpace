package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.commands.*;
import frc.robot.commands.Climber.ClimberOff;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

//TODO Figure out Invert so spinning direction is positive
public class Climber extends Subsystem {

    WPI_TalonSRX lTop = Robot.robotmap.C_LeftTop;
    WPI_TalonSRX lBottom = Robot.robotmap.C_LeftBottom;
    WPI_TalonSRX rTop = Robot.robotmap.C_RightTop;
    WPI_TalonSRX rBottom = Robot.robotmap.C_RightBottom;

    SpeedControllerGroup front = new SpeedControllerGroup(lTop, rTop);
    SpeedControllerGroup back = new SpeedControllerGroup(lBottom, rBottom);

    public Climber() {
       setupMotor(lTop);
       setupMotor(lBottom);
       setupMotor(rTop);
       setupMotor(rBottom);
    
       lTop.setSensorPhase(true);
       lBottom.setSensorPhase(false); 

       lBottom.follow(lTop);
       rBottom.follow(lBottom); 
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ClimberOff());
    }

    public void extendBackLifter() {
        lBottom.set(0.5);
        rBottom.set(0.5);
    }

    public void retractBackLifter() {
        lBottom.set(-0.5);
        rBottom.set(-0.5);
    }

    public void stopBackLifter() {
        lBottom.set(0.0);
        rBottom.set(0.0); 
    }

    public void stopFrontLifter() {
        lTop.set(0.0); 
        rTop.set(0.0);
    }

    public void extendFrontLifter() {
        lTop.set(0.5);
        rTop.set(0.5);
    }

    public void retractFrontLifter() {
        lTop.set(-0.5);
        rTop.set(0.5); 
    }

    private void setupMotor(WPI_TalonSRX controller) {
		controller.configOpenloopRamp(0.35);

		controller.configPeakCurrentLimit(41);
		controller.configPeakCurrentDuration(1);
		controller.configContinuousCurrentLimit(40);
        controller.enableCurrentLimit(true);
    }
}