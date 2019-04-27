package edu.ycp.cs320.CapstoneActivityTracker.db.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstonActivtyTracker.db.DerbyDatabase;
import edu.ycp.cs320.CapstonActivtyTracker.db.hashSHA256;
import edu.ycp.cs320.CapstoneActivityTracker.model.AdminAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;

public class DerbyDatabaseTest {
	DerbyDatabase db;
	AdminAccount admin = null;
	StudentAccount student = null;
	
	@Before
	public void setUp() throws Exception {
		db = new DerbyDatabase();
	}

	@Test
	public void testVerifyStudentAccount() {
		assertTrue(student == null);
		//verify an existing account
		student = db.verifyStudentAccount("jsteinberg@ycp.edu", hashSHA256.getHash("password"));
		assertTrue(student != null);//something was returned 
		assertTrue(student.getFirstname().equals("Jason"));
		assertEquals(1, student.getAccountID());
		
		//incorrect password
		student = db.verifyStudentAccount("jsteinberg@ycp.edu", "wrong");
		assertTrue(student == null);
		
		//incorrect email
		student = db.verifyStudentAccount("wrong", hashSHA256.getHash("password"));
		assertTrue(student == null);
	}
	
	@Test
	public void testVerifyAdminAccount() {
		assertTrue(admin == null);
		//verify an existing account
		admin = db.verifyAdminAccount("mscott@ycp.edu", hashSHA256.getHash("steve"));
		assertTrue(admin != null);//something was returned
		assertTrue(admin.getLastname().equals("Scott"));
		assertEquals(1, admin.getAccountID());
		
		//incorrect password
		admin = db.verifyAdminAccount("mscott@ycp.edu", "wrong");
		assertTrue(admin == null);
		
		//incorrect email
		admin = db.verifyAdminAccount("wrong", hashSHA256.getHash("steve"));
		assertTrue(admin == null);
	}
	
	@Test
	public void testGetStudentAccountWithID() {
		assertTrue(student == null);
		
		//get an existing student
		student = db.getStudentAccountWithID(1);//should return Jason Steinberg
		assertTrue(student != null);//something was returned
		assertTrue(student.getFirstname().equals("Jason"));
		assertTrue(student.getLastname().equals("Steinberg"));
		assertEquals(1, student.getAccountID());
		
		//check a nonexistent id
		student = db.getStudentAccountWithID(-1);//should return null
		assertTrue(student == null);
		
		
	}

	@Test
	public void testGetStudentAccountWithEmailandSchoolID() {
		assertTrue(student == null);
		
		//get an existing student
		student = db.getStudentAccountWithEmailandSchoolID("jsteinberg@ycp.edu", "900000000");
		assertTrue(student != null);//something was returned
		assertTrue(student.getFirstname().equals("Jason"));
		assertTrue(student.getLastname().equals("Steinberg"));
		assertEquals(1, student.getAccountID());
		
		//attempt to get a studentAccount with email that doesnt exist
		student = db.getStudentAccountWithEmailandSchoolID("wrong", "900000000");
		assertTrue(student == null);//account not found
		
		//attempt to get a studentAccount with ID that doesnt exist
		student = db.getStudentAccountWithEmailandSchoolID("jsteinberg@ycp.edu", "wrong");
		assertTrue(student == null);//account not found
	}

	@Test
	public void testGetStudentsInSubTeam() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAdminAccountWithID() {
		fail("Not yet implemented");
	}


	@Test
	public void testGetRoomsForASubTeam() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRoomEventsForStudentWithDates() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSubTeamsInTopTeam() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreatSubTeam() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateTopTeam() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateStudentAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreatAdminAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAdminAccountWithEmailandSchoolID() {
		fail("Not yet implemented");
	}

}
