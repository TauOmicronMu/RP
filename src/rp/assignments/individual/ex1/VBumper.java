package rp.assignments.individual.ex1;

import java.util.ArrayList;

import lejos.robotics.RangeFinder;
import lejos.robotics.Touch;
import rp.config.RangeFinderDescription;
import rp.robotics.DifferentialDriveRobot;
import rp.robotics.EventBasedTouchSensor;
import rp.robotics.TouchSensorEvent;
import rp.robotics.TouchSensorEventSource;
import rp.robotics.TouchSensorListener;

public class VBumper implements Touch, TouchSensorEventSource, EventBasedTouchSensor {
	
	private RangeFinderDescription desc;
	private RangeFinder ranger;
	private Float touchRange;
	private ArrayList<TouchSensorListener> listeners;
	private boolean press;
	private Thread mainThread;

	public VBumper(RangeFinderDescription _desc, RangeFinder _ranger, Float _touchRange) {
		this.desc = _desc;
		this.ranger = _ranger;
		this.touchRange = _touchRange;
		
		this.listeners = new ArrayList<>();
		
		this.mainThread = new Thread( () -> run() );
		this.mainThread.setDaemon(true);
		this.mainThread.start();
		
	}

	@Override
	public void addTouchSensorListener(TouchSensorListener _listener) {
		this.listeners.add(_listener);
	}

	@Override
	public boolean isPressed() {
		return (this.ranger.getRange() <= this.touchRange + this.desc.getNoise());
	}
	
	public void run() {
		while(true){
			if(!this.press){
				if(this.ranger.getRange() <= this.touchRange + this.desc.getNoise()) {
					for(TouchSensorListener item : listeners) {
						item.sensorPressed(new TouchSensorEvent(0.00F, 100.00F));
						this.press = true;
					}
				}
			}
			else{
				if(this.ranger.getRange() > this.touchRange + this.desc.getNoise()) {
					for(TouchSensorListener item : listeners) {
					    item.sensorReleased(new TouchSensorEvent(100.00F, 0.00F));	
					    item.sensorBumped(new TouchSensorEvent(100.00F, 0.00F));
					    this.press = false;
					}
				}
			}
		}
	}
}
