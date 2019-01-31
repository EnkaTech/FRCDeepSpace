/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Spark;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  /**
   * Changes the input value to match the desired range. This is especially useful
   * when getting distance values from encoders and rangefinders.
   * 
   * @param x       Input value
   * @param in_min  Minimum known value of the input
   * @param in_max  Maximum known value of the input
   * @param out_min Desired output value for in_min
   * @param out_max Desired output value for in_max
   */
  public static double map(double x, double in_min, double in_max, double out_min, double out_max) {
    return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
  }

  private static AnalogInput rangeInput = new AnalogInput(0);

  public static double getDistance() {
    double dist = rangeInput.getVoltage();
    dist = RobotMap.map(dist, 0.28, 4.67, 0.2, 5.5) * 100;
    return dist;
  }

  public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  /**
   * Channel 0 - Front Left 
   * Channel 1 - Rear Left 
   * Channel 8 - Front Right 
   * Channel 9 - Rear Right
   */

  public static SpeedController dtFrontLeft = new Spark(0);
  public static SpeedController dtFrontRight = new Spark(9);
  public static SpeedController dtRearLeft = new Spark(1);
  public static SpeedController dtRearRight = new Spark(8);

  public static Relay LEDArray = new Relay(0, Relay.Direction.kForward);
  public static boolean lightsOn = false;
  public static double wantedAngle;

  public static MecanumDrive driveTrain;
  public static double fastSpd = 0.5;
  public static double slowSpd = 0.2;

}
