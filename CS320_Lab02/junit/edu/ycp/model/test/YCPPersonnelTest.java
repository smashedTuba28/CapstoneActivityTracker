package edu.ycp.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.Account;
import edu.ycp.cs320.CapstoneActivityTracker.model.AdminAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;
import edu.ycp.model.YCPPersonnel;

public class YCPPersonnelTest {

 	private YCPPersonnel personnel;
	private YCPPersonnel testJason;
	private YCPPersonnel testTravis;
	private YCPPersonnel testWill;
	private YCPPersonnel testJohn;
	
	
	@Before
	public void setUp() throws Exception {
		personnel = new YCPPersonnel();
		testJohn = new YCPPersonnel("John", "Doe", "Jdoe@ycp.edu", "900000000", true );
		testJason = new YCPPersonnel("Jason", "Steinberg", "jsteinerg@ycp.edu", "900000001", false);
		testTravis = new YCPPersonnel("Travis", "Wetzel", "twetzel1@ycp.edu", "900000002", false);
		testWill = new YCPPersonnel("William", "Taylor", "wtaylor1@ycp.edu", "900000003", false);
	}

	@Test
	public void testSetFirstname() {
		String name = "Jim";
		personnel.setFirstname(name);
		assertTrue(personnel.getFirstname().equals("Jim"));
		assertTrue(testJohn.getFirstname().equals("John"));
		assertTrue(testJason.getFirstname().equals("Jason"));
		assertTrue(testTravis.getFirstname().equals("Travis"));
		assertTrue(testWill.getFirstname().equals("William"));
		personnel.setFirstname("Dwight");
		assertFalse(personnel.getFirstname().equals(name));
		
	}

	@Test
	public void testSetLastname() {
		String name = "Halpert";
		personnel.setLastname(name);
		assertTrue(personnel.getLastname().equals("Halpert"));
		assertTrue(testJohn.getLastname().equals("Doe"));
		assertTrue(testJason.getLastname().equals("Steinberg"));
		assertTrue(testTravis.getLastname().equals("Wetzel"));
		assertTrue(testWill.getLastname().equals("Taylor"));
		personnel.setLastname("Schrute");
		assertFalse(personnel.getLastname().equals(name));
	}

	@Test
	public void testSetEmail() {
		String email = "mscott@ycp.edu";
		personnel.setEmail(email);
		assertTrue(personnel.getEmail().equals("mscott@ycp.edu"));
		assertTrue(testJohn.getEmail().equals("Jdoe@ycp.edu"));
		assertTrue(testJason.getEmail().equals("jsteinerg@ycp.edu"));
		assertTrue(testTravis.getEmail().equals("twetzel1@ycp.edu"));
		assertTrue(testWill.getEmail().equals("wtaylor1@ycp.edu"));
		personnel.setEmail("pbeesly@ycp.edu");
		assertFalse(personnel.getEmail().equals(email));
	}

	@Test
	public void testSetSchoolID() {
		String id = "901234567";
		personnel.setSchoolID(id);
		assertTrue(personnel.getSchoolID().equals("901234567"));
		assertTrue(testJohn.getSchoolID().equals("900000000"));
		assertTrue(testJason.getSchoolID().equals("900000001"));
		assertTrue(testTravis.getSchoolID().equals("900000002"));
		assertTrue(testWill.getSchoolID().equals("900000003"));
		personnel.setSchoolID("9076543210");
		assertFalse(personnel.getSchoolID().equals(id));
	}

	@Test
	public void testSetFaculty() {
		personnel.setFaculty(true);
		assertTrue(personnel.getFaculty());
		assertTrue(testJohn.getFaculty());
		
		personnel.setFaculty(false);
		assertFalse(personnel.getFaculty());
		assertFalse(testJason.getFaculty());
		assertFalse(testTravis.getFaculty());
		assertFalse(testWill.getFaculty());
	}
}