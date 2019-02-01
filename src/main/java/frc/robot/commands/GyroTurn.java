/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class GyroTurn extends Command {
  private double angle;

  /**
   * Rotates the robot to the specified angle
   * 
   * @param angle The angle that the robot is wanted to turn
   */
  public GyroTurn(double angle) {
    requires(Robot.driveTrain);
    this.angle = angle;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.driveTrain.gyroTurn(RobotMap.gyro, angle);
  }

  @Override
  protected boolean isFinished() {
    return Math.abs(angle - RobotMap.gyro.getAngleZ()) <= 1;
  }

  @Override
  protected void end() {
    Robot.driveTrain.drive(0, 0, 0, false);
  }

  @Override
  protected void interrupted() {
  }
}
