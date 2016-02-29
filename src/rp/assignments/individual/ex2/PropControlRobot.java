package rp.assignments.individual.ex2;

import lejos.robotics.RangeFinder;
import lejos.robotics.navigation.DifferentialPilot;
import rp.config.RangeFinderDescription;
import rp.robotics.DifferentialDriveRobot;
import rp.robotics.simulation.SimulationCore;
import rp.systems.StoppableRunnable;

public class PropControlRobot implements StoppableRunnable {
    private final DifferentialDriveRobot robot;
    private final DifferentialPilot pilot;
    private final SteppableController steppable;
    
    private SimulationCore core;
    
    public PropControlRobot(DifferentialDriveRobot robot,
    		                float maxDistance,
    		                RangeFinder ranger,
    		                RangeFinderDescription desc) {
    	
    	this.steppable = new SteppableController(ranger, robot, maxDistance);
    	this.robot = robot;
    	this.pilot = this.robot.getDifferentialPilot();
    }

	@Override
	public void run() {
		this.pilot.forward();
		this.core.addAndWaitSteppable(this.steppable, 5);
		this.pilot.stop();
	}

	@Override
	public void stop() {
		this.steppable.stop();
	}
	
	public void setSimulation(SimulationCore core) {
		this.core = core;
	}
}
