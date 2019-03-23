package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.ycp.cs320.CapstoneActivityTracker.model.Room;

public class Team {
	
	List<StudentAccount> students;
	List<Room> rooms;
	
	String teamname;
	
	//initialization
	public Team() {
		rooms = new ArrayList<Room>();
		students = new ArrayList<StudentAccount>();
	}
	
	//if a team name is input then it is set immediately
	public Team(String teamname){
		this.teamname = teamname;
		rooms = new ArrayList<Room>();
		students = new ArrayList<StudentAccount>();
	}
	
	public String getTeamname() {
		return teamname;
	}
	
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	
	public void addRoom(String roomname) {
		Room room = findRoom(roomname);
		rooms.add(room);
	}
	
	public List<Room> getRooms(){
		return rooms;
	}
	
	//removing a room from the list of rooms
	public void removeRoom(String roomname) {
		Room room = findRoom(roomname);
		if(room == null) {
			 throw new NoSuchElementException();
		}
		else {
			rooms.remove(room);
		}
	}
	
	//finding a room in the list of rooms
	public Room findRoom(String roomname) {
		for(Room room: this.rooms) {
			if(room.getRoomName().equals(roomname)) {
				return room;
			}
		}
		return null;
	}
	
	//adding a member to the team
	public void addMember(StudentAccount member) {
		students.add(member);
	}
		
	/*
	public void removeMember(String member) {
		
	}
	*/
	
	/*
	 * public String findMember(String membername) {
		for(String member: this.studentIDs) {
			if(member..equals(roomname)) {
				return room;
			}
		}
		return null;
	}*/
	
}
