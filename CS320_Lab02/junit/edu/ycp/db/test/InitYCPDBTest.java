package edu.ycp.db.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.db.InitYCPDB;
import edu.ycp.model.YCPPersonnel;

public class InitYCPDBTest {
	private static List<YCPPersonnel> adminList;
	private static List<YCPPersonnel> studentList;

	@Before
	public void setUp() throws Exception {

		adminList = new ArrayList<YCPPersonnel>();
		studentList = new ArrayList<YCPPersonnel>();

		adminList = InitYCPDB.getAdmins();
		studentList = InitYCPDB.getStudents();
	}

	@Test
	public void testGetAdmins() {
		assertTrue(adminList.get(0).getFirstname().equals("John"));
	}

	@Test
	public void testGetStudents() {
	}

}
