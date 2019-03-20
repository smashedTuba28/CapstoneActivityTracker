package edu.ycp.cs320.CapstoneActivityTracker.model;


import java.util.List;
import java.util.NoSuchElementException;

import edu.ycp.cs320.CapstoneActivityTracker.model.Team;
import edu.ycp.cs320.CapstoneActivityTracker.model.Room;

public class AdminAccount {

	private List<Team> teams;
	private List<Room> rooms;

	public AdminAccount() {
	
	}

	public void createTeam(String teamname) {
		teams.add(new Team(teamname));
	}

	public Team findTeam(String teamname) {
		for(Team team: this.teams) {
			if(team.getTeamname().equals(teamname)) {
				return team;
			}
		}
		return null;
	}

	public void removeTeam(String teamname){
		Team team = findTeam(teamname);
		if(team == null) {
			 throw new NoSuchElementException();
		}
		else {
			teams.remove(team);
		}
	}

	public void createRoom(String roomname) {
		rooms.add(new Room(roomname));
	}

	public Room findRoom(String roomname) {
		for(Room room: this.rooms) {
			if(room.getRoomname().equals(roomname)) {
				return room;
			}
		}
		return null;
	}

	public void removeRoom(String roomname){
		Room room = findRoom(roomname);
		if(room == null) {
			 throw new NoSuchElementException();
		}
		else {
			rooms.remove(room);
		}
	}

	public void assignTeamRoom(String teamname, String roomname) {
		Team team = findTeam(teamname);
		Room room = findRoom(roomname);
		if(room == null || team == null) {
			throw new NoSuchElementException();
		}
		else {
			team.addRoom(room);
		}
		
	}
}
