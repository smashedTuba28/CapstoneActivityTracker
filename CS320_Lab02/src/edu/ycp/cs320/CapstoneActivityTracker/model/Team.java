package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.List;
import edu.ycp.cs320.CapstoneActivityTracker.model.Room;

public class Team {

	List<Room> rooms;
	String teamname;
	
	public Team(String teamname){
		this.teamname = teamname;
	}
	
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}
	
}
