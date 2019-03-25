package edu.ycp.cs320.CapstonActivtyTracker.db;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.ycp.cs320.CapstoneActivityTracker.model.*;

public class FakeDatabase {
	private List<AdminAccount> adminList;
	private List<StudentAccount> studentList;
	private List<TopTeam> topTeamList;
	private List<Room> roomList;
	private List<Account> accountList;
	private List<Week> weekList;
	private List<Log> logList;
	//List<SubTeam> subTeamList;
	//List<RoomEvent> roomEventList;
	
	public FakeDatabase() {
	}

	public void init() {
		//initialize hard coded models
		accountList = new ArrayList<Account>();
		adminList = new ArrayList<AdminAccount>();
		studentList = new ArrayList<StudentAccount>();
		topTeamList = new ArrayList<TopTeam>();
		//subTeamList = new ArrayList<SubTeam>();
		roomList = new ArrayList<Room>();
		//roomEventList = new ArrayList<RoomEvent>();	
		weekList = new ArrayList<Week>();
		logList = new ArrayList<Log>();
		
		//create fake data for hard coded examples
		adminList.add(new AdminAccount("John", "Doe", "jdoe@ycp.edu", "password" , "900000000", true ));
		studentList.add(new StudentAccount("Jason", "Steinberg", "jsteinberg@ycp.edu", "password", "900000001", false));
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
		topTeamList.add(new TopTeam("The Office"));
		
		//create subTeams 
		topTeamList.get(0).addSubTeam(new SubTeam("Controls"));
		topTeamList.get(0).addSubTeam(new SubTeam("Aircraft Design"));
		topTeamList.get(1).addSubTeam(new SubTeam("Party Planning Committee"));
		topTeamList.get(1).addSubTeam(new SubTeam("Finer Things Club"));
		topTeamList.get(1).addSubTeam(new SubTeam("Scott's Tots"));
		
		topTeamList.get(0).addRoom(roomList.get(0));//add Power Systems Lab to Drone Team
		topTeamList.get(0).addRoom(roomList.get(1));//add Computer Lab to Drone Team
		
		topTeamList.get(1).addRoom(roomList.get(1));//add Computer Lab to Office
		topTeamList.get(1).addRoom(roomList.get(2));//add Software Engineering Lab
		topTeamList.get(1).addRoom(roomList.get(3));//add Visualization Lab 
		
		//populate existing subTeams
		topTeamList.get(0).addMemberToSubTeam(studentList.get(0), "Controls");
		topTeamList.get(0).addMemberToSubTeam(studentList.get(1), "Controls");
		topTeamList.get(0).addMemberToSubTeam(studentList.get(2), "Aircraft Design");
		topTeamList.get(0).addMemberToSubTeam(studentList.get(3), "AirCraft Design");
		
		//create durations for several students
		
		weekList.add(new Week("120", "140", "120", "375", "800", "840", "240"));
		weekList.add(new Week("0", "0", "247", "390", "690", "720", "240"));
		weekList.add(new Week("120", "30", "120", "420", "600", "840", "240"));
		weekList.add(new Week("480", "480", "480", "480", "0", "0", "480"));
		
		logList.add(new Log("This is a test log for Jason",
				"This is a second test log for Jason",
				"This is a third.. yada yada",
				"We need a fake database???????",
				"Creating fake database",
				"testing fake database",
				"nailed fake database"));
		logList.add(new Log("This is a test log for Travis",
				"This is a second test log for Travis",
				"creating lots of models and lots of tests",
				"We need a fake database???????",
				"Creating lots more tests and polishing models",
				"Creating Slides and Schema",
				""));
		logList.add(new Log("This is a test log for Bill",
				"This is a second test log for Bill",
				"Making some JSPs",
				"We need a fake database???????",
				"Using JavaScript to make hardcoded graphs",
				"Using JavaScript to use models for graph data",
				"Proving I nailed it with demo"));
		logList.add(new Log("Fear Plays an interestinf rle in our lives",
				"How dare we let it motivate us?",
				"How dare we let it into our decision making",
				"We need a fake database???????",
				"into our livelihoods, into our relationships",
				"it's funny isn't it",
				"we take a day a year to dress up in costume and celebrate fear"));
	}
	
	//verifies an account exists with email and password
	public boolean verifyAccount(String email, String password) {
		for(Account a : accountList) {
			if (a.getEmail().equals(email) && a.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	//Verifies account using email and schholID
	public boolean verifyAccountWithEmailSchoolID(String email, String schoolID) {
		for(Account a : accountList) {
			if (a.getEmail().equals(email) && a.getSchoolID().equals(schoolID)) {
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
	
	public SubTeam findSubTeam(String subTeamName) {
		for(TopTeam team: topTeamList) {
			for (SubTeam sub: topTeamList.get(topTeamList.indexOf(team)).getSubTeams()){
				if(sub.getTeamname().equals(subTeamName)) {
					//if sub team name matches input name then return
					return sub;
				}
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
	
	public void removeTopTeam(String name) {
		TopTeam top = findTopTeam(name);
		if(top == null) {
			throw new NoSuchElementException();
		}
		else {
			topTeamList.remove(top);
		}
		
	}
	
	public void removeSubTeam(String name) {
		TopTeam top = findTopTeamOfSubTeam(name);
		if(top == null) {
			throw new NoSuchElementException();
		}
		else {
			SubTeam sub = findSubTeam(name);
			topTeamList.get(topTeamList.indexOf(top)).removeSubTeam(sub);
		}
	}
	
	
	public void assignTeamRoom(String teamname, String roomname){
		Team team = findTopTeam(teamname);
		Room room = findRoom(roomname);
		if(room == null || team == null) {
			throw new NoSuchElementException();
		}
		else {
			team.addRoom(room);
			team.getRooms().get(0);
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
	
	public void removeRoomFromTeam(String teamname, String roomname) {
		Team team = findTopTeam(teamname);
		Room room = findRoom(roomname);
		//checking if any inputs return a null value
		if(room == null || team == null) {
			throw new NoSuchElementException();
		}
		else{
			team.removeRoom(room);
		}
	}
	
	public List<TopTeam> findAllTeamsWithRoom(String roomname) {
		ArrayList<TopTeam> teams = new ArrayList<TopTeam>();
		for(TopTeam t: topTeamList) {
			for(Room room: t.getRooms()) {
				if (room.getRoomName().equals(roomname)) {
					teams.add(t);
					break;
				}
			}
		}
		return teams;
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
			//remove room from database
			roomList.remove(room);
			
			//remove room from all teams
			List<TopTeam> teams = findAllTeamsWithRoom(roomname);
			for(TopTeam t: teams) {
				t.removeRoom(room);
			}
		}
	}
	
	public List<Account> getAllAccounts(){
		return accountList;
	}
	
	public List<AdminAccount> getAllAdminAccounts(){
		return adminList;
	}
	
	public List<StudentAccount> getAllStudentAccounts(){
		return studentList;
	}
	
	public List<TopTeam> getAllTopTeams(){
		return topTeamList;
	}
	
	public List<Room> getAllRooms(){
		return roomList;
	}	
	
	public List<Log> getAllLogs(){
		return logList;
	}
	
	public List<Week> getAllWeek(){
		return weekList;
	}
	
}