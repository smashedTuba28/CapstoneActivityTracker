package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.time.temporal.ChronoUnit;
import java.util.Date;

public class RoomEvent {
	
	//start time and end time of the room event
	private Date start, end;
	
	//each room event is numbers incrementally
	private int event_id;
	
	private int studentAccountID, roomID;
	
	
	//lognote for roomevent with information about the event
	private String lognote;
	
	//flag for when the student has stayed in the room past a certain time or amount of hours
	private boolean flag;
	
	//RoomEvent starts at a later time, this just intializes the RoomEvent
	public RoomEvent() {
		flag = false;
	}
	
	//RoomEvent starts immediately when this constructor is called
	public RoomEvent(Date start) {
		this.start = start;
		flag = false;
	}
	
	public Date getEndTime() {
		return end;
	}
	
	public void setEndTime(Date end) {
		this.end = end;
	}
	
	public Date getStartTime() {
		return start;
	}
	
	public void setStartTime(Date start) {
		this.start = start;
	}

	public boolean getFlag() {
		return flag;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public long getDuration() {
		return (end.getTime() - start.getTime()) / 60000 ; // duration = milliseconds / (60000 ms/min)
	}
	
	public String getLognote() {
		return lognote;
	}
	
	public void setLognote(String lognote) {
		this.lognote = lognote;
	}
	
	public int getRoomEventID() {
		return event_id;
	}
	
	public void setRoomEventID(int number) {
		this.event_id = number;
	}
	
	
	public void setStudentAccountID(int studentAccountID) {
		this.studentAccountID = studentAccountID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public int getStudentAccountID() {
		return studentAccountID;
	}
	public int getRoomID() {
		return roomID;
	}
	
}
