/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
  public static DriveTrain driveTrain = new DriveTrain();
  public static OI IO;
  Timer timer;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {

    RobotMap.driveTrain = new MecanumDrive(RobotMap.dtFrontLeft, RobotMap.dtRearLeft, RobotMap.dtFrontRight,
        RobotMap.dtRearRight);
    IO = new OI();
    timer = new Timer();
    RobotMap.gyro.calibrate();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called once before autonomous
   */
  @Override
  public void autonomousInit() {
  
    timer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    //Scheduler.getInstance().run();
    
  
      /*
        if(timer.get()<=2.0) driveTrain.drive(-0.4, 0.0, 0.0, false); 
        else if((timer.get()>2.0) && (timer.get()<=3.0)) driveTrain.drive(0.0, 0.0, -0.6, false);
        else if(timer.get()<=5.0) driveTrain.drive(-0.4, 0.0, 0.0, false);
        else if(timer.get()<=6) driveTrain.drive(0.0, 0.0, -0.6, false);
        else if(timer.get()<=8) driveTrain.drive(-0.4, 0.0, 0.0, false);
        else if(timer.get()<=9) driveTrain.drive(0.0, 0.0, -0.6, false);
        else if(timer.get()<=11) driveTrain.drive(-0.4, 0.0, 0.0, false);
        else{driveTrain.drive(0.0,0.0,0.0,false);} */
      
    
      
        if(timer.get()<=2.0) driveTrain.drive(-0.4, 0.0, 0.0, false);
        else if(timer.get()<=6) driveTrain.drive(0.0, -0.4, 0.0, false);
        else if(timer.get()<= 8) driveTrain.drive(0.4, 0.0, 0.0, false);
        else if (timer.get()<=12) driveTrain.drive(0.0, 0.4, 0.0, false);
        else driveTrain.drive(0.0, 0.0, 0.0, false);
         
        
        ;
        
  }

  @Override
  public void teleopInit() {
    RobotMap.gyro.reset();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
