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
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANError;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
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
   public WPI_TalonSRX C_LeftTop;
   public WPI_TalonSRX C_LeftBottom;
   public WPI_TalonSRX C_RightTop;
   public WPI_TalonSRX C_RightBottom;

   //Holder
   public DoubleSolenoid hatchSolenoid = new DoubleSolenoid(6, 7);
   public DoubleSolenoid clawSolenoid = new DoubleSolenoid(0, 1);
   public DoubleSolenoid elevatorSolenoid = new DoubleSolenoid(2, 3);

   public PigeonIMU IMU;

   public DigitalInput ElevatorBottomSwitch = new DigitalInput(1);

   public UsbCamera usbCamera = CameraServer.getInstance().startAutomaticCapture("Cam 0", 0);  

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

      C_LeftTop = new WPI_TalonSRX(11);
      C_LeftBottom = new WPI_TalonSRX(10);
      C_RightTop = new WPI_TalonSRX(8);
      C_RightBottom = new WPI_TalonSRX(9);

      IMU = new PigeonIMU(BL);

      usbCamera.setResolution(320, 240);
      usbCamera.setFPS(15);
      
   }


}
