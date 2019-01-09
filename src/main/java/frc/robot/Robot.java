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
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutonomousCommand;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static NetworkTable table;
  public static DriveTrain driveTrain = new DriveTrain();
  public static CameraServer cameraServer;
  public static UsbCamera camera;
  public static OI IO;
  Timer timer;
  SendableChooser<Integer> autoChooser;

  @Override
  public void robotInit() {

    RobotMap.driveTrain = new MecanumDrive(RobotMap.dtFrontLeft, RobotMap.dtRearLeft, RobotMap.dtFrontRight,
        RobotMap.dtRearRight);
    IO = new OI();
    timer = new Timer();
    autoChooser = new SendableChooser<Integer>();
    autoChooser.setDefaultOption("Method 1", 1);
    autoChooser.addOption("Method 2", 2);
    SmartDashboard.putData("Auto mode", autoChooser);
    RobotMap.gyro.calibrate();
    cameraServer = CameraServer.getInstance();
    camera = cameraServer.startAutomaticCapture();
    camera.setResolution(640, 480);
    table = NetworkTableInstance.getDefault().getTable("imgproc");
  }

  @Override
  public void robotPeriodic() {
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
    timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    new AutonomousCommand(autoChooser.getSelected()).start();

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
