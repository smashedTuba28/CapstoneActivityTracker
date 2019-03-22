package edu.ycp.cs320.CapstoneActivityTracker.model;

public class Account {
	
	private String firstname, lastname, email, schoolID;
	private String password; //TODO: This will be changed for encryption purposes in the future
	private boolean faculty;
	
	public Account() {
		
	}
	
	public Account(String firstname, String lastname, String email, String password, String schoolID, boolean faculty) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.schoolID = schoolID;
		this.faculty = faculty;
	}
	
	public Account(int var) {
		password = "12345678";
		if(var == 1) {
			firstname = "Jason";
			lastname = "Steinberg";
			email = "jsteinberg@ycp.edu";
			schoolID = "903123456";
			faculty = false;
		}
		else if (var == 2) {
			firstname = "Travis";
			lastname = "Wetzel";
			email = "twetzel1@ycp.edu";
			schoolID = "903110312";
			faculty = false;
		}
		else if (var == 3) {
			firstname = "William";
			lastname = "Taylor";
			email = "wtaylor1@ycp.edu";
			schoolID = "903112233";
			faculty = false;
		}
		else if (var == 4) {
			firstname = "John";
			lastname = "Doe";
			email = "jdoe@ycp.edu";
			schoolID = "903123123";
			faculty = true;
		}
		else {
			//do nothing
		}
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
	
	public boolean validAccount() {//TODO: comment out once DB is implemented
		if(this.firstname == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean verifyCreation() {//TODO: comment out once DB is implemented
		if(this.firstname == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void createAccount(String firstname, String lastname, String email, String password, String schoolID) {//TODO: comment out once DB is implemented
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.schoolID = schoolID;
	}
	
	
}
