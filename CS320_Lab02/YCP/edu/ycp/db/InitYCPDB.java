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
		adminList.add(new YCPPersonnel("Michael","Scott","mscott@ycp.edu","910000000", true));
		adminList.add(new YCPPersonnel("Toby","Flenderson","tflenderson@ycp.edu","910000001", true));
		adminList.add(new YCPPersonnel("Jan","Levinson","jlevinson@ycp.edu","910000002", true));
		adminList.add(new YCPPersonnel("Robert","California","lizardking@ycp.edu","910000003", true));
		adminList.add(new YCPPersonnel("David","Wallace","dwallace@ycp.edu","910000004", true));
		adminList.add(new YCPPersonnel("John", "Doe", "jdoe@ycp.edu", "910000005", true)); 
		
		return adminList;
	}
	public static List<YCPPersonnel> getStudents(){
		studentList = new ArrayList<YCPPersonnel>();
		
		//adding all student YCPPersonnel to the list of students
		studentList.add(new YCPPersonnel("Jason", "Steinberg", "jsteinberg@ycp.edu", "900000001", false));
		studentList.add(new YCPPersonnel("Travis", "Wetzel", "twetzel1@ycp.edu", "900000002", false));
		studentList.add(new YCPPersonnel("Dwight", "Schrute" , "dschrute@ycp.edu "," 900000002", false));
		studentList.add(new YCPPersonnel("Pam","Beesly","pbeesly@ycp.edu","900000003", false));
		studentList.add(new YCPPersonnel("Jim","Halpert","jhalpert@ycp.edu","900000004", false));
		studentList.add(new YCPPersonnel("Kevin","Malone","kmalone@ycp.edu","900000005", false));
		studentList.add(new YCPPersonnel("Angela","Martin","amartin@ycp.edu","900000006", false));
		studentList.add(new YCPPersonnel("Oscar","Martinez","omartinez@ycp.edu","900000007", false));
		studentList.add(new YCPPersonnel("Meredith","Palmer","mpalmer@ycp.edu","900000008", false));
		studentList.add(new YCPPersonnel("Creed","Bratton","cbratton@ycp.edu","900000009", false));
		studentList.add(new YCPPersonnel("Darryl","Philbin","dphilbin@ycp.edu","900000010", false));
		studentList.add(new YCPPersonnel("Phyllis","Vance","pvance1@ycp.edu","900000011", false));
		studentList.add(new YCPPersonnel("Stanley","Hudson","shudson@ycp.edu","900000012", false));
		studentList.add(new YCPPersonnel("Andy","Bernard","abernard@ycp.edu","900000013", false));
		studentList.add(new YCPPersonnel("Ryan","Howard","rhoward@ycp.edu","900000014", false));
		studentList.add(new YCPPersonnel("Kelly","Kapoor","kkapoor@ycp.edu","900000015", false));
		studentList.add(new YCPPersonnel("Gabe", "Lewis", "glewis@ycp.edu", "900000016", false));
		studentList.add(new YCPPersonnel("Erin", "Hannon",  "ehannon@ycp.edu", "900000017", false));
		studentList.add(new YCPPersonnel("Nellie", "Bertram", "nbertram@ycp.edu", "900000018", false));
		studentList.add(new YCPPersonnel("Pete", "Miller", "pmiller@ycp.edu", "900000019", false));
		studentList.add(new YCPPersonnel("Clark", "Green", "cgreen@ycp.edu", "900000020", false));
		
		
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