package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.*;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.RoomEvent;

public class RoomEventTest {
	
	Date start, time1, time2, time3, weekStart;
	Date end1, end2, end3, end4, weekEnd;
	
	RoomEvent event, fakeevent;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		start = new Date(2000,0,20,10,30,0);
		time1 = new Date(2000, 3, 20, 10, 30, 0);
		time2 = new Date(2000, 7, 20, 11, 30, 0);
		time3 = new Date(2000, 12, 22, 11, 33, 0);
		
		weekStart = new Date(2019, 2, 31, 00, 00, 00);
		weekEnd = new Date(2019, 3, 6, 24, 00, 00);
		
		
		end1 = new Date(2000, 0, 20, 11, 33, 0);
		end2 = new Date(2000, 3, 20, 11, 33, 0);
		end3 = new Date(2000, 7, 20, 11, 33, 0);
		end4 = new Date(2000, 12, 22, 11, 50, 0);
		
		event = new RoomEvent();
		//System.out.println(end4);
		//Timestamp stamp = new Timestamp(end4.getTime());
		//System.out.println(stamp);
		
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
		long var = event.getDuration();
		assertEquals(63, var);
		
		event.setStartTime(time1);
		event.setEndTime(end2);
		var = event.getDuration();
		assertEquals(63, var);
		
		event.setStartTime(time2);
		event.setEndTime(end3);
		var = event.getDuration();
		assertEquals(3, var);
		
		event.setStartTime(time3);
		event.setEndTime(end4);
		var = event.getDuration();
		assertEquals(17, var);
	}
	
	@Test
	public void testSetLogNote() {
		assertTrue(event.getLognote() == null);
		event.setLognote("I am very tired");
		assertTrue(event.getLognote().equals("I am very tired"));
		event.setLognote("Still tired");
		assertFalse(event.getLognote().equals("I am very tired"));
	}
	
	@Test
	public void testWeekDuration() {
		event.setStartTime(weekStart);
		event.setEndTime(weekEnd);
		assertEquals(10080, event.getDuration());
	}
}
