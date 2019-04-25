package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.ArrayList;
import java.util.List;

public class SubTeam extends Team{
	//SubTeam has no SubTeams, this is the lowest level of Team with simple getters and setters

	private SubTeam subTeam;
	List<StudentAccount> students;
	List<Room> rooms;
	
	
	private int topTeamID, accountID;
	
	public SubTeam() {
		super();
		rooms = new ArrayList<Room>();
		students = new ArrayList<StudentAccount>();
	}
	
	public SubTeam(String subteamName) {
		super(subteamName);
		rooms = new ArrayList<Room>();
		students = new ArrayList<StudentAccount>();
	}
	
	public SubTeam getSubTeam() {
		return subTeam;
	}
	public List<StudentAccount> getStudents(){
		return students;
	}
	public List<Room> getRooms(){
		return rooms;
	}
	
	public void setSubTeam(SubTeam subTeam) {
		this.subTeam = subTeam;
	}
	public void addStudent(StudentAccount student) {
		this.students.add(student);
	}
	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	
	
	
	public void setTopTeamID(int topTeamID) {
		this.topTeamID = topTeamID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	
	public int getTopTeamID() {
		return topTeamID;
	}
	public int getAccountID() {
		return accountID;
	}
}
