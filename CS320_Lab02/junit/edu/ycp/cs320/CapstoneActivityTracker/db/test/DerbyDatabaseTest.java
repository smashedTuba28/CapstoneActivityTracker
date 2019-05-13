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
		assertTrue(student.getFirstname().equals("Jason"));//testing info from accounts table
		assertFalse(student.getStatus());//testing info from studentAccounts table
		assertEquals(1, student.getAccountID());
		
		//incorrect password
		student = db.verifyStudentAccount("jsteinberg@ycp.edu", "wrong");
		assertTrue(student == null);
		
		//incorrect email
		student = db.verifyStudentAccount("wrong", hashSHA256.getHash("password"));
		assertTrue(student == null);
	
		//existing account that has no student data aka admin account
		student = db.verifyStudentAccount("mscott@ycp.edu", hashSHA256.getHash("steve"));
		assertTrue(student == null);
	
	}
	 
	@Test
	public void testVerifyAdminAccount() {
		assertTrue(admin == null);
		//verify an existing account
		admin = db.verifyAdminAccount("mscott@ycp.edu", hashSHA256.getHash("steve"));
		assertTrue(admin != null);//something was returned
		assertTrue(admin.getLastname().equals("Scott"));
		assertEquals(17, admin.getAccountID());
		
		//incorrect password
		admin = db.verifyAdminAccount("mscott@ycp.edu", "wrong");
		assertTrue(admin == null);
		
		//incorrect email
		admin = db.verifyAdminAccount("wrong", hashSHA256.getHash("steve"));
		assertTrue(admin == null);
		
		//student account
		admin = db.verifyAdminAccount("jsteinberg@ycp.edu", hashSHA256.getHash("password"));
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
		assertEquals(1, student.getStudentAccountID());
		//check a nonexistent id
		student = db.getStudentAccountWithID(-1);//should return null
		assertTrue(student == null);
		//existing account that has no student data aka admin account
		student = db.verifyStudentAccount("mscott@ycp.edu", hashSHA256.getHash("steve"));
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
		
		//test admin account
		student = db.verifyStudentAccount("mscott@ycp.edu", hashSHA256.getHash("steve"));
		assertTrue(student == null);
	}

	@Test
	public void testGetStudentsInSubTeam() {
		assertTrue(students == null);
		
		//get all students in sales
		students = db.getStudentsInSubTeam(1);//sales
		assertTrue(students != null);
		assertEquals(5, students.size());//should be 5 students 
		
		assertEquals(3,students.get(0).getAccountID());//
		assertEquals(5,students.get(1).getAccountID());
		assertEquals(12, students.get(2).getAccountID());
		assertEquals(13, students.get(3).getAccountID());
		assertEquals(14, students.get(4).getAccountID());
		
		//non existent subTeam
		students = db.getStudentsInSubTeam(-1);
		assertTrue(students.isEmpty());
		
	}

	@Test
	public void testGetAdminAccountWithID() {
		assertTrue(admin == null);
		//get an existing admin
		admin = db.getAdminAccountWithID(17);//should return Michael Scott
		assertTrue(admin != null);//something was returned
		assertTrue(admin.getFirstname().equals("Michael"));
		assertTrue(admin.getLastname().equals("Scott"));
		assertEquals(17, admin.getAccountID());
		assertEquals(1, admin.getAdminAccountID());
		
		//check a nonexistent id
		admin = db.getAdminAccountWithID(-1);//should return null
		assertTrue(admin == null);
		
		//check for student
		admin = db.getAdminAccountWithID(1);//jason
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
	public void testGetSubTeamsInTopTeam() {
		String teamname = "Dunder Mifflin";
		subTeams = new ArrayList<SubTeam>();
		
		subTeams = db.getSubTeamsInTopTeam(teamname);
		assertTrue(subTeams.get(0).getTeamname().equals("Accounting"));
		assertTrue(subTeams.get(1).getTeamname().equals("Annex"));
		
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
	public void testGetAdminAccountWithEmailandSchoolID() {
		assertTrue(admin == null);
		
		//get an existing adminAccount
		admin = db.getAdminAccountWithEmailandSchoolID("mscott@ycp.edu", "910000000");
		assertTrue(admin.getFirstname().equals("Michael"));
		assertTrue(admin.getLastname().equals("Scott"));
		assertEquals(17,admin.getAccountID());
		assertEquals(1, admin.getAdminAccountID());
		
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
		db.createAdminAccount(firstname, lastname, email, password, schoolID);
		
		//verify that it now exists and retrieve account
		admin = db.getAdminAccountWithEmailandSchoolID(email, schoolID);
		assertTrue(admin != null);
		
		//delete adminAccount
		db.deleteAdminAccount(admin.getAdminAccountID());
		
		//verify that it can no longer be found
		admin = db.getAdminAccountWithEmailandSchoolID(email, schoolID);
		assertTrue(admin == null);
	}
	
	@Test
	public void testCreateAndDeleteStudentAccount() {
		String firstname = "Test";
		String lastname = "Tester";
		String email = "ttester@ycp.edu";
		String password = hashSHA256.getHash("testpassword");
		String schoolID = "999999999";
	
		//will need to add a room event to the account too
		Integer studentAccount_id;
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
		studentAccount_id = student.getStudentAccountID();
		System.out.println(studentAccount_id);
		
		//attempt to create a room event
		assertTrue(db.createRoomEventForStudentAccountWithIDandUpdateStatus(studentAccount_id, room_id, new Timestamp(start.getTime())));
		
		//check that a roomEvent has been added
		List<RoomEvent> events = db.getAllRoomEventForStudentAccountWithAccountID(studentAccount_id);
		assertTrue(events != null);
		assertNotEquals(0, events.size());
		
		//delete the student Account
		Integer account_id = student.getAccountID();
		assertTrue(db.deleteStudentAccount(studentAccount_id));
		
		//check that the account doesnt exists
		student = db.getStudentAccountWithID(account_id);
		assertTrue(student == null);
		
		//check that all roomEvents are also gone
		roomEventList = db.getAllRoomEventForStudentAccountWithAccountID(studentAccount_id);		
		assertTrue(roomEventList != null);
		assertEquals(0, roomEventList.size());
		
		db.deleteStudentAccount(studentAccount_id);
	}
	
	@Test
	public void createRoomEventForStudentAccountWithIDandUpdateStatus() {
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
		account_id = student.getStudentAccountID();
		
		//attempt to create a room event
		assertFalse(student.getStatus());
		assertTrue(db.createRoomEventForStudentAccountWithIDandUpdateStatus(account_id, room_id, new Timestamp(start.getTime())));
		
		//refresh student info
		student = db.getStudentAccountWithEmailandSchoolID(email, schoolID);
		//check that status is now true
		assertTrue(student.getStatus());
		
		//check that a roomEvent has been added
		List<RoomEvent> events = db.getAllRoomEventForStudentAccountWithAccountID(account_id);
		assertTrue(events != null);
		assertNotEquals(0, events.size());

		//cleanup after test
		db.deleteStudentAccount(student.getStudentAccountID());
	}
	
	@Test
	public void testGetAllRoomEventsForStudentAccountWithAccountID() {
		Integer account_id = 2; //Travis from CSV
		List<RoomEvent> events = null;
		
		//get all room events for existing account
		events = db.getAllRoomEventForStudentAccountWithAccountID(account_id);
		
		//should not be empty
		assertTrue(events != null);
		assertNotEquals(0, events.size());
		
		//should have 8 events
		assertEquals(10, events.size());
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
		
		//make sure that only expected students are returned
		for (StudentAccount s : students) {
			if( s.getStudentAccountID() == 3 || s.getStudentAccountID() == 5
			 || s.getStudentAccountID() == 12 || s.getStudentAccountID() == 13
			 || s.getStudentAccountID() == 14) {
				//do nothing
			}
			else {
				fail("STUDENT NOT IN THIS TEAM");
			}
		}
	}
	
	@Test
	public void testGetStudentAccountWithSchoolID() {
		String schoolID = "900000000";
		assertTrue(student == null);
		
		//test existing schoolID for student
		student = db.getStudentAccountWithSchoolID(schoolID);
		assertTrue(student != null);
		assertEquals(1, student.getStudentAccountID());
		assertTrue(student.getLastname().equals("Steinberg"));
		
		//check admin schoolID
		student = db.getStudentAccountWithSchoolID("910000000");
		assertTrue(student == null);
		
		//check non existent ID
		student = db.getStudentAccountWithSchoolID("-1");
		assertTrue(student == null);	
	}
	
	@Test
	public void testGetRoomsForStudentAccount() {
		Integer studentAccount_id = 3; //JIM
		assertTrue(roomList == null);
		
		roomList = db.getRoomsForStudentAccount(3);
		assertTrue(roomList != null);
		assertEquals(2, roomList.size());
		assertEquals(19, roomList.get(0).getRoomID());
		assertTrue(roomList.get(0).getRoomName().equals("Main office"));
		assertEquals(20, roomList.get(1).getRoomID());
	}
	
	
	@Test
	public void testGetLastRoomEventForStudent() {
		String firstname = "Test";
		String lastname = "Tester";
		String email = "ttester@ycp.edu";
		String password = hashSHA256.getHash("testpassword");
		String schoolID = "999999999";
		
		Integer studentAccount_id = null;
		Integer room_id = 19;
		
		//create a fresh account
		db.createStudentAccount(firstname, lastname, email, password, schoolID);
		
		student = db.getStudentAccountWithEmailandSchoolID(email, schoolID);
		assertTrue(student != null);
		studentAccount_id = student.getStudentAccountID();
		
		//populate some fake roomevents
		db.createRoomEventForStudentAccountWithIDandUpdateStatus(studentAccount_id, room_id, new Timestamp(new Date().getTime()));
		db.createRoomEventForStudentAccountWithIDandUpdateStatus(studentAccount_id, room_id, new Timestamp(new Date().getTime()));
		db.createRoomEventForStudentAccountWithIDandUpdateStatus(studentAccount_id, room_id, new Timestamp(new Date().getTime()));
		db.createRoomEventForStudentAccountWithIDandUpdateStatus(studentAccount_id, room_id, new Timestamp(new Date().getTime()));
		try {Thread.sleep((long) 10);} catch (InterruptedException e1) {}
		
		Date start = new Date();
		db.createRoomEventForStudentAccountWithIDandUpdateStatus(studentAccount_id, room_id, new Timestamp(start.getTime()));
		
		//get most recent
		RoomEvent e = db.getLastRoomEventForStudent(studentAccount_id);
		assertTrue(e != null);
		
		assertEquals(19,e.getRoomID());
		assertTrue(e.getStartTime().equals(start));
		
		//cleanup
		db.deleteStudentAccount(studentAccount_id);
	}
	
	@Test
	public void testUpdateLogNoteforRoomEvent() {
		String firstname = "Test";
		String lastname = "Tester";
		String email = "ttester@ycp.edu";
		String password = hashSHA256.getHash("testpassword");
		String schoolID = "999999999";
		
		Integer studentAccount_id = null;
		Integer room_id = 19;
		
		//create a fresh account
		db.createStudentAccount(firstname, lastname, email, password, schoolID);
		
		student = db.getStudentAccountWithEmailandSchoolID(email, schoolID);
		assertTrue(student != null);
		studentAccount_id = student.getStudentAccountID();
		
		//populate fake roomevent
		db.createRoomEventForStudentAccountWithIDandUpdateStatus(studentAccount_id, room_id, new Timestamp(new Date().getTime()));
		
		//get the roomEvent and check lognote
		RoomEvent event = db.getLastRoomEventForStudent(studentAccount_id);
		assertTrue(event.getLognote().equals(""));
		
		db.updateLogNoteforRoomEvent(event.getRoomEventID(), "I am an Update");
		
		event = db.getLastRoomEventForStudent(studentAccount_id);
		assertTrue(event.getLognote().equals("I am an Update"));
		
		//cleanup
		db.deleteStudentAccount(studentAccount_id);
	}
	
	@Test
	public void testUpdateRoomEventandStatusForStudentAccountWithAccountIDandEventID() {
		String firstname = "Test";
		String lastname = "Tester";
		String email = "ttester@ycp.edu";
		String password = hashSHA256.getHash("testpassword");
		String schoolID = "999999999";
		
		Integer studentAccount_id = null;
		Integer room_id = 19;
		
		//create a fresh account
		db.createStudentAccount(firstname, lastname, email, password, schoolID);
		//get account info
		student = db.getStudentAccountWithEmailandSchoolID(email, schoolID);
		assertTrue(student != null);
		studentAccount_id = student.getStudentAccountID();
		
		//populate fake roomevent
		db.createRoomEventForStudentAccountWithIDandUpdateStatus(studentAccount_id, room_id, new Timestamp(new Date().getTime()));
		student = db.getStudentAccountWithEmailandSchoolID(email, schoolID);
		
		//status should be now be
		assertTrue(student.getStatus());
		
		//get most recent event back
		RoomEvent event = db.getLastRoomEventForStudent(student.getStudentAccountID());
		assertTrue(event != null);
		
		//set up new date for end timestamp
		Date end = new Date();
		//call udate
		db.updateRoomEventandStatusForStudentAccountWithAccountIDandEventID(studentAccount_id, 
				event.getRoomEventID(), new Timestamp(end.getTime()), Boolean.FALSE, Boolean.FALSE);
		//get event again
		RoomEvent event1 = db.getLastRoomEventForStudent(studentAccount_id);
		assertTrue(event1 != null);
		//make sure eventid matches expected and that the end time has now changed
		assertTrue(event1.getRoomEventID() == event.getRoomEventID());
		assertFalse(event1.getEndTime().equals(event.getEndTime()));
		
		//check that status has changed as well
		student = db.getStudentAccountWithEmailandSchoolID(email, schoolID);
		assertFalse(student.getStatus());
		
		//cleanup
		db.deleteStudentAccount(student.getStudentAccountID());
	}
	
	@Test
	public void testGetTopTeamWithAccountID() {
		assertTrue(topTeam == null);
		
		topTeam = db.getTopTeamWithAccountID(3); //Jim Halpert member of dunder mifflin
		assertTrue(topTeam != null);
		assertTrue(topTeam.getTeamname().equals("Dunder Mifflin"));
		assertEquals(1, topTeam.getTeamID());
		
		topTeam = db.getTopTeamWithAccountID(-1);
		assertTrue(topTeam == null);
		
	}
	
	@Test
	public void testGetSubTeamWithAccountID() {
		assertTrue(subTeam == null);
		
		subTeam = db.getSubTeamWithAccountID(1);//jason on controls subteam
		assertTrue(subTeam != null);
		assertTrue(subTeam.getTeamname().equals("Controls"));
		assertEquals(8, subTeam.getTeamID());
		assertEquals(2, subTeam.getTopTeamID());
		
		subTeam = db.getSubTeamWithAccountID(-1);
		assertTrue(subTeam == null);
	}
	
	@Test
	public void testAssignStudentToSubTeam() {
		String firstname = "Test";
		String lastname = "Tester";
		String email = "ttester@ycp.edu";
		String password = hashSHA256.getHash("testpassword");
		String schoolID = "999999999";
		String topTeam= "TEST TOP";
		String subTeam = "TEST SUB";
		
		
		System.out.println("#######################");
		//set up topteam, subteam, and student account
		db.createTopTeam(topTeam);
		int tid = db.getTopTeamWithTeamname(topTeam).getTeamID();
		System.out.println(tid);
		db.createSubTeam(subTeam, tid);
		int sid = db.getSubTeamWithTeamname(subTeam).getTeamID();
		System.out.println(sid);
		db.createStudentAccount(firstname, lastname, email, password, schoolID);
		student = db.getStudentAccountWithEmailandSchoolID(email, schoolID);
		//suTeam should have any students yet
		//add student to sub team
		assertTrue(db.assignStudentToSubTeam(subTeam, student.getAccountID()));
		
		List<StudentAccount> students = db.getAllStudentsInSubTeamWithTeamName(subTeam);
		assertTrue(students!=null);
		//assertEquals(1, students.size());
		assertEquals(1, students.size());
		
		db.deleteStudentAccount(student.getStudentAccountID());
		db.deleteSubTeam(sid);
		db.deleteTopTeam(tid);
	}	
}
