package edu.ycp.db;

import edu.ycp.cs320.CapstoneActivityTracker.model.*;
import edu.ycp.model.*;

import java.util.ArrayList;
import java.util.List;

public class InitYCPDB {

	private static List<YCPPersonnel> adminList;
	private static List<YCPPersonnel> studentList;
	private static List<Room> roomList;
	
	public static List<YCPPersonnel> getAdmins(){
		adminList = new ArrayList<YCPPersonnel>();
		
		//adding all admin YCPPersonnel to the list of admins
		adminList.add(new YCPPersonnel("John", "Doe", "jdoe@ycp.edu", "900000000", true));
		
		return adminList;
	}
	public static List<YCPPersonnel> getStudents(){
		studentList = new ArrayList<YCPPersonnel>();
		
		//adding all student YCPPersonnel to the list of students
		studentList.add(new YCPPersonnel("Jason", "Steinberg", "jsteinberg@ycp.edu", "900000001", false));
		studentList.add(new YCPPersonnel("Travis", "Wetzel", "twetzel1@ycp.edu", "900000002", false));
		studentList.add(new YCPPersonnel("William", "Taylor", "wtaylor1@ycp.edu", "900000003", false));
		
		return studentList;
	}
	public static List<Room> getRooms(){
		roomList = new ArrayList<Room>();
		
		//adding the shop in Kinsley as Shop with room number 100
		roomList.add(new Room("Shop", 100));
		//adding 30 rooms to the list of rooms KEC101->KEC130
		for(int i = 101; i < 130; i++) {
			roomList.add(new Room("KEC", i));
		}
		
		return roomList;
	}
}
//Room(String roomname, int number)