
package edu.ycp.cs320.CapstoneActivityTracker.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentAccount extends Account{

	
	//status refers to whether the student is currently in the room
	private boolean status;	
	private List<RoomEvent> events;
	private RoomEvent event;
	//when account is created student is not in a room, therefore status is false
	public StudentAccount() {
		super();
		status = false;
		events = new ArrayList<RoomEvent>();
	}
	
	
	//immediately initializing StudentAccount with credentials
	public StudentAccount(String firstname, String lastname, String email, String password, String schoolID, boolean faculty) {
		super(firstname, lastname, email, password, schoolID, faculty);
		status = false;
		events = new ArrayList<RoomEvent>();
	}
	
	//getting status
	public boolean getStatus() {
		return status;
	}
	
	//setting status
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	//creating new RoomEvent and returning the value, once the RoomEvent is created the student is now in the room
	public void createRoomEvent(LocalDateTime start) {
		 event = new RoomEvent(start);
		 status = true;
		 events.add(event);
		 //incrementing the number set for the room number
		 event.setNumber(events.size());
	}
	
	//a list of RoomEvents is returned between the two LocalDateTime values passed in
	public List<RoomEvent> findRoomEvents(LocalDateTime start, LocalDateTime end) {
		ArrayList<RoomEvent> list = new ArrayList<RoomEvent>();
		for(RoomEvent roomevent: events) {
			if(roomevent.getStartTime().compareTo(start) >= 0 && roomevent.getEndTime().compareTo(end) <= 0) {
				list.add(roomevent);
			}
		}
		return list;
	}
	
	//an individual RoomEvent is found with the number associated with that RoomEvent
	public RoomEvent findRoomEvent(int number) {
		for(RoomEvent roomevent: events) {
			if(roomevent.getNumber() == number) {
				return roomevent;
			}
		}
		return null;
	}
	
	//updating lognote through StudentAccount, this is most likely redundant 
	//TODO: figure out if this is redundant
	public void updateRoomEventLognote(int number, String newLognote) {
		RoomEvent event = findRoomEvent(number);
		event.setLognote(newLognote);
	}
	
	public List<RoomEvent> getRoomEventList(){
		return events;
	}
}
