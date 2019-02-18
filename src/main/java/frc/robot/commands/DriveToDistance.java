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

public class DriveToDistance extends Command {
  double currentAngle;
  double wantedDist;
  double currentDist;
  final double kP = 0.01;
  double error;
  double power;
  public DriveToDistance(double dist) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain);
    wantedDist = dist;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    RobotMap.frontRightEncoder.reset();
    RobotMap.rearLeftEncoder.reset();
    currentAngle = RobotMap.gyro.getAngleX();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentDist = (RobotMap.frontRightEncoder.getDistance() + RobotMap.rearLeftEncoder.getDistance())/2;
    error = wantedDist - currentDist;
    power = error * kP;
    if(power > 0  && power < 0.1)
      power = 0.1;
    else if(power < 0 && power > -0.1)
      power = -0.1;
    else if(power < -0.3)
      power = -0.3;
    else if(power > 0.3)
    Robot.driveTrain.gyroDriveX(RobotMap.gyro, power, currentAngle);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(error) <= 3;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.drive(0 ,0 ,0 ,false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
