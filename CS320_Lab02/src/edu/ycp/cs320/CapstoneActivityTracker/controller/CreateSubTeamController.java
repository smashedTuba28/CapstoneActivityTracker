package edu.ycp.cs320.CapstoneActivityTracker.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.CapstonActivtyTracker.db.DatabaseProvider;
import edu.ycp.cs320.CapstonActivtyTracker.db.DerbyDatabase;
import edu.ycp.cs320.CapstonActivtyTracker.db.IDatabase;
import edu.ycp.cs320.CapstoneActivityTracker.model.TopTeam;

public class CreateSubTeamController {
	
	private IDatabase db;
	private List<TopTeam> topTeamsList;
	
	public CreateSubTeamController() {
		
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	public boolean createSubTeam(String subTeamname, String topTeamname) {
		if(subTeamname.equals("null") || topTeamname.equals("null")) {
			return false;
		}
		//getting top team id with the given teamname from the servlet
		int topTeamID = db.getTopTeamWithTeamname(topTeamname).getTeamID();
		//returning either true or false based on whether the data is valid or not
		return db.createSubTeam(subTeamname, topTeamID);
	}
	public List<TopTeam> getTopTeamsList() {
		return db.getAllTopTeams();
	}
	
}
