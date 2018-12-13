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

/**
 * A command to drive the robot with the first joystick
 */
public class JoystickDrive extends Command {
  public JoystickDrive() {
    requires(Robot.driveTrain);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.driveTrain.drive(Robot.IO.joy1, Robot.IO.joy1.getRawButton(2) ? RobotMap.slowSpd : RobotMap.fastSpd,
        Robot.IO.joy1.getRawButton(1));
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.driveTrain.drive(0, 0, 0, false);
  }

  @Override
  protected void interrupted() {
  }
}
