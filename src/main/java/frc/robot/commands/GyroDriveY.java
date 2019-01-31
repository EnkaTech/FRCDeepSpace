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
 * Follows a straight line in the Y axis for a given time period
 */
public class GyroDriveY extends TimedCommand {
  private boolean a;
  private boolean right;
  private double angle;

  /**
   * Follows a straight line in the Y axis for a given time period
   * 
   * @param timeout Timeout for the command
   * @param right   Whether to go right or left
   * @param angle   The angle to track
   */
  public GyroDriveY(double timeout, boolean right, double angle) {
    super(timeout);
    requires(Robot.driveTrain);
    this.right = right;
    this.angle = angle;
  }

  public GyroDriveY(double timeout, boolean right) {
    super(timeout);
    requires(Robot.driveTrain);
    this.right = right;
    this.angle = RobotMap.wantedAngle;
    a = true;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (a) {
      angle = RobotMap.wantedAngle;
      right = RobotMap.error;
    }
    Robot.driveTrain.gyroDriveY(RobotMap.gyro, right, angle);
  }

  @Override
  protected void end() {
    Robot.driveTrain.drive(0, 0, 0, false);
  }

  @Override
  protected void interrupted() {
  }
}
