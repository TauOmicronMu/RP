package rp.assignments.individual.ex2;

import lejos.robotics.RangeFinder;
import rp.config.RangeFinderDescription;
import rp.robotics.DifferentialDriveRobot;
import rp.systems.StoppableRunnable;

public class SolutionFactory {
	public static StoppableRunnable createRangeController( DifferentialDriveRobot _robot, RangeFinderDescription _desc, RangeFinder _ranger, Float _maxDistance) {
		return new PropControlRobot(_robot, _maxDistance, _ranger, _desc);
	}
}