package edu.ycp.cs320.CapstonActivtyTracker.db;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.ycp.cs320.CapstoneActivityTracker.model.*;

public class FakeDatabase {
	List<AdminAccount> adminList;
	List<StudentAccount> studentList;
	List<TopTeam> topTeamList;
	List<Room> roomList;
	List<Account> accountList;
	//List<SubTeam> subTeamList;
	//List<RoomEvent> roomEventList;
	

	public FakeDatabase() {
		//initialize hard coded models
		accountList = new ArrayList<Account>();
		adminList = new ArrayList<AdminAccount>();
		studentList = new ArrayList<StudentAccount>();
		topTeamList = new ArrayList<TopTeam>();
		//subTeamList = new ArrayList<SubTeam>();
		roomList = new ArrayList<Room>();
		//roomEventList = new ArrayList<RoomEvent>();		
		
		//create fake data for hard coded examples
		adminList.add(new AdminAccount("John", "Doe", "Jdoe@ycp.edu", "password" , "900000000", true ));
		studentList.add(new StudentAccount("Jason", "Steinberg", "jsteinerg@ycp.edu", "password", "900000001", false));
		studentList.add(new StudentAccount("Travis", "Wetzel", "twetzel1@ycp.edu", "password", "900000002", false));
		studentList.add(new StudentAccount("William", "Taylor", "wtaylor1@ycp.edu", "password", "900000003", false));
		studentList.add(new StudentAccount("Robert", "California", "lizardking@ycp.edu", "password", "900000004", false));
		
		accountList.add(adminList.get(0));
		accountList.add(studentList.get(0));
		accountList.add(studentList.get(1));
		accountList.add(studentList.get(2));
		accountList.add(studentList.get(3));
		
		roomList.add(new Room("Power Systems Lab", 128));
		roomList.add(new Room("Computer Lab", 132));
		roomList.add(new Room("Software Engineering Lab", 119));
		roomList.add(new Room("Visualization Lab", 118));
		
		//create team
		topTeamList.add(new TopTeam("Drone Team"));
		
		//create subTeams 
		topTeamList.get(0).addSubTeam(new SubTeam("Controls"));
		topTeamList.get(0).addSubTeam(new SubTeam("Aircraft Design"));
		
		//populate existing subTeams
		topTeamList.get(0).addMemberToSubTeam(studentList.get(0), "Controls");
		topTeamList.get(0).addMemberToSubTeam(studentList.get(1), "Controls");
		topTeamList.get(0).addMemberToSubTeam(studentList.get(2), "Aircraft Design");
		topTeamList.get(0).addMemberToSubTeam(studentList.get(4), "AirCraftDesign");
	}
	
	//verifies an account exists with given credentials 
	public boolean verifyAccount(String email, String password) {
		for(Account a : accountList) {
			if (a.getEmail().equals(email) && a.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	//creates a new faculty or student account accordingly
	public void createAccount(String firstname, String lastname, String email, String password, String schoolID, boolean faculty) {
		if (faculty) {
			AdminAccount admin = new AdminAccount(firstname, lastname, email, password, schoolID, faculty);
			adminList.add(admin);
			accountList.add(admin);
		}
		else {
			StudentAccount student = new StudentAccount(firstname, lastname, email, password, schoolID, faculty);
			studentList.add(student);
			accountList.add(student);
		}
	}
	
	//creates a new Top Team
	public void createTopTeam(String teamname) {
		topTeamList.add(new TopTeam(teamname));
	}
	
	
	public void createSubTeam(String topname, String subname){
		TopTeam top = findTopTeam(topname);
		if (top == null) {
			throw new NoSuchElementException();
		}
		else {
			topTeamList.get(topTeamList.indexOf(top)).addSubTeam(new SubTeam(subname));
		}
	}
	
	
	//looks through both subteams and topteams
	//returns whichever is needed
	public Team findTeam(String teamname) {
		//iterates through top team
		for(TopTeam team: topTeamList) {
			if(team.getTeamname().equals(teamname)) {
				//if top teams name matches inputname return
				return team;
			}
			//for each top team check all sub teams
			for (SubTeam sub: topTeamList.get(topTeamList.indexOf(team)).getSubTeams()){
				if(sub.getTeamname().equals(teamname)) {
					//if sub team name matches input name then return
					return sub;
				}
			}
		}
		//team doesn't exist
		return null;
	}
	
	public TopTeam findTopTeam(String topname) {
		for (TopTeam top : topTeamList ) {
			if (top.getTeamname().equals(topname)) {
				return top;
			}
		}
		return null;
	}
	
	
	//find the top team that owns the sub team
	public TopTeam findTopTeamOfSubTeam(String subTeamName) {
		for(TopTeam team: topTeamList) {
			for (SubTeam sub: topTeamList.get(topTeamList.indexOf(team)).getSubTeams()){
				if(sub.getTeamname().equals(subTeamName)) {
					//if sub team name matches input name then return
					return team;
				}
			}
		}
		return null;
	}
	
	//handles both sub team and top team
	public void removeTeam(String teamname){
		Team team = findTeam(teamname);
		if(team == null) {
			 throw new NoSuchElementException();
		}
		else if(team.getClass().getName().equals(TopTeam.class.getName())) {//class type is a TopTeam
			topTeamList.remove(team);
		}
		else {//not null or topteam means its a subteam
			TopTeam top = findTopTeamOfSubTeam(teamname);
			//wont be null because subTeams already known to exist
			//and sub teams can't exist without topTeams
			
			//get team using indexOf then remove subTeam from it
			topTeamList.get(topTeamList.indexOf(top)).removeSubTeam((SubTeam)team);			
		}
	}
	
	
	public void assignTeamRoom(String teamname, String roomname){
		Team team = findTeam(teamname);
		Room room = findRoom(roomname);
		if(room == null || team == null) {
			throw new NoSuchElementException();
		}
		else {
			team.addRoom(roomname);
		}
	}
	
	public Room findRoom(String roomname) {
		for (Room r: roomList) {
			if (r.getRoomName().equals(roomname)) {
				return r;
			}
		}
		return null;
	}
	
	public void removeTeamRoom(String teamname, String roomname) {
		Team team = findTeam(teamname);
		Room room = findRoom(roomname);
		//checking if any inputs return a null value
		if(room == null || team == null) {
			throw new NoSuchElementException();
		}
		else{
			team.removeRoom(roomname);
		}
	}
	
	public void createRoom(String roomname, int roomNumber) {
		roomList.add(new Room(roomname, roomNumber));
	}
	
	public void removeRoom(String roomname) {
		Room room = findRoom(roomname);
		if(room == null) {
			throw new NoSuchElementException();
		}
		else {
			roomList.remove(room);
		}
	}
	
	
	
	
}