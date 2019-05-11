package edu.ycp.cs320.CapstoneActivityTracker.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.CapstonActivtyTracker.db.DatabaseProvider;
import edu.ycp.cs320.CapstonActivtyTracker.db.DerbyDatabase;
import edu.ycp.cs320.CapstonActivtyTracker.db.IDatabase;
import edu.ycp.cs320.CapstoneActivityTracker.model.AdminView;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeam;
import edu.ycp.cs320.CapstoneActivityTracker.model.TopTeam;

public class AdminViewController {

	private IDatabase db;
	private AdminView model;
	
	public AdminViewController(){

		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}

	public void setModel(AdminView model) {
		this.model = model;
	}
	
	public void populateModelWithTopTeams(){
		List<TopTeam> topTeamList = new ArrayList<TopTeam>();
		topTeamList = db.getAllTopTeams();
		for(TopTeam topTeam: topTeamList) {
			System.out.println(topTeam);
		}
		model.setTopTeamList(topTeamList);
	}
	public void setModelTopTeam(String teamname) {
		model.setTopTeam(db.getTopTeamWithTeamname(teamname));
	}
	public void populateModelWithSubTeams(String topTeamname) {
		List<SubTeam> subTeamList = new ArrayList<SubTeam>();
		subTeamList = db.getSubTeamsInTopTeam(topTeamname);
		for(SubTeam subTeam: subTeamList) {
			System.out.println(subTeam);
		}
		model.setSubTeamList(subTeamList);
	}
	public void setModelSubTeam(String subTeamname) {
		SubTeam subTeam = new SubTeam();
		subTeam = db.getSubTeamWithTeamname(subTeamname);
		System.out.println(subTeam);
		model.setSubTeam(subTeam);
	}
	public void populateModelWithStudents(String subTeamname) {
		List<StudentAccount> studentsList = new ArrayList<StudentAccount>();
		studentsList = db.getAllStudentsInSubTeamWithTeamName(subTeamname);
		for(StudentAccount students: studentsList) {
			System.out.println(students);
		}
		model.setStudents(studentsList);
	}
}
