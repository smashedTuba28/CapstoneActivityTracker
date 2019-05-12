package edu.ycp.cs320.CapstoneActivityTracker.controller;

import edu.ycp.cs320.CapstonActivtyTracker.db.DatabaseProvider;
import edu.ycp.cs320.CapstonActivtyTracker.db.DerbyDatabase;
import edu.ycp.cs320.CapstonActivtyTracker.db.IDatabase;

public class CreateTopTeamController {
	
	private IDatabase db;
	
	public CreateTopTeamController() {
		
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
		
	}
	
	public boolean createTopTeam(String teamname) {
		
		if(teamname.equals("null")) {
			return false;
		}
		
		return db.createTopTeam(teamname);
		
	}
}
