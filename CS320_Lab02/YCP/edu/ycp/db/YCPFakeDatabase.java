package edu.ycp.db;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.model.*;

public class YCPFakeDatabase {
	private List<YCPPersonnel> adminList;
	private List<YCPPersonnel> studentList;
	
	public YCPFakeDatabase() {
		adminList = new ArrayList<YCPPersonnel>();
		studentList = new ArrayList<YCPPersonnel>();
		
		readInitialData();
		
		System.out.println(adminList.size() + " admins");
		System.out.println(studentList.size() + " students");
	}
	
	public void readInitialData() {
			adminList.addAll(InitYCPDB.getAdmins());
			studentList.addAll(InitYCPDB.getStudents());
	}
}
