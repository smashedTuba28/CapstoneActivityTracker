package edu.ycp.cs320.CapstoneActivityTracker.model;

public class AdminAccount extends Account{
	
	private int adminAccountID;

	//initializing teams and rooms along with the super class to null values
	public AdminAccount() {
		super();
		
	}
	
	//itializing super class with input values immediately
	public AdminAccount(String firstname, String lastname, String email, String password, String schoolID) {
		super(firstname, lastname, email, password, schoolID);
	}

	public int getAdminAccountID() {
		return adminAccountID;
	}

	public void setAdminAccountID(int adminAccount_id) {
		this.adminAccountID = adminAccount_id;
	}
}