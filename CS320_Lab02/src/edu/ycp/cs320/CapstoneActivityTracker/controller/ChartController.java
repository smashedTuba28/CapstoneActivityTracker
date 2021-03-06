package edu.ycp.cs320.CapstoneActivityTracker.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.ycp.cs320.CapstoneActivityTracker.model.AdminAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.ChartModel;
import edu.ycp.cs320.CapstoneActivityTracker.model.RoomEvent;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeam;
import edu.ycp.cs320.CapstoneActivityTracker.model.TopTeam;
import edu.ycp.cs320.CapstonActivtyTracker.db.*;

public class ChartController {
	private IDatabase db;
	private ChartModel model;
	
	  
	public ChartController(){ 
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	
	public void setModel(ChartModel model) {
		this.model = model;
	}
	 
	public void populateStudentWeek(String[] name, String teamname, Date start, Date end, int offset) {
		List<StudentAccount> students = db.getAllStudentsInSubTeamWithTeamName(teamname);
		for(StudentAccount s: students) {
			if(s.getFirstname().equals(name[0]) && s.getLastname().equals(name[1])) {
				populateStudentWeek(s.getAccountID(), start, end, offset);
			}
		}
	}
	
	public void populateStudentWeek(Integer account_id, Date start, Date end, int offset){
		model.setOffset(offset);
		StudentAccount student = db.getStudentAccountWithID(account_id);//get student from DB using general AccountID	
		//get all room events for student
		List<RoomEvent> eventList = db.getAllRoomEventForStudentAccountWithAccountID(student.getStudentAccountID());
		
		try{
			model.setCurrentSub(db.getSubTeamWithAccountID(account_id).getTeamname());
		}
		catch(NullPointerException e) {
			
		}
		
		//System.out.println(eventList.size());
		
		//for(int i = 0; i<eventList.size(); i++) {
		//	System.out.println("start: " + eventList.get(i).getStartTime() );
		//	System.out.println("end: " + eventList.get(i).getEndTime());
		//}
		
		
		long[] dateDuration = getWeekFromRoomEvents(start, end, eventList);//get durations and dates from 
		
		//for (int i = 0; i<dateDuration.length; i++) {
		//System.out.println(dateDuration[i]);
		//}
		
		String title = "Individual Capstone Work Hours";
		Calendar c = Calendar.getInstance();//create a calendar instance/reference
		String data = "[['Date', 'Hours']"; 
		//System.out.println("Date Duration count:");
		for(int i = 0; i < 7; i++) {
				//System.out.println(i + ": " + dateDuration[i]);
				c.setTimeInMillis(start.getTime() + i*86400000);
				//creates data chart using month, day, and dur value
				data += ",['" + (c.get(Calendar.MONTH)+1)  + "-" + c.get(Calendar.DAY_OF_MONTH) + "-" 
				+ c.get(Calendar.YEAR) + "', " + dateDuration[i] / 60. + "]";
		}			
		//finalize data string by closing bracket
		data += "]";
		
		model.setData(data);//assign formatted data to model
		model.setCurrentStudent(student.getFirstname() + " " + student.getLastname());
		model.setTitle(title);
	}
	
	
	public void populateSubTeamWeekWithTeamName(String subTeamname, Date start, Date end, int offset) {
		List<StudentAccount> students = db.getAllStudentsInSubTeamWithTeamName(subTeamname);
		try{
			String account_id = String.valueOf(students.get(0).getAccountID());
			populateSubTeamWeek(account_id, start,end,offset);
		}
		catch (IndexOutOfBoundsException e){
			
		}
	}
	
	
	
	
	
	public void populateSubTeamWeek(String account_id, Date start, Date end, int offset) {
		SubTeam subTeam = db.getSubTeamWithAccountID(Integer.parseInt(account_id));		
		
		model.setOffset(offset);
		model.setCurrentSub(subTeam.getTeamname());
		
		String title = subTeam.getTeamname() + " Work Hours";
		ArrayList<long[]> weekList = new ArrayList<long[]>();
		String data = "[['Date'";
		
		//get all students in the Team
		List<StudentAccount> students = db.getAllStudentsInSubTeamWithTeamName(subTeam.getTeamname());
		
		for(StudentAccount s: students) {//get each students week events
			weekList.add(getWeekFromRoomEvents(start, end, db.getAllRoomEventForStudentAccountWithAccountID(s.getStudentAccountID())));
			data += ",'" + s.getFirstname() + " " + s.getLastname() + "'";
		}
		data += "]";

		Calendar c = Calendar.getInstance();//create a calendar instance/reference
		
		for (int i=0; i<7; i++) {
			c.setTimeInMillis(start.getTime() + i*86400000);
			
			data += ",['" + (c.get(Calendar.MONTH)+1)  + "-" + c.get(Calendar.DAY_OF_MONTH) + "-"+ c.get(Calendar.YEAR) +  "'";
			for(int j=0; j < weekList.size(); j++) {
				data += ", " + weekList.get(j)[i] / 60.;
			}
			data += "]";
		}
		data += "]";
		
		model.setData(data);
		model.setTitle(title);
	}
	
	public void getTeamInfoForAccount(Integer account_id, String accountType) {
		
		if(accountType.equals("student")) {
			//student is currently logged in
			StudentAccount student = db.getStudentAccountWithID(account_id);
			model.setStudent(student.getFirstname() + " " +student.getLastname());
			
			//get the logged in student's topteam name
			TopTeam topTeam = db.getTopTeamWithAccountID(account_id);
			model.setTopTeamName(topTeam.getTeamname());
			
			//get the logged in students subTeam
			SubTeam subTeam = db.getSubTeamWithAccountID(account_id);
			model.setMySubTeamName(subTeam.getTeamname());
			
			//get a list of the subTeams in that topTeam
			List<SubTeam> subTeams = db.getSubTeamsInTopTeam(topTeam.getTeamname());
			//ArrayList<String> subTeamNames = new ArrayList<String>();
			//format as a javaScript array
			String subTeamNames = "[";
			for (int i = 0; i<subTeams.size(); i++) {
				subTeamNames += "\"" + subTeams.get(i).getTeamname() + "\"";
				if(i<subTeams.size()-1) {
					subTeamNames +=",";
				}
			}
			subTeamNames += "]";
			
			//for(SubTeam sub: subTeams) {
				//if(sub.getTeamID() != subTeam.getTeamID())
				//{
				//	subTeamNames.add(sub.getTeamname());
				//}
			//}
			model.setSubTeamNames(subTeamNames);
		
			
			//get all students
			List<StudentAccount> studentList = db.getAllStudentsInSubTeamWithTeamName(subTeam.getTeamname());
			//ArrayList<String> studentNames = new ArrayList<String>();
			//format into a javaScript array
			String studentNames = "[";
			for (int i = 0; i<studentList.size(); i++) {
				studentNames += "\"" + studentList.get(i).getFirstname() + " " + studentList.get(i).getLastname() + "\"";
				if(i<studentList.size()-1) {
					studentNames += ",";
				}
			}
			studentNames += "]";
			/*
			for(StudentAccount s: studentList) {
				if(s.getAccountID()!=account_id) {
					studentNames.add(s.getFirstname() + " " + s.getLastname());
				}
			}*/
			model.setStudentNames(studentNames);
			System.out.println(model.getStudentNames());	
		}
		else {
			AdminAccount admin = db.getAdminAccountWithID(account_id);
			model.setStudent(admin.getFirstname() + " " + admin.getLastname());
		}
	}

	public long[] getWeekFromRoomEvents(Date start, Date end, List<RoomEvent> eventList) {
		
		ArrayList<RoomEvent> events = new ArrayList<RoomEvent>();//list for all events within timeframe
		long durations[] = new long[7];
	
		long delta = (end.getTime()-start.getTime()) / 7;//splitting period into 7 portions (7 days)
		Date temp =  new Date(start.getTime() + delta);//temp date for start + 1
		long dur = 0;//initialize current duration to 0
		
		for(RoomEvent e: eventList) {//populate the events list with all applicable RoomEvents
			if (((e.getStartTime().after(start) || e.getStartTime().equals(start)) && e.getStartTime().before(end)) //if it start in the time frame or
			  || ((e.getEndTime().before(end) || e.getEndTime().equals(end)) && e.getEndTime().after(start))) { //it ends in the time frame	
				if(e.getStartTime().after(e.getEndTime())) {}
				else {
					events.add(e);
				}
			}
		}
		
		//System.out.println("SIZE AFTER CHECK: " + events.size());
		
		//for(int i = 0; i<events.size(); i++) {
		//	System.out.println("start: " + events.get(i).getStartTime() );
		//	System.out.println("end: " + events.get(i).getEndTime());
		//}
		
		
		//stash the lognotes of these room events into to ChartModel
		populateModelLognotes(events);
		
		for(int i= 0; i < 7; i++) {//always doing 7
			//for loop checks all applicable events to see if they transpire on a certain day
			
			for (RoomEvent e: events) {
				
				//System.out.println("Beginning Check on Room Event");
				
				if((e.getStartTime().after(start) ||e.getStartTime().equals(start)) 
				  && (e.getEndTime().before(temp) || e.getEndTime().equals(temp))) {//if it happens all in one day add the complete duration to dur
					dur += e.getDuration();
					//System.out.println("All in one day::: " + dur);
					//events.remove(e);//remove since all of time has been accounted for
				}
				else if(e.getStartTime().before(temp) && e.getEndTime().after(temp) 
				  && (e.getStartTime().after(start) || e.getStartTime().equals(start))) {//starts in current timeframe continues into next
					dur += (temp.getTime() - e.getStartTime().getTime()) / 60000; //find duration in minutes that belongs to current time frame 
					//System.out.println("Start in current time frame, end later::: " + dur);	
				}
				else if(e.getStartTime().before(start) && e.getEndTime().after(start)
				  && (e.getEndTime().before(temp) || e.getEndTime().equals(temp))) {//started on a previous timeframe and ends during current
					dur += (e.getEndTime().getTime() - start.getTime()) / 60000;//finds duration in minutes that belongs to current timeframe
					//events.remove(e);//remove since all time will have been accounted for in previous iterations and this one
					//System.out.println("Ends in Current Time Frame, started prior::: " + dur);
				}
				else if(e.getStartTime().before(start) && e.getEndTime().after(temp)) {//starts on a previous timeframe and ends after current
					dur += (temp.getTime() - start.getTime()) / 60000;//finds portion that belongs to current timeframe
					//System.out.println("Start and ends outside of current time frame::: " + dur);
				}
				//else {System.out.println("ouside of current bounds");}
			}	
			
			//checked through all available events and added duration to a sum total
			//System.out.println("Current start day: " + start.toString());
			//System.out.println("Current end day: " + temp.toString());
			//System.out.println("total duration sum for time frame: " + dur);
			//System.out.println("\n\n");
		
			durations[i] = dur;//save total time into duration array
			//durations[1][i] = start.getTime();
			start = new Date(temp.getTime());	//move start position to current end position
			temp.setTime(temp.getTime() + delta);//advance temp to next position == current start + 1
			
			//System.out.println("New Start Day::: " + start.toString());
			//System.out.println("New End Day::: " + temp.toString());
			//System.out.println("\n\n");
		
			dur=0;//reset duration for next iteration
			
		}			
		//Finished going through all room events and adding durations 
		return durations;
	}
	
	public void populateModelLognotes(List<RoomEvent> events) {
		model.setEvents(events);
	}

	public Boolean updateLognote(Integer event_id, String lognote) {
		if(db.updateLogNoteforRoomEvent(event_id, lognote)){
			return true;
		}
		return false;
	}
}

