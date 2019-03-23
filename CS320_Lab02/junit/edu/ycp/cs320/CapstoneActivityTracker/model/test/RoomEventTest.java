package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import java.time.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.RoomEvent;

public class RoomEventTest {
	
	private LocalDateTime start, end;
	private RoomEvent event;
	@Before
	public void setUp() {
		start = LocalDateTime.of(2000, Month.APRIL, 20, 10, 30, 0);
		end = LocalDateTime.of(2000, Month.APRIL, 20, 11, 30, 0);
		event = new RoomEvent();
	}

	@Test
	public void testRoomEventLocalDateTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEndTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStartTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFlag() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDuration() {
		event.setStartTime(start);
		event.setEndTime(end);
		event.setDuration();
		long var = event.getDuration();
		System.out.println(var);
	}

}
