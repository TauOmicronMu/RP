package rp.assignments.individual.ex1;

import javax.swing.JFrame;

import org.junit.runner.JUnitCore;

import lejos.robotics.navigation.Pose;
import rp.robotics.DifferentialDriveRobotPC;
import rp.robotics.simulation.MapBasedSimulation;
import rp.robotics.simulation.SimulatedRobots;
import rp.robotics.testing.TestMaps;
import rp.robotics.visualisation.KillMeNow;
import rp.robotics.visualisation.MapVisualisationComponent;
import rp.systems.StoppableRunnable;

/**
 * Demonstrates the use, and visualisation, of a simulated simple robot on a
 * line map.
 * 
 * @author Nick Hawes
 *
 */
public class Test {

	public void run() {
    /*
		// Create the simulation using the given map. This simulation can run
		// with a GUI.
		MapBasedSimulation sim = new MapBasedSimulation(TestMaps.EMPTY_8_x_6);

		// Add a robot of a given configuration to the simulation. The return
		// value is the object you can use to control the robot.
		//
		// The dimensions of the simulated robot are defined in metres, thus all
		// other parts of your code should use metres too.
		DifferentialDriveRobotPC robot = sim.addRobot(
				SimulatedRobots.EXPRESS_BOT_WITH_SENSORS, new Pose(0.5f, 0.5f,
						0));

		// This is the controller that actually makes the robot move
		StoppableRunnable controller = SolutionFactory
				.createEquilateralTriangleController(robot, 1.0f);


		// Create visualisation JComponent that renders map, robots etc
		MapVisualisationComponent viz = MapBasedSimulation
				.createVisulation(sim);

		// Add the visualisation to a JFrame to display it
		displayVisualisation(viz);

		// Start the controller running
		controller.run();
		
	    */
		
		org.junit.runner.Result result = JUnitCore
				.runClasses(rp.assignments.individual.ex1.Ex1Tests.class);
			System.out.println((result.getRunCount() - result.getFailureCount()) + "/" + (result.getRunCount()) + " tests successful");
			for (org.junit.runner.notification.Failure failure : result.getFailures()) {
				System.out.println(failure.toString());
			}

	}

	public static JFrame displayVisualisation(MapVisualisationComponent viz) {
		// Create a frame to contain the viewer
		JFrame frame = new JFrame("Simulation Viewer");

		// Add visualisation to frame
		frame.add(viz);
		frame.addWindowListener(new KillMeNow());

		frame.pack();
		frame.setSize(viz.getMinimumSize());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		return frame;
	}

	public static void main(String[] args) {
		Test demo = new Test();
		demo.run();
	}

}