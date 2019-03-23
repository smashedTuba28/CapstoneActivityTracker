package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.ArrayList;
import java.util.List;

public class TopTeam extends Team{

	//TopTeam is a Team with SubTeams
	
	private List<SubTeam> subTeams;
	
	public TopTeam() {
		subTeams = new ArrayList<SubTeam>();
	}
	
	public TopTeam(String name) {
		super(name);
		subTeams = new ArrayList<SubTeam>();
	}
	
	public List<SubTeam> getSubTeams() {
		return subTeams;
	}
	
	//setting all subTeams
	public void setTeams(ArrayList<SubTeam> subTeams) {
		this.subTeams = subTeams;
	}
	
	//adding a subTeam to a list of teams
	public void addSubTeam(SubTeam subteam) {
		subTeams.add(subteam);
	}
	
	//removing a subTeam from the list of subTeams
	public void removeSubTeam(SubTeam subteam) {
		subTeams.remove(subteam);
	}
	
	public void addMemberToSubTeam(StudentAccount member, String name) {
		//check to see if member is already in this team
		if (findMember(member) == null) {
			//if they aren't add them to top team
			this.students.add(member);	
		}
		//check to see that sub team already exists
		SubTeam sub = findSubTeam(name);
		if(sub == null) {
			//if it doesnt add new subTeam
			sub = new SubTeam(name);
			addSubTeam(sub);
		}
		
		//both team and student already exist in top team
		//add student to sub team
		sub.addMember(member);
	}
	
	//searches through existing students to find a match
	public StudentAccount findMember(StudentAccount find) {		
		for(StudentAccount o: students) {
			if(o.getSchoolID().equals(find.getSchoolID())) {
				return find;
			}
		}
		return null;
	}
	
	//searches through subTeams to find a match
	public SubTeam findSubTeam(String name) {
		for(SubTeam sub: subTeams) {
			if (sub.getTeamname().equals(name)) {
				return sub;
			}
		}
		return null;
	}
}
