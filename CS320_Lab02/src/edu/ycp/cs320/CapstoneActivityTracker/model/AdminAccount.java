package edu.ycp.cs320.CapstoneActivityTracker.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.ycp.cs320.CapstoneActivityTracker.model.Team;
import edu.ycp.cs320.CapstoneActivityTracker.model.Room;

public class AdminAccount extends Account{

	private List<Team> teams;
	private List<Room> rooms;

	//initializing teams and rooms along with the super class to null values
	public AdminAccount() {
		super();
		teams = new ArrayList<Team>();
		rooms = new ArrayList<Room>();
	}
	
	//itializing super class with input values immediately
	public AdminAccount(String firstname, String lastname, String email, String password, String schoolID, boolean faculty) {
		super(firstname, lastname, email, password, schoolID, faculty);
	}

	/* method now in db
	//creating a team and adding to the list of teams
	public void createTeam(String teamname) {
		teams.add(new Team(teamname));
	}*/
	
	/* method now in db
	//finding a team within the list
	public Team findTeam(String teamname) {
		for(Team team: this.teams) {
			if(team.getTeamname().equals(teamname)) {
				return team;
			}
		}
		return null;
	}
	 */
	//removing a team from the list of teams
	
	/* moved to DB
	public void removeTeam(String teamname){
		Team team = findTeam(teamname);
		if(team == null) {
			 throw new NoSuchElementException();
		}
		else {
			teams.remove(team);
		}
	}
	*/
	
	
	//creating a room and adding the room to the list of rooms
	public void createRoom(String roomname, int number) {
		rooms.add(new Room(roomname, number));
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

	//removing a room from the list of rooms by the room name
	public void removeRoom(String roomname){
		Room room = findRoom(roomname);
		if(room == null) {
			 throw new NoSuchElementException();
		}
		else {
			rooms.remove(room);
		}
	}

	//assigning a room to a team
	/* moved to db
	public void assignTeamRoom(String teamname, String roomname){
		Team team = findTeam(teamname);
		Room room = findRoom(roomname);
		if(room == null || team == null || teamname == null || roomname == null) {
			throw new NoSuchElementException();
		}
		else {
			team.addRoom(roomname);
		}
	}
	*/
	
	/* moved to db
	//removing a room by finding both team in the list of teams and room in a list of rooms
	public void removeTeamRoom(String teamname, String roomname) {
		Team team = findTeam(teamname);
		Room room = findRoom(roomname);
		//checking if any inputs return a null value
		if(teamname == null || roomname == null || room == null || team == null) {
			throw new NoSuchElementException();
		}
		else{
			team.removeRoom(roomname);
		}
	}
	*/
	
	//adding team to list of teams
	public void addTeam(Team team){
		teams.add(team);
	}
	
	
	public List<Team> getTeams(){
		return teams;
	}
	
	//adding a room to the list of rooms
	public void addRoom(Room room){
		rooms.add(room);
	}
	
	public List<Room> getRooms(){
		return rooms;
	}
	
	//adding a member to a team passed through along with the student's ID
	public void addMemberToTeam(StudentAccount student, Team team) {
		team.addMember(student);
	}

	
	
}
