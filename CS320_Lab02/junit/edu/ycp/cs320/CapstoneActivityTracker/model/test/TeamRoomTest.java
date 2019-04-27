package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.TeamRoom;

public class TeamRoomTest {
	private TeamRoom tr;
	@Before
	public void setUp() throws Exception {
		tr = new TeamRoom();
	}

	@Test
	public void testSetTeamID() {
		int id = 1;
		tr.setTeamID(id);
		assertEquals(1, tr.getTeamID());
		tr.setTeamID(2);
		assertNotEquals(id, tr.getTeamID());
	}

	@Test
	public void testSetRoomID() {
		int id = 1;
		tr.setRoomID(id);
		assertEquals(1, tr.getRoomID());
		tr.setRoomID(2);
		assertNotEquals(id, tr.getRoomID());
	}
}
