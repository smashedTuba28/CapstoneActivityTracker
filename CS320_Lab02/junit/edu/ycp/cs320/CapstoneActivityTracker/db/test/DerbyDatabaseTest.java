package edu.ycp.cs320.CapstoneActivityTracker.db.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstonActivtyTracker.db.DerbyDatabase;
import edu.ycp.cs320.CapstonActivtyTracker.db.hashSHA256;
import edu.ycp.cs320.CapstoneActivityTracker.model.AdminAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.RoomEvent;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeam;

public class DerbyDatabaseTest {
	private DerbyDatabase db;
	private AdminAccount admin = null;
	private StudentAccount student = null;
	private List<RoomEvent> roomEventList; 
	private SubTeam subTeam = null;
	
	@Before
	public void setUp() throws Exception {
		db = new DerbyDatabase();
		roomEventList = new ArrayList<RoomEvent>();
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
	public void testGetSubTeamWithTeamname() {
		//assuming subTeam initialized but not populated
		assertTrue(subTeam == null);
		
		//get an existing subTeam
		subTeam = db.getSubTeamWithTeamname("Sales");
		
		//something was returned
		assertTrue(subTeam != null);
		assertTrue(subTeam.getTeamname().equals("Sales"));
		assertEquals(1, subTeam.getTeamID());
		assertEquals(1, subTeam.getTopTeamID());
		
		//attempt to get a subTeam with teamname that doesn't exist
		subTeam = db.getSubTeamWithTeamname("wrong");
		//subTeam not found
		assertTrue(subTeam == null);
	}

	@Test
	public void testCreateSubTeam() {
		String teamname = "Test";
		Integer topTeamID = 1;
		
		//check that account does not already exist
		subTeam = db.getSubTeamWithTeamname(teamname);
		assertTrue(subTeam == null);
		
		//create the subTeam account
		db.createSubTeam(teamname, topTeamID);
		
		//find and veirfy new subTeam exists
		subTeam = db.getSubTeamWithTeamname(teamname);
		assertTrue(subTeam != null);
		assertTrue(subTeam.getTeamname().equals("Test"));
		assertEquals(17, subTeam.getTopTeamID());
	
		//delete the account after testing checks out
		db.deleteSubTeam(subTeam.getTeamID());
	}
	
	@Test
	public void testDeleteSubTeam() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateTopTeam() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateStudentAccount() {
		String firstname = "Test";
		String lastname = "Tester";
		String email = "ttester@ycp.edu";
		String password = hashSHA256.getHash("testpassword");
		String schoolID = "999999999";
		
		//check that account does not already exist
		student = db.getStudentAccountWithEmailandSchoolID(email, schoolID);
		assertTrue(student == null);
		
		//create the student account
		db.createStudentAccount(firstname, lastname, email, password, schoolID);
		
		//find and veirfy new student exists
		student = db.getStudentAccountWithEmailandSchoolID(email, schoolID);
		assertTrue(student != null);
		assertTrue(student.getFirstname().equals("Test"));
		assertEquals(17, student.getAccountID());
	
		//delete the account after testing checks out
		db.deleteStudentAccount(student.getAccountID());
	}

	@Test
	public void testCreatAdminAccount() {
		String firstname = "Test";
		String lastname = "Tester";
		String email = "ttester@ycp.edu";
		String password = hashSHA256.getHash("testpassword");
		String schoolID = "999999999";
		
		//check that account does not already exist
		admin = db.getAdminAccountWithEmailandSchoolID(email, schoolID);
		assertTrue(admin == null);
		
		//create a new admin
		db.creatAdminAccount(firstname, lastname, email, password, schoolID);
		
		//verify that account now exists in DB
		admin = db.getAdminAccountWithEmailandSchoolID(email, schoolID);
		assertTrue(admin != null);
		assertTrue(admin.getFirstname().equals("Test"));
		
		//delete from table as to not cause issues every time this runs
		db.deleteAdminAccount(admin.getAccountID());
	}  

	@Test
	public void testGetAdminAccountWithEmailandSchoolID() {
		assertTrue(admin == null);
		
		//get an existing adminAccount
		admin = db.getAdminAccountWithEmailandSchoolID("mscott@ycp.edu", "910000000");
		assertTrue(admin.getFirstname().equals("Michael"));
		assertTrue(admin.getLastname().equals("Scott"));
		assertEquals(1,admin.getAccountID());
		
		//attempt to get account with email that doesn't exist
		admin = db.getAdminAccountWithEmailandSchoolID("wrong", "910000000");
		assertTrue(admin == null);
		 
		//attempt to get account with schoolID that doesn't exist
		admin = db.getAdminAccountWithEmailandSchoolID("mscott@ycp.edu", "wrong");
		assertTrue(admin == null);
	}
	
	@Test
	public void testDeleteAdminAccount() {
		String firstname = "Mike";
		String lastname = "Wazowski";
		String email = "mwazowski@ycp.edu";
		String password = hashSHA256.getHash("moster");
		String schoolID = "666666666";
		
		//check that it doesnt exist
		admin = db.getAdminAccountWithEmailandSchoolID(email, schoolID);
		assertTrue(admin == null);
		
		//insert a new admin
		db.creatAdminAccount(firstname, lastname, email, password, schoolID);
		
		//verify that it now exists and retrieve account
		admin = db.getAdminAccountWithEmailandSchoolID(email, schoolID);
		assertTrue(admin != null);
		
		//delete adminAccount
		db.deleteAdminAccount(admin.getAccountID());
		
		//verify that it can no longer be found
		admin = db.getAdminAccountWithEmailandSchoolID(email, schoolID);
		assertTrue(admin == null);
	}
	
	@Test
	public void testDeleteStudentAccount() {
		String firstname = "Test";
		String lastname = "Tester";
		String email = "ttester@ycp.edu";
		String password = hashSHA256.getHash("testpassword");
		String schoolID = "999999999";
	
		//TODO: will need to add a room event to the account too
		
		//temp test to delete account from csv load
		assertTrue(db.deleteStudentAccount(3));
		
		//check that the account doesnt exists
		student = db.getStudentAccountWithID(3);
		assertTrue(student == null);
		
		//check that all roomEvents are also gone
		roomEventList = db.getAllRoomEventForStudentAccountWithAccountID(3);		
		
	}
	
	@Test
	public void createRoomEventForStudentAccountWithID() {
		String firstname = "Test";
		String lastname = "Tester";
		String email = "ttester@ycp.edu";
		String password = hashSHA256.getHash("testpassword");
		String schoolID = "999999999";
		
		Integer account_id;
		Integer room_id = 19;
		Date start = new Date();
		
		//make sure student doesn't exist
		student = db.getStudentAccountWithEmailandSchoolID(email, schoolID);
		assertTrue(student == null);
		
		//create new student for fresh slate
		assertTrue(db.createStudentAccount(firstname, lastname, email, password, schoolID));
		
		//get studentAccountID
		student = db.getStudentAccountWithEmailandSchoolID(email, schoolID);
		assertTrue(student != null);
		account_id = student.getAccountID();
		
		//attempt to create a room event
		assertTrue(db.createRoomEventForStudentAccountWithID(account_id, room_id, new Timestamp(start.getTime())));
		
		//TODO: finish
		
		
	}
	
}
