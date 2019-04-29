package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import edu.ycp.cs320.CapstoneActivityTracker.model.Room;
public class RoomTest {

	Room testRoom;
	
	@Before
	public void setUp() throws Exception {
		testRoom = new Room();
	}

	@Test
	public void testSetRoomname() {
		String roomname = "Power Systems Lab";
		testRoom.setRoomName(roomname);
		assertTrue(testRoom.getRoomName().equals("Power Systems Lab"));
		testRoom.setRoomName("nope");
		assertFalse(testRoom.getRoomName().equals(roomname));
	}

	@Test
	public void testRoomStringInt() {
		String name = "Robotics Lab";
		int number = 121;
		Room room = new Room(name, number);
		assertTrue(room.getRoomName().equals("Robotics Lab"));
	}
	
	@Test
	public void testSetRoomnumber() {
		int roomNumber = 132;
		testRoom.setRoomNumber(roomNumber);
		assertEquals(132, testRoom.getRoomNumber());
		testRoom.setRoomNumber(128);
		assertNotEquals(roomNumber, testRoom.getRoomNumber());
	}
	
	@Test
	public void testSetRoomID() {
		int id = 1;
		testRoom.setRoomID(id);
		assertEquals(1, testRoom.getRoomID());
		testRoom.setRoomID(2);
		assertNotEquals(id, testRoom.getRoomID());
	}
}
