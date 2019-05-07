package edu.ycp.cs320.CapstonActivtyTracker.db;

import java.sql.Timestamp;
import java.util.List;

import edu.ycp.cs320.CapstoneActivityTracker.model.AdminAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.Room;
import edu.ycp.cs320.CapstoneActivityTracker.model.RoomEvent;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeam;
import edu.ycp.cs320.CapstoneActivityTracker.model.TopTeam;

public interface IDatabase {
	public Boolean createStudentAccount(String firstname, String lastname, String email, String password, String schoolID);//insert
	public Boolean createAdminAccount(String firstname, String lastname, String email, String password, String schoolID);//insert
	
	public StudentAccount getStudentAccountWithID(Integer account_id);//select
	public StudentAccount getStudentAccountWithEmailandSchoolID(String email, String schoolID);//select
	public StudentAccount verifyStudentAccount(String email, String password);//select
	public List<StudentAccount> getStudentsInSubTeam(Integer subTeam_id);//select
	public Boolean deleteStudentAccount(Integer studentAccount_id);//delete
	public List<StudentAccount> getAllStudentsInSubTeamWithTeamName(String teamname);
	
	public AdminAccount getAdminAccountWithEmailandSchoolID(String email, String schoolID);
	public AdminAccount getAdminAccountWithID(Integer account_id);//select
	public AdminAccount verifyAdminAccount(String email, String password);//select
	public Boolean deleteAdminAccount(Integer adminAccount_id);//delete
	
	public List<Room> getRoomsForASubTeam(Integer subTeam_id);//select
	
	public List<RoomEvent> getAllRoomEventForStudentAccountWithAccountID(Integer studentAccount_id);
	public List<RoomEvent> getRoomEventsForStudentWithDates(Integer account_id, Timestamp start, Timestamp end);//select
	public boolean createRoomEventForStudentAccountWithID(Integer studentAccount_id, Integer room_id, Timestamp start);//insert
	public boolean updateRoomEventForStudentAccountWithAccountIDandEventID(Integer account_id, Integer roomEvent_id, Timestamp end, Boolean flag);
	
	public List<SubTeam> getSubTeamsInTopTeam(String topTeamname);//select
	public TopTeam getTopTeamWithTeamname(String topTeamname);
	public Boolean deleteTopTeam(Integer topTeam_id);
	
	public SubTeam getSubTeamWithTeamname(String teamname);//select
	public Boolean createSubTeam(String name, Integer topTeamID);//insert
	public Boolean deleteSubTeam(Integer subTeamID);//delete
	
	public Boolean createTopTeam(String topTeamname);//insert
}
