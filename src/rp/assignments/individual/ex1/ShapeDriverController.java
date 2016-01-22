package rp.assignments.individual.ex1;

import rp.robotics.DifferentialDriveRobot;
import rp.systems.StoppableRunnable;

/**
 * Creates a controller that drives a robot around a 2D shape with a given side length and number of sides.
 * @author txg523
 *
 */
public class ShapeDriverController implements StoppableRunnable {

	private DifferentialDriveRobot robot;
    private Float sideLength;
    private int sideNumber;
    private Double angle;
	
	public ShapeDriverController(DifferentialDriveRobot _robot, Float _sideLength, int _sideNumber) {
		this.robot = _robot;
		this.sideLength = _sideLength;
		this.sideNumber = _sideNumber;
		
		this.angle = 360.0/(double)sideNumber;
		
	}

	@Override
	public void run() {
		for(int i = 0 ; i < sideNumber ; i++) {
			this.robot.getDifferentialPilot().travel(this.sideLength);
			this.robot.getDifferentialPilot().rotate(this.angle);
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

}
