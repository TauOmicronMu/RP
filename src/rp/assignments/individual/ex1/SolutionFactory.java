package rp.assignments.individual.ex1;

import rp.robotics.DifferentialDriveRobot;
import rp.robotics.EventBasedTouchSensor;
import rp.systems.ControllerWithTouchSensor;
import rp.systems.StoppableRunnable;
import lejos.robotics.RangeFinder;
import rp.assignments.individual.ex1.ShapeDriverController;
import rp.config.RangeFinderDescription;

public class SolutionFactory {
    
	public static StoppableRunnable createEquilateralTriangleController( DifferentialDriveRobot _robot, Float _sideLength) {
        return new ShapeDriverController(_robot, _sideLength, 3);
    }

	public static StoppableRunnable createSquareController( DifferentialDriveRobot _robot, Float _sideLength) {
		return new ShapeDriverController(_robot, _sideLength, 4);
    }
    
	public static StoppableRunnable createDecagonController( DifferentialDriveRobot _robot, Float _sideLength) {
        return new ShapeDriverController(_robot, _sideLength, 10);
    } 
    
	public static ControllerWithTouchSensor createBumperController(DifferentialDriveRobot _robot) {
		return new BumperRobot(_robot);
	} 
	
	public static EventBasedTouchSensor createVirtualBumper(RangeFinderDescription _desc, RangeFinder _ranger, Float _touchRange) {
		return new VBumper(_desc, _ranger, _touchRange);
	}

}