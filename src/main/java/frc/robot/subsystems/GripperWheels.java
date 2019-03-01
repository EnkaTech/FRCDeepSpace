/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class GripperWheels extends Subsystem {

  private SpeedController m_wheel1 = RobotMap.wheel1;
  private SpeedController m_wheel2 = RobotMap.wheel2;

  @Override
  public void initDefaultCommand() {
  }

  public void intake(double x) {
    m_wheel1.set(-x);
    m_wheel2.set(x);
  }
}
