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
  double wantedAngle;
  double currentAngle;

  public AlignH() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentAngle = RobotMap.gyro.getAngleX();
    wantedAngle = currentAngle + Robot.table.getEntry("Heading").getDouble(0);
    error = Robot.table.getEntry("Horizontal error").getNumber(0).floatValue();
    power = ((error * Robot.driveTrain.Kp) / -4.4);
    if (power >= 0.35)
      power = 0.35;
    else if (power <= -0.35)
      power = -0.35;
    Robot.driveTrain.drive(0, power, 0, false);
    // Robot.driveTrain.gyroDriveY(RobotMap.gyro, power, wantedAngle);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(error) <= 8;
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
