package edu.ycp.cs320.CapstoneActivityTracker.controller;

import edu.ycp.cs320.CapstonActivtyTracker.db.DatabaseProvider;
import edu.ycp.cs320.CapstonActivtyTracker.db.DerbyDatabase;
import edu.ycp.cs320.CapstonActivtyTracker.db.IDatabase;

public class CreateSubTeamController {
	private IDatabase db;
	
	public CreateSubTeamController() {
		
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	public boolean createSubTeam(String subTeamname, String topTeamID) {
		if(subTeamname.equals("null") || topTeamID.equals("null")) {
			return false;
		}
		return db.createSubTeam(subTeamname, Integer.parseInt(topTeamID));
	}
}
