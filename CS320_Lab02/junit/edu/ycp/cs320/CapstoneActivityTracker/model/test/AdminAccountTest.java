package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.*;

public class AdminAccountTest {
	AdminAccount admin;
	List<Team> teams;
	List<Room> rooms;
	Team ppc, ss, tein, tots;
	Team tester;
	Room conference, office, broom;
	Room test;
	
	@Before
	public void setUp() throws Exception {
		admin = new AdminAccount("Toby", "Flenderson", "tflenderson@ycp.edu", "iheartpam", "901234567", true);
		
		teams = new ArrayList<Team>();
		rooms = new ArrayList<Room>();
		
		ppc = new Team("Party Planning Committee");
		ss = new Team("Scranton Stanglers");
		tein = new Team("The Einsteins");
		tots = new Team("Scott's Tots");
		
		
		conference = new Room("Confrence Room", 101);
		office = new Room("Manager Office", 100);
		broom = new Room("Break Room", 105);
	
		
		
	}

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
		
		test = admin.findRoom("Annex");
		assertTrue(test.getRoomName().equals("Annex"));
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
		
		test = admin.findRoom(conference.getRoomName());
		
		assertTrue(test != null);
		assertTrue(test.getRoomName().equals(conference.getRoomName()));
		assertEquals(conference.getRoomNumber(), test.getRoomNumber());
	}

	@Test
	public void testRemoveRoom() {
		admin.addRoom(conference);
		admin.addRoom(office);
		admin.addRoom(broom);
		
		rooms = admin.getRooms();
		assertEquals(3,rooms.size());
		
		test = admin.findRoom(office.getRoomName());
		assertTrue(test != null);
		
		admin.removeRoom(broom.getRoomName());
		test = admin.findRoom(broom.getRoomName());
		assertTrue(test == null);
		
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
}
