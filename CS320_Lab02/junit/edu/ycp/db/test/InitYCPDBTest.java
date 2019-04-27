package edu.ycp.db.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstoneActivityTracker.model.Room;
import edu.ycp.db.InitYCPDB;
import edu.ycp.model.YCPPersonnel;

public class InitYCPDBTest {
	//creating admin and student lists
	private static List<YCPPersonnel> adminList;
	private static List<YCPPersonnel> studentList;
	private static List<Room> roomList;

	@Before
	public void setUp() throws Exception {

		//setting lists to new lists
		adminList = new ArrayList<YCPPersonnel>();
		studentList = new ArrayList<YCPPersonnel>();
		roomList = new ArrayList<Room>();

		//adding all admin to the adminList
		adminList.addAll(InitYCPDB.getAdmins());
		//adding all students to the studentList in the same order
		studentList.addAll(InitYCPDB.getStudents());
		//adding all students to the studentList in the same order
		roomList.addAll(InitYCPDB.getRooms());
	}

	@Test
	public void testGetAdmins() {
		//checking john doe (first and only person in the list) for correct information
		assertTrue(adminList.get(5).getFirstname().equals("John"));
		assertTrue(adminList.get(5).getLastname().equals("Doe"));
		assertTrue(adminList.get(5).getEmail().equals("jdoe@ycp.edu"));
		assertTrue(adminList.get(5).getSchoolID().equals("910000005"));
		assertTrue(adminList.get(5).getFaculty());
	}

	@Test
	public void testGetStudents() {
		//checking jason (first student in the list) for correct information
		assertTrue(studentList.get(0).getFirstname().equals("Jason"));
		assertTrue(studentList.get(0).getLastname().equals("Steinberg"));
		assertTrue(studentList.get(0).getEmail().equals("jsteinberg@ycp.edu"));
		assertTrue(studentList.get(0).getSchoolID().equals("900000001"));
		assertFalse(studentList.get(0).getFaculty());
		//checking Travis (second student in the list) for correct information
		assertTrue(studentList.get(1).getFirstname().equals("Travis"));
		assertTrue(studentList.get(1).getLastname().equals("Wetzel"));
		assertTrue(studentList.get(1).getEmail().equals("twetzel1@ycp.edu"));
		assertTrue(studentList.get(1).getSchoolID().equals("900000002"));
		assertFalse(studentList.get(1).getFaculty());
		
	}
	@Test
	public void testGetRooms() {
		//testing first room Shop, 100
		assertTrue(roomList.get(0).getRoomName().equals("Shop"));
		assertTrue(roomList.get(0).getRoomNumber() == 100);
		
		//testing all room's name and number in a for loop
		for(int i = 101; i < 130; i++) {
			assertTrue(roomList.get(i-100).getRoomName().equals("KEC"));
			assertTrue(roomList.get(i-100).getRoomNumber() == i);
		}
	}

}
