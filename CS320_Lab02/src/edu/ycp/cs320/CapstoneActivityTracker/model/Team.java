package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.ycp.cs320.CapstoneActivityTracker.model.Room;

public class Team {

	List<Room> rooms;
	String teamname;
	
	public Team() {
		rooms = new ArrayList<Room>();
	}
	
	public Team(String teamname){
		this.teamname = teamname;
		rooms = new ArrayList<Room>();
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
	
	public List<Room> getRooms(){
		return rooms;
	}
	
	public void removeRoom(String roomname) {
		Room room = findRoom(roomname);
		if(room == null) {
			 throw new NoSuchElementException();
		}
		else {
			rooms.remove(room);
		}
	}
	
	public Room findRoom(String roomname) {
		for(Room room: this.rooms) {
			if(room.getRoomName().equals(roomname)) {
				return room;
			}
		}
		return null;
	}
	
	
	
}
