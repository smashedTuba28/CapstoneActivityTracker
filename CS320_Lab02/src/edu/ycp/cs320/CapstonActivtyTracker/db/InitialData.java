package edu.ycp.cs320.CapstonActivtyTracker.db;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.CapstoneActivityTracker.model.AdminAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.Room;
import edu.ycp.cs320.CapstoneActivityTracker.model.RoomEvent;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeam;
import edu.ycp.cs320.CapstoneActivityTracker.model.TopTeam;
import edu.ycp.cs320.booksdb.persist.ReadCSV;

public class InitialData {

	
	// reads initial RoomEvent data from CSV file and returns a List of RoomEvents
	public static List<RoomEvent> getRoomEvents() throws IOException, ParseException{
		List<RoomEvent> eventList = new ArrayList<RoomEvent>();
		ReadCSV readRoomEvents = new ReadCSV("roomEvents.csv");
		
		//formatter to parse through a strong and create a Date
		//https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
		//from oracle docs
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		
		try {
			while(true) {
				List<String> tuple = readRoomEvents.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				RoomEvent event = new RoomEvent();
				event.setAccountID(Integer.parseInt(i.next()));
				event.setRoomID(Integer.parseInt(i.next()));
				event.setStartTime(format.parse(i.next()));
				event.setEndTime(format.parse(i.next()));
				event.setFlag(Boolean.parseBoolean(i.next()));
				event.setLognote(i.next());
				eventList.add(event);
			}
			System.out.println("roomEventList loaded from CSV file");
			return eventList;
		}finally {
			readRoomEvents.close();
		}	
	}

	//reads initial StudentAccount data from CSV file and return List of StudentAccounts
	public static List<StudentAccount> getStudentAccounts() throws IOException{
		List<StudentAccount> studentList = new ArrayList<StudentAccount>();
		ReadCSV readStudentAccounts = new ReadCSV("studentAccounts.csv");
		try {
			//auto-generated primary key
			Integer account_id = 1;
			while(true) {
				List<String> tuple = readStudentAccounts.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				StudentAccount student = new StudentAccount();
				
				//auto-generate id
				student.setAccountID(account_id++);
				student.setFirstname(i.next());
				student.setLastname(i.next());
				student.setSchoolID(i.next());
				student.setEmail(i.next());
				student.setPassword(hashSHA256.getHash(i.next()));
				student.setStatus(Boolean.FALSE);
				studentList.add(student);
			}
			System.out.println("studentList loaded from CSV file");
			return studentList;
		}finally {
			readStudentAccounts.close();
		}
	}

	//reads initial AdminAccount data from CSV file and return List of AdminAccounts
	public static List<AdminAccount> getAdminAccounts() throws IOException{
		List<AdminAccount> adminList = new ArrayList<AdminAccount>();
		ReadCSV readAdminAccounts = new ReadCSV("adminAccounts.csv");
		try {
			//auto-generated primary key
			Integer account_id = 1;
			while(true) {
				List<String> tuple = readAdminAccounts.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				AdminAccount admin = new AdminAccount();
				
				//auto-generate id
				admin.setAccountID(account_id++);
				admin.setFirstname(i.next());
				admin.setLastname(i.next());
				admin.setSchoolID(i.next());
				admin.setEmail(i.next());
				admin.setPassword(hashSHA256.getHash(i.next()));
				adminList.add(admin);
			}
			System.out.println("adminList loaded from CSV file");
			return adminList;
		}finally {
			readAdminAccounts.close();
		}
	}

	//reads initial Rooms data from CSV file and return List of Rooms
	public static List<Room> getRooms() throws IOException{
		List<Room> roomList = new ArrayList<Room>();
		ReadCSV readRooms = new ReadCSV("rooms.csv");
		try {
			// auto-generated primary key for table rooms
			Integer roomID = 1;
			while (true) {
				List<String> tuple = readRooms.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Room room = new Room();

				// auto-generate room ID
				room.setRoomID(roomID++);	
				room.setSubTeamID(Integer.parseInt(i.next()));
				room.setRoomNumber(Integer.parseInt(i.next()));
				room.setRoomName(i.next());
				roomList.add(room);
			}
			System.out.println("roomList loaded from CSV file");			
			return roomList;
		} finally {
			readRooms.close();
		}
	}

	public static List<TopTeam> getTopTeams() throws IOException{
		List<TopTeam> topTeamList = new ArrayList<TopTeam>();
		ReadCSV readTopTeams = new ReadCSV("topTeams.csv");
		try {
			//auto-generated primary key
			Integer topTeamID = 1;
			while(true) {
				List<String> tuple = readTopTeams.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				TopTeam top = new TopTeam();
				
				//auto-generated topTeam_id
				top.setTeamID(topTeamID++);
				top.setTeamname(i.next());
				topTeamList.add(top);
			}
			System.out.println("topTeamList loaded from CVS file");
			return topTeamList;
		}finally {
			readTopTeams.close();
		}
	}

	public static List<SubTeam> getSubTeams() throws IOException{
		List<SubTeam> subTeamList = new ArrayList<SubTeam>();
		ReadCSV readSubTeams = new ReadCSV("subTeams.csv");
		try {
			//auto generated primary key
			Integer subTeamID = 1;
			while(true) {
				List<String> tuple = readSubTeams.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				SubTeam sub = new SubTeam();
				
				//auto-generated subTeamID
				sub.setTeamID(subTeamID++);
				sub.setTopTeamID(Integer.parseInt(i.next()));
				sub.setAccountID(Integer.parseInt(i.next()));
				sub.setTeamname(i.next());
				subTeamList.add(sub);
			}
			System.out.println("subTeamList loaded from CSV file");
			return subTeamList;
		}finally {
			readSubTeams.close();
		}
	}
}