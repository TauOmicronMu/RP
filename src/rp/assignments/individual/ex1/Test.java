package rp.assignments.individual.ex1;

import org.junit.runner.JUnitCore;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.net.httpserver.Authenticator.Failure;

import rp.robotics.simulation.MapBasedSimulation;
import rp.robotics.testing.TestMaps;
import rp.robotics.testing.TestViewer;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException {
		org.junit.runner.Result result = JUnitCore
				.runClasses(rp.assignments.individual.ex1.Ex1Tests.class);
			
			System.out.println((result.getRunCount() - result.getFailureCount()) + "/" + (result.getRunCount()) + " tests successful");
			for (org.junit.runner.notification.Failure failure : result.getFailures()) {
				System.out.println(failure.toString());
			}
	}
 
}
