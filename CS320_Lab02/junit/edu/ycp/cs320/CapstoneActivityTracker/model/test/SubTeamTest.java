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
<<<<<<< Upstream, based on origin/master
	int 	topTeam_id;
=======
	int topID;
	SubTeam subTeam;
>>>>>>> 8be365d Random Fixes to make less JUnits fail during presentation
	SubTeam testSubTeam;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		topTeam_id = 5;
		testSubTeam = new SubTeam();
<<<<<<< Upstream, based on origin/master
=======
		topID = 11;
		
<<<<<<< Upstream, based on origin/master
=======

>>>>>>> a43e58f Rebase
		testSubTeam.setTeamname("testing SubTeam");
<<<<<<< Upstream, based on origin/master
=======

		testSubTeam.setTeamname("testing SubTeam"); 
		//subTeam.setSubTeam(subTeam);

>>>>>>> a43e58f Rebase
		
>>>>>>> 8be365d Random Fixes to make less JUnits fail during presentation
	}

	@Test
<<<<<<< Upstream, based on origin/master
	public void testSetTopTeamID() {
		testSubTeam.setTopTeamID(topTeam_id); 
		assertEquals(5, testSubTeam.getTopTeamID());
		testSubTeam.setTopTeamID(1);
		assertNotEquals(topTeam_id, testSubTeam.getTopTeamID());
=======
	public void testSetTopTeam() {
		subTeam.setTopTeamID(topID);
		assertTrue(subTeam.getTopTeamID() == 11);
>>>>>>> 8be365d Random Fixes to make less JUnits fail during presentation
	}
}
