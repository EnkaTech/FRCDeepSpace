/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoHatch extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoHatch(int level){
    this(level, true);
  }
  public AutoHatch(int level, boolean eject) {
    //TODO: Asansor yuksekligi ayarlanacak
   // addSequential(new Align());
    addSequential(new ChangeMode(true));
    switch(level) {
      case 1:
        addSequential(new SetElevatorHeight(30));
        break;
      case 2:
        addSequential(new SetElevatorHeight(105));
        break;
      case 3:
        addSequential(new SetElevatorHeight(165));
    }
    //Timer.delay(0.2);
    if(eject)
      addSequential(new Eject());
      addSequential(new SetElevatorHeight(26));
      addSequential(new DisableElevator());
  }
}
