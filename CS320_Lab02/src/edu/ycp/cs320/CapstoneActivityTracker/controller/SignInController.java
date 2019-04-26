package edu.ycp.cs320.CapstoneActivityTracker.controller;

import edu.ycp.cs320.CapstonActivtyTracker.db.*;
import edu.ycp.cs320.CapstoneActivityTracker.model.Account;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;


public class SignInController {
	private IDatabase db;
	private Account model;
	
	public SignInController() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	
	public Account getModel() {
		return model;
	}
	
	public boolean validateCredentials(String email, String password) {
		if(db.verifyAccount(email, password)) {
			//this.model = db.getAccountWithEmail(email);
			return true;
		}
		return false;
	}
}
