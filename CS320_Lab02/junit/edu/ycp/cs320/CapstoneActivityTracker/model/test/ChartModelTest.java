package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.ChartModel;
import edu.ycp.cs320.CapstoneActivityTracker.model.RoomEvent;

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
	
	@Test
	public void testSetCurrentStudent() {
		assertTrue(model.getCurrentStudent() == null);//check that it start null
		model.setCurrentStudent("I AM STUDENT");//set a value
		assertTrue(model.getCurrentStudent().equals("I AM STUDENT"));//make sure value is correct
		model.setCurrentStudent("I am also student");//set new value
		assertFalse(model.getCurrentStudent().equals("I AM STUDENT"));//make sure value changed
	}

	@Test
	public void testSetTopTeamName() {
		assertTrue(model.getTopTeamName() == null);//check that it start null
		model.setTopTeamName("I AM STUDENT");//set a value
		assertTrue(model.getTopTeamName().equals("I AM STUDENT"));//make sure value is correct
		model.setTopTeamName("I am also student");//set new value
		assertFalse(model.getTopTeamName().equals("I AM STUDENT"));//make sure value changed
	}
	
	@Test
	public void testSetSubTeamNames() {
		assertTrue(model.getSubTeamNames() == null);//check that it start null
		model.setSubTeamNames("I AM STUDENT");//set a value
		assertTrue(model.getSubTeamNames().equals("I AM STUDENT"));//make sure value is correct
		model.setSubTeamNames("I am also student");//set new value
		assertFalse(model.getSubTeamNames().equals("I AM STUDENT"));//make sure value changed
	}
	
	@Test
	public void testSetStudentNames() {
		assertTrue(model.getStudentNames() == null);//check that it start null
		model.setStudentNames("I AM STUDENT");//set a value
		assertTrue(model.getStudentNames().equals("I AM STUDENT"));//make sure value is correct
		model.setStudentNames("I am also student");//set new value
		assertFalse(model.getStudentNames().equals("I AM STUDENT"));//make sure value changed
	}
	
	@Test
	public void testSetMySubTeamName() {
		assertTrue(model.getMySubTeamName() == null);//check that it start null
		model.setMySubTeamName("I AM STUDENT");//set a value
		assertTrue(model.getMySubTeamName().equals("I AM STUDENT"));//make sure value is correct
		model.setMySubTeamName("I am also student");//set new value
		assertFalse(model.getMySubTeamName().equals("I AM STUDENT"));//make sure value changed
	}
	
	@Test
	public void testSetOffest() {
		int offset = 10;
		model.setOffset(offset);
		assertEquals(10, model.getOffset());
		model.setOffset(14);
		assertNotEquals(offset, model.getOffset());
	}
	
	@Test
	public void testSetEvents() {
		ArrayList<RoomEvent> events = new ArrayList<RoomEvent>();
		events.add(new RoomEvent());
		events.add(new RoomEvent());
		model.setEvents(events);
		assertTrue(model.getEvents() != null);
		assertEquals(2, model.getEvents().size());
	}
}
