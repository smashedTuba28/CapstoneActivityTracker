package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.*;

public class AdminAccountTest {
	AdminAccount admin, testAdmin;
	/*
	List<Team> teams;
	List<Room> rooms;
	Team ppc, ss, tein, tots;
	Team tester;
	Room conference, office, broom;
	Room testRoom;
	*/
	@Before
	public void setUp() throws Exception {
		
		admin = new AdminAccount();
		
		
		/* Not used due to all of this being functionality of the FakeDatabase.java
		teams = new ArrayList<Team>();
		rooms = new ArrayList<Room>();
		
		ppc = new Team("Party Planning Committee");
		ss = new Team("Scranton Stanglers");
		tein = new Team("The Einsteins");
		tots = new Team("Scott's Tots");
		
		
		
		conference = new Room("Confrence Room", 101);
		office = new Room("Manager Office", 100);
		broom = new Room("Break Room", 105);
		*/
	
	}
	
	@Test
	public void testSetFirstname() {
		String name = "Jim";
		admin.setFirstname(name);
		assertTrue(admin.getFirstname().equals("Jim"));
		admin.setFirstname("Dwight");
		assertFalse(admin.getFirstname().equals(name));
	}

	@Test
	public void testSetLastname() {
		String name = "Halpert";
		admin.setLastname(name);
		assertTrue(admin.getLastname().equals("Halpert"));
		admin.setLastname("Schrute");
		assertFalse(admin.getLastname().equals(name));
	}

	@Test
	public void testSetEmail() {
		String email = "mscott@ycp.edu";
		admin.setEmail(email);
		assertTrue(admin.getEmail().equals("mscott@ycp.edu"));
		admin.setEmail("pbeesly@ycp.edu");
		assertFalse(admin.getEmail().equals(email));
	}

	@Test
	public void testSetSchoolID() {
		String id = "901234567";
		admin.setSchoolID(id);
		assertTrue(admin.getSchoolID().equals("901234567"));
		admin.setSchoolID("9076543210");
		assertFalse(admin.getSchoolID().equals(id));
	}

	@Test
	public void testSetPassword() {
		String password = "beets";
		admin.setPassword(password);
		assertTrue(admin.getPassword().equals("beets"));
		admin.setFirstname("bears");
		assertFalse(admin.getFirstname().equals(password));
	}
	
	@Test
	public void testSetAdminAccountID() {
		int id = 1;
		admin.setAdminAccountID(id);
		assertEquals(1, admin.getAdminAccountID());
		admin.setAdminAccountID(2);
		assertNotEquals(id, admin.getAdminAccountID());
	}
	
	/* All of this information is now in the database. therefore most of these test will end up in the test cases for the FakeDatabase.java
	@Test
	public void testCreateTeam() {
		String teamname = "Finer Things Club";
		admin.createTeam(teamname);
		tester = admin.findTeam(teamname);
		assertTrue(tester.getTeamname().equals("Finer Things Club"));		
	}

	@Test
	public void testAddTeam() {
		assertTrue(teams.isEmpty());
		
		admin.addTeam(ss);
		teams = admin.getTeams();
		assertFalse(teams.isEmpty());
		assertEquals(1,teams.size());

	}
	
	@Test
	public void testFindTeam() {
		assertTrue(admin.findTeam("Party Planning Committee") == null);
		
		admin.addTeam(ppc);
		tester = admin.findTeam("Party Planning Committee");
		
		assertTrue(tester != null);
		assertTrue(tester.getTeamname().equals("Party Planning Committee"));
	}

	@Test
	public void testRemoveTeam() {
		admin.addTeam(ppc);
		admin.addTeam(ss);
		admin.addTeam(tein);
		admin.addTeam(tots);
		
		teams = admin.getTeams();
		assertEquals(4, teams.size());
		
		tester = admin.findTeam(ppc.getTeamname());
		assertTrue(tester != null);
		
		admin.removeTeam(ppc.getTeamname());
		tester = admin.findTeam(ppc.getTeamname());
		assertTrue(tester == null);
		
		teams = admin.getTeams();
		assertEquals(3, teams.size());
	}

	@Test
	public void testCreateRoom() {
		String roomname = "Annex";
		int number = 120;
		admin.createRoom(roomname, number);
		
		testRoom = admin.findRoom("Annex");
		assertTrue(testRoom.getRoomName().equals("Annex"));
	}
	
	@Test
	public void testAddRoom() {
		assertTrue(rooms.isEmpty());
		
		admin.addRoom(conference);
		admin.addRoom(broom);
		admin.addRoom(office);
		
		rooms = admin.getRooms();
		assertFalse(rooms.isEmpty());
		assertEquals(3, rooms.size());
	}
	
	@Test
	public void testFindRoom() {
		assertTrue(admin.findRoom("Conference Room") == null);
		
		admin.addRoom(conference);
		
		testRoom = admin.findRoom(conference.getRoomName());
		
		assertTrue(testRoom != null);
		assertTrue(testRoom.getRoomName().equals(conference.getRoomName()));
		assertEquals(conference.getRoomNumber(), testRoom.getRoomNumber());
	}

	@Test
	public void testRemoveRoom() {
		admin.addRoom(conference);
		admin.addRoom(office);
		admin.addRoom(broom);
		
		rooms = admin.getRooms();
		assertEquals(3,rooms.size());
		
		testRoom = admin.findRoom(office.getRoomName());
		assertTrue(testRoom != null);
		
		admin.removeRoom(broom.getRoomName());
		testRoom = admin.findRoom(broom.getRoomName());
		assertTrue(testRoom == null);
		
		rooms = admin.getRooms();
		assertEquals(2, rooms.size());
	}

	@Test
	public void testAssignTeamRoom() {
		admin.addTeam(ppc);
		admin.addRoom(conference);
		admin.addRoom(broom);
		
		assertTrue(rooms.isEmpty());
		rooms = admin.findTeam(ppc.getTeamname()).getRooms();
		assertTrue(rooms.isEmpty());
		
		
		admin.assignTeamRoom(ppc.getTeamname(), conference.getRoomName());
		admin.assignTeamRoom(ppc.getTeamname(), broom.getRoomName());

		rooms = admin.findTeam(ppc.getTeamname()).getRooms();
		assertFalse(rooms.isEmpty());
		assertEquals(2, rooms.size());
	}
	*/
}
