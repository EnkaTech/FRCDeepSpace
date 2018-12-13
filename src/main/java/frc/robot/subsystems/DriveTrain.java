/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.JoystickDrive;

/**
 * Mecanum drive subsystem
 */
public class DriveTrain extends Subsystem {
  private double Kp = 0.02;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new JoystickDrive());
  }

  /**
   * Drives and rotates the robot according to speeds along the three axis of
   * motion
   * 
   * @param xSpeed        Speed in X axis(reverse-forward) [-1..1]
   * @param ySpeed        Speed in Y axis(left-right) [-1..1]
   * @param zSpeed        Speed of rotation(ccw-cw) [-1..1]
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
  }

  /**
   * Drives the robot in a straight line
   * 
   * @param gyro        Gyroscope to use
   * @param front       Whether to drive forward or backward
   * @param wantedAngle The angle that the robot is wanted to track
   */
  public void gyroDriveX(ADIS16448_IMU gyro, boolean front, double wantedAngle) {
    double angle = (wantedAngle + gyro.getAngleZ()) * Kp;
    if (front)
      drive(0.4, 0, angle, false);
    else
      drive(-0.4, 0, angle, false);

    Timer.delay(0.0004);
  }

  /**
   * Drives the robot sideways in a straight line
   * 
   * @param gyro        Gyroscope to use
   * @param right       Whether to drive right or left
   * @param wantedAngle The angle that the robot is wanted to track
   */
  public void gyroDriveY(ADIS16448_IMU gyro, boolean right, double wantedAngle) {
    double angle = (wantedAngle + gyro.getAngleZ()) * Kp;
    if (right)
      drive(0, 0.4, angle, false);
    else
      drive(0, -0.4, angle, false);

    Timer.delay(0.0004);
  }

  /**
   * Rotates the robot to the specified angle
   * 
   * @param gyro        Gyroscope to use
   * @param wantedAngle The angle that the robot is wanted to turn
   */
  public void gyroTurn(ADIS16448_IMU gyro, double wantedAngle) {
    double angle = gyro.getAngleZ() % 360;
    double power = (wantedAngle - angle) * Kp * 8;
    if (power > 0.2)
      power = 0.2;
    if (power < -0.2)
      power = -0.2;

    Timer.delay(0.0004);
    drive(0, 0, power, false);
  }
}