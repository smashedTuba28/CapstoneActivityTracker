package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeamStudent;

public class TeamStudentTest {
	private SubTeamStudent ts;
	
	@Before
	public void setUp() throws Exception {
		ts = new SubTeamStudent();
	}

	@Test
	public void testSetTeamID() {
		int id = 1;
		ts.setTeamID(id);
		assertEquals(1, ts.getTeamID());
		ts.setTeamID(2);
		assertNotEquals(id, ts.getTeamID());
	}

	@Test
	public void testSetStudentID() {
		int id = 1;
		ts.setStudentID(id);
		assertEquals(1, ts.getStudentID());
		ts.setStudentID(2);
		assertNotEquals(id, ts.getStudentID());
	}
}
