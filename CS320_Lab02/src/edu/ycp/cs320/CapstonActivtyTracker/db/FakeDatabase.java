package edu.ycp.cs320.CapstonActivtyTracker.db;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.CapstoneActivityTracker.model.*;

public class FakeDatabase {
	List<AdminAccount> adminList;
	List<StudentAccount> studentList;
	List<TopTeam> topTeamList;
	List<Room> roomList;
	//List<SubTeam> subTeamList;
	//List<RoomEvent> roomEventList;
	

	public FakeDatabase() {
		//initialize hard coded models
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
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
