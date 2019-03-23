package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.Date;
import java.time.*;

public class Room {
	
	//number of the room
	int number;
	
	//name of the room
	String name;
	
	//does not account for daylight savings, open time is the time that the room opens in the morning, close time is the time that the room closes
	LocalTime open, close;
	
	public Room() {
	
	}
	
	//immediately intializing the room with the name and number
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
