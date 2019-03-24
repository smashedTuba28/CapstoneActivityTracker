package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.time.LocalDateTime;
import java.time.temporal.*;

public class RoomEvent {
	
	//start time and end time of the room event
	private LocalDateTime start, end;
	
	//duration of the time in roomevent
	private long duration;
	
	//each room event is numbers incrementally
	private int number;
	
	//lognote for roomevent with information about the event
	private String lognote;
	
	//flag for when the student has stayed in the room past a certain time or amount of hours
	private boolean flag;
	
	//RoomEvent starts at a later time, this just intializes the RoomEvent
	public RoomEvent() {
		flag = false;
	}
	
	//RoomEvent starts immediately when this constructor is called
	public RoomEvent(LocalDateTime start) {
		this.start = start;
		flag = false;
	}
	
	public LocalDateTime getEndTime() {
		return end;
	}
	
	public void setEndTime(LocalDateTime end) {
		this.end = end;
	}
	
	public LocalDateTime getStartTime() {
		return start;
	}
	
	public void setStartTime(LocalDateTime start) {
		this.start = start;
	}

	public boolean getFlag() {
		return flag;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public long getDuration() {
		return duration;
	}
	
	public void setDuration() {
		this.duration = ChronoUnit.MINUTES.between(start, end);
	}
	
	public String getLognote() {
		return lognote;
	}
	
	public void setLognote(String lognote) {
		this.lognote = lognote;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
}
