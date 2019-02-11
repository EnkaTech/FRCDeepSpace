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
import frc.robot.commands.ChangeMode;
import frc.robot.commands.DisableElevator;
import frc.robot.commands.Eject;
import frc.robot.commands.Intake;
import frc.robot.commands.ManualElevator;
import frc.robot.commands.ManualJoint;
import frc.robot.commands.SetElevatorHeight;
import frc.robot.commands.ToggleLights;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public Joystick joy1 = new Joystick(0);
  public Joystick joy2 = new Joystick(1);
  public Button j1_3 = new JoystickButton(joy1, 3);
  public Button j1_11 = new JoystickButton(joy1, 11);
  public Button j1_9  = new JoystickButton(joy1, 9);
  public Button j1_10 = new JoystickButton(joy1, 10);
  public Button j1_7 = new JoystickButton(joy1, 7);
  public Button j1_8 = new JoystickButton(joy1, 8);
  public Button j1_5 = new JoystickButton(joy1, 5);
  public Button j1_6 = new JoystickButton(joy1, 6);
  public Button j1_4 = new JoystickButton(joy1, 4);
  public Button j1_12 = new JoystickButton(joy1, 12);
  public Button j2_1 = new JoystickButton(joy2, 1);
  public Button j2_2 = new JoystickButton(joy2, 2);
  public Button j2_3 = new JoystickButton(joy2, 3);
  public Button j2_4 = new JoystickButton(joy2, 4);
  public Button j2_5 = new JoystickButton(joy2, 5);

  public OI() {
    j1_3.whenPressed(new ChangeMode(true));
    j1_4.whenPressed(new ChangeMode(false));
    j1_12.whenPressed(new Eject());
    j1_11.whenPressed(new Align());
    j1_9.whileHeld(new Intake(true));
    j1_10.whileHeld(new Intake(false));
    j1_7.whileHeld(new ManualElevator(0.1));
    j1_8.whileHeld(new ManualElevator(-0.2));
    j1_6.whileHeld(new ManualJoint(0.4));
    j1_5.whileHeld(new ManualJoint(-0.4));
    j2_1.whenPressed(new SetElevatorHeight(30));
    j2_2.whenPressed(new SetElevatorHeight(90));
    j2_3.whenPressed(new DisableElevator());
    j2_4.whenPressed(new SetElevatorHeight(60));
    j2_5.whenPressed(new ToggleLights());
  }
}
