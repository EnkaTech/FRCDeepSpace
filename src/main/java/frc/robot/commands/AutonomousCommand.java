/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {
  /**
   * An autonomous code that draws a square
   * 
   * @param choice The method to choose
   */
  public AutonomousCommand(int choice) {
    addSequential(new DriveToDistance(320));
    addSequential(new GyroTurn(45));
    addSequential(new DriveToDistance(27));
  }
}
