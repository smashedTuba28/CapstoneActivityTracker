package edu.ycp.cs320.CapstoneActivityTracker.controller;

import edu.ycp.cs320.CapstonActivtyTracker.db.*;
import edu.ycp.cs320.CapstoneActivityTracker.model.Account;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;


public class SignInController {
	private IDatabase db;
	private Account model = null;
	
	public SignInController() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	
	public void setModel(Account model) {
		this.model = model;
	}
	
	public Account getModel() {
		return model;
	}
	
	public boolean validateCredentials(String email, String password, String accountType) {
		if (accountType.equals("admin")) {
			model = db.verifyAdminAccount(email, password);
			return true;
		}
		else if (accountType.equals("student")) {
			System.out.println(password);
			return true;
		}
		return false;
	}
}
