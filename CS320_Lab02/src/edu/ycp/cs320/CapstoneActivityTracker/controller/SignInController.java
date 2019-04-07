package edu.ycp.cs320.CapstoneActivityTracker.controller;

import edu.ycp.cs320.CapstonActivtyTracker.db.*;
import edu.ycp.cs320.CapstoneActivityTracker.model.Account;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;


public class SignInController {
	private FakeDatabase fdb;
	private Account model;
	
	public SignInController() {
		fdb = new FakeDatabase();
		fdb.init();
	}
	
	public Account getModel() {
		return model;
	}
	
	public boolean validateCredentials(String email, String password) {
		if(fdb.verifyAccount(email, password)) {
			this.model = fdb.getAccountWithEmail(email);
			return true;
		}
		return false;
	}
}
