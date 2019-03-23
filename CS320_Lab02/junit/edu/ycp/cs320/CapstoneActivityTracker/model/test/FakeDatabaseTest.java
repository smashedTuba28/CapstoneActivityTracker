package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.Account;
import edu.ycp.cs320.CapstoneActivityTracker.model.AdminAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.Room;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeam;
import edu.ycp.cs320.CapstoneActivityTracker.model.TopTeam;
import edu.ycp.cs320.CapstonActivtyTracker.db.FakeDatabase;

public class FakeDatabaseTest {
	private List<AdminAccount> testAdminList;
	private List<StudentAccount> testStudentList;
	private List<TopTeam> testTopTeamList;
	private List<Room> testRoomList;
	private List<Account> testAccountList;
	private List<SubTeam> testSubTeamList;
	
	private FakeDatabase fakedb;
	
	
	
	@Before
	public void setUp() throws Exception {
		testAccountList = new ArrayList<Account>();
		testAdminList = new ArrayList<AdminAccount>();
		testStudentList = new ArrayList<StudentAccount>();
		testTopTeamList = new ArrayList<TopTeam>();
		testRoomList = new ArrayList<Room>();
		
		fakedb = new FakeDatabase();
		fakedb.init();
	}

	
	@Test
	public void testGetAllAccounts() {
		assertTrue(testAccountList.isEmpty());
		
		testAccountList = fakedb.getAllAccounts();
		assertFalse(testAccountList.isEmpty());
		
		assertTrue(testAccountList.get(0).getFirstname().equals("John"));
		assertTrue(testAccountList.get(0).getLastname().equals("Doe"));
		assertTrue(testAccountList.get(0).getEmail().equals("jdoe@ycp.edu"));
		assertTrue(testAccountList.get(0).getSchoolID().equals("900000000"));
		assertTrue(testAccountList.get(0).getFaculty());
	
		assertTrue(testAccountList.get(1).getFirstname().equals("Jason"));
		assertTrue(testAccountList.get(1).getLastname().equals("Steinberg"));
		assertTrue(testAccountList.get(1).getEmail().equals("jsteinberg@ycp.edu"));
		assertTrue(testAccountList.get(1).getSchoolID().equals("900000001"));
		assertFalse(testAccountList.get(1).getFaculty());
		
		assertTrue(testAccountList.get(2).getFirstname().equals("Travis"));
		assertTrue(testAccountList.get(2).getLastname().equals("Wetzel"));
		assertTrue(testAccountList.get(2).getEmail().equals("twetzel1@ycp.edu"));
		assertTrue(testAccountList.get(2).getSchoolID().equals("900000002"));
		assertFalse(testAccountList.get(2).getFaculty());
		
		assertTrue(testAccountList.get(3).getFirstname().equals("William"));
		assertTrue(testAccountList.get(3).getLastname().equals("Taylor"));
		assertTrue(testAccountList.get(3).getEmail().equals("wtaylor1@ycp.edu"));
		assertTrue(testAccountList.get(3).getSchoolID().equals("900000003"));
		assertFalse(testAccountList.get(3).getFaculty());
		
		assertTrue(testAccountList.get(4).getFirstname().equals("Robert"));
		assertTrue(testAccountList.get(4).getLastname().equals("California"));
		assertTrue(testAccountList.get(4).getEmail().equals("lizardking@ycp.edu"));
		assertTrue(testAccountList.get(4).getSchoolID().equals("900000004"));
		assertFalse(testAccountList.get(4).getFaculty());
	}
	
	@Test
	public void testGetAllAdminAccounts() {
		assertTrue(testAdminList.isEmpty());
		
		testAdminList = fakedb.getAllAdminAccounts();
		assertFalse(testAdminList.isEmpty());
		
		assertTrue(testAdminList.get(0).getFirstname().equals("John"));
		assertTrue(testAdminList.get(0).getLastname().equals("Doe"));
		assertTrue(testAdminList.get(0).getEmail().equals("jdoe@ycp.edu"));
		assertTrue(testAdminList.get(0).getSchoolID().equals("900000000"));
		assertTrue(testAdminList.get(0).getFaculty());
	}
	
	@Test
	public void testGetAllStudentAccounts() {
		assertTrue(testStudentList.isEmpty());
		
		testStudentList = fakedb.getAllStudentAccounts();
		assertFalse(testStudentList.isEmpty());
		
		assertTrue(testStudentList.get(0).getFirstname().equals("Jason"));
		assertTrue(testStudentList.get(0).getLastname().equals("Steinberg"));
		assertTrue(testStudentList.get(0).getEmail().equals("jsteinberg@ycp.edu"));
		assertTrue(testStudentList.get(0).getSchoolID().equals("900000001"));
		assertFalse(testStudentList.get(0).getFaculty());
		
		assertTrue(testStudentList.get(1).getFirstname().equals("Travis"));
		assertTrue(testStudentList.get(1).getLastname().equals("Wetzel"));
		assertTrue(testStudentList.get(1).getEmail().equals("twetzel1@ycp.edu"));
		assertTrue(testStudentList.get(1).getSchoolID().equals("900000002"));
		assertFalse(testStudentList.get(1).getFaculty());
		
		assertTrue(testStudentList.get(2).getFirstname().equals("William"));
		assertTrue(testStudentList.get(2).getLastname().equals("Taylor"));
		assertTrue(testStudentList.get(2).getEmail().equals("wtaylor1@ycp.edu"));
		assertTrue(testStudentList.get(2).getSchoolID().equals("900000003"));
		assertFalse(testStudentList.get(2).getFaculty());
		
		assertTrue(testStudentList.get(3).getFirstname().equals("Robert"));
		assertTrue(testStudentList.get(3).getLastname().equals("California"));
		assertTrue(testStudentList.get(3).getEmail().equals("lizardking@ycp.edu"));
		assertTrue(testStudentList.get(3).getSchoolID().equals("900000004"));
		assertFalse(testStudentList.get(3).getFaculty());
	}
	
	@Test
	public void testGetAllTeam() {
		assertTrue(testTopTeamList.isEmpty());
		
		testTopTeamList = fakedb.getAllTopTeams();
		assertFalse(testTopTeamList.isEmpty());
	
		assertTrue(testTopTeamList.get(0).getTeamname().equals("Drone Team"));
		assertTrue(testTopTeamList.get(1).getTeamname().equals("The Office"));
		
		testSubTeamList = testTopTeamList.get(0).getSubTeams();
		assertTrue(testSubTeamList.get(0).getTeamname().equals("Controls"));
		assertTrue(testSubTeamList.get(1).getTeamname().equals("Aircraft Design"));

		testSubTeamList = testTopTeamList.get(1).getSubTeams();
		assertTrue(testSubTeamList.get(0).getTeamname().equals("Party Planning Committee"));
		assertTrue(testSubTeamList.get(1).getTeamname().equals("Finer Things Club"));
		assertTrue(testSubTeamList.get(2).getTeamname().equals("Scott's Tots"));
		
	}
	
	@Test
	public void testGetAllRooms() {
		assertTrue(testRoomList.isEmpty());
		
		testRoomList = fakedb.getAllRooms();
		assertFalse(testRoomList.isEmpty());
	
		assertTrue(testRoomList.get(0).getRoomName().equals("Power Systems Lab"));
		assertEquals(128, testRoomList.get(0).getRoomNumber());
		
		assertTrue(testRoomList.get(1).getRoomName().equals("Computer Lab"));
		assertEquals(132, testRoomList.get(1).getRoomNumber());
	
		assertTrue(testRoomList.get(2).getRoomName().equals("Software Engineering Lab"));
		assertEquals(119, testRoomList.get(2).getRoomNumber());
	
		assertTrue(testRoomList.get(3).getRoomName().equals("Visualization Lab"));
		assertEquals(118, testRoomList.get(3).getRoomNumber());
	}
	
	
	@Test
	public void testVerifyAccount() {
		//check all initialized accounts
		assertTrue(fakedb.verifyAccount("jdoe@ycp.edu", "password"));
		assertTrue(fakedb.verifyAccount("jsteinberg@ycp.edu", "password"));
		assertTrue(fakedb.verifyAccount("twetzel1@ycp.edu", "password"));
		assertTrue(fakedb.verifyAccount("wtaylor1@ycp.edu", "password"));
		assertTrue(fakedb.verifyAccount("lizardking@ycp.edu", "password"));
		
		//existing email with wrong password
		assertFalse(fakedb.verifyAccount("jdoe@ycp.edu", "wrongpassword"));
		
		//wrong email with correct password
		assertFalse(fakedb.verifyAccount("wrongemail@ycp.edu", "password"));
		
		//neither password nor email exist in db
		assertFalse(fakedb.verifyAccount("idontexist@ycp.edu", "asIf"));
	}

	@Test
	public void testCreateAccount() {
		testAdminList = fakedb.getAllAdminAccounts();
		testStudentList = fakedb.getAllStudentAccounts();
		testAccountList = fakedb.getAllAccounts();
		assertEquals(1, testAdminList.size());//initialized value
		assertEquals(4, testStudentList.size());//initialized value
		assertEquals(5, testAccountList.size());//initialized value
		
		//test that faculty becomes an AdminAccount
		//should be unable to find Toby Flenderson
		assertFalse(fakedb.verifyAccount("tflenderson", "iheartpam"));
		fakedb.createAccount("Toby", "Flenderson", "tflenderson@ycp.edu", "iheartpam", "901234567", true);
		//admin account should increase size of adminList and AccountList not StudnetList
		testAdminList = fakedb.getAllAdminAccounts();
		testStudentList = fakedb.getAllStudentAccounts();
		testAccountList = fakedb.getAllAccounts();
		assertEquals(2, testAdminList.size());//previous+ 1
		assertEquals(4, testStudentList.size());//previous value
		assertEquals(6, testAccountList.size());//previous value + 1
		//account should now return true for valid
		assertTrue(fakedb.verifyAccount("tflenderson@ycp.edu", "iheartpam"));
		
		
		//test for student account being created as StudentAccount
		//should be unable to find account
		assertFalse(fakedb.verifyAccount("jhalpert@ycp.edu", "iheartbeesly"));
		fakedb.createAccount("Jim", "Halpert", "jhalpert@ycp.edu", "iheartbeesly", "903123123", false);
		//student account should increase size of studentList and AccountList not AdminList
		testAdminList = fakedb.getAllAdminAccounts();
		testStudentList = fakedb.getAllStudentAccounts();
		testAccountList = fakedb.getAllAccounts();
		assertEquals(2, testAdminList.size());//previous value
		assertEquals(5, testStudentList.size());//previous value + 1
		assertEquals(7, testAccountList.size());//previous value + 1
		//account should now return true for valid
		assertTrue(fakedb.verifyAccount("jhalpert@ycp.edu", "iheartbeesly"));

	}
	

	@Test
	public void testCreateTopTeam() {
		testTopTeamList = fakedb.getAllTopTeams();
		assertEquals(2, testTopTeamList.size()); //only two top teams from init
		assertTrue(fakedb.findTopTeam("Kevin and The Zits") == null);
		
		fakedb.createTopTeam("Kevin and The Zits");
		testTopTeamList = fakedb.getAllTopTeams();
		TopTeam top = fakedb.findTopTeam("Kevin and The Zits");
		
		assertEquals(3, testTopTeamList.size());
		assertTrue(top.getTeamname().equals("Kevin and The Zits"));
		
	}

	@Test
	public void testCreateSubTeam() {
		testTopTeamList = fakedb.getAllTopTeams();
		assertEquals(2, testTopTeamList.size());
		assertEquals(3, testTopTeamList.get(1).getSubTeams().size());
		
		//make sure sub team doesnt already exist
		SubTeam sub = fakedb.findSubTeam("Kevin and The Zits");
		assertTrue(sub == null);
		
		//using existing Top Team
		fakedb.createSubTeam("The Office", "Kevin and The Zits");
		sub = fakedb.findSubTeam("Kevin and The Zits");
		assertTrue(sub != null);
		assertTrue(sub.getTeamname().equals("Kevin and The Zits"));
		TopTeam top = fakedb.findTopTeamOfSubTeam("Kevin and The Zits");
		assertTrue(top != null);
		assertTrue(top.getTeamname().equals("The Office"));
		
		//top team doesn't exist should throw exception
		try {
			fakedb.createSubTeam("NOT A REAL TEAM", "Kevin and The Zits");
			fail("Sould have thrown an exception");
		}catch(NoSuchElementException e) {
		}
	}

	

	@Test
	public void testFindTopTeam() {
	
		//find a top team that exists	
		TopTeam top = (fakedb.findTopTeam("Drone Team"));			
		assertTrue(top!=null);
		assertTrue(top.getTeamname().equals("Drone Team"));
	
		//return null for one that doesnt exist 
		top = fakedb.findTopTeam("Scranton Stranglers");
		assertTrue(top==null);
<<<<<<< Upstream, based on origin/twetzel1_V1
	
=======
>>>>>>> 03bf2cd FakeDB Test updates
	}
	
	@Test
	public void testFindSubTeam() {
		//find sub team that exists
		SubTeam sub = (fakedb.findSubTeam("Controls"));
		assertTrue(sub != null);
		assertTrue(sub.getTeamname().equals("Controls"));
		
		//should return null if sub team doesnt exist
		sub = fakedb.findSubTeam("Party Planning Comittee");
		assertTrue(sub == null);		
	}

	@Test
	public void testFindTopTeamOfSubTeam() {
		// find top team for existing sub team
		TopTeam top = fakedb.findTopTeamOfSubTeam("Aircraft Design");
		assertTrue(top != null);
		assertTrue(top.getTeamname().equals("Drone Team"));
		
		//should return null if subteam doesnt exist
		top = fakedb.findTopTeamOfSubTeam("Scranton Stranglers");
		assertTrue(top == null);
	}

	
	@Test
	public void testRemoveTopTeam() {
		//confirm that team exists with sub classes
		testTopTeamList = fakedb.getAllTopTeams();
		assertEquals(2, testTopTeamList.size());
		assertEquals(3, testTopTeamList.get(1).getSubTeams().size());
		assertTrue(fakedb.findTopTeam("The Office") != null);
		assertTrue(fakedb.findSubTeam("Party Planning Committee") != null);
		assertTrue(fakedb.findSubTeam("Finer Things Club") != null);
		assertTrue(fakedb.findSubTeam("Scott's Tots") != null);
		//delete topTeam "The Office"
		fakedb.removeTopTeam("The Office");
		//check that top team and sub teams are gone
		assertTrue(fakedb.findTopTeam("The Office") == null);
		assertTrue(fakedb.findSubTeam("Party Planning Committee") == null);
		assertTrue(fakedb.findSubTeam("Finer Things Club") == null);
		assertTrue(fakedb.findSubTeam("Scott's Tots") == null);
	}
	
	@Test
	public void testRemoveSubTeam() {
		//confrim that top team exists and sub classes
		testTopTeamList = fakedb.getAllTopTeams();
		assertEquals(2, testTopTeamList.size());
		assertEquals(3, testTopTeamList.get(1).getSubTeams().size());
		assertTrue(fakedb.findTopTeam("The Office") != null);
		assertTrue(fakedb.findSubTeam("Party Planning Committee") != null);
		assertTrue(fakedb.findSubTeam("Finer Things Club") != null);
		assertTrue(fakedb.findSubTeam("Scott's Tots") != null);
		
		//delete subTeam Scott's Tots
		fakedb.removeSubTeam("Scott's Tots");
		//check that sub team is gone but top team still exists
		testTopTeamList = fakedb.getAllTopTeams();
		assertEquals(2, testTopTeamList.size());//size should stay the same
		assertEquals(2, testTopTeamList.get(1).getSubTeams().size());//size should be 1 less
		assertTrue(fakedb.findTopTeam("The Office") != null);
		assertTrue(fakedb.findSubTeam("Party Planning Committee") != null);
		assertTrue(fakedb.findSubTeam("Finer Things Club") != null);
		assertTrue(fakedb.findSubTeam("Scott's Tots") == null);//this should be null if removal success
		
		//should throw exception is TopTeam doesnt exist
		try {
			fakedb.removeSubTeam("Not A Real SubTeam");
			fail("Should Have Caught Exception");
		}catch(NoSuchElementException e) {
		}
	}
	
	@Test
	public void testAssignTeamRoom() {
		
		
		
		
		
		
	}

	@Test
	public void testFindRoom() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveTeamRoom() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateRoom() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveRoom() {
		fail("Not yet implemented");
	}
}
