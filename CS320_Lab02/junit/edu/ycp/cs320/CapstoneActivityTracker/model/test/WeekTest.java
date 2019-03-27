/**
 * 
 */
package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.*;

/**
 * @author TravisWetzel
 *
 */
public class WeekTest extends Week {

	private Week one;
	private Week two;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		one =  new Week();
		one.setDur0(0);
		one.setDur1(1);
		one.setDur2(2);
		one.setDur3(3);
		one.setDur4(4);
		one.setDur5(5);
		one.setDur6(6);
		
		two = new Week(0,1,2,3,4,5,6);
	}

	/**
	 * Test method for setDur0(long)
	 */
	@Test
	public void testSetDur0() {
		assertTrue(one.getDur0() == 0);
		assertFalse (one.getDur0() == 1);
		assertFalse (one.getDur0() == 2);
		assertFalse (one.getDur0() == 3);
		assertFalse (one.getDur0() == 4);
		assertFalse (one.getDur0() == 5);
		assertFalse (one.getDur0() == 6);
		

		assertTrue(two.getDur0() == 0);
		assertFalse (two.getDur0() == 1);
		assertFalse (two.getDur0() == 2);
		assertFalse (two.getDur0() == 3);
		assertFalse (two.getDur0() == 4);
		assertFalse (two.getDur0() == 5);
		assertFalse (two.getDur0() == 6);
	}

	/**
	 * Test method for setDur1(long)
	 */
	@Test
	public void testSetDur1() {
		assertFalse(one.getDur1() == 0);
		assertTrue (one.getDur1() == 1);
		assertFalse (one.getDur1() == 2);
		assertFalse (one.getDur1() == 3);
		assertFalse (one.getDur1() == 4);
		assertFalse (one.getDur1() == 5);
		assertFalse (one.getDur1() == 6);
		

		assertFalse(two.getDur1() == 0);
		assertTrue (two.getDur1() == 1);
		assertFalse (two.getDur1() == 2);
		assertFalse (two.getDur1() == 3);
		assertFalse (two.getDur1() == 4);
		assertFalse (two.getDur1() == 5);
		assertFalse (two.getDur1() == 6);
	}

	/**
	 * Test method for setDur2(long)
	 */
	@Test
	public void testSetDur2() {
		assertFalse(one.getDur2() == 0);
		assertFalse (one.getDur2() == 1);
		assertTrue (one.getDur2() == 2);
		assertFalse (one.getDur2() == 3);
		assertFalse (one.getDur2() == 4);
		assertFalse (one.getDur2() == 5);
		assertFalse (one.getDur2() == 6);
		

		assertFalse(two.getDur2() == 0);
		assertFalse (two.getDur2() == 1);
		assertTrue (two.getDur2() == 2);
		assertFalse (two.getDur2() == 3);
		assertFalse (two.getDur2() == 4);
		assertFalse (two.getDur2() == 5);
		assertFalse (two.getDur2() == 6);
	}

	/**
	 * Test method for setDur3(long)
	 */
	@Test
	public void testSetDur3() {
		assertFalse(one.getDur3() == 0);
		assertFalse (one.getDur3() == 1);
		assertFalse (one.getDur3() == 2);
		assertTrue (one.getDur3() == 3);
		assertFalse (one.getDur3() == 4);
		assertFalse (one.getDur3() == 5);
		assertFalse (one.getDur3() == 6);
		

		assertFalse(two.getDur3() == 0);
		assertFalse (two.getDur3() == 1);
		assertFalse (two.getDur3() == 2);
		assertTrue (two.getDur3() == 3);
		assertFalse (two.getDur3() == 4);
		assertFalse (two.getDur3() == 5);
		assertFalse (two.getDur3() == 6);
	}

	/**
	 * Test method for setDur4(long)
	 */
	@Test
	public void testSetDur4() {
		assertFalse(one.getDur4() == 0);
		assertFalse (one.getDur4() == 1);
		assertFalse (one.getDur4() == 2);
		assertFalse (one.getDur4() == 3);
		assertTrue (one.getDur4() == 4);
		assertFalse (one.getDur4() == 5);
		assertFalse (one.getDur4() == 6);
		

		assertFalse(two.getDur4() == 0);
		assertFalse (two.getDur4() == 1);
		assertFalse (two.getDur4() == 2);
		assertFalse (two.getDur4() == 3);
		assertTrue (two.getDur4() == 4);
		assertFalse (two.getDur4() == 5);
		assertFalse (two.getDur4() == 6);
	}

	/**
	 * Test method for setDur5(long)
	 */
	@Test
	public void testSetDur5() {
		assertFalse(one.getDur5() == 0);
		assertFalse (one.getDur5() == 1);
		assertFalse (one.getDur5() == 2);
		assertFalse (one.getDur5() == 3);
		assertFalse (one.getDur5() == 4);
		assertTrue (one.getDur5() == 5);
		assertFalse (one.getDur5() == 6);
		

		assertFalse(two.getDur5() == 0);
		assertFalse (two.getDur5() == 1);
		assertFalse (two.getDur5() == 2);
		assertFalse (two.getDur5() == 3);
		assertFalse (two.getDur5() == 4);
		assertTrue (two.getDur5() == 5);
		assertFalse (two.getDur5() == 6);
	}

	@Test
	public void testSetDur6() {
		assertFalse(one.getDur6() == 0);
		assertFalse (one.getDur6() == 1);
		assertFalse (one.getDur6() == 2);
		assertFalse (one.getDur6() == 3);
		assertFalse (one.getDur6() == 4);
		assertFalse (one.getDur6() == 5);
		assertTrue (one.getDur6() == 6);
		

		assertFalse(two.getDur6() == 0);
		assertFalse (two.getDur6() == 1);
		assertFalse (two.getDur6() == 2);
		assertFalse (two.getDur6() == 3);
		assertFalse (two.getDur6() == 4);
		assertFalse (two.getDur6() == 5);
		assertTrue (two.getDur6() == 6);
	}
}
