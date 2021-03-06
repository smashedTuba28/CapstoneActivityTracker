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
	private String topteamname;
	private String subteamname;
	
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
		/*
		for(TopTeam topTeam: topTeamList) {
			System.out.println(topTeam.getTeamname());
		}
		*/
		model.setTopTeamList(topTeamList);
	}
	public void setTopTeam(String topteamname) {
		TopTeam topteam = db.getTopTeamWithTeamname(topteamname);
		model.setTopTeam(topteam);
	}
	public void setModelTopTeam(String teamname) {
		model.setTopTeam(db.getTopTeamWithTeamname(teamname));
	}
	public void populateModelWithSubTeams(String topTeamname) {
		List<SubTeam> subTeamList = new ArrayList<SubTeam>();
		subTeamList = db.getSubTeamsInTopTeam(topTeamname);
		for(SubTeam subTeam: subTeamList) {
			System.out.println(subTeam.getTeamname());
		}
		model.setSubTeamList(subTeamList);
	}
	public void getSubTeam(String subteamname) {
		SubTeam subteam = db.getSubTeamWithTeamname(subteamname);
		model.setSubTeam(subteam);;
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

	public String getTopteamname() {
		return topteamname;
	}

	public void setTopteamname(String topteamname) {
		this.topteamname = topteamname;
	}

	public String getSubteamname() {
		return subteamname;
	}
	public void setSubteamname(String subteamname) {
		this.subteamname = subteamname;
	}
	public void setAdminname(String account_id) {
		String adminfirstname = db.getAdminAccountWithID(Integer.parseInt(account_id)).getFirstname();
		String adminlastname = db.getAdminAccountWithID(Integer.parseInt(account_id)).getLastname();
		String adminname = adminfirstname + " " + adminlastname;
		model.setAdminname(adminname);
		
	}
}
