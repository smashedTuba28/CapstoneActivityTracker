package edu.ycp.cs320.CapstoneActivityTracker.model;

public class SubTeamStudents {

	private int studentAccountID;
	private int subTeamID;
	
	public SubTeamStudents() {
	}
	
	public void setStudentAccountID(int studentAccountID) {
		this.studentAccountID = studentAccountID;
	}
	public int getStudentAccountID() {
		return studentAccountID;
	}
	
	public void setSubTeamID(int subTeamID) {
		this.subTeamID = subTeamID;
	}
	public int getSubTeamID() {
		return subTeamID;
	}
}