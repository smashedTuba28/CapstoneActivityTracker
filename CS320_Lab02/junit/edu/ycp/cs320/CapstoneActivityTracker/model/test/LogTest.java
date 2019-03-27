/**
 * 
 */
package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.*;

/**
 * @author TravisWetzel
 *
 */
public class LogTest extends Log {
	
	private Log firstLog;
	private Log secondLog;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		firstLog =  new Log();
		firstLog.setLog0("zero");
		firstLog.setLog1("first");
		firstLog.setLog2("second");
		firstLog.setLog3("third");
		firstLog.setLog4("forth");
		firstLog.setLog5("fifth");
		firstLog.setLog6("sixth");
		
		secondLog = new Log("0","1","2","3","4","5","6");
	}

	/**
	 * Test method for setLog0(java.lang.String)
	 */
	@Test
	public void testSetLog0() {
		assertTrue(firstLog.getLog0().equals("zero"));
		assertFalse (firstLog.getLog0().equals("first"));
		assertFalse (firstLog.getLog0().equals("second"));
		assertFalse (firstLog.getLog0().equals("third"));
		assertFalse (firstLog.getLog0().equals("forth"));
		assertFalse (firstLog.getLog0().equals("fifth"));
		assertFalse (firstLog.getLog0().equals("sixth"));
		

		assertTrue(secondLog.getLog0().equals("0"));
		assertFalse (secondLog.getLog0().equals("1"));
		assertFalse (secondLog.getLog0().equals("2"));
		assertFalse (secondLog.getLog0().equals("3"));
		assertFalse (secondLog.getLog0().equals("4"));
		assertFalse (secondLog.getLog0().equals("5"));
		assertFalse (secondLog.getLog0().equals("6"));
	}

	/**
	 * Test method for setLog1(java.lang.String)
	 */
	@Test
	public void testSetLog1() {
		assertFalse(firstLog.getLog1().equals("zero"));
		assertTrue (firstLog.getLog1().equals("first"));
		assertFalse (firstLog.getLog1().equals("second"));
		assertFalse (firstLog.getLog1().equals("third"));
		assertFalse (firstLog.getLog1().equals("forth"));
		assertFalse (firstLog.getLog1().equals("fifth"));
		assertFalse (firstLog.getLog1().equals("sixth"));
		

		assertFalse(secondLog.getLog1().equals("0"));
		assertTrue (secondLog.getLog1().equals("1"));
		assertFalse (secondLog.getLog1().equals("2"));
		assertFalse (secondLog.getLog1().equals("3"));
		assertFalse (secondLog.getLog1().equals("4"));
		assertFalse (secondLog.getLog1().equals("5"));
		assertFalse (secondLog.getLog1().equals("6"));
	}

	/**
	 * Test method for setLog2(java.lang.String)
	 */
	@Test
	public void testSetLog2() {
		assertFalse(firstLog.getLog2().equals("zero"));
		assertFalse (firstLog.getLog2().equals("first"));
		assertTrue (firstLog.getLog2().equals("second"));
		assertFalse (firstLog.getLog2().equals("third"));
		assertFalse (firstLog.getLog2().equals("forth"));
		assertFalse (firstLog.getLog2().equals("fifth"));
		assertFalse (firstLog.getLog2().equals("sixth"));
		
		
		assertFalse(secondLog.getLog2().equals("0"));
		assertFalse (secondLog.getLog2().equals("1"));
		assertTrue (secondLog.getLog2().equals("2"));
		assertFalse (secondLog.getLog2().equals("3"));
		assertFalse (secondLog.getLog2().equals("4"));
		assertFalse (secondLog.getLog2().equals("5"));
		assertFalse (secondLog.getLog2().equals("6"));
	}

	/**
	 * Test method for setLog3(java.lang.String)
	 */
	@Test
	public void testSetLog3() {
		assertFalse(firstLog.getLog3().equals("zero"));
		assertFalse (firstLog.getLog3().equals("first"));
		assertFalse (firstLog.getLog3().equals("second"));
		assertTrue (firstLog.getLog3().equals("third"));
		assertFalse (firstLog.getLog3().equals("forth"));
		assertFalse (firstLog.getLog3().equals("fifth"));
		assertFalse (firstLog.getLog3().equals("sixth"));
		
		
		assertFalse(secondLog.getLog3().equals("0"));
		assertFalse (secondLog.getLog3().equals("1"));
		assertFalse (secondLog.getLog3().equals("2"));
		assertTrue (secondLog.getLog3().equals("3"));
		assertFalse (secondLog.getLog3().equals("4"));
		assertFalse (secondLog.getLog3().equals("5"));
		assertFalse (secondLog.getLog3().equals("6"));
	}

	/**
	 * Test method for setLog4(java.lang.String)
	 */
	@Test
	public void testSetLog4() {
		assertFalse(firstLog.getLog4().equals("zero"));
		assertFalse (firstLog.getLog4().equals("first"));
		assertFalse (firstLog.getLog4().equals("second"));
		assertFalse (firstLog.getLog4().equals("third"));
		assertTrue (firstLog.getLog4().equals("forth"));
		assertFalse (firstLog.getLog4().equals("fifth"));
		assertFalse (firstLog.getLog4().equals("sixth"));
		
		
		assertFalse(secondLog.getLog4().equals("0"));
		assertFalse (secondLog.getLog4().equals("1"));
		assertFalse (secondLog.getLog4().equals("2"));
		assertFalse (secondLog.getLog4().equals("3"));
		assertTrue (secondLog.getLog4().equals("4"));
		assertFalse (secondLog.getLog4().equals("5"));
		assertFalse (secondLog.getLog4().equals("6"));
	}

	/**
	 * Test method for setLog5(java.lang.String)
	 */
	@Test
	public void testSetLog5() {
		assertFalse(firstLog.getLog5().equals("zero"));
		assertFalse (firstLog.getLog5().equals("first"));
		assertFalse (firstLog.getLog5().equals("second"));
		assertFalse (firstLog.getLog5().equals("third"));
		assertFalse (firstLog.getLog5().equals("forth"));
		assertTrue (firstLog.getLog5().equals("fifth"));
		assertFalse (firstLog.getLog5().equals("sixth"));
		
		
		assertFalse(secondLog.getLog5().equals("0"));
		assertFalse (secondLog.getLog5().equals("1"));
		assertFalse (secondLog.getLog5().equals("2"));
		assertFalse (secondLog.getLog5().equals("3"));
		assertFalse (secondLog.getLog5().equals("4"));
		assertTrue (secondLog.getLog5().equals("5"));
		assertFalse (secondLog.getLog5().equals("6"));
	}

	@Test
	public void testSetLog6() {
		assertFalse(firstLog.getLog6().equals("zero"));
		assertFalse (firstLog.getLog6().equals("first"));
		assertFalse (firstLog.getLog6().equals("second"));
		assertFalse (firstLog.getLog6().equals("third"));
		assertFalse (firstLog.getLog6().equals("forth"));
		assertFalse (firstLog.getLog6().equals("fifth"));
		assertTrue (firstLog.getLog6().equals("sixth"));
		
		
		assertFalse(secondLog.getLog6().equals("0"));
		assertFalse (secondLog.getLog6().equals("1"));
		assertFalse (secondLog.getLog6().equals("2"));
		assertFalse (secondLog.getLog6().equals("3"));
		assertFalse (secondLog.getLog6().equals("4"));
		assertFalse (secondLog.getLog6().equals("5"));
		assertTrue (secondLog.getLog6().equals("6"));
	}
}
