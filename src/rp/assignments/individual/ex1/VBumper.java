package rp.assignments.individual.ex1;

import java.util.ArrayList;

import lejos.robotics.RangeFinder;
import lejos.robotics.Touch;
import rp.config.RangeFinderDescription;
import rp.robotics.DifferentialDriveRobot;
import rp.robotics.TouchSensorEvent;
import rp.robotics.TouchSensorEventSource;
import rp.robotics.TouchSensorListener;

public class VBumper implements Touch, TouchSensorEventSource {
	
	private RangeFinderDescription desc;
	private RangeFinder ranger;
	private Float touchRange;
	private ArrayList<TouchSensorListener> listeners;
	private boolean press;

	public VBumper(RangeFinderDescription _desc, RangeFinder _ranger, Float _touchRange) {
		this.desc = _desc;
		this.ranger = _ranger;
		this.touchRange = _touchRange;
	}

	@Override
	public void addTouchSensorListener(TouchSensorListener _listener) {
		this.listeners.add(_listener);
	}

	@Override
	public boolean isPressed() {
		return (this.ranger.getRange() <= this.touchRange);
	}
	
	public void run() {
		while(true){
			if(this.ranger.getRange() <= this.touchRange) {
				for(TouchSensorListener item : listeners) {
					item.sensorPressed(new TouchSensorEvent(0.00F, 100.00F));
					this.press = true;
				}
			}
			else if(this.press){
				for(TouchSensorListener item : listeners) {
				    item.sensorReleased(new TouchSensorEvent(100.00F, 0.00F));	
				    item.sensorBumped(new TouchSensorEvent(100.00F, 0.00F));
				    this.press = false;
				}
			}
		}
	}
}
