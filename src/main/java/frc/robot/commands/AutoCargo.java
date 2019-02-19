/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCargo extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoCargo(int level) {
    // addSequential(new Align());
    addSequential(new Approach(90));
    addSequential(new ChangeMode(false));
    switch(level) {
      case 1:
        addSequential(new SetElevatorHeight(77));
        addSequential(new Intake(0.7), 1);
        break;
    
      case 2:
        addSequential(new SetElevatorHeight(145));
        addSequential(new Intake(0.7), 1);
        break;
      case 3:
        addSequential(new SetElevatorHeight(181));
        addSequential(new SetJointAngle(30));
        addSequential(new Intake(0.8), 1.5);
        break;
      
      case 4:
        addSequential(new SetElevatorHeight(120));
        addSequential(new SetJointAngle(-12));
        addSequential(new Intake(0.7), 1);
      default:
        break;
    }
    addSequential(new SetJointAngle(0));
    addSequential(new SetElevatorHeight(29));
    addSequential(new DisableElevator());
    addSequential(new DisableJoint());
    addSequential(new ChangeMode(true));
  }
}
