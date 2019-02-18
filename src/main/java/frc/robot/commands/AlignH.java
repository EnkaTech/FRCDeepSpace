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

public class AlignH extends Command {
  float error;
  double power;
  double currentAngle;

  public AlignH() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    currentAngle = RobotMap.gyro.getAngleX();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    error = Robot.table.getEntry("Horizontal error").getNumber(0).floatValue();
    power = -(error * Robot.driveTrain.Kp) / 5;
    if (power <= 0.1 && power > 0)
      power = 0.1;
    else if (power >= 0.3)
      power = 0.3;
    else if (power <= -0.3)
      power = -0.3;
    else if (power >= -0.1 && power < 0)
      power = -0.1;
    Robot.driveTrain.drive(0, power, (currentAngle - RobotMap.gyro.getAngleX()) * Robot.driveTrain.Kp, false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(error) <= 5;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.drive(0, 0, 0, false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
