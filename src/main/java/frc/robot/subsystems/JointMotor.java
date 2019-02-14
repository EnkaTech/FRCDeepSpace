/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class JointMotor extends PIDSubsystem {
  /**
   * Add your docs here.
   */
  private Encoder m_encoder = RobotMap.angleEncoder;
  private SpeedController m_motor = RobotMap.jointMotor;
  public JointMotor() {
    // Intert a subsystem name and PID values here
    super("Angle Motor", 0.08, 0, 0);
    setAbsoluteTolerance(2);
    setOutputRange(-0.5, 0.5);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return m_encoder.getDistance();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    m_motor.set(output);
  }
}
