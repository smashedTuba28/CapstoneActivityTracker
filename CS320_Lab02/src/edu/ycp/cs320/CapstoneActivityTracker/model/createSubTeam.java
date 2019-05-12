package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.List;

public class createSubTeam {
	//createSubTeam page needs to get topteams the top team chosen to assign the correct topTeam to the SubTeam being created
	private List<TopTeam> topTeamList;
	private TopTeam topTeam;
	
	public TopTeam getTopTeam() {
		return topTeam;
	}
	public void setTopTeam(TopTeam topTeam) {
		this.topTeam = topTeam;
	}
	public List<TopTeam> getTopTeamList() {
		return topTeamList;
	}
	public void setTopTeamList(List<TopTeam> topTeamList) {
		this.topTeamList = topTeamList;
	}
}
