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
		System.setOut(old);//for some reason need to set it back
		
		
		//test not a tracked room
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		System.setOut(ps);
		controller.handleEvent("900000000|-1|20190507120000");//room ID not tracked for Controls subteam
		System.out.flush();
		assertTrue(baos.toString().trim().equals("Not tracking this room for Jason Steinberg"));
		System.setOut(old);
	
		//check when status == false
		
		
		
		
		
		
		System.setOut(old);
		System.out.println(baos.toString());
		
		//check when scanning out
		
		
		//check when scanning in without having scanned out
		
		
		
	}

}
