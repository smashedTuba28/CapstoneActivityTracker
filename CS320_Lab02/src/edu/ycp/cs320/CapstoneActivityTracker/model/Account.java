package edu.ycp.cs320.CapstoneActivityTracker.model;

public class Account {
	
	private String firstname, lastname, email, schoolID;
	private String password; //TODO: This will be changed for encryption purposes in the future
	private boolean faculty; 
	//private boolean valid; from before db implemeted
	
	// setting no values, therefore the account is not yet valid
	public Account() {
		//this.valid = false;
	}
	
	//setting all values in the constructor along with the valid boolean
	public Account(String firstname, String lastname, String email, String password, String schoolID, boolean faculty) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.schoolID = schoolID;
		this.faculty = faculty;
		//this.valid = true;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setSchoolID(String school_ID) {
		this.schoolID = school_ID;
	}
	
	public String getSchoolID() {
		return schoolID;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setFaculty(boolean faculty) {
		this.faculty = faculty;
	}
	
	public boolean getFaculty() {
		return faculty;
	}
	
	/*//checking if the account is valid (function of the database)
	public boolean validAccount() {//comment out once DB is implemented
		return valid;
	}*/

}
