package edu.ycp.cs320.CpastoneActivityTracker.controller.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.ChartModel;
import edu.ycp.cs320.CapstoneActivityTracker.model.RoomEvent;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;
import edu.ycp.cs320.CapstoneActivityTracker.controller.ChartController;

public class ChartControllerTest {
	private ChartModel model;
	private ChartController controller;
	
	private StudentAccount tester;
	private List<RoomEvent> events;
	
	private Date start, end;
	
	@Before
	public void setUp() throws Exception {
		controller = new ChartController();
		model = new ChartModel();
		controller.setModel(model);
		
		start = new Date(2019, 2, 31, 0, 0, 0);
		end = new Date(2019, 3, 6, 24, 0, 0);
		
		tester = new StudentAccount("Test", "Tester", "ttester@ycp.edu", "password", "900000005", false);
		
		//ROOM EVENT 1
		tester.createRoomEvent(new Date(2019,2,17,10,00,0));//create and close a room event
		tester.closeRoomEvent(new Date(2019,2,17,14,30,0));//4 hour 30 min duration on March 17th
		
		//ROOM EVENT 2
		tester.createRoomEvent(new Date(2019, 2, 31, 13, 30, 0));//create and close room event
		tester.closeRoomEvent(new Date(2019, 2, 31, 22, 33, 0));//9 hour 3 min duration in only one day March 31st
		
		//ROOM EVENT 3
		tester.createRoomEvent(new Date(2019, 3, 1, 23, 0, 0));//create and close room event
		tester.closeRoomEvent(new Date(2019, 3, 2, 3, 0, 0));//4 hour duration on two days April 1st to 2nd
															//1 hour on the 1st :::: 3 hours on the 2nd	
		//ROOM EVENT 4
		tester.createRoomEvent(new Date(2019, 3, 3, 12, 00, 0));//create and close room event
		tester.closeRoomEvent(new Date(2019, 3, 5, 12, 00, 0));//48 hour duration April 3rd - April 5th
															//12 hours on April 3rd :::: 24 hours on April 4th
															//12 hours on April 5th ::::
		//ROOM EVENT 5
		tester.createRoomEvent(new Date(2019, 3, 6, 22, 30, 0));//create and close room event
		tester.closeRoomEvent(new Date(2019, 3, 7, 4, 30, 0));//6 hour duration across two days April 6th - April 7th
															//1 hour and 30 min on April 6th ::: 4 hours and 30 min on April 7th
	}


	@Test
	public void testPopulateStudentWeek() {
		assertTrue(model.getData() == null);//data should be empty
		String email = "jsteinberg@ycp.edu";
		Date start = new Date(119, 2, 31, 0,0,0);
		Date end = new Date(119,3,6,24,0,0);
		
		
		controller.populateStudentWeek(email, start, end);
		//expecting model to be populated with data for Jason Steinberg
		assertTrue(model.getTitle().equals("Individual Hours"));
		assertTrue(model.getStudent().equals("Jason Steinberg"));
		assertTrue(model.getData().equals("[['Date', 'Hours'],"
				+ "['3-31', 4.5],"
				+ "['4-1', 5.0],"
				+ "['4-2', 1.7833333333333334],"
				+ "['4-3', 3.0],"
				+ "['4-4', 6.816666666666666],"
				+ "['4-5', 7.5],"
				+ "['4-6', 0.0]]"));
		
		//expecting model to be populated with data fro Travis Wetezel
		email = "twetzel1@ycp.edu";
		controller.populateStudentWeek(email, start, end);
		assertTrue(model.getTitle().equals("Individual Hours"));
		assertTrue(model.getStudent().equals("Travis Wetzel"));
		assertTrue(model.getData().equals("[['Date', 'Hours'],"
				+ "['3-31', 2.5],"
				+ "['4-1', 5.0],"
				+ "['4-2', 1.8333333333333333],"
				+ "['4-3', 6.0],"
				+ "['4-4', 14.316666666666666],"
				+ "['4-5', 4.0],"
				+ "['4-6', 0.0]]"));	
	}

	@Test
	public void testGetWeekfromRoomEvents() {
		long[][] result = new long[2][7];//know that method will return [2][7] array of long references
		
		result = controller.getWeekfromRoomEvents(start, end, tester);
		
		/*
		 * BREAK DOWN OF EXPECTED VALUES
		 * March 17th has a total of 4 hours and 30 minutes	that will be excluded for being before the start date
		 * March 31st has a total of 9 hours and 3 minutes 		=> 0543 minutes 
		 * April 1st has a total of 1 hour						=> 0060 minutes	
		 * April 2nd has a total of 3 hours						=> 0180 minutes
		 * April 3rd has a total of 12 hours					=> 0720 minutes
		 * April 4th has a total of 24 hours					=> 1440 minutes
		 * April 5th has a total of 12 hours					=> 0720 minutes
		 * April 6th has a total of 1 hour and 30 minutes		=> 0090 minutes
		 * April 7th has a total of 4 hours and 30 minutes that will be excluded for being past the end date
		 */
		
		//test all duration values result[0][x]
		assertEquals((long)  543, result[0][0]);
		assertEquals((long)   60, result[0][1]);
		assertEquals((long)  180, result[0][2]);
		assertEquals((long)  720, result[0][3]);
		assertEquals((long) 1440, result[0][4]);
		assertEquals((long)  720, result[0][5]);
		assertEquals((long)   90, result[0][6]);
		
		//test that all date values are as expected
		Calendar c = Calendar.getInstance();//create calendar instance
		
		c.setTimeInMillis(result[1][0]);
		assertEquals(2, c.get(Calendar.MONTH));//March expected
		assertEquals(31, c.get(Calendar.DAY_OF_MONTH));//31st expected
		assertEquals(0, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.MINUTE));
		
		c.setTimeInMillis(result[1][1]);
		assertEquals(3, c.get(Calendar.MONTH));//April expected
		assertEquals(1, c.get(Calendar.DAY_OF_MONTH));//1st expected
		assertEquals(0, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.MINUTE));
		
		c.setTimeInMillis(result[1][2]);
		assertEquals(3, c.get(Calendar.MONTH));//April expected
		assertEquals(2, c.get(Calendar.DAY_OF_MONTH));//2nd expected
		assertEquals(0, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.MINUTE));
		
		c.setTimeInMillis(result[1][3]);
		assertEquals(3, c.get(Calendar.MONTH));//April expected
		assertEquals(3, c.get(Calendar.DAY_OF_MONTH));//3rd expected
		assertEquals(0, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.MINUTE));
		
		c.setTimeInMillis(result[1][4]);
		assertEquals(3, c.get(Calendar.MONTH));//April expected
		assertEquals(4, c.get(Calendar.DAY_OF_MONTH));//4th expected
		assertEquals(0, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.MINUTE));
		
		c.setTimeInMillis(result[1][5]);
		assertEquals(3, c.get(Calendar.MONTH));//April expected
		assertEquals(5, c.get(Calendar.DAY_OF_MONTH));//5th expected
		assertEquals(0, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.MINUTE));
		
		c.setTimeInMillis(result[1][6]);
		assertEquals(3, c.get(Calendar.MONTH));//April expected
		assertEquals(6, c.get(Calendar.DAY_OF_MONTH));//6th expected
		assertEquals(0, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.MINUTE));
	}
}
