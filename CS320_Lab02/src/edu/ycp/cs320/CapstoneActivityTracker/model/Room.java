package edu.ycp.cs320.CapstoneActivityTracker.model;


public class Room {
	
	//number of the room
	private int number, roomID, subTeamID;
	
	//name of the room
	String name;
	
	
	
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

	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}	
	public int getRoomID() {
		return roomID;
	}

	public void setSubTeamID(Integer subTeamID) {
		this.subTeamID = subTeamID;
	}
	public int subTeamID() {
		return subTeamID;
	}
}
