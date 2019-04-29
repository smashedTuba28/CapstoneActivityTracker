/**
 * 
 */
package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeam;

/**
 * @author TravisWetzel
 *
 */
public class SubTeamTest extends SubTeam {
	int 	topTeam_id;
	SubTeam testSubTeam;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		topTeam_id = 5;
		testSubTeam = new SubTeam();

	}

	@Test

	public void testSetTopTeamID() {
		testSubTeam.setTopTeamID(topTeam_id); 
		assertEquals(5, testSubTeam.getTopTeamID());
		testSubTeam.setTopTeamID(1);
		assertNotEquals(topTeam_id, testSubTeam.getTopTeamID());
	}
}
