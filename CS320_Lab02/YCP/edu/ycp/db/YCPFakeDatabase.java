package edu.ycp.db;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.CapstoneActivityTracker.model.*;
import edu.ycp.model.*;

public class YCPFakeDatabase {
	private List<YCPPersonnel> adminList;
	private List<YCPPersonnel> studentList;
	private List<Room> roomList;
	
	public YCPFakeDatabase() {
		adminList = new ArrayList<YCPPersonnel>();
		studentList = new ArrayList<YCPPersonnel>();
		roomList = new ArrayList<Room>();
		
		readInitialData();
		
		System.out.println(adminList.size() + " admins");
		System.out.println(studentList.size() + " students");
	}

	//Initialization of YCPFakeDB through InitYCPDB Methods
	public void readInitialData() {
			adminList.addAll(InitYCPDB.getAdmins());
			studentList.addAll(InitYCPDB.getStudents());
			roomList.addAll(InitYCPDB.getRooms());
	}

	//finding student YCPPersonnel by their email
	public YCPPersonnel findStudentByEmail(String email) {
		for(YCPPersonnel student: studentList) {
			if(student.getEmail().equals(email)) {
				return student;
			}
		}
		return null;
	}

	//finding student YCPPersonnel by their email
	public YCPPersonnel findAdminByEmail(String email) {
		for(YCPPersonnel admin: adminList) {
			if(admin.getEmail().equals(email)) {
				return admin;
			}
		}
		return null;
	}
	
	//finding student YCPPersonnel by their schoolID
	public YCPPersonnel findStudentBySchoolID(String schoolID) {
		for(YCPPersonnel student: studentList) {
			if(student.getSchoolID().equals(schoolID)) {
				return student;
			}
		}
		return null;
	}
	
	//finding admin YCPPersonnel by their schoolID
	public YCPPersonnel findAdminBySchoolID(String schoolID) {
		for(YCPPersonnel admin: adminList) {
			if(admin.getSchoolID().equals(schoolID)) {
				return admin;
			}
		}
		return null;
	}
	
	//used to check if student exists within the YCPDB
	public boolean verifyStudent(String email, String schoolID) {
		for(YCPPersonnel student: studentList) {
			if(student.getEmail().equals(email) && student.getSchoolID().equals(schoolID)) {
				return true;
			}
		}
		return false;
	}
	
	//used to check if admin exists within the YCPDB
	public boolean verifyAdmin(String email, String schoolID) {
		for(YCPPersonnel admin: adminList) {
			if(admin.getEmail().equals(email) && admin.getSchoolID().equals(schoolID)) {
				return true;
			}
		}
		return false;
	}
	//runs through both admin and student to see if the personnel exists, this may not get used
	public boolean verifyPersonnel(String email, String schoolID) {
		if(verifyAdmin(email,schoolID)) {
			return true;
		}
		else if(verifyStudent(email,schoolID)) {
			return true;
		}
		else
		return false;
	}
	//returns a room from a room number given
	public Room findRoomByNumber(int number) {
		for(Room room: roomList) {
			if(room.getRoomNumber() == number) {
				return room;
			}
		}
		return null;
	}
	
	public List<YCPPersonnel> getAllAdmin(){
		return adminList;
	}
	
	public List<YCPPersonnel> getAllStudents(){
		return studentList;
	}
	
	public List<Room> getAllRooms(){
		return roomList;
	}
}

/*
 * TODO:  
 * make YCPPersonnel
 * findStudentByEmail()
 * findRoomByNumber()
 * findStudentBySchoolID
 * YCPDB will be hardcoded students, admins, and rooms with swipes
 */

