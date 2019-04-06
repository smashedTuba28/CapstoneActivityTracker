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
	SubTeam subTeam;
	SubTeam testSubTeam;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		subTeam = new SubTeam();
		testSubTeam = new SubTeam();
		
		testSubTeam.setTeamname("testing SubTeam");
		subTeam.setSubTeam(subTeam);
		
	}

	@Test
	public void testSetSubTeam() {
		subTeam.setSubTeam(testSubTeam);
		assertTrue(subTeam.getSubTeam().equals(testSubTeam));
	}
}
