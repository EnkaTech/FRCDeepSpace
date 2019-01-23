/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AlignRotate extends Command {
  float rError;
  double rPower;

  public AlignRotate() {
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
  rError =  Robot.table.getEntry("Rotate error").getNumber(0).floatValue();
  rPower = (rError*Robot.driveTrain.Kp);
  if(rPower <= 0.05 && rPower > 0)
    rPower = 0.05;
  else if(rPower >= 0.2)
    rPower = 0.2;
  else if(rPower <= -0.2)
    rPower = -0.2;
  else if(rPower >= -0.05 && rPower < 0)
    rPower = -0.05;
  Robot.driveTrain.drive(0, 0, rPower, false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(rError) <= 1 ;
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
