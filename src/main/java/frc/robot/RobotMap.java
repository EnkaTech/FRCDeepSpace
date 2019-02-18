/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

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
  // Sensorler
  private static AnalogInput rangeInput = new AnalogInput(0);
  // Elevator encoder (AMT-103V)
  public static Encoder elevatorEncoder = new Encoder(0, 1, false, EncodingType.k4X);
  // 1 tur = 3.224cm 
  private static double elevatorPPR = 2048;
  public static double elevatorDPP = (1 / elevatorPPR) * 6.2;
  // Angle encoder (Hall effect)
  public static Encoder angleEncoder = new Encoder(2, 3, true, EncodingType.k4X);
  private static double anglePPR = 7;
  public static double angleDPP = (1 / anglePPR) * 0.74;
  //4.468 = 1 tur cimcoder 20 ppr
  /*
  front right
  5v turuncu
  ch a kahve
  ch b siyah
  toprk kirmizi
 rear left
  5v kırmızı
  ch a kahve
  ch b siyah
  toprak turuncu
  */
  public static Encoder rearLeftEncoder = new Encoder(4,6,true,EncodingType.k4X);
  public static Encoder frontRightEncoder = new Encoder(7,8,true,EncodingType.k4X);
  private static double cimPPR = 20;
  public static double cimDPP = (1 / cimPPR) * 4.468;
  

  public static double getDistance() {
    double dist = rangeInput.getVoltage();
    dist = RobotMap.map(dist, 0.28, 4.67, 0.2, 5.5) * 100;
    return dist;
  }

  public static ADIS16448_IMU gyro = new ADIS16448_IMU();
  // DriveTrain
  public static SpeedController dtFrontLeft = new WPI_VictorSPX(4);
  public static SpeedController dtFrontRight = new WPI_VictorSPX(3);
  public static SpeedController dtRearLeft = new WPI_TalonSRX(1);
  public static SpeedController dtRearRight = new WPI_TalonSRX(2);

  // Asansor
  private static SpeedController elevatorMotor1 = new VictorSP(5);
  private static SpeedController elevatorMotor2 = new VictorSP(1);
  public static SpeedControllerGroup elevatorMotors = new SpeedControllerGroup(elevatorMotor1, elevatorMotor2);

  // Gripper Tekerleri
  public static SpeedController wheel1 = new VictorSP(2);
  public static SpeedController wheel2 = new VictorSP(3);

  // Açı motoru
  public static SpeedController jointMotor = new WPI_VictorSPX(5);

  // Solenoidler
  public static DoubleSolenoid modeSolenoid = new DoubleSolenoid(4, 5);
  public static DoubleSolenoid hatchSolenoid = new DoubleSolenoid(6, 7);

  public static Relay LEDArray = new Relay(0, Relay.Direction.kForward);
  public static boolean lightsOn = false;
  public static double wantedAngle;

  public static MecanumDrive driveTrain;
  public static double fastSpd = 0.5;
  public static double slowSpd = 0.2;

}
