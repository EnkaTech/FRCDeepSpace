/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Follows a straight line in the X axis for a given time period
 */
public class GyroDriveX extends TimedCommand {
  private boolean fwd;
  private double angle;

  /**
   * Follows a straight line in the X axis for a given time period
   * 
   * @param timeout Timeout for the command
   * @param forward Whether to go forward or backward
   * @param angle   The angle to track
   */
  public GyroDriveX(double timeout, boolean forward, double angle) {
    super(timeout);
    requires(Robot.driveTrain);
    this.fwd = forward;
    this.angle = angle;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.driveTrain.gyroDriveX(RobotMap.gyro, fwd, angle);
  }

  @Override
  protected void end() {
    Robot.driveTrain.drive(0, 0, 0, false);
  }

  @Override
  protected void interrupted() {
  }
}
