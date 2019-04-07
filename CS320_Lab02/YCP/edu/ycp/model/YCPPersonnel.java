 package edu.ycp.model;

public class YCPPersonnel {
	private String firstname, lastname, email, schoolID;
	private boolean faculty; 
	
	public YCPPersonnel() {
	}
	
	//setting all values in the constructor along with the valid boolean
	public YCPPersonnel(String firstname, String lastname, String email, String schoolID, boolean faculty) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.schoolID = schoolID;
		this.faculty = faculty;
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
	
	public void setFaculty(boolean faculty) {
		this.faculty = faculty;
	}
	
	public boolean getFaculty() {
		return faculty;
	}
	

}
