package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.Date;

public class Room {
	int number;
	String name;
	Date open, close;
	
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
	
	public void setOpenTime(Date open) {
		this.open = open;
	}
	
	public Date getOpenTime() {
		return open;
	}
	
	public void setCloseTime(Date close) {
		this.close = close;
	}
	
	public Date getCloseTime() {
		return close;
	}
	
}
