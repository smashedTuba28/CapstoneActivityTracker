package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.Date;
import java.time.*;

public class Room {
	int number;
	String name;
	LocalTime open, close;	//does not account for daylight savings
	
	public Room() {
	}
	
	public Room(String roomname, int number){
		this.name = roomname;
		this.number = number;
	}
	
	public String getRoomName() {
		return name;
	}
	
	public void setRoomName(String roomname) {
		this.name = roomname;
	}
	
	public int getRoomNumber() {
		return number;
	}
	
	public void setRoomNumber(int number) {
		this.number = number;
	}	
	
	public void setOpenTime(LocalTime open) {
		this.open = open;
	}
	
	public LocalTime getOpenTime() {
		return open;
	}
	
	public void setCloseTime(LocalTime close) {
		this.close = close;
	}
	
	public LocalTime getCloseTime() {
		return close;
	}
	
	
}
