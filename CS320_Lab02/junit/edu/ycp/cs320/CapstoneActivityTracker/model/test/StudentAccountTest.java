
package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import edu.ycp.cs320.CapstoneActivityTracker.model.*;
import java.time.*;

public class StudentAccountTest {
	
	StudentAccount student;
	//List<RoomEvent> createevents;
	Date start, time1, time2, time3;
	Date end1, end2, end3, end4;
	Date testend1, testend2, testend3, testend4;
	Date teststart1, teststart2, teststart3, teststart4;
	RoomEvent event, fakeevent;
	String oldLogNote, newLogNote;
	
	@Before
	public void setUp() throws Exception{
		
		student = new StudentAccount();
		//createevents = new ArrayList<RoomEvent>();
		start = new Date(2000, 0, 22, 10, 30, 0);
		time1 = new Date(2000, 2, 20, 10, 30, 0);
		time2 = new Date(2000, 6, 20, 11, 30, 0);
		time3 = new Date(2000, 11, 22, 11, 33, 0);
		
		end1 = new Date(2000, 0, 22, 11, 33, 0);
		end2 = new Date(2000, 2, 20, 11, 33, 0);
		end3 = new Date(2000, 6, 20, 11, 33, 0);
		end4 = new Date(2000, 11, 22, 11, 50, 0);

		teststart1 = new Date(1999, 11, 30, 11, 33, 0);
		teststart2 = new Date(2000, 1, 26, 11, 33, 0);
		teststart3 = new Date(2000, 7, 30, 11, 33, 0);
		teststart4 = new Date(2000, 11, 30, 12, 33, 0);
		
		testend1 = new Date(2000, 0, 30, 11, 33, 0);
		testend2 = new Date(2000, 2, 30, 11, 33, 0);
		testend3 = new Date(2000, 6, 30, 11, 33, 0);
		testend4 = new Date(2000, 11, 30, 11, 33, 0);
		
		oldLogNote = "oldlog";
		newLogNote = "newlog";
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
		//intializing fakeevent
		fakeevent = new RoomEvent();
		
		//creating a list of room events within student
		student.createRoomEvent(start);
		student.createRoomEvent(time1);
		student.createRoomEvent(time2);
		student.createRoomEvent(time3);
		
		//fakeevent has been set the same way as RoomEvent #1 in student
		fakeevent.setStartTime(start);
		
		//fakeevent is only equal to the first event in the student event list
		assertTrue(fakeevent.getStartTime().equals(student.findRoomEvent(1).getStartTime()));
		assertFalse(fakeevent.getStartTime().equals(student.findRoomEvent(2).getStartTime()));
		assertFalse(fakeevent.getStartTime().equals(student.findRoomEvent(3).getStartTime()));
		assertFalse(fakeevent.getStartTime().equals(student.findRoomEvent(4).getStartTime()));
		
		//checking that status has been changed once the student has created a roomevent
		assertTrue(student.getStatus());
		
		//checking if all of the numbers of the events are correct
		assertEquals(student.findRoomEvent(1).getRoomEventID(), 1);
		assertEquals(student.findRoomEvent(2).getRoomEventID(), 2);
		assertEquals(student.findRoomEvent(3).getRoomEventID(), 3);
		assertEquals(student.findRoomEvent(4).getRoomEventID(), 4);
		
		//there is no 5th event therefore this should the null value and return false
		assertFalse(student.findRoomEvent(2).getRoomEventID() == 5);
		
		//the list size is 4 and our roomevents are based off of this number
		assertEquals(student.getRoomEventList().size(), 4);
		
	}

	@Test
	public void testFindRoomEvent() {
		
		//creating list of RoomEvents in student
		student.createRoomEvent(start);
		student.createRoomEvent(time1);
		student.createRoomEvent(time2);
		student.createRoomEvent(time3);
		
		//enter false values knowing they would fail
		RoomEvent fake = student.findRoomEvent(5);
		//the fake RoomEvent should have nothing in it since RoomEvent 5 does not exist
		assertTrue(fake == null);
		
		//create a real room event 
		RoomEvent real = student.findRoomEvent(1);
		
		//second real room event
		event = student.findRoomEvent(2);
		
		//check for that event
		//fake = ....
		//!=
		
		//check start == start
		assertFalse(real.getStartTime().equals(student.findRoomEvent(2).getStartTime()));
		assertFalse(real.getStartTime().equals(student.findRoomEvent(3).getStartTime()));
		assertFalse(real.getStartTime().equals(student.findRoomEvent(4).getStartTime()));
		assertFalse(real.getStartTime().equals(event.getStartTime()));
		
		//asserting true that the real events made will equal the events within student's start times
		assertTrue(real.getStartTime().equals(student.findRoomEvent(1).getStartTime()));
		assertTrue(event.getStartTime().equals(student.findRoomEvent(2).getStartTime()));
		
		/*should fail due to asserting a room = a room in the list w/ same start time
		assertTrue(student.findRoomEvent(2).equals(event2));
		
		fails either way due to asserting a room is equals to a different room with a different start time
		assertFalse(student.findRoomEvent(1).equals(event2));
		assertFalse(student.findRoomEvent(3).equals(event));
		assertFalse(student.findRoomEvent(3).equals(event2));
		*/
		
	}

	@Test
	public void testFindRoomEvents() {
		List<RoomEvent> listOfOne = new ArrayList<RoomEvent>();
		List<RoomEvent> listOfTwo = new ArrayList<RoomEvent>();
		List<RoomEvent> listOfThree = new ArrayList<RoomEvent>();
		List<RoomEvent> listOfFour = new ArrayList<RoomEvent>();
		
		//creating a list of room events within student
		student.createRoomEvent(start);
		student.createRoomEvent(time1);
		student.createRoomEvent(time2);
		student.createRoomEvent(time3);
		
		//setting end times for events
		student.findRoomEvent(1).setEndTime(end1);
		student.findRoomEvent(2).setEndTime(end2);
		student.findRoomEvent(3).setEndTime(end3);
		student.findRoomEvent(4).setEndTime(end4);
		
		//creating lists of all the events within the time span
		listOfOne = student.findRoomEvents(teststart1, testend1);
		listOfTwo = student.findRoomEvents(teststart1, testend2);
		listOfThree = student.findRoomEvents(teststart1, testend3);
		listOfFour = student.findRoomEvents(teststart1, testend4);
	
		//checking that the list has all events
		assertTrue(listOfOne.size() == 1);
		assertTrue(listOfTwo.size() == 2);
		assertTrue(listOfThree.size() == 3);
		assertTrue(listOfFour.size() == 4);
		
		//checking that the right events are in the correct order
		assertTrue(listOfOne.get(0).equals(student.findRoomEvent(1)));				//ArrayList's first position is 0
		
		assertTrue(listOfTwo.get(0).equals(student.findRoomEvent(1)));	
		assertTrue(listOfTwo.get(1).equals(student.findRoomEvent(2)));
		
		assertTrue(listOfThree.get(0).equals(student.findRoomEvent(1)));	
		assertTrue(listOfThree.get(1).equals(student.findRoomEvent(2)));
		assertTrue(listOfThree.get(2).equals(student.findRoomEvent(3)));
		
		assertTrue(listOfFour.get(0).equals(student.findRoomEvent(1)));	
		assertTrue(listOfFour.get(1).equals(student.findRoomEvent(2)));
		assertTrue(listOfFour.get(2).equals(student.findRoomEvent(3)));
		assertTrue(listOfFour.get(3).equals(student.findRoomEvent(4)));				//positions of this list are then shifted down by one
		
		//checking to make sure that I am correct in my numbering logic
		assertFalse(listOfFour.get(1).equals(student.findRoomEvent(1)));
		assertFalse(listOfFour.get(2).equals(student.findRoomEvent(2)));
		assertFalse(listOfFour.get(3).equals(student.findRoomEvent(3)));

		//testing values within the RoomEvents
		assertTrue(listOfFour.get(0).getStartTime().equals(student.findRoomEvent(1).getStartTime()));
		assertTrue(listOfFour.get(1).getStartTime().equals(student.findRoomEvent(2).getStartTime()));
		assertTrue(listOfFour.get(2).getStartTime().equals(student.findRoomEvent(3).getStartTime()));
		assertTrue(listOfFour.get(3).getStartTime().equals(student.findRoomEvent(4).getStartTime()));
		
		//testing end values within the RoomEvents
		assertTrue(listOfFour.get(0).getEndTime().equals(student.findRoomEvent(1).getEndTime()));
		assertTrue(listOfFour.get(1).getEndTime().equals(student.findRoomEvent(2).getEndTime()));
		assertTrue(listOfFour.get(2).getEndTime().equals(student.findRoomEvent(3).getEndTime()));
		assertTrue(listOfFour.get(3).getEndTime().equals(student.findRoomEvent(4).getEndTime()));
		
		
	}

	@Test
	public void testUpdateRoomEventLognote() {
		
		//creating a list of room events within student
		student.createRoomEvent(start);
		student.createRoomEvent(time1);
		student.createRoomEvent(time2);
		student.createRoomEvent(time3);
		
		student.updateRoomEventLognote(1, oldLogNote);
		student.updateRoomEventLognote(2, newLogNote);
		
		//using oldlognote in RoomEvent 1
		assertTrue(student.findRoomEvent(1).getLognote().equals("oldlog"));
		assertTrue(student.findRoomEvent(1).getLognote().equals(oldLogNote));
		assertFalse(student.findRoomEvent(1).getLognote().equals(newLogNote));
		
		//using newlognote in RoomEvent 2
		assertTrue(student.findRoomEvent(2).getLognote().equals("newlog"));
		assertTrue(student.findRoomEvent(2).getLognote().equals(newLogNote));
		
		//updating the oldLogNote to the newLogNote
		student.updateRoomEventLognote(1, newLogNote);
		
		//checking if the lognote has been updated
		assertTrue(student.findRoomEvent(1).getLognote().equals(newLogNote));
		assertFalse(student.findRoomEvent(1).getLognote().equals(oldLogNote));
	
	}
	
	@Test
	public void testSetStudentAccountID() {
		int id = 1;
		student.setStudentAccountID(id);
		assertEquals(1, student.getStudentAccountID());
		student.setStudentAccountID(2);
		assertNotEquals(id, student.getStudentAccountID());
	}
}