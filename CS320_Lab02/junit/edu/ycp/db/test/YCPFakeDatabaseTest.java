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
		assertTrue(ycpfdb.findStudentByEmail("jsteinberg@ycp.edu").equals(ycpfdb.getAllStudents().get(0)));
		assertTrue(ycpfdb.findStudentByEmail("twetzel1@ycp.edu").equals(ycpfdb.getAllStudents().get(1)));
		
	}

	@Test
	public void testFindAdminByEmail() {
		assertTrue(ycpfdb.findAdminByEmail("jdoe@ycp.edu").equals(ycpfdb.getAllAdmin().get(5)));
	}

	@Test
	public void testFindStudentBySchoolID() {
		assertTrue(ycpfdb.findStudentBySchoolID("900000001").equals(ycpfdb.getAllStudents().get(0)));
		assertTrue(ycpfdb.findStudentBySchoolID("900000002").equals(ycpfdb.getAllStudents().get(1)));
	}
 
	@Test
	public void testFindAdminBySchoolID() {
		assertTrue(ycpfdb.findAdminBySchoolID("910000000").equals(ycpfdb.getAllAdmin().get(0)));
	}

	@Test
	public void testVerifyStudent() {
		assertTrue(ycpfdb.verifyStudent("jsteinberg@ycp.edu", "900000001")!=null);
		assertTrue(ycpfdb.verifyStudent("twetzel1@ycp.edu", "900000002")!=null);	
		assertFalse(ycpfdb.verifyStudent("jdoe@ycp.edu", "900000000")!=null);
		assertFalse(ycpfdb.verifyStudent("twetzel1@ycp.edu", "111111111")!=null);
		assertFalse(ycpfdb.verifyStudent("Martin4654564645644984@rocketmail.com", "9020613165")!=null);
	}

	@Test
	public void testVerifyAdmin() {
		assertTrue(ycpfdb.verifyAdmin("jdoe@ycp.edu", "910000005")!=null);
		assertFalse(ycpfdb.verifyAdmin("twetzel1@ycp.edu", "900000002")!=null);
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

		assertTrue(ycpfdb.verifyPersonnel("jsteinberg@ycp.edu", "900000001"));
		assertTrue(ycpfdb.verifyPersonnel("twetzel1@ycp.edu", "900000002"));
		assertTrue(ycpfdb.verifyPersonnel("jdoe@ycp.edu", "910000005"));
		
		assertFalse(ycpfdb.verifyPersonnel("Martin4654564645644984@rocketmail.com", "9020613165"));
	}

}
