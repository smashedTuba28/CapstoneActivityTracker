package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.List;

public class AdminView {
	private List<TopTeam> topTeamList;
	private TopTeam topTeam;
	private List<SubTeam> subTeamList;
	private SubTeam subTeam;
	private List<StudentAccount> students;
	private StudentAccount student;
	private String adminname;

	
	
	public List<TopTeam> getTopTeamList() {
		return topTeamList;
	}

	public void setTopTeamList(List<TopTeam> topTeamList) {
		this.topTeamList = topTeamList;
	}

	public TopTeam getTopTeam() {
		return topTeam;
	}

	public void setTopTeam(TopTeam topTeam) {
		this.topTeam = topTeam;
	}

	public List<SubTeam> getSubTeamList() {
		return subTeamList;
	}

	public void setSubTeamList(List<SubTeam> subTeamList) {
		this.subTeamList = subTeamList;
	}

	public SubTeam getSubTeam() {
		return subTeam;
	}

	public void setSubTeam(SubTeam subTeam) {
		this.subTeam = subTeam;
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

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
}
