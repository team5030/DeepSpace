/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANError;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
   //DRIVE TRAIN

   public WPI_TalonSRX FL;
   public WPI_TalonSRX FR;
   public WPI_TalonSRX BL;
   public WPI_TalonSRX BR;

   //ELEVATOR
   public WPI_TalonSRX elev;

   //CLAW
   public Spark clawIntakeLeft = new Spark(0);
   public Spark clawIntakeRight = new Spark(1);

   public WPI_TalonSRX wrist;

   //CLIMB
   public CANSparkMax C_LeftTop;
   public CANEncoder E_LeftTop;
   public CANSparkMax C_LeftBottom;
   public CANEncoder E_LeftBottom;
   public CANSparkMax C_RightTop;
   public CANEncoder E_RightTop;
   public CANSparkMax C_RightBottom;
   public CANEncoder E_RightBottom;

   //Holder
   public DoubleSolenoid hatchSolenoid = new DoubleSolenoid(6, 7);
   public DoubleSolenoid clawSolenoid = new DoubleSolenoid(0, 1);
   public DoubleSolenoid elevatorSolenoid = new DoubleSolenoid(2, 3);

   public PigeonIMU IMU;

   public DigitalInput ElevatorBottomSwitch = new DigitalInput(1);

   public RobotMap() {
      FL = new WPI_TalonSRX(0);
      FR = new WPI_TalonSRX(2);
      BL = new WPI_TalonSRX(1);
      BR = new WPI_TalonSRX(3);

      wrist = new WPI_TalonSRX(5);
      elev = new WPI_TalonSRX(4);

      /* This line allows the talon to drive through its limit switch.
      To stop it from doing this, set the boolean to true. */
      elev.overrideLimitSwitchesEnable(false);

      C_LeftTop = new CANSparkMax(11, MotorType.kBrushless);
      E_LeftTop = C_LeftTop.getEncoder();
      C_LeftBottom = new CANSparkMax(10, MotorType.kBrushless);
      E_LeftBottom = C_LeftBottom.getEncoder();
      C_RightTop = new CANSparkMax(8, MotorType.kBrushless);
      E_RightTop = C_RightTop.getEncoder();
      C_RightBottom = new CANSparkMax(9, MotorType.kBrushless);
      E_RightBottom = C_RightBottom.getEncoder();

      IMU = new PigeonIMU(BL);

      

   }


}
