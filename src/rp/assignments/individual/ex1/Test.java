package rp.assignments.individual.ex1;

import javax.swing.JFrame;

import org.junit.runner.JUnitCore;

import lejos.robotics.navigation.Pose;
import rp.robotics.DifferentialDriveRobotPC;
import rp.robotics.simulation.MapBasedSimulation;
import rp.robotics.simulation.SimulatedRobots;
import rp.robotics.testing.TestMaps;
import rp.robotics.testing.TestViewer;
import rp.robotics.testing.ZoneSequenceTestWithSim;
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

	public void run() throws ClassNotFoundException {
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

	public static void main(String[] args) throws ClassNotFoundException {
		//Run a Triangle Test
		/*
		Ex1Tests tests = new Ex1Tests();
		ZoneSequenceTestWithSim<DifferentialDriveRobotPC, ?> test = tests.createTriangleTest();
		TestViewer demo = new TestViewer(test, test.getSimulation());
		demo.run();
		*/
		 
		
		//Run a Square Test
		/*
		Ex1Tests tests2 = new Ex1Tests();
		ZoneSequenceTestWithSim<DifferentialDriveRobotPC, ?> test2 = tests2.createSquareTest();
		TestViewer demo2 = new TestViewer(test2, test2.getSimulation());
		demo2.run();
		*/
		
		//Run a Decagon Test
		/*
		Ex1Tests tests3 = new Ex1Tests();
		ZoneSequenceTestWithSim<DifferentialDriveRobotPC, ?> test3 = tests3.createDecagonTest();
		TestViewer demo3 = new TestViewer(test3, test3.getSimulation());
		demo3.run();
		*/
		
		//Run a Bumper Test
		/*
		Ex1Tests tests4 = new Ex1Tests();
		ZoneSequenceTestWithSim<DifferentialDriveRobotPC, ?> test4 = tests4.createBumperTest();
		TestViewer demo4 = new TestViewer(test4, test4.getSimulation());
		demo4.run();
		*/
		
		Test test = new Test();
		test.run();
		
	}

}