package frc.robot.commands.Climber;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

public class RetractLifters extends Command {
    private Joystick stick;

    public RetractLifters(Joystick joystick) {
        stick = joystick;
    }

    public void execute() {
        if(stick.getRawButton(1)) {
            Robot.climberSubsytem.retractBackLifter();
            System.out.println("Retract Back");
        } else {
            Robot.climberSubsytem.stopBackLifter();
        }

        if(stick.getPOV() == 180) {
            Robot.climberSubsytem.retractFrontLifter();
            System.out.println("Retract Front");
        } else {
            Robot.climberSubsytem.stopFrontLifter();
        }
    }

    public boolean isFinished() {
        return !stick.getRawButton(4) && stick.getPOV() != 180;
    }
}