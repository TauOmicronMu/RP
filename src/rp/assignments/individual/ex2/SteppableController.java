package rp.assignments.individual.ex2;

import java.time.Duration;
import java.time.Instant;

import lejos.robotics.RangeFinder;
import lejos.robotics.navigation.DifferentialPilot;
import rp.robotics.DifferentialDriveRobot;
import rp.robotics.simulation.SimulationSteppable;

public class SteppableController implements SimulationSteppable {
    private final RangeFinder ranger;
    private final DifferentialDriveRobot robot;
    private final DifferentialPilot pilot;
    private final float maxDistance;
    private final double initialSpeed;
    
    private final double upperBound;
    private final double lowerBound;
    
    private float currentDistance;
    private float previousDistance;
    private float distanceDiff;
    private double currentSpeed;
    private double speedDiff;
    private double timeDiff;
	private boolean removed;
	
	public SteppableController(RangeFinder ranger, 
			               DifferentialDriveRobot robot,
			               float maxDistance) {
		
		this.ranger = ranger;
		this.robot = robot;
		this.pilot = this.robot.getDifferentialPilot();
		this.maxDistance = maxDistance;
		this.initialSpeed = this.pilot.getMaxTravelSpeed();
		
		this.upperBound = 0.9 * this.maxDistance;
		this.lowerBound = 0.7 * this.maxDistance;
		
		this.currentDistance = 0.0F;
		this.previousDistance = 0.0F;
		this.speedDiff = 0;
		this.distanceDiff = 0.0F;
		this.timeDiff = 0;
		this.removed = false;
		this.currentSpeed = this.initialSpeed;
	}
    
    
	
	@Override
	public boolean remove(Instant _now, Duration _stepInterval) {
		return this.removed;
	}
	
	public void stop() {
		this.removed = true;
	}

	@Override
	public void step(Instant _now, Duration _stepInterval) {
	    /*
	     * Get the current distance to the nearest object.
	     */
		this.currentDistance = this.ranger.getRange();
		
		/*
		 * Work out the difference in distance between this step and the 
		 * previous step.
		 */
		this.distanceDiff = this.currentDistance - this.previousDistance;
	    
		/*
		 * Convert the time difference between the current step and the 
		 * previous step from nanoseconds to seconds (taking into account
		 * both the second and nanosecond parts of _stepInterval).
		 */
		this.timeDiff = (double) (((_stepInterval.getNano()) / 1e9) + _stepInterval.getSeconds());
		
		/*
		 * Calculate the change in speed of the wall (in relation to the robot).
		 * If the wall has accelerated, this will be +ve;
		 * If the wall has decelerated, this will be -ve;
		 */
		this.speedDiff = this.distanceDiff/this.timeDiff;
		
		/*
		 * Change the travel speed of the robot, proportional to the distance 
		 * from the wall.
		 */
		this.currentSpeed += this.speedDiff;
		
		/*
		 * Adjust the speed to ensure that it lies within the constraint:
		 * 0 < speed < max speed
		 */
		this.currentSpeed = (this.currentSpeed > this.pilot.getMaxTravelSpeed() || this.currentDistance > this.upperBound) ? this.pilot.getMaxTravelSpeed() : this.currentSpeed;
		this.currentSpeed = (this.currentSpeed < 0 || this.currentDistance < this.lowerBound) ? 0 : this.currentSpeed;
		
		/*
		 * Set the pilot's speed to the new current speed.
		 */
		this.pilot.setTravelSpeed(this.currentSpeed);
		
		/*
		 * The current distance is now the previous distance, so assign it as such.
		 */
		this.previousDistance = this.currentDistance;
	}
	
}
