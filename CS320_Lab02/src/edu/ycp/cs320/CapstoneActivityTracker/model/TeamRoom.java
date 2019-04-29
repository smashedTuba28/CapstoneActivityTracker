package edu.ycp.cs320.CapstoneActivityTracker.model;

public class TeamRoom {

	private int teamID;
	private int roomID;
	
	public TeamRoom() {
	}
	
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	public int getTeamID() {
		return teamID;
	}
	
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public int getRoomID() {
		return roomID;
	}
}