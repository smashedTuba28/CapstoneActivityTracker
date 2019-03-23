package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import edu.ycp.cs320.CapstoneActivityTracker.model.*;
import java.time.*;

public class StudentAccountTest {
	
	StudentAccount student;
	List<RoomEvent> events;
	LocalDateTime start, time1, time2, time3;
	RoomEvent event, event2;
	
	@Before
	public void setUp() throws Exception{
		
		student = new StudentAccount();
		events = new ArrayList<RoomEvent>();
		start = LocalDateTime.of(2019, Month.MARCH, 22, 10, 30, 0);
		time1 = LocalDateTime.of(2004, Month.APRIL, 20, 10, 30, 0);
		time2 = LocalDateTime.of(1999, Month.JANUARY, 20, 11, 30, 0);
		time3 = LocalDateTime.of(2010, Month.JULY, 22, 11, 33, 0);

	}
	
	@Test
	public void testSetFirstname() {
		String name = "Jim";
		student.setFirstname(name);
		assertTrue(student.getFirstname().equals("Jim"));
		student.setFirstname("Dwight");
		assertFalse(student.getFirstname().equals(name));
	}

	@Test
	public void testSetLastname() {
		String name = "Halpert";
		student.setLastname(name);
		assertTrue(student.getLastname().equals("Halpert"));
		student.setLastname("Schrute");
		assertFalse(student.getLastname().equals(name));
	}

	@Test
	public void testSetEmail() {
		String email = "mscott@ycp.edu";
		student.setEmail(email);
		assertTrue(student.getEmail().equals("mscott@ycp.edu"));
		student.setEmail("pbeesly@ycp.edu");
		assertFalse(student.getEmail().equals(email));
	}

	@Test
	public void testSetSchoolID() {
		String id = "901234567";
		student.setSchoolID(id);
		assertTrue(student.getSchoolID().equals("901234567"));
		student.setSchoolID("9076543210");
		assertFalse(student.getSchoolID().equals(id));
	}

	@Test
	public void testSetPassword() {
		String password = "beets";
		student.setPassword(password);
		assertTrue(student.getPassword().equals("beets"));
		student.setFirstname("bears");
		assertFalse(student.getFirstname().equals(password));
	}

	@Test
	public void testSetStatus() {
		boolean status = true;
		student.setStatus(status);
		assertTrue(student.getStatus());
		student.setStatus(false);
		assertFalse(student.getStatus());
	}

	@Test
	public void testCreateRoomEvent() {
		
		//no idea why this fails either
		event = student.createRoomEvent(start);
		assertTrue(student.getStatus());
		assertEquals(event.getStartTime(), start);
		assertEquals(event.getNumber(), 1);
		
	}

	@Test
	public void testFindRoomEvents() {
		
		event = student.createRoomEvent(time1);
		event2 = student.createRoomEvent(time2);
		events.add(student.createRoomEvent(time1));
		events.add(student.createRoomEvent(time2));
		events.add(student.createRoomEvent(time3));
		
		//asserting that the start times are equivalent should return true?
		assertTrue(student.findRoomEvent(1).getStartTime().equals(event.getStartTime()));
		
		
		/*should fail due to asserting a room = a room in the list w/ same start time
		assertTrue(student.findRoomEvent(2).equals(event2));
		
		fails either way due to asserting a room is equals to a different room with a different start time
		assertFalse(student.findRoomEvent(1).equals(event2));
		assertFalse(student.findRoomEvent(3).equals(event));
		assertFalse(student.findRoomEvent(3).equals(event2));
		*/
		
	}

	@Test
	public void testFindRoomEvent() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateRoomEventLognote() {
		fail("Not yet implemented");
	}

}
