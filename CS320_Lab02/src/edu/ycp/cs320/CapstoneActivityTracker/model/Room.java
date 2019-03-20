package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.Date;

public class Room {
	int number;
	String roomname;
	Date open, close;
	
	public Room(String roomname){
		this.roomname = roomname;
	}
	
	public String getRoomname() {
		return roomname;
	}
	
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	
	public int getRoomnumber() {
		return number;
	}
	
	public void setRoomnumber(int number) {
		this.number = number;
	}
}
