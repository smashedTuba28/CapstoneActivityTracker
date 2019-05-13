package edu.ycp.cs320.CapstoneActivityTracker.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.CapstonActivtyTracker.db.DatabaseProvider;
import edu.ycp.cs320.CapstonActivtyTracker.db.DerbyDatabase;
import edu.ycp.cs320.CapstonActivtyTracker.db.IDatabase;
import edu.ycp.cs320.CapstoneActivityTracker.model.AssignStudent;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeam;
import edu.ycp.cs320.CapstoneActivityTracker.model.TopTeam;

public class AssignStudentController {
	
	private IDatabase db;
	private AssignStudent model;
	private List<TopTeam> topTeams;
	private List<SubTeam> subTeams;
	private List<StudentAccount> students;
	private List<SubTeam> subs;
	private List<StudentAccount> studs;
	
	
	public AssignStudentController(){
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
		subs = new ArrayList<SubTeam>();
		studs = new ArrayList<StudentAccount>();
		topTeams = new ArrayList<TopTeam>();
		subTeams = new ArrayList<SubTeam>();
		students = new ArrayList<StudentAccount>();
	}

	public void setModel(AssignStudent model) {
		this.model = model;
	}
	
	public void populateModelWithStudentsandSubTeams() {
		topTeams = db.getAllTopTeams();
		for(TopTeam topTeam: topTeams) {
			subTeams = db.getSubTeamsInTopTeam(topTeam.getTeamname());
			for(SubTeam subTeam: subTeams) {
				subs.add(subTeam);
				students = db.getAllStudentsInSubTeamWithTeamName(subTeam.getTeamname());
				for(StudentAccount student: students) {
					studs.add(student);
				}
			}
		}
		model.setSubTeams(subs);
		model.setStudents(studs);
	}
	
	public  boolean assignStudentToSubTeam(String teamname, String studentID) {
		return db.assignStudentToSubTeam(teamname, Integer.parseInt(studentID));
	}
	
}
