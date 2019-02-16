/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutonomousCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.GripperWheels;
import frc.robot.subsystems.HatchHolder;
import frc.robot.subsystems.JointMotor;
import frc.robot.subsystems.ModeSwitcher;
import frc.robot.subsystems.TempElevator;
import frc.robot.subsystems.TempJoint;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Compressor compressor = new Compressor();
  public static NetworkTable table;
  public static HatchHolder holder = new HatchHolder();
  public static Elevator elevator = new Elevator();
  public static TempElevator tElevator = new TempElevator();
  public static TempJoint tJoint = new TempJoint();
  public static JointMotor joint = new JointMotor();
  public static ModeSwitcher switcher = new ModeSwitcher();
  public static DriveTrain driveTrain = new DriveTrain();
  public static GripperWheels gripperWheels = new GripperWheels();
  public static CameraServer cameraServer;
  public static UsbCamera camera;
  public static OI IO;
  SendableChooser<Integer> autoChooser;

  @Override
  public void robotInit() {
    RobotMap.dtFrontLeft.setInverted(true);
    //RobotMap.dtRearLeft.setInverted(true);
    RobotMap.driveTrain = new MecanumDrive(RobotMap.dtFrontLeft, RobotMap.dtRearLeft, RobotMap.dtFrontRight,
        RobotMap.dtRearRight);
    IO = new OI();
    autoChooser = new SendableChooser<Integer>();
    autoChooser.setDefaultOption("Method 1", 1);
    autoChooser.addOption("Method 2", 2);
    SmartDashboard.putData("Auto mode", autoChooser);
    RobotMap.gyro.calibrate();
    cameraServer = CameraServer.getInstance();
    camera = cameraServer.startAutomaticCapture();
    camera.setResolution(640, 480);
    table = NetworkTableInstance.getDefault().getTable("imgproc");
    RobotMap.elevatorEncoder.setDistancePerPulse(RobotMap.elevatorDPP);
    compressor.setClosedLoopControl(true);
    RobotMap.angleEncoder.setDistancePerPulse(RobotMap.angleDPP);
  }

  @Override
  public void robotPeriodic() {
    /*if(compressor.getPressureSwitchValue())
      compressor.setClosedLoopControl(false);
    else if(IO.joy2.getRawButtonPressed(8))
      compressor.setClosedLoopControl(!compressor.getClosedLoopControl());
      */
    SmartDashboard.putNumber("Distance", RobotMap.getDistance());
    SmartDashboard.putNumber("Wanted angle", RobotMap.wantedAngle);
    SmartDashboard.putNumber("Elevator height", -RobotMap.elevatorEncoder.getDistance() + 26);
    SmartDashboard.putNumber("Joint angle", RobotMap.angleEncoder.getDistance());
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    RobotMap.gyro.reset();
    new AutonomousCommand(autoChooser.getSelected()).start();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();

  }

  @Override
  public void teleopInit() {
    RobotMap.gyro.reset();
  }

  @Override
  public void teleopPeriodic() {
  Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
