/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public static ADIS16448_IMU gyro = new ADIS16448_IMU();

  private static int frontL = 0;
  private static int frontR = 2;
  private static int rearL = 1;
  private static int rearR = 3;

  public static SpeedController dtFrontLeft = new Spark(frontL);
  public static SpeedController dtFrontRight = new Spark(frontR);
  public static SpeedController dtRearLeft = new Spark(rearL);
  public static SpeedController dtRearRight = new Spark(rearR);

  public static MecanumDrive driveTrain;
  public static double fastSpd = 0.9;
  public static double slowSpd = 0.4;
  public static boolean isFieldOriented = false;

}
