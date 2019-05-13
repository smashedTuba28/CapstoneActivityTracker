package edu.ycp.cs320.CapstoneActivityTracker.controller;
import edu.ycp.cs320.CapstoneActivityTracker.model.*;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.CapstonActivtyTracker.db.DatabaseProvider;
import edu.ycp.cs320.CapstonActivtyTracker.db.DerbyDatabase;
import edu.ycp.cs320.CapstonActivtyTracker.db.IDatabase;
import edu.ycp.cs320.CapstoneActivityTracker.model.TopTeam;

public class CreateSubTeamController {
	
	private IDatabase db;
	private List<TopTeam> topTeamsList;
	private CreateSubTeam model;
	private String topteamname;
	
	
	public CreateSubTeamController() {
		
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	
	public boolean createSubTeam(String subTeamname, String topTeamname) {
		if(subTeamname.equals("null") || topTeamname.equals("null")) {
			return false;
		}
		
		//getting top team id with the given teamname from the servlet
		TopTeam topTeam = db.getTopTeamWithTeamname(topTeamname);
		
		System.out.println("Top Team Name and ID from controller: " + topTeam.getTeamname() + ", " + topTeam.getTeamID());
		//setting the model TopTeam to topTeam
		model.setTopTeam(topTeam);
		//doing this step instead of calling DB again to optimize system calls
		int topTeamID = model.getTopTeam().getTeamID();
		//returning either true or false based on whether the data is valid or not
		return db.createSubTeam(subTeamname, topTeamID);
	}
	
	public List<TopTeam> getTopTeamsList() {
		return db.getAllTopTeams();
	}
	
	public void setModel(CreateSubTeam model) {
		this.model = model;
	}
	
	public void populateModelWithTopTeams(){
		List<TopTeam> topTeamList = new ArrayList<TopTeam>();
		
		topTeamList = db.getAllTopTeams();
		model.setTopTeamList(topTeamList);
	}
	
	public String getTopteamname() {
		return topteamname;
	}
	
	public void setTopteamname(String topteamname) {
		this.topteamname = topteamname;
	}
}
