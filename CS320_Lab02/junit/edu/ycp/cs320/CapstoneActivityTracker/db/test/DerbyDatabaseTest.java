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
import edu.ycp.cs320.CapstoneActivityTracker.model.Room;
import edu.ycp.cs320.CapstoneActivityTracker.model.RoomEvent;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeam;
import edu.ycp.cs320.CapstoneActivityTracker.model.TopTeam;

public class DerbyDatabaseTest {
	private DerbyDatabase db;
	private AdminAccount admin = null;
	private StudentAccount student = null;
	private List<StudentAccount> students = null;
	private List<RoomEvent> roomEventList; 
	private List<Room> roomList; 
	private SubTeam subTeam = null;
	private TopTeam topTeam = null;
	List<SubTeam> subTeams = null;
	
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
		assertTrue(admin == null);
		
		//get an existing admin
		admin = db.getAdminAccountWithID(1);//should return Michael Scott
		assertTrue(admin != null);//something was returned
		assertTrue(admin.getFirstname().equals("Michael"));
		assertTrue(admin.getLastname().equals("Scott"));
		assertEquals(1, admin.getAccountID());
		
		//check a nonexistent id
		admin = db.getAdminAccountWithID(-1);//should return null
		assertTrue(admin == null);
	}

	@Test
	public void testGetRoomsForASubTeam() {
		Integer subTeam_id = 2;
		roomList = new ArrayList<Room>();
		
		roomList = db.getRoomsForASubTeam(subTeam_id);
		
		System.out.println(roomList.get(0).getRoomID());
		System.out.println(roomList.get(1).getRoomID());
		System.out.println(roomList.get(2).getRoomID());
		System.out.println(roomList.get(0).getRoomName());
		System.out.println(roomList.get(0).getRoomNumber());
		
		assertTrue(roomList.get(0).getRoomID() == 19);
		assertTrue(roomList.get(0).getRoomName().equals("Main office"));
		assertTrue(roomList.get(0).getRoomNumber() == 300);
		

		assertTrue(roomList.get(1).getRoomID() == 25);
		assertTrue(roomList.get(1).getRoomName().equals("Manager's Office"));
		assertTrue(roomList.get(1).getRoomNumber() == 399);
		
		assertTrue(roomList.get(2).getRoomID() == 20);
		assertTrue(roomList.get(2).getRoomName().equals("Conference room"));
		assertTrue(roomList.get(2).getRoomNumber() == 301);
		
	}

	@Test
	public void testGetRoomEventsForStudentWithDates() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSubTeamsInTopTeam() {
		String teamname = "Dunder Mifflin";
		subTeams = new ArrayList<SubTeam>();
		
		subTeams = db.getSubTeamsInTopTeam(teamname);
		
		assertTrue(subTeams.get(0).getTeamname().equals("Sales"));
		assertTrue(subTeams.get(1).getTeamname().equals("Reception"));
		
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
	public void testCreateAndDeleteSubTeam() {
		String teamname = "Test";
		Integer topTeamID = 1;
		
		//initially delete just in case it was not deleted before
		//db.deleteSubTeam(db.getSubTeamWithTeamname(teamname).getTeamID());
		
		//check that account does not already exist
		assertTrue(db.getSubTeamWithTeamname(teamname) == null);
		
		//double-checking null value
		//System.out.println(subTeam.getTeamname());
		//assertTrue(subTeam.getTeamname() == null);
		
		//create the subTeam account
		db.createSubTeam(teamname, topTeamID);
		
		//find and veirfy new subTeam exists
		subTeam = db.getSubTeamWithTeamname(teamname);
		assertTrue(subTeam != null);
		assertTrue(subTeam.getTeamname().equals("Test"));
		assertEquals(1, subTeam.getTopTeamID());
	
		/////////////TESTING DELETION/////////////
		//delete subTeam with subTeamID
		assertTrue(db.deleteSubTeam(subTeam.getTeamID()));
	}
	
	/*
	@Test
	public void testDeleteSubTeam() {
		String teamname = "Test";
		
		//temp test to delete account from csv load
		assertTrue(db.deleteSubTeam(3));
		
		//check that the account doesn't exists
		subTeam = db.getSubTeamWithTeamname(teamname);
		assertTrue(student == null);
		
		//TODO: need to implement getallstudentsfromsubteam() to test this
		//check that all roomEvents are also gone
		students = db.getStudentsInSubTeam(3);			
		
	}
	*/
	
	@Test
	public void testGetTopTeamWithTeamname() {
		//assuming topTeam initialized but not populated
		assertTrue(topTeam == null);
		
		//get an existing topTeam
		topTeam = db.getTopTeamWithTeamname("Dunder Mifflin");
		
		//something was returned
		assertTrue(topTeam != null);
		assertTrue(topTeam.getTeamname().equals("Dunder Mifflin"));
		assertEquals(1, topTeam.getTeamID());
		
		//attempt to get a topTeam with teamname that doesn't exist
		topTeam = db.getTopTeamWithTeamname("wrong");
		//topTeam not found
		assertTrue(topTeam == null);
	}
	
	
	@Test
	public void testCreateAndDeleteTopTeam() {
		String teamname = "Test2";
		
		//initially delete just in case it was not deleted before
		//db.deleteSubTeam(db.getSubTeamWithTeamname(teamname).getTeamID());
		
		//check that account does not already exist
		assertTrue(db.getTopTeamWithTeamname(teamname) == null);
		
		//double-checking null value
		//System.out.println(topTeam.getTeamname());
		//assertTrue(topTeam.getTeamname() == null);
		
		//create the topTeam account
		db.createTopTeam(teamname);
		
		//find and veirfy new topTeam exists
		topTeam = db.getTopTeamWithTeamname(teamname);
		assertTrue(topTeam != null);
		assertTrue(topTeam.getTeamname().equals("Test2"));
		
		/////////////TESTING DELETION/////////////
		//delete subTeam with subTeamID
		assertTrue(db.deleteTopTeam(topTeam.getTeamID()));
		
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
		assertTrue(student.getLastname().equals("Tester"));
	
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
	
		//will need to add a room event to the account too
		Integer account_id;
		Integer room_id = 19;
		Date start = new Date();
		
		//clean slate make sure student doesn't exist
		student = db.getStudentAccountWithEmailandSchoolID(email, schoolID);
		assertTrue(student == null);
		
		//create the studentAccount
		db.createStudentAccount(firstname, lastname, email, password, schoolID);
		
		//get studentAccountID
		student = db.getStudentAccountWithEmailandSchoolID(email, schoolID);
		assertTrue(student != null);
		account_id = student.getAccountID();
		
		//attempt to create a room event
		assertTrue(db.createRoomEventForStudentAccountWithID(account_id, room_id, new Timestamp(start.getTime())));
		
		//check that a roomEvent has been added
		List<RoomEvent> events = db.getAllRoomEventForStudentAccountWithAccountID(account_id);
		assertTrue(events != null);
		assertNotEquals(0, events.size());
		
		//delete the student Account
		assertTrue(db.deleteStudentAccount(account_id));
		
		//check that the account doesnt exists
		student = db.getStudentAccountWithID(account_id);
		assertTrue(student == null);
		
		//check that all roomEvents are also gone
		roomEventList = db.getAllRoomEventForStudentAccountWithAccountID(account_id);		
		assertTrue(roomEventList != null);
		assertEquals(0, roomEventList.size());
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
		
		//check that a roomEvent has been added
		List<RoomEvent> events = db.getAllRoomEventForStudentAccountWithAccountID(account_id);
		assertTrue(events != null);
		assertNotEquals(0, events.size());

		//cleanup after test
		db.deleteStudentAccount(account_id);
	}
	
	@Test
	public void testGetAllRoomEventsForStudentAccountWithAccountID() {
		Integer account_id = 3; //Jim from CSV
		List<RoomEvent> events = null;
		
		//get all room events for existing account
		events = db.getAllRoomEventForStudentAccountWithAccountID(account_id);
		
		//should not be empty
		assertTrue(events != null);
		assertNotEquals(0, events.size());
		
		//should have 8 events
		assertEquals(8, events.size());
	}
	
	@Test
	public void testGetAllStudentsInSubTeamWithTeamName() {
		String teamname = "Sales";
		
		//make sure empty
		assertTrue(students == null);
		
		//make sure subteam exists
		assertTrue(db.getSubTeamWithTeamname(teamname) != null);
		
		//get all student in the subTeam
		students = db.getAllStudentsInSubTeamWithTeamName(teamname);
		
		//there should be 5
		assertTrue(students != null);
		assertEquals(5, students.size());
		
		//make sure that only expectedd students are returned
		for (StudentAccount s : students) {
			if( s.getAccountID() == 3 || s.getAccountID() == 5
			 || s.getAccountID() == 12 || s.getAccountID() == 13
			 || s.getAccountID() == 14) {
				//do nothing
			}
			else {
				fail("STUDENT NOT IN THIS TEAM");
			}
		}
	}
}
