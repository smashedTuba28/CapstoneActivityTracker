package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.ycp.cs320.CapstoneActivityTracker.model.Room;

public class Team {

	String teamname;
	
	//initialization
	public Team() {
	}
	
	//if a team name is input then it is set immediately
	public Team(String teamname){
		this.teamname = teamname;
	}
	
	public String getTeamname() {
		return teamname;
	}
	
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
}
