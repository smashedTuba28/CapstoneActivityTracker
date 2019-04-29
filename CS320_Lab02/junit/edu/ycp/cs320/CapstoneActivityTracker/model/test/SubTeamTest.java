/**
 * 
 */
package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeam;
import edu.ycp.cs320.CapstoneActivityTracker.model.TopTeam;

/**
 * @author TravisWetzel
 *
 */
public class SubTeamTest extends SubTeam {
	int topID;
	SubTeam subTeam;
	SubTeam testSubTeam;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		subTeam = new SubTeam();
		testSubTeam = new SubTeam();
		topID = 11;
		
		testSubTeam.setTeamname("testing SubTeam");
		
	}

	@Test
	public void testSetTopTeam() {
		subTeam.setTopTeamID(topID);
		assertTrue(subTeam.getTopTeamID() == 11);
	}
}
