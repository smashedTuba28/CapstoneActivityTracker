package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.Team;

public class TeamTest {
	private Team team;
	
	
	@Before
	public void setUp() throws Exception {
		team = new Team();
	}

	@Test
	public void testSetTeamname() {
		String teamname = "tester";
		team.setTeamname(teamname);
		assertTrue(team.getTeamname().equals("tester"));
		team.setTeamname("fake");
		assertFalse(team.getTeamname().equals(teamname));
	}

	@Test
	public void testSetTeamID() {
		int id = 1;
		team.setTeamID(id);
		assertEquals(1, team.getTeamID());
		team.setTeamID(2);
		assertNotEquals(id, team.getTeamID());
	}

	@Test
	public void testTeam() {
		team = new Team("tester", 1);
		assertTrue(team.getTeamname().equals("tester"));
		assertEquals(1, team.getTeamID());
	}
}
