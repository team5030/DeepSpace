package frc.robot.commands.Climber;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ExtendLifters extends Command {
    private Joystick stick;

    public ExtendLifters(Joystick joystick) {
        requires(Robot.climberSubsytem);
        stick = joystick;
    }

    public void execute() {
        if(stick.getRawButton(4)) {
            Robot.climberSubsytem.extendBackLifter();
            System.out.println("Extend Back");
        } else {
            Robot.climberSubsytem.stopBackLifter();
        }

        if(stick.getPOV() == 0) {
            Robot.climberSubsytem.extendFrontLifter();
            System.out.println("Extend Front");
        } else {
            Robot.climberSubsytem.stopFrontLifter();
        }
    }

    public boolean isFinished() {
        return !stick.getRawButton(4) && stick.getPOV() != 0;
    }
}