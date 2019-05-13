package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.List;

public class AssignStudent {
	private List<StudentAccount> students;
	private List<SubTeam> subTeams;
	private StudentAccount student;
	
	public List<SubTeam> getSubTeams() {
		return subTeams;
	}
	public void setSubTeams(List<SubTeam> subTeams) {
		this.subTeams = subTeams;
	}
	public List<StudentAccount> getStudents() {
		return students;
	}
	public void setStudents(List<StudentAccount> students) {
		this.students = students;
	}
	public StudentAccount getStudent() {
		return student;
	}
	public void setStudent(StudentAccount student) {
		this.student = student;
	}
	
}
