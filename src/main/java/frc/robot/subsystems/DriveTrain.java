/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.JoystickDrive;

/**
 * Mecanum drive subsystem
 */
public class DriveTrain extends Subsystem {

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new JoystickDrive());
  }

  /**
   * Drives and rotates the robot according to speeds along the three axis of
   * motion
   * 
   * @param xSpeed        Speed in X axis [-1..1]
   * @param ySpeed        Speed in Y axis [-1..1]
   * @param zSpeed        Speed of rotation [-1..1]
   * @param fieldOriented Whether to drive relative to the field
   */
  public void drive(double xSpeed, double ySpeed, double zSpeed, boolean fieldOriented) {
    if (fieldOriented)
      RobotMap.driveTrain.driveCartesian(ySpeed, xSpeed, zSpeed, RobotMap.gyro.getAngleZ());
    else
      RobotMap.driveTrain.driveCartesian(ySpeed, xSpeed, zSpeed);
  }

  /**
   * Drives and rotates the robot according to joystick input
   * 
   * @param joy           Joystick to read the values from
   * @param mtp           Speed multiplier [0..1.0]
   * @param fieldOriented Whether to drive in field oriented mode
   */
  public void drive(Joystick joy, double mtp, boolean fieldOriented) {
    drive(-joy.getRawAxis(1) * mtp, joy.getRawAxis(0) * mtp, joy.getRawAxis(2) * mtp, fieldOriented);
    //drive(joy.getY() * mtp, joy.getZ() * mtp, joy.getZ() * mtp, fieldOriented);
  }
}
