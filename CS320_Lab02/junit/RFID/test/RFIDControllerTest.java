package RFID.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstonActivtyTracker.db.DatabaseProvider;
import edu.ycp.cs320.CapstonActivtyTracker.db.DerbyDatabase;
import edu.ycp.cs320.CapstonActivtyTracker.db.IDatabase;
import edu.ycp.cs320.CapstonActivtyTracker.db.hashSHA256;
import edu.ycp.cs320.CapstoneActivityTracker.RFID.RFIDController;

public class RFIDControllerTest {
	private IDatabase db;
	private RFIDController controller = new RFIDController();
	private ByteArrayOutputStream baos;
	private PrintStream ps, old;
	
	
	@Before
	public void setUp() throws Exception {
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}

	
	
	//I found the process to redirect Console output on stack overflow
	//Ernest Friedman-Hill was the profile that provided a potential solution 
	//code is used to change the system's output and save the output as a byte array
	
	@Test
	public void testHandleEvent() {
		//example of valid input
		//schoolID|room#|Timestamp
		//900000000|128|20190422090000

		//test not a student account 
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		old = System.out;
		System.setOut(ps);
		controller.handleEvent("910000000|128|20190507120000");//schoolID for admin
		System.out.flush();
		assertTrue(baos.toString().trim().equals("Not a tracked Student"));
		
		//test not a tracked room
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		System.setOut(ps);
		controller.handleEvent("900000000|-1|20190507120000");//room ID not tracked for Controls subteam
		System.out.flush();
		assertTrue(baos.toString().trim().equals("Not tracking this room for Jason Steinberg"));
	
		//check when status == false
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		System.setOut(ps);
		controller.handleEvent("900000000|128|20190507120000");//all good data 
		System.out.flush();
		assertTrue(baos.toString().trim().equals("New RoomEvent being created...\r\n" + 
				"Room Event Created for Jason Steinberg in Room 128"));
		
		
		//check when scanning in without having scanned out
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		System.setOut(ps);
		controller.handleEvent("900000000|120|20190507120000");//all good data 
		assertTrue(baos.toString().trim().equals("AUTO CLOSED PREVIOULY OPENED ROOM EVENT: FLAGGED : SYSTEM CLOSED\r\n" + 
				"New RoomEvent being created...\r\n" + 
				"Room Event Created for Jason Steinberg in Room 120"));
		
		//check when scanning out
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		System.setOut(ps);
		controller.handleEvent("900000000|120|20190507120000");//all good data 
		System.out.flush();
		assertTrue(baos.toString().trim().equals("Student<Jason Steinberg> scanned out of room 120"));
		
		//reset console output
		System.setOut(old);
	}
}
