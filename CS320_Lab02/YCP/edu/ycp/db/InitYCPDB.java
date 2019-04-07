package edu.ycp.db;

import edu.ycp.model.*;
import java.util.List;

public class InitYCPDB {

	private static List<YCPPersonnel> adminList;
	private static List<YCPPersonnel> studentList;
	
	public static List<YCPPersonnel> getAdmins(){
		adminList.add(new YCPPersonnel("John", "Doe", "jdoe@ycp.edu", "900000000", true));
		return adminList;
	}
	public static List<YCPPersonnel> getStudents(){
		studentList.add(new YCPPersonnel("John", "Doe", "jdoe@ycp.edu", "900000000", false));
		studentList.add(new YCPPersonnel("Jason", "Steinberg", "jsteinerg@ycp.edu", "900000001", false));
		studentList.add(new YCPPersonnel("Travis", "Wetzel", "twetzel1@ycp.edu", "900000002", false));
		studentList.add(new YCPPersonnel("William", "Taylor", "wtaylor1@ycp.edu", "900000003", false));
		return studentList;
	}
}
