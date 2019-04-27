package edu.ycp.cs320.CapstoneActivityTracker.model;

public class AdminAccount extends Account{

	//initializing teams and rooms along with the super class to null values
	public AdminAccount() {
		super();
		
	}
	
	//itializing super class with input values immediately
	public AdminAccount(String firstname, String lastname, String email, String password, String schoolID, boolean faculty) {
		super(firstname, lastname, email, password, schoolID, faculty);
	}
}