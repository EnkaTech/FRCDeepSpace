/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Align;
import frc.robot.commands.ToggleLights;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public Joystick joy1 = new Joystick(0);
  public Button j1_3 = new JoystickButton(joy1, 3);
  public Button j1_11 = new JoystickButton(joy1, 11);

  public OI() {
    j1_3.whenPressed(new ToggleLights());
    j1_11.whenPressed(new Align());
  }
}
