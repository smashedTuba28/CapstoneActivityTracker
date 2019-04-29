package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.ArrayList;
import java.util.List;

public class SubTeam extends Team{
	//SubTeam has no SubTeams, this is the lowest level of Team with simple getters and setters

	private int topTeamID;
	
	public SubTeam() {
		super();
	}
	
	public SubTeam(String subteamName, Integer topTeamID, Integer subTeamID) {
		super(subteamName, subTeamID);
		this.topTeamID = topTeamID;
	}
	
	public void setTopTeamID(int topTeamID) { 
		this.topTeamID = topTeamID;
	}
	public int getTopTeamID() {
		return topTeamID;
	}
}