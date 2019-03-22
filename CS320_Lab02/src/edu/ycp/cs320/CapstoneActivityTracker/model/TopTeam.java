package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.ArrayList;
import java.util.List;

public class TopTeam extends Team{

	private List<SubTeam> subTeams;
	
	public TopTeam() {
		subTeams = new ArrayList<SubTeam>();
	}
	
	public List<SubTeam> getTeams() {
		return subTeams;
	}
	
	public void setTeams(ArrayList<SubTeam> subTeams) {
		this.subTeams = subTeams;
	}
	
	public void addSubTeam(SubTeam subteam) {
		subTeams.add(subteam);
	}
	
	public void removeSubTeam(SubTeam subteam) {
		subTeams.remove(subteam);
	}
	
}
