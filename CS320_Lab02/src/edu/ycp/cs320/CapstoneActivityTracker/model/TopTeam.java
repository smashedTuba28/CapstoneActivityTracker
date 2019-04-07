package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.ArrayList;
import java.util.List;

public class TopTeam extends Team{

	//TopTeam is a Team with SubTeams
	
	private List<SubTeam> subTeams;
	
	public TopTeam() {
		subTeams = new ArrayList<SubTeam>();
	}
	
	public TopTeam(String name) {
		super(name);
		subTeams = new ArrayList<SubTeam>();
	}
	
	public List<SubTeam> getSubTeams() {
		return subTeams;
	}
	
	//setting all subTeams
	public void setTeams(ArrayList<SubTeam> subTeams) {
		this.subTeams = subTeams;
	}
	
	//adding a subTeam to a list of teams
	public void addSubTeam(SubTeam subteam) {
		subTeams.add(subteam);
	}
	
	//removing a subTeam from the list of subTeams
	public void removeSubTeam(SubTeam subteam) {
		subTeams.remove(subteam);
	}
	
	public void addRoomToAllSubTeams(Room room) {
		for(SubTeam s: subTeams) {//run through loop to add room to all subTeams
			s.addRoom(room);
		}
	}
	
}
