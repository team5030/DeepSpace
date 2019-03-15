package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.commands.*;
import frc.robot.commands.Climber.ClimberOff;
import frc.robot.commands.Climber.ClimberFirstPos;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

//TODO Figure out Invert so spinning direction is positive
public class Climber extends Subsystem {
    public Climber() {
        Robot.robotmap.C_LeftTop.setSmartCurrentLimit(30);
        Robot.robotmap.C_LeftBottom.setSmartCurrentLimit(30);
        Robot.robotmap.C_RightTop.setSmartCurrentLimit(30);
        Robot.robotmap.C_RightBottom.setSmartCurrentLimit(30);
    }

	public void ClimberStop()
    {
        Robot.robotmap.C_LeftTop.set(0.0);
        Robot.robotmap.C_LeftBottom.set(0.0);
        Robot.robotmap.C_RightTop.set(0.0);
        Robot.robotmap.C_RightBottom.set(0.0);
    }
    
    public void ClimberFirstPos() {
        Robot.robotmap.C_RightTop.follow(Robot.robotmap.C_LeftTop);
        Robot.robotmap.C_RightBottom.follow(Robot.robotmap.C_LeftBottom);

        double climbfront = 12.0;
        double climbback = 14.0;

        while(Robot.robotmap.E_LeftTop.getPosition() != climbfront) {
            Robot.robotmap.C_LeftTop.set(0.5);
        }

        while(Robot.robotmap.E_LeftBottom.getPosition() != climbback) {
            Robot.robotmap.C_LeftBottom.set(0.5); 
        }

        Robot.robotmap.FL.set(ControlMode.Position, 25);
        Robot.robotmap.BL.set(ControlMode.Follower, 0);

        while(Robot.robotmap.E_LeftTop.getPosition() != -climbfront) {
            Robot.robotmap.C_LeftTop.set(-0.5);
        }

        Robot.robotmap.FR.set(ControlMode.Position, 25);
        Robot.robotmap.BR.set(ControlMode.Follower, 1);

        while(Robot.robotmap.E_LeftBottom.getPosition() != -climbback) {
            Robot.robotmap.C_LeftBottom.set(-0.5); 
        }

        Robot.robotmap.FL.set(0.0);
        Robot.robotmap.BL.set(0.0);
        Robot.robotmap.FR.set(0.0);
        Robot.robotmap.BR.set(0.0);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ClimberOff());
    }

    public void extendBackLifter() {
        Robot.robotmap.C_LeftBottom.set(0.5);
        Robot.robotmap.C_RightBottom.set(0.5);
    }

    public void retractBackLifter() {
        Robot.robotmap.C_LeftBottom.set(-0.5);
        Robot.robotmap.C_RightBottom.set(-0.5);
    }

    public void stopBackLifter() {
        Robot.robotmap.C_LeftBottom.set(0);
        Robot.robotmap.C_RightBottom.set(0.0); 
    }

    public void stopFrontLifter() {
        Robot.robotmap.C_LeftTop.set(0); 
        Robot.robotmap.C_RightTop.set(0.0);
    }

    public void extendFrontLifter() {
        Robot.robotmap.C_LeftTop.set(0.5);
        Robot.robotmap.C_RightTop.set(0.5);
    }

    public void retractFrontLifter() {
        Robot.robotmap.C_LeftTop.set(-0.5);
        Robot.robotmap.C_RightTop.set(0.5); 
    }
}