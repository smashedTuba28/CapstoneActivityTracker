package edu.ycp.db.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


import edu.ycp.cs320.CapstoneActivityTracker.model.*;
import edu.ycp.db.YCPFakeDatabase;
import edu.ycp.model.YCPPersonnel;

public class YCPFakeDatabaseTest {

	YCPFakeDatabase ycpfdb;
	
	@Before
	public void setUp() throws Exception {
		ycpfdb = new YCPFakeDatabase();

		
	}

	
	@Test
	public void testFindStudentByEmail() {
		assertTrue(ycpfdb.findStudentByEmail("jsteinerg@ycp.edu").equals(ycpfdb.getAllStudents().get(0)));
		assertTrue(ycpfdb.findStudentByEmail("twetzel1@ycp.edu").equals(ycpfdb.getAllStudents().get(1)));
		assertTrue(ycpfdb.findStudentByEmail("wtaylor1@ycp.edu").equals(ycpfdb.getAllStudents().get(2)));
		
	}

	@Test
	public void testFindAdminByEmail() {
		assertTrue(ycpfdb.findAdminByEmail("jdoe@ycp.edu").equals(ycpfdb.getAllAdmin().get(0)));
	}

	@Test
	public void testFindStudentBySchoolID() {
		assertTrue(ycpfdb.findStudentBySchoolID("900000001").equals(ycpfdb.getAllStudents().get(0)));
		assertTrue(ycpfdb.findStudentBySchoolID("900000002").equals(ycpfdb.getAllStudents().get(1)));
		assertTrue(ycpfdb.findStudentBySchoolID("900000003").equals(ycpfdb.getAllStudents().get(2)));
	}

	@Test
	public void testFindAdminBySchoolID() {
		assertTrue(ycpfdb.findAdminBySchoolID("900000000").equals(ycpfdb.getAllAdmin().get(0)));
	}

	@Test
	public void testVerifyStudent() {
		assertTrue(ycpfdb.verifyStudent("jsteinerg@ycp.edu", "900000001"));
		assertTrue(ycpfdb.verifyStudent("twetzel1@ycp.edu", "900000002"));
		assertTrue(ycpfdb.verifyStudent("wtaylor1@ycp.edu", "900000003"));
		
		assertFalse(ycpfdb.verifyStudent("jdoe@ycp.edu", "900000000"));
		assertFalse(ycpfdb.verifyStudent("twetzel1@ycp.edu", "111111111"));
		assertFalse(ycpfdb.verifyStudent("Martin4654564645644984@rocketmail.com", "9020613165"));
	}

	@Test
	public void testVerifyAdmin() {
		assertTrue(ycpfdb.verifyAdmin("jdoe@ycp.edu", "900000000"));
		assertFalse(ycpfdb.verifyAdmin("twetzel1@ycp.edu", "900000002"));
	}

	@Test
	public void testFindRoomByNumber() {
		assertTrue(ycpfdb.findRoomByNumber(100).getRoomName().equals("Shop"));
		for(int i = 101; i < 130; i++) {
			assertTrue(ycpfdb.findRoomByNumber(i).getRoomName().equals("KEC"));
		}
	}
	
	@Test
	public void testVerifyPersonnel() {

		assertTrue(ycpfdb.verifyPersonnel("jsteinerg@ycp.edu", "900000001"));
		assertTrue(ycpfdb.verifyPersonnel("twetzel1@ycp.edu", "900000002"));
		assertTrue(ycpfdb.verifyPersonnel("wtaylor1@ycp.edu", "900000003"));
		assertTrue(ycpfdb.verifyPersonnel("jdoe@ycp.edu", "900000000"));
		
		assertFalse(ycpfdb.verifyPersonnel("Martin4654564645644984@rocketmail.com", "9020613165"));
	}

}
