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
    private Double interiorAngle;
	
	public ShapeDriverController(DifferentialDriveRobot _robot, Float _sideLength, int _sideNumber) {
		this.robot = _robot;
		this.sideLength = _sideLength;
		this.sideNumber = _sideNumber;
		
		this.interiorAngle = ((sideNumber - 2) * 180)/sideNumber;
		
	}

	@Override
	public void run() {
		for(int i = 0 ; i < sideNumber ; i++) {
			this.robot.getDifferentialPilot().travel(1.0);
			this.robot.getDifferentialPilot().rotate(this.interiorAngle);
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

}
