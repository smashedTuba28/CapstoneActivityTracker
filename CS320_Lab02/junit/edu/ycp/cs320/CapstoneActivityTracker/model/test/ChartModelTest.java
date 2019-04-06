package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.ChartModel;

public class ChartModelTest {
	private ChartModel model;
	
	
	
	
	@Before
	public void setUp() throws Exception {
		model = new ChartModel();	
	}

	@Test
	public void testSetData() {
		assertTrue(model.getData() == null);//check that it start null
		model.setData("I AM DATA");//set a value
		assertTrue(model.getData().equals("I AM DATA"));//make sure value is correct
		model.setData("I am also Data");//set new value
		assertFalse(model.getData().equals("I AM DATA"));//make sure value changed
	}

	@Test
	public void testSetStudent() {
		assertTrue(model.getStudent() == null);//check that it start null
		model.setStudent("I AM STUDENT");//set a value
		assertTrue(model.getStudent().equals("I AM STUDENT"));//make sure value is correct
		model.setStudent("I am also student");//set new value
		assertFalse(model.getStudent().equals("I AM STUDENT"));//make sure value changed
	}
	
	@Test
	public void testSetTitle() {
		assertTrue(model.getTitle() == null);//check that it start null
		model.setTitle("I AM TITLE");//set a value
		assertTrue(model.getTitle().equals("I AM TITLE"));//make sure value is correct
		model.setTitle("I am also Title");//set new value
		assertFalse(model.getTitle().equals("I AM TITLE"));//make sure value changed
	}
	

}
