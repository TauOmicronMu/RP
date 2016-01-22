package rp.assignments.individual.ex1;

import lejos.robotics.navigation.DifferentialPilot;
import rp.robotics.DifferentialDriveRobot;
import rp.robotics.TouchSensorEvent;
import rp.robotics.TouchSensorListener;
import rp.systems.ControllerWithTouchSensor;
import rp.systems.StoppableRunnable;
import rp.util.Rate;

public class BumperRobot implements ControllerWithTouchSensor{

	private final DifferentialDriveRobot robot;
	private final DifferentialPilot pilot;
	private boolean isRunning;
    private boolean isBumped;
	
	public BumperRobot(DifferentialDriveRobot _robot) {
		this.robot = _robot;
		this.pilot = this.robot.getDifferentialPilot();
		
		this.isRunning = false;
		this.isBumped = false;	
	}

	@Override
	public void run() {
		isRunning = true;
		
		while(isRunning) {
			//Move the pilot 1m forward.
			this.pilot.travel(1.0, true);
			
			//Define a rate of 50ms
			Rate rate = new Rate(50);
			
			//If the pilot is moving and bumped, sleep the pilot.
			if(this.pilot.isMoving() && this.isBumped) {
				rate.sleep();
			}
			
			//If the pilot is bumped, reverse and spin.
			if(this.isBumped) {
				this.pilot.stop();
				this.pilot.travel(-0.20, false);
				this.pilot.rotate(180.0);
			}
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
