package edu.ycp.cs320.CapstoneActivityTracker.RFID;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import edu.ycp.cs320.CapstonActivtyTracker.db.DatabaseProvider;
import edu.ycp.cs320.CapstonActivtyTracker.db.DerbyDatabase;
import edu.ycp.cs320.CapstonActivtyTracker.db.IDatabase;
import edu.ycp.cs320.CapstoneActivityTracker.model.Room;
import edu.ycp.cs320.CapstoneActivityTracker.model.RoomEvent;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;

public class RFIDController {
	private IDatabase db;
	private StudentAccount student;
	public RFIDController(){
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	
	//will make calls to DB to crete or finish room event
	//only if student exists, room is in DB, 
	//and if the student is apart of a subteam associated with the room
	public void handleEvent(String scan) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");//format of incoming time string
		StringTokenizer tok = new StringTokenizer(scan, "|");//tokenized input string
		String schoolID = tok.nextToken().trim();//schoolId is first element
		Integer roomNumber = Integer.parseInt(tok.nextToken().trim());//roomNumber is second element
		Boolean tracking = false;
		Timestamp time = null;//third element 
		Integer room_id = 0;
		
		try {
			time = new Timestamp( format.parse(tok.nextToken().trim()).getTime());//attempt to parse string into a timestamp object
		} catch (ParseException e) {
			System.out.println("FAILED TO PARSE INPUT TIMESTAMP");
		}
		
		
		//check if student is being tracked
		student = db.getStudentAccountWithSchoolID(schoolID);
		if(student != null) {
			//is a student and they are being tracked...
			
			//find students tracked rooms
			List<Room> rooms = db.getRoomsForStudentAccount(student.getStudentAccountID());

			for (Room room : rooms) {//look through tracked rooms to see if any match scanned room
				if(room.getRoomNumber() == roomNumber){
					tracking = true;//room does need to be tracked
					room_id = room.getRoomID();//save id for later use
					break;
				}
			}
			
			if(tracking) {
				//if the room is being tracker for the student
				if(student.getStatus()) {
					//they are currently in a room
					
					//get most recent room event
					RoomEvent roomEvent = db.getLastRoomEventForStudent(student.getStudentAccountID());
					
					if(roomEvent.getRoomID() == room_id){
						//scanning out of current room
						if(db.updateRoomEventandStatusForStudentAccountWithAccountIDandEventID(student.getStudentAccountID(), 
								roomEvent.getRoomEventID(), time, Boolean.FALSE, Boolean.FALSE)) {//flag false because student signed in and out
							System.out.println("Student<" + student.getFirstname() + " " + student.getLastname() + "> scanned out of room " + roomNumber);
							//updating the room event this way also set student status to false
						}
						else {
							System.out.println("Unable to Scan out");
						}
					}
					else {
						//they are scanning card at a new room
						//first close and flag the previous roomEvent
						if(db.updateRoomEventandStatusForStudentAccountWithAccountIDandEventID(student.getStudentAccountID(), 
								roomEvent.getRoomEventID(), time, Boolean.TRUE, Boolean.FALSE)) {
							System.out.println("AUTO CLOSED PREVIOULY OPENED ROOM EVENT: FLAGGED : SYSTEM CLOSED");
							db.updateLogNoteforRoomEvent(roomEvent.getRoomEventID(), "System Note::: OOPS. Silly me, I can't be in two places at once.");
						}
						else {
							System.out.println("Unable to close previous event");
						}
						//now open a new event for the new room
						System.out.println("New RoomEvent being created...");
						if(db.createRoomEventForStudentAccountWithIDandUpdateStatus(student.getStudentAccountID(), room_id, time)) {
							System.out.println("Room Event Created for " + student.getFirstname() + " " + student.getLastname()
									+ " in Room " + roomNumber);
						}
						else {
							System.out.println("Failed to create new room event");
						}
					}
				}
				else {
					//not in a room need to create a new roomEvent
					System.out.println("New RoomEvent being created...");
					if(db.createRoomEventForStudentAccountWithIDandUpdateStatus(student.getStudentAccountID(), room_id, time)) {
						System.out.println("Room Event Created for " + student.getFirstname() + " " + student.getLastname()
								+ " in Room " + roomNumber);
					}
					else {
						System.out.println("Failed to create new room event");
					}
				}
			}
			else {
				System.out.println("Not tracking this room for " + student.getFirstname() + " " +student.getLastname());
			}
		}
		else {
			System.out.println("Not a tracked Student");
		}
		
	}
}
