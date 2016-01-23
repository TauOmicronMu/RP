package rp.assignments.individual.ex1;

import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import rp.robotics.DifferentialDriveRobot;
import rp.robotics.TouchSensorEvent;
import rp.robotics.TouchSensorListener;
import rp.systems.ControllerWithTouchSensor;
import rp.systems.StoppableRunnable;
import rp.util.Rate;

public class BumperRobot implements ControllerWithTouchSensor {

	private final DifferentialDriveRobot robot;
	private final DifferentialPilot pilot;
	private boolean isRunning;
    private boolean isBumped;
	
	public BumperRobot(DifferentialDriveRobot _robot) {
		this.robot = _robot;
		this.pilot = this.robot.getDifferentialPilot();
		
		this.pilot.setTravelSpeed(0.4);
		
		this.isRunning = false;
		this.isBumped = false;	
	}

	@Override
	public void run() {
		isRunning = true;
		/*
		if(isRunning) {
		   this.pilot.travel(1.0, true);
		}
		*/
		while(isRunning) {
			this.pilot.travel(1.0, true);
			Delay.msDelay(40);
			
			//If the pilot is bumped, reverse and spin.
			if(this.isBumped) {
				this.pilot.stop();
				this.pilot.travel(-0.20, false);
				this.pilot.rotate(180.0);
				this.isBumped = false;
			}
			//this.run();	
		}
	}

	@Override
	public void stop() {
		this.isRunning = false;
	}

	@Override
	public void sensorPressed(TouchSensorEvent _e) {
		this.isBumped = true;
		
	}

	@Override
	public void sensorReleased(TouchSensorEvent _e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sensorBumped(TouchSensorEvent _e) {
		// TODO Auto-generated method stub
		
	}

}
