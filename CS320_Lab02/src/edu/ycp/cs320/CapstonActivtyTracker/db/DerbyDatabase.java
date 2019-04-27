package edu.ycp.cs320.CapstonActivtyTracker.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.CapstoneActivityTracker.model.AdminAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.Room;
import edu.ycp.cs320.CapstoneActivityTracker.model.RoomEvent;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeam;
import edu.ycp.cs320.CapstoneActivityTracker.model.TeamRoom;
import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeamStudents;
import edu.ycp.cs320.CapstoneActivityTracker.model.TopTeam;


public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	
	
	// wrapper SQL transaction function that calls actual transaction function (which has retries)
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	// SQL transaction function which retries the transaction MAX_ATTEMPTS times before failing
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	// TODO: Here is where you name and specify the location of your Derby SQL database
	// TODO: Change it here and in SQLDemo.java under CS320_LibraryExample_Lab06->edu.ycp.cs320.sqldemo
	// TODO: DO NOT PUT THE DB IN THE SAME FOLDER AS YOUR PROJECT - that will cause conflicts later w/Git
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/cs320-spring2019/db/tracker.db;create=true");		
		
		// Set autocommit() to false to allow the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
/*	// retrieves Author information from query result set
	private void loadAuthor(Author author, ResultSet resultSet, int index) throws SQLException {
		author.setAuthorId(resultSet.getInt(index++));
		author.setLastname(resultSet.getString(index++));
		author.setFirstname(resultSet.getString(index++));
	}
	
	// retrieves Book information from query result set
	private void loadBook(Book book, ResultSet resultSet, int index) throws SQLException {
		book.setBookId(resultSet.getInt(index++));
//		book.setAuthorId(resultSet.getInt(index++));  // no longer used
		book.setTitle(resultSet.getString(index++));
		book.setIsbn(resultSet.getString(index++));
		book.setPublished(resultSet.getInt(index++));
	}
	
	// retrieves WrittenBy information from query result set
	private void loadBookAuthors(BookAuthor bookAuthor, ResultSet resultSet, int index) throws SQLException {
		bookAuthor.setBookId(resultSet.getInt(index++));
		bookAuthor.setAuthorId(resultSet.getInt(index++));
	}
*/	
	
	//create all db tables
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;
				PreparedStatement stmt7 = null;
				
				try {
					//room table
					stmt1 = conn.prepareStatement(
						"create table rooms (" +
						"	room_id_1 integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +
						"   room_id_2 integer," +
						"	number integer," +
						"	name varchar(70)" +
						")"
					);	
					stmt1.executeUpdate();
					System.out.println("Rooms table created");
					
					// student accounts table
					stmt2 = conn.prepareStatement(
							"create table studentAccounts(" +
							"	account_id_1 integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"   account_id_2 integer," +
							"	firstname varchar(20)," +
							"	lastname varchar(20)," +
							"   email varchar(35)," + 
							" 	password varchar(100)," +
							"	schoolID varchar(9)," +
							"   status boolean" +
							")"
					);
					stmt2.executeUpdate();
					System.out.println("studentAccounts table created");					
					
					
					//room events table
					stmt3 = conn.prepareStatement(
							"create table roomEvents (" +
							"   roomEvent_id integer primary key " +
							"      	generated always as identity (start with 1, increment by 1)," +
							"	account_id_1 integer constraint account_id_1 references studentAccounts," +
							"	room_id_2 integer constraint room_id_2 references rooms," + 
							"	startTime timestamp," + 
							"	endTime timestamp," +
							"	lognote varchar(400)" +
							")"
					);
					stmt3.executeUpdate();
					System.out.println("roomEvents table created");					
							
					
					//Top teams table
					stmt4 = conn.prepareStatement(
							"create table topTeams (" +
							"	topTeam_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +									
							"	teamname varchar(40)" +
							")"
					);
					stmt4.executeUpdate();
					System.out.println("topTeams table created");	
					
					//Sub teams table
					stmt4 = conn.prepareStatement(
							"create table subTeams (" +
							"	subTeam_id_1 integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"   subTeam_id_2 integer," +
							"	teamname varchar(40)" +
						//	" 	topTeam_id integer constraint topTeam_id references topTeams," +
						//	" 	account_id integer constraint studentAccount_id references studentAccounts" +
							")"
					);
					stmt4.executeUpdate();
					System.out.println("topTeam table created");
					
					//admin acccounts table
					stmt5 = conn.prepareStatement(
							"create table adminAccounts(" +
							"	adminAccount_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	firstname varchar(20)," +
							"	lastname varchar(20)," +
							"   email varchar(35)," + 
							" 	password varchar(100)," +
							"	schoolID varchar(9)" +
							")"
					);
					stmt5.executeUpdate();
					System.out.println("adminAccount table created");
					
					//teamRooms
					stmt6 = conn.prepareStatement(
							"create table teamRooms (" +
									"	subTeam_id_1 integer constraint subTeam_id_1 references subTeams, " +
									"	room_id_1 integer constraint room_id_1 references rooms " +
									")"
					);
					stmt6.executeUpdate();
					System.out.println("teamRooms table created");
					
					
					//topSub
					stmt7 = conn.prepareStatement(
							"create table subTeamStudents (" +
									"	subTeam_id_2 integer constraint subTeam_id_2 references subTeams, " +
									"	account_id_2 integer constraint account_id_2 references studentAccounts " +
									")"
					);
					stmt7.executeUpdate();
					System.out.println("subTeamStudents table created");
					
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
	// loads data retrieved from CSV files into DB tables in batch mode
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<RoomEvent> roomEventList;
				List<StudentAccount> studentList;
				List<AdminAccount> adminList;
				List<Room> roomList;
				List<TopTeam> topTeamList;
				List<SubTeam> subTeamList;
				
				List<TeamRoom> teamRoomList;
				List<SubTeamStudents> subTeamStudentsList;
				
				
				try {
					roomEventList     	= InitialData.getRoomEvents();
					studentList       	= InitialData.getStudentAccounts();
					adminList 			= InitialData.getAdminAccounts();
					roomList			= InitialData.getRooms();
					topTeamList			= InitialData.getTopTeams();
					subTeamList			= InitialData.getSubTeams();
					teamRoomList		= InitialData.getTeamRooms();
					subTeamStudentsList	= InitialData.getTopSub();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				} catch (ParseException e) {
					throw new SQLException("Couldn't parse initial data", e);
				}

				PreparedStatement insertRoomEvent     	= null;
				PreparedStatement insertStudent       	= null;
				PreparedStatement insertAdmin 			= null;
				PreparedStatement insertRoom			= null;
				PreparedStatement insertTopTeam			= null;
				PreparedStatement insertSubTeam			= null;
				PreparedStatement insertTeamRoom		= null;
				PreparedStatement insertSubTeamStudents	= null;
				
				try {
					
					insertRoom = conn.prepareStatement("insert into rooms (room_id_2, number, name) values (?, ?, ?)");
					for (Room room : roomList) {
						insertRoom.setInt(1, room.getRoomID());//second id just copying from primary key to use as a second foreign key
						insertRoom.setInt(2, room.getRoomNumber());
						insertRoom.setString(3, room.getRoomName());
						insertRoom.addBatch();
					}
					insertRoom.executeBatch();
					System.out.println("Rooms table populated");
					
					
					insertStudent = conn.prepareStatement("insert into studentAccounts (firstname, lastname, email, password, schoolID, status, account_id_2) values (?,?,?,?,?,?,?)");
					for (StudentAccount student : studentList) {
						insertStudent.setString(1, student.getFirstname());
						insertStudent.setString(2, student.getLastname());
						insertStudent.setString(3, student.getEmail());
						insertStudent.setString(4, student.getPassword());
						insertStudent.setString(5, student.getSchoolID());
						insertStudent.setBoolean(6, student.getStatus());
						insertStudent.setInt(7, student.getAccountID());
						insertStudent.addBatch();
					}
					insertStudent.executeBatch();
					System.out.println("StudentAccounts table populated");					
					
					
					insertAdmin = conn.prepareStatement("insert into adminAccounts (firstname, lastname, email, password, schoolID) values (?, ?, ?, ?, ?)");
					for (AdminAccount admin : adminList) {
						insertAdmin.setString(1, admin.getFirstname());
						insertAdmin.setString(2, admin.getLastname());
						insertAdmin.setString(3, admin.getEmail());
						insertAdmin.setString(4, admin.getPassword());
						insertAdmin.setString(5, admin.getSchoolID());
						insertAdmin.addBatch();
					}
					insertAdmin.executeBatch();
					System.out.println("AdminAccounts table populated");	
					
					
					insertTopTeam = conn.prepareStatement("insert into topTeams (teamname) values (?)");
					for (TopTeam top : topTeamList) {
						insertTopTeam.setString(1, top.getTeamname());
						insertTopTeam.addBatch();
					}
					insertTopTeam.executeBatch();
					System.out.println("TopTeam table populated");
					
					
					insertRoomEvent = conn.prepareStatement("insert into roomEvents (account_id_1, room_id_2, startTime, endTime, lognote) values (?,?,?,?,?) ");
					for (RoomEvent event : roomEventList) {
						insertRoomEvent.setInt(1, event.getAccountID());
						insertRoomEvent.setInt(2, event.getRoomID());
						insertRoomEvent.setTimestamp(3, new Timestamp(event.getStartTime().getTime()));
						insertRoomEvent.setTimestamp(4, new Timestamp(event.getEndTime().getTime()));
						insertRoomEvent.setString(5, event.getLognote());
						insertRoomEvent.addBatch();
					}
					insertRoomEvent.executeBatch();
					System.out.println("RoomEvents Table populated");
					
									
					insertSubTeam = conn.prepareStatement("insert into subTeams (subTeam_id_2, teamname) values (?,?)");
					for (SubTeam sub : subTeamList) {
						insertSubTeam.setInt(1, sub.getTeamID());
						insertSubTeam.setString(2, sub.getTeamname());
						insertSubTeam.addBatch();
					}
					insertSubTeam.executeBatch();
					System.out.println("SubTeams Table populated");
				
					insertTeamRoom = conn.prepareStatement("insert into teamRooms (subTeam_id_1, room_id_1) values (?,?)");
					for (TeamRoom tr : teamRoomList) {
						insertTeamRoom.setInt(1, tr.getTeamID());
						insertTeamRoom.setInt(2, tr.getRoomID());
						insertTeamRoom.addBatch();
					}
					insertTeamRoom.executeBatch();
					System.out.println("TeamRooms table populated");
					
					insertSubTeamStudents = conn.prepareStatement("insert into subTeamStudents (account_id_2, subTeam_id_2) values (?,?)");
					for(SubTeamStudents subStudent : subTeamStudentsList) {
						insertSubTeamStudents.setInt(1, subStudent.getStudentAccountID());
						insertSubTeamStudents.setInt(2, subStudent.getSubTeamID());
						insertSubTeamStudents.addBatch();
					}
					insertSubTeamStudents.executeBatch();
					System.out.println("subTeamStudents table populated");
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertStudent);
					DBUtil.closeQuietly(insertAdmin);
					DBUtil.closeQuietly(insertRoom);
				//	DBUtil.closeQuietly(insertRoomEvent);
					DBUtil.closeQuietly(insertTopTeam);
					DBUtil.closeQuietly(insertSubTeam);
					DBUtil.closeQuietly(insertTeamRoom);
					DBUtil.closeQuietly(insertSubTeamStudents);
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Library DB successfully initialized!");
	}

	
	//Finds and returns studentAccount for login
	@Override
	public StudentAccount verifyStudentAccount(String email, String password) {
		return executeTransaction(new Transaction<StudentAccount>() {
			@Override
			public StudentAccount execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				StudentAccount studentAccount =  null;
				
				try {
					stmt = conn.prepareStatement(
						"select studentAccounts.* "
						+ "	from studentAccounts "
						+ " where studentAccounts.email = ?"
						+ " and studentAccounts.password = ?"
					);
					
					stmt.setString(1, email);
					stmt.setString(2, password);
					resultSet = stmt.executeQuery();
					
					if(resultSet.next()) {		
						studentAccount = new StudentAccount();//create new model
						// result set to populate model
						studentAccount.setAccountID(resultSet.getInt(1));
						studentAccount.setFirstname(resultSet.getString(3));
						studentAccount.setLastname(resultSet.getString(4));
						studentAccount.setEmail(resultSet.getString(5));
						studentAccount.setPassword(resultSet.getString(6));
						studentAccount.setSchoolID(resultSet.getString(7));
						studentAccount.setStatus(resultSet.getBoolean(8));
					}
					
					return studentAccount;//return either null or populated model
				}finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
			}
		});	
	}

	

	@Override
	public StudentAccount getStudentAccountWithID(Integer account_id) {
		return executeTransaction(new Transaction<StudentAccount>() {
			@Override
			public StudentAccount execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				StudentAccount studentAccount =  null;
				
				try {
					stmt = conn.prepareStatement(
						"select studentAccounts.* "
						+ "	from studentAccounts "
						+ " where studentAccounts.account_id_1 = ?"
					);
					
					stmt.setInt(1, account_id);
					resultSet = stmt.executeQuery();
					
					if(resultSet.next()) {		
						studentAccount = new StudentAccount();//create new model
						// result set to populate model
						studentAccount = new StudentAccount();//new account
						studentAccount.setAccountID(resultSet.getInt(1));//column 1 = account_id_1
																			//column 2 = account_id_2 : unused outside of schema and always equals account_id_1
						studentAccount.setFirstname(resultSet.getString(3));//column 3 = firstname
						studentAccount.setLastname(resultSet.getString(4));//column 4 = lastname
						studentAccount.setEmail(resultSet.getString(5));//column 5 = email
						studentAccount.setPassword(resultSet.getString(6));//column 6 = password
						studentAccount.setSchoolID(resultSet.getString(7));//column 7 = schoolID
						studentAccount.setStatus(resultSet.getBoolean(8));//column 8 = status
					}
					return studentAccount;//return either null or populated model
				}finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
			}
		});	
	}

	@Override
	public StudentAccount getStudentAccountWithEmailandSchoolID(String email, String schoolID) {
		return executeTransaction(new Transaction<StudentAccount>() {
			@Override
			public StudentAccount execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;//for select
				ResultSet	resultSet1 = null;//result from select
				StudentAccount studentAccount = null;//for return
				
				try {
					//prepare SQL statement to select
					stmt1 = conn.prepareStatement(
						"select studentAccounts.* "
						+ "  from studentAccounts"
						+ "  where studentAccounts.email = ?"
						+ "  and studentAccounts.schoolID = ?"
					);
							
					//insert values into prepared statement
					stmt1.setString(1, email);
					stmt1.setString(2, schoolID);
					
					//execute query and get result set
					resultSet1 = stmt1.executeQuery();
					
					if(resultSet1.next()) {//resultSet not empty
						//populate return model
						studentAccount = new StudentAccount();//new account
						studentAccount.setAccountID(resultSet1.getInt(1));//column 1 = account_id_1
																			//column 2 = account_id_2 : unused outside of schema and always equals account_id_1
						studentAccount.setFirstname(resultSet1.getString(3));//column 3 = firstname
						studentAccount.setLastname(resultSet1.getString(4));//column 4 = lastname
						studentAccount.setEmail(resultSet1.getString(5));//column 5 = email
						studentAccount.setPassword(resultSet1.getString(6));//column 6 = password
						studentAccount.setSchoolID(resultSet1.getString(7));//column 7 = schoolID
						studentAccount.setStatus(resultSet1.getBoolean(8));//column 8 = status
					}
					return studentAccount; //will either be fully populated or null
				}finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(resultSet1);
				}
			}
		});
	}

	@Override
	public List<StudentAccount> getStudentsInSubTeam(Integer subTeam_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminAccount getAdminAccountWithID(Integer adminAccount_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminAccount verifyAdminAccount(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getRoomsForASubTeam(Integer subTeam_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomEvent> getRoomEventsForStudentWithDates(Integer account_id, Timestamp start, Timestamp end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubTeam> getSubTeamsInTopTeam(String topTeamname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean creatSubTeam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean createTopTeam() {
		// TODO Auto-generated method stub
		return null;
	}

	
	//insert a new studentAccount into the studentAccount table
	@Override
	public Boolean createStudentAccount(String firstname, String lastname, String email, String password, String schoolID) {
		return executeTransaction(new Transaction<Boolean>() {

			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;//for inserting
				PreparedStatement stmt2 = null;//for getting studentAccount back
				PreparedStatement stmt3 = null;//for updating
				
				ResultSet resultSet1 = null;//resultset for stmt2
				
				Integer account_id = -1;
				
				try {
					conn.setAutoCommit(false);
					//prepare SQL statement to insert a new studentAccount
					stmt1 = conn.prepareStatement(
							"insert into studentAccounts " +
							"  (account_id_2, firstname, lastname, email, password, schoolID, status) "	+	
							"  values(?,?,?,?,?,?,?) "
					);
					
					stmt1.setInt(1, -1);//use a dud value until update stage
					stmt1.setString(2, firstname);
					stmt1.setString(3, lastname);
					stmt1.setString(4, email);
					stmt1.setString(5, password);
					stmt1.setString(6, schoolID);
					stmt1.setBoolean(7, Boolean.FALSE);//auto false upon creation
					
					stmt1.executeUpdate();//execute the update
					System.out.println("new account created with dud_id");
					
					//Retrieve the newly inserted student's account_id_1
					stmt2 = conn.prepareStatement(
						"select account_id_1 " +
						" 	from studentAccounts " +	
						" 	where schoolID = ? " +
						"   and firstname = ? " +
						"   and lastname = ?"
					);
					stmt2.setString(1, schoolID);
					stmt2.setString(2, firstname);
					stmt2.setString(3, lastname);
					
					//execute the query
					resultSet1 = stmt2.executeQuery();
					
					//get the result
					if (resultSet1.next()) {
						account_id = resultSet1.getInt(1);
					}
					else {
						System.out.println("cant find studentAccount");
						return false;
					}
					
					//prepare update to studentAccount's account_id_2
					stmt3 = conn.prepareStatement( 
						"update studentAccounts"
						+ " set account_id_2 = ? "
						+ " where account_id_1 = ? "	
					);
					stmt3.setInt(1, account_id);
					stmt3.setInt(2, account_id);
					
					//execute update
					stmt3.executeUpdate();
					
					System.out.println("Account for <" + firstname + "> created");
					
					conn.commit();
					return true;
				}finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(resultSet1);
				}				
			}
		});
	}

	@Override
	public Boolean creatAdminAccount(String firstname, String lastname, String email, String password, String schoolID) {
		return executeTransaction(new Transaction<Boolean>() {

			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;//for inserting
			
				try {
					conn.setAutoCommit(false);
					//prepare SQL statement to insert a new adminAccount
					stmt1 = conn.prepareStatement(
							"insert into adminAccounts " +
							"  (firstname, lastname, email, password, schoolID) "	+	
							"  values(?,?,?,?,?) "
					);
					
					stmt1.setString(1, firstname);
					stmt1.setString(2, lastname);
					stmt1.setString(3, email);
					stmt1.setString(4, password);
					stmt1.setString(5, schoolID);
					
					stmt1.executeUpdate();//execute the update
					
					System.out.println("AdminAccount for <" + firstname + "> created");
					
					conn.commit();
					return true;
				}finally {
					DBUtil.closeQuietly(stmt1);
				}				
			}
		});
	}

	@Override
	public AdminAccount getAdminAccountWithEmailandSchoolID(String email, String schoolID) {
		return executeTransaction(new Transaction<AdminAccount>() {
			@Override
			public AdminAccount execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;//for select
				ResultSet	resultSet1 = null;//result from select
				AdminAccount adminAccount = null;//for return
				
				try {
					//prepare SQL statement to select
					stmt1 = conn.prepareStatement(
						"select adminAccounts.* "
						+ "  from adminAccounts"
						+ "  where adminAccounts.email = ?"
						+ "  and adminAccounts.schoolID = ?"
					);
							
					//insert values into prepared statement
					stmt1.setString(1, email);
					stmt1.setString(2, schoolID);
					
					//execute query and get result set
					resultSet1 = stmt1.executeQuery();
					
					if(resultSet1.next()) {//resultSet not empty
						//populate return model
						adminAccount = new AdminAccount();//new account
						adminAccount.setAccountID(resultSet1.getInt(1));//column 1 = account_id_1
						adminAccount.setFirstname(resultSet1.getString(2));//column 2 = firstname
						adminAccount.setLastname(resultSet1.getString(3));//column 3 = lastname
						adminAccount.setEmail(resultSet1.getString(4));//column 4 = email
						adminAccount.setPassword(resultSet1.getString(5));//column 5 = password
						adminAccount.setSchoolID(resultSet1.getString(6));//column 6 = schoolID
					}
					return adminAccount; //will either be fully populated or null
				}finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(resultSet1);
				}
			}
		});
	}
}