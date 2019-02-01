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

public class Approach extends Command {
  double currentDist;
  double wantedDist;
  double startingAngle;
  double error;
  double power;
  final double kP = -0.01;

  public Approach(double wantedDistance) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
    wantedDist = wantedDistance;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    startingAngle = RobotMap.gyro.getAngleZ();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentDist = RobotMap.getDistance();
    error = currentDist - wantedDist;
    power = error * kP;

    if (power <= 0.05 && power > 0)
      power = 0.05;
    else if (power >= 0.3)
      power = 0.3;
    else if (power <= -0.3)
      power = -0.3;
    else if (power >= -0.05 && power < 0)
      power = -0.05;
    Robot.driveTrain.gyroDriveX(RobotMap.gyro, power, startingAngle);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(error) <= 15;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
