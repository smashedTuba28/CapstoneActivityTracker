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
	
	private Date start, end;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		controller = new ChartController();
		model = new ChartModel();
		controller.setModel(model);
		
		start = new Date(2019, 2, 31, 0, 0, 0);
		end = new Date(2019, 3, 6, 24, 0, 0);
		
		tester = new StudentAccount("Test", "Tester", "ttester@ycp.edu", "password", "900000005");
		
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
		Integer account_id = 1;
		Date start = new Date(119, 2, 31, 0,0,0);
		Date end = new Date(119,3,6,24,0,0);
		
		
		controller.populateStudentWeek(account_id, start, end);
		//expecting model to be populated with data for Jason Steinberg
		assertTrue(model.getTitle().equals("Individual Work Hours"));
		assertTrue(model.getStudent().equals("Jason Steinberg"));
		assertTrue(model.getData().equals("[['Date', 'Hours'],"
				+ "['3-31', 4.5],"
				+ "['4-1', 5.0],"
				+ "['4-2', 1.7833333333333334],"
				+ "['4-3', 3.0],"
				+ "['4-4', 6.816666666666666],"
				+ "['4-5', 7.5],"
				+ "['4-6', 10.0]]"));
		
		//expecting model to be populated with data fro Travis Wetezel
		account_id = 2;
		controller.populateStudentWeek(account_id, start, end);
		assertTrue(model.getTitle().equals("Individual Work Hours"));
		assertTrue(model.getStudent().equals("Travis Wetzel"));
		System.out.println(model.getData());
		assertTrue(model.getData().equals("[['Date', 'Hours'],"
				+ "['3-31', 2.5],"
				+ "['4-1', 5.0],"
				+ "['4-2', 1.8333333333333333],"
				+ "['4-3', 6.0],"
				+ "['4-4', 14.316666666666666],"
				+ "['4-5', 4.0],"
				+ "['4-6', 10.0]]"));	
	}

	@Test
	public void testGetWeekfromRoomEvents() {
		long[] result = new long[7];//know that method will return [2][7] array of long references
		
		result = controller.getWeekFromRoomEvents(start, end, tester.getRoomEventList());
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
		assertEquals((long)  543, result[0]);
		assertEquals((long)   60, result[1]);
		assertEquals((long)  180, result[2]);
		assertEquals((long)  720, result[3]);
		assertEquals((long) 1440, result[4]);
		assertEquals((long)  720, result[5]);
		assertEquals((long)   90, result[6]);
	}
	
	@Test
	public void testPopulateTopTeamWeek() {
		assertTrue(model.getData() == null);
		String email = "jsteinberg@ycp.edu";
		Date start = new Date(119, 2, 31, 0,0,0);//March31st 2019
		Date end = new Date(119,3,6,24,0,0);//April6th 2019
		
		controller.populateTopTeamWeek(email, start, end);   
		
		
		assertTrue(model.getTitle().equals("Drone Team Hours"));
		assertTrue(model.getData().equals("[['Date','Jason Steinberg','Travis Wetzel','William Taylor'],"
				+ "['3-31', 4.5, 2.5, 1.5],"
				+ "['4-1', 5.0, 5.0, 1.0],"
				+ "['4-2', 1.7833333333333334, 1.8333333333333333, 0.0],"
				+ "['4-3', 3.0, 6.0, 0.5],"
				+ "['4-4', 6.816666666666666, 14.316666666666666, 6.816666666666666],"
				+ "['4-5', 7.5, 4.0, 3.933333333333333],"
				+ "['4-6', 10.0, 10.0, 0.0]]"));
		
		email = "twetzel1@ycp.edu"; //change email do different person
		//team is the same so asserts shouldn't change
		assertTrue(model.getTitle().equals("Drone Team Hours"));
		assertTrue(model.getData().equals("[['Date','Jason Steinberg','Travis Wetzel','William Taylor'],"
				+ "['3-31', 4.5, 2.5, 1.5],"
				+ "['4-1', 5.0, 5.0, 1.0],"
				+ "['4-2', 1.7833333333333334, 1.8333333333333333, 0.0],"
				+ "['4-3', 3.0, 6.0, 0.5],"
				+ "['4-4', 6.816666666666666, 14.316666666666666, 6.816666666666666],"
				+ "['4-5', 7.5, 4.0, 3.933333333333333],"
				+ "['4-6', 10.0, 10.0, 0.0]]"));
	}
}
