package rp.assignments.individual.ex1;

import rp.robotics.DifferentialDriveRobot;
import rp.systems.StoppableRunnable;

public class SolutionFactory {
    
    public static StoppableRunnable createEquilateralTriangleController(DifferentialDriveRobot _robot, Float _sideLength) {
        return ShapeDriverController(_robot, _sideLength, 3);
    }

	public static StoppableRunnable createSquareController( DifferentialDriveRobot _robot, Float _sideLength) {
		return ShapeDriverController(_robot, _sideLength, 4);
    }
    
    public static StoppableRunnable createDecagonController(DifferentialDriveRobot _robot, Float _sideLength) {
        return ShapeDriverController(_robot, _sideLength, 10);
    }

}