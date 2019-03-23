package edu.ycp.cs320.CapstoneActivityTracker.model;

public class SubTeam extends Team{
	//SubTeam has no SubTeams, this is the lowest level of Team with simple getters and setters

	private SubTeam subTeam;
	
	public SubTeam() {
		super();
	}
	
	public SubTeam(String subteam) {
		super(subteam);
	}
	
	public SubTeam getSubTeam() {
		return subTeam;
	}
	
	public void setSubTeam(SubTeam subTeam) {
		this.subTeam = subTeam;
	}
}
