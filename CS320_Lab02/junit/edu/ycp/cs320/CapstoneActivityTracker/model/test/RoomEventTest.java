package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import java.time.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.RoomEvent;

public class RoomEventTest {
	
	LocalDateTime start, time1, time2, time3;
	LocalDateTime end1, end2, end3, end4;
	
	RoomEvent event, fakeevent;
	
	@Before
	public void setUp() {
		start = LocalDateTime.of(2000, Month.JANUARY, 22, 10, 30, 0);
		time1 = LocalDateTime.of(2000, Month.MARCH, 20, 10, 30, 0);
		time2 = LocalDateTime.of(2000, Month.JULY, 20, 11, 30, 0);
		time3 = LocalDateTime.of(2000, Month.DECEMBER, 22, 11, 33, 0);
		
		end1 = LocalDateTime.of(2000, Month.JANUARY, 22, 11, 33, 0);
		end2 = LocalDateTime.of(2000, Month.MARCH, 20, 11, 33, 0);
		end3 = LocalDateTime.of(2000, Month.JULY, 20, 11, 33, 0);
		end4 = LocalDateTime.of(2000, Month.DECEMBER, 22, 11, 50, 0);
		
		event = new RoomEvent();
	}

	@Test
	public void testSetEndTime() {
		assertTrue(event.getEndTime() == null);
		event.setEndTime(end1);
		assertTrue(event.getEndTime().equals(end1));
		event.setEndTime(end2);
		assertFalse(event.getEndTime().equals(end1));
	}

	@Test
	public void testSetStartTime() {
		assertTrue(event.getStartTime() == null);
		event.setStartTime(start);
		assertTrue(event.getStartTime().equals(start));
		event.setStartTime(time1);
		assertFalse(event.getStartTime().equals(start));
	}

	@Test
	public void testSetFlag() {
		assertFalse(event.getFlag());
		event.setFlag(true);
		assertTrue(event.getFlag());
		event.setFlag(false);
		assertFalse(event.getFlag());
	}

	@Test
	public void testGetDuration() {
		event.setStartTime(start);
		event.setEndTime(end1);
		event.setDuration();
		long var = event.getDuration();
		assertEquals((long)63, var);
		
		event.setStartTime(time1);
		event.setEndTime(end2);
		event.setDuration();
		var = event.getDuration();
		assertEquals((long)63, var);
		
		event.setStartTime(time2);
		event.setEndTime(end3);
		event.setDuration();
		var = event.getDuration();
		assertEquals((long)3, var);
		
		event.setStartTime(time3);
		event.setEndTime(end4);
		event.setDuration();
		var = event.getDuration();
		assertEquals((long)17, var);
	}
	
	@Test
	public void testSetLogNote() {
		assertTrue(event.getLognote() == null);
		event.setLognote("I am very tired");
		assertTrue(event.getLognote().equals("I am very tired"));
		event.setLognote("Still tired");
		assertFalse(event.getLognote().equals("I am very tired"));
	}
}
