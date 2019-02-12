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

public class AlignRotate extends Command {
  double error;
  double power;
  double currentAngle;
  double wantedAngle;
  double rem;

  public AlignRotate() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    error = Robot.table.getEntry("Rotate error").getDouble(0);
    currentAngle = RobotMap.gyro.getAngleX();
    rem = currentAngle % 90;
    if (error < 0){ //ccw
      if(rem < 30)
        wantedAngle = currentAngle - rem;
      else
        wantedAngle = currentAngle - rem + 30;}
    else if (error > 0){//cw
      if(rem > 60)
        wantedAngle = currentAngle - rem + 90;
      else
        wantedAngle = currentAngle - rem + 30;}
    else 
      wantedAngle = currentAngle;
    RobotMap.wantedAngle = wantedAngle;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    error = Robot.table.getEntry("Rotate error").getDouble(0);
    power = error * Robot.driveTrain.Kp * 4;

    if (power <= 0.05 && power > 0)
      power = 0.05;
    else if (power >= 0.2)
      power = 0.2;
    else if (power <= -0.2)
      power = -0.2;
    else if (power >= -0.05 && power < 0)
      power = -0.05;

    Robot.driveTrain.gyroTurn(RobotMap.gyro, wantedAngle);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(wantedAngle - RobotMap.gyro.getAngleX()) <= 1;

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
