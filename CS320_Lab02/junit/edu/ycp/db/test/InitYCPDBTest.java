package edu.ycp.db.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.db.InitYCPDB;
import edu.ycp.model.YCPPersonnel;

public class InitYCPDBTest {
	//creating admin and student lists
	private static List<YCPPersonnel> adminList;
	private static List<YCPPersonnel> studentList;

	@Before
	public void setUp() throws Exception {

		//setting lists to new lists
		adminList = new ArrayList<YCPPersonnel>();
		studentList = new ArrayList<YCPPersonnel>();

		//adding all admin to the adminList
		adminList.addAll(InitYCPDB.getAdmins());
		//adding all students to the studentList in the same order
		studentList.addAll(InitYCPDB.getStudents());
	}

	@Test
	public void testGetAdmins() {
		//checking john doe (first and only person in the list) for correct information
		assertTrue(adminList.get(0).getFirstname().equals("John"));
		assertTrue(adminList.get(0).getLastname().equals("Doe"));
		assertTrue(adminList.get(0).getEmail().equals("jdoe@ycp.edu"));
		assertTrue(adminList.get(0).getSchoolID().equals("900000000"));
		assertTrue(adminList.get(0).getFaculty());
	}

	@Test
	public void testGetStudents() {
		//checking jason (first student in the list) for correct information
		assertTrue(studentList.get(0).getFirstname().equals("Jason"));
		assertTrue(studentList.get(0).getLastname().equals("Steinberg"));
		assertTrue(studentList.get(0).getEmail().equals("jsteinerg@ycp.edu"));
		assertTrue(studentList.get(0).getSchoolID().equals("900000001"));
		assertFalse(studentList.get(0).getFaculty());
		//checking Travis (second student in the list) for correct information
		assertTrue(studentList.get(1).getFirstname().equals("Travis"));
		assertTrue(studentList.get(1).getLastname().equals("Wetzel"));
		assertTrue(studentList.get(1).getEmail().equals("twetzel1@ycp.edu"));
		assertTrue(studentList.get(1).getSchoolID().equals("900000002"));
		assertFalse(studentList.get(1).getFaculty());
		//checking william (third student in the list) for correct information
		assertTrue(studentList.get(2).getFirstname().equals("William"));
		assertTrue(studentList.get(2).getLastname().equals("Taylor"));
		assertTrue(studentList.get(2).getEmail().equals("wtaylor1@ycp.edu"));
		assertTrue(studentList.get(2).getSchoolID().equals("900000003"));
		assertFalse(studentList.get(2).getFaculty());
	}

}
