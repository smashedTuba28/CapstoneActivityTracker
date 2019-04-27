package edu.ycp.cs320.CapstonActivtyTracker.db;

import java.sql.Timestamp;
import java.util.List;

import edu.ycp.cs320.CapstoneActivityTracker.model.AdminAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.Room;
import edu.ycp.cs320.CapstoneActivityTracker.model.RoomEvent;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeam;

public interface IDatabase {
	
	
	public Boolean createStudentAccount(String firstname, String lastname, String email, String password, String schoolID);//insert
	public Boolean creatAdminAccount(String firstname, String lastname, String email, String password, String schoolID);//insert
	
	public StudentAccount getStudentAccountWithID(Integer account_id);//select
	public StudentAccount getStudentAccountWithFirstandLastname(String firstname, String lastname);//select
	public StudentAccount verifyStudentAccount(String email, String password);//select
	public List<StudentAccount> getStudentsInSubTeam(Integer subTeam_id);
	
	
	

	public AdminAccount getAdminAccountWithID(Integer adminAccount_id);//select
	public AdminAccount verifyAdminAccount(String email, String password);//select

	public List<Room> getRoomsForASubTeam(Integer subTeam_id);
	
	public List<RoomEvent> getRoomEventsForStudentWithDates(Integer account_id, Timestamp start, Timestamp end);//select
	
	
	public List<SubTeam> getSubTeamsInTopTeam(String topTeamname);//select
	public Boolean creatSubTeam();//insert
	
	
	public Boolean createTopTeam();//insert
	
}
