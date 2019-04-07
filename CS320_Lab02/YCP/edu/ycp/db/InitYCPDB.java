package edu.ycp.db;

import edu.ycp.model.*;
import java.util.List;

public class InitYCPDB {

	private List<YCPPersonnel> adminList;
	private List<YCPPersonnel> studentList;
	
	public InitYCPDB() {
		adminList = getAdmins();
		studentList = getStudents();
	}
	private List<YCPPersonnel> getAdmins(){
		adminList.add(new YCPPersonnel("John", "Doe", "jdoe@ycp.edu", "900000000", true));
		return adminList;
	}
	private List<YCPPersonnel> getStudents(){
		studentList.add(new YCPPersonnel("John", "Doe", "jdoe@ycp.edu", "900000000", false));
		studentList.add(new YCPPersonnel("Jason", "Steinberg", "jsteinerg@ycp.edu", "900000001", false));
		studentList.add(new YCPPersonnel("Travis", "Wetzel", "twetzel1@ycp.edu", "900000002", false));
		studentList.add(new YCPPersonnel("William", "Taylor", "wtaylor1@ycp.edu", "900000003", false));
		return studentList;
	}
}
