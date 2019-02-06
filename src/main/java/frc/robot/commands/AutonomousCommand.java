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
    switch (choice) {
    case 2:
      addSequential(new GyroDriveX(1.3, true, 0));
      addSequential(new GyroDriveY(2.6, false, 0));
      addSequential(new GyroDriveX(1.3, false, 0));
      addSequential(new GyroDriveY(2.6, true, 0));
      break;
    case 1:
    default:
      // The traditional way
      addSequential(new GyroDriveX(0.4, true, 0));
      addSequential(new GyroTurn(90));
      addSequential(new GyroDriveX(0.8, true, 90));
      addSequential(new GyroTurn(180));
      addSequential(new GyroDriveX(0.4, true, 180));
      addSequential(new GyroTurn(270));
      addSequential(new GyroDriveX(0.8, true, 270));
      break;
    }
  }
}
