package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.ArrayList;
import java.util.List;

public class TopTeam extends Team{

	//TopTeam is a Team with SubTeams
	
	private List<SubTeam> subTeams;
	
	public TopTeam() {
		subTeams = new ArrayList<SubTeam>();
	}
	
	public List<SubTeam> getTeams() {
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
	
}