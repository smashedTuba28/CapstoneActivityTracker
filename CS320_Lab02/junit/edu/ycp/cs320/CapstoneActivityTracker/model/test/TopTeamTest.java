package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.TopTeam;

public class TopTeamTest {
	private TopTeam top;
	@Before
	public void setUp() throws Exception {
		top = new TopTeam();
	}

	@Test
	public void testTopTeam() {
		top = new TopTeam("test", 1);
		assertTrue(top.getTeamname().equals("test"));
		assertEquals(1, top.getTeamID());
	}

}
