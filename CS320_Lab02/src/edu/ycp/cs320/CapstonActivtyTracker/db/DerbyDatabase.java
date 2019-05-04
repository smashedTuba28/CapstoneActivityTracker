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
import java.util.Date;
import java.util.List;

import edu.ycp.cs320.CapstoneActivityTracker.model.Account;
import edu.ycp.cs320.CapstoneActivityTracker.model.AdminAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.Room;
import edu.ycp.cs320.CapstoneActivityTracker.model.RoomEvent;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeam;
import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeamStudent;
import edu.ycp.cs320.CapstoneActivityTracker.model.TeamRoom;
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
	
	// retrieves Roomevent information from query result set modified from lab06
	private void loadRoomEvent(RoomEvent event, ResultSet resultSet, int index) throws SQLException {
		event.setRoomEventID(resultSet.getInt(index++));
		event.setAccountID(resultSet.getInt(index++));
		event.setRoomID(resultSet.getInt(index++));
		event.setStartTime(new Date(resultSet.getTimestamp(index++).getTime()));
		event.setEndTime(new Date(resultSet.getTimestamp(index++).getTime()));
		event.setFlag(resultSet.getBoolean(index++));
		event.setLognote(resultSet.getString(index++));
		
	}
	
	//retrieves subTeamStudent information from query result set
	private void loadSubTeamStudent(SubTeamStudent st, ResultSet resultSet, int index) throws SQLException {
		st.setTeamID(resultSet.getInt(index++));
		st.setStudentID(resultSet.getInt(index++));
	}
	
	//retrieves subTeam information from query result set
	private void loadSubTeam(SubTeam subTeam, ResultSet resultSet, int index) throws SQLException {
		subTeam.setTeamID(resultSet.getInt(index++));
		subTeam.setTeamname(resultSet.getString(index++));
		subTeam.setTopTeamID(resultSet.getInt(index++));
	}
	
	//retrieves adminAccount information from query result set
	private void loadAdminAccount(AdminAccount admin, ResultSet resultSet, int index) throws SQLException{
		admin.setAccountID(resultSet.getInt(index++));
		admin.setFirstname(resultSet.getString(index++));
		admin.setLastname(resultSet.getString(index++));
		admin.setEmail(resultSet.getString(index++));
		admin.setPassword(resultSet.getString(index++));
		admin.setSchoolID(resultSet.getString(index++));
	}
	
	//retrives studentAccount info from query 
	//index must start at 2
	private void loadStudentAccount(StudentAccount student, ResultSet resultSet, int index) throws SQLException{
		student.setAccountID(resultSet.getInt(index++));
		student.setFirstname(resultSet.getString(index++));
		student.setLastname(resultSet.getString(index++));
		student.setEmail(resultSet.getString(index++));
		student.setPassword(resultSet.getString(index++));
		student.setSchoolID(resultSet.getString(index++));
		student.setStatus(resultSet.getBoolean(index++));
	}
	
 /* 
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
				PreparedStatement stmt8 = null;
				PreparedStatement stmt9 = null;
				
				try {
		///////////////////////////////////////////////
		//////////////rooms table creation//////////////
		///////////////////////////////////////////////			
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
					
			/////////////////////////////////////		
			///////////accounts table////////////
			/////////////////////////////////////		
					stmt9 = conn.prepareStatement(
						"create table accounts(" +
								"	account_id_1 integer primary key " +
								"		generated always as identity (start with 1, increment by 1), " +
								"   account_id_2 integer," +
								"	firstname varchar(20)," +
								"	lastname varchar(20)," +
								"   email varchar(35)," + 
								" 	password varchar(100)," +
								"	schoolID varchar(9)," +
								"   faculty boolean" +
								")"
					);
					stmt9.executeUpdate();
					System.out.println("studentAccounts table created");
					
		/////////////////////////////////////////////
		//////////// studentAccounts table//////////
		/////////////////////////////////////////////
					stmt2 = conn.prepareStatement(
							"create table studentAccounts(" +
							"	studentAccount_id_1 integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"   studentAccount_id_2 integer," +
							"	account_id_1 integer constraint account_id_1 references accounts, " +
							"   status boolean" +
							")"
					);
					stmt2.executeUpdate();
					System.out.println("studentAccounts table created");					
					
			////////////////////////////////////////		
			///////////roomEvents table////////////
			////////////////////////////////////////		
					stmt3 = conn.prepareStatement(
							"create table roomEvents (" +
							"   roomEvent_id integer primary key " +
							"      	generated always as identity (start with 1, increment by 1)," +
							"	studentAccount_id_1 integer constraint studentAccount_id_1 references studentAccounts," +
							"	room_id_2 integer constraint room_id_2 references rooms," + 
							"	startTime timestamp," + 
							"	endTime timestamp," +
							"   flag boolean," +
							"	lognote varchar(400)" +
							")"
					);
					stmt3.executeUpdate();
					System.out.println("roomEvents table created");					
							
			////////////////////////////////////		
			//////////topTeams table///////////
			////////////////////////////////////		
					stmt4 = conn.prepareStatement(
							"create table topTeams (" +
							"	topTeam_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +									
							"	teamname varchar(40)" +
							")"
					);
					stmt4.executeUpdate();
					System.out.println("topTeams table created");	
					
			/////////////////////////////////////		
			/////////subTeams table//////////////
			/////////////////////////////////////
					stmt5 = conn.prepareStatement(
							"create table subTeams (" +
							"	subTeam_id_1 integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"   subTeam_id_2 integer," +
							"	teamname varchar(40)," +
							" 	topTeam_id integer constraint topTeam_id references topTeams" +
							")"
					);
					stmt5.executeUpdate();
					System.out.println("subTeam table created");
					
			/////////////////////////////////////////	
			///////////adminAcccounts table//////////
			/////////////////////////////////////////		
					stmt6 = conn.prepareStatement(
							"create table adminAccounts(" +
							"	adminAccount_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	account_id_2 integer constraint account_id_2 references accounts " +
							")"
					);
					stmt6.executeUpdate();
					System.out.println("adminAccount table created");
					
				
			//////////////////////////////////////		
			////////////////teamRooms/////////////
			//////////////////////////////////////		
					stmt7 = conn.prepareStatement(
							"create table teamRooms (" +
									"	subTeam_id_1 integer constraint subTeam_id_1 references subTeams, " +
									"	room_id_1 integer constraint room_id_1 references rooms " +
									")"
					);
					stmt7.executeUpdate();
					System.out.println("teamRooms table created");
					
		
			//////////////////////////////////////		
			//////////subTeamStudents Table///////
			//////////////////////////////////////		
					stmt8 = conn.prepareStatement(
							"create table subTeamStudents (" +
									"	subTeam_id_2 integer constraint subTeam_id_2 references subTeams, " +
									"	studentAccount_id_2 integer constraint studentAccount_id_2 references studentAccounts " +
									")"
					);
					stmt8.executeUpdate();
					System.out.println("subTeamStudents table created");
					
					System.out.println("ALL TABLES CREATED\n");
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
					DBUtil.closeQuietly(stmt7);
					DBUtil.closeQuietly(stmt8);
					DBUtil.closeQuietly(stmt9);
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
				List<Account> accountList;
				List<Room> roomList;
				List<TopTeam> topTeamList;
				List<SubTeam> subTeamList;
				List<TeamRoom> teamRoomList;
				List<SubTeamStudent> subTeamStudentsList;
				
				try {
					roomEventList     	= InitialData.getRoomEvents();
					accountList 		= InitialData.getAccounts();
					roomList			= InitialData.getRooms();
					topTeamList			= InitialData.getTopTeams();
					subTeamList			= InitialData.getSubTeams();
					teamRoomList		= InitialData.getTeamRooms();
					subTeamStudentsList	= InitialData.getSubTeamStudents();
					System.out.println("Initial Data Loaded\n");
					
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				} catch (ParseException e) {
					//if you receive this error something has gone wrong with reading the timestamps
					//from the CSV for roomEvents
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
				PreparedStatement insertAccount			= null;
				
				try {
					System.out.println("Populating Tables...");
					
					insertRoom = conn.prepareStatement("insert into rooms (room_id_2, number, name) values (?, ?, ?)");
					for (Room room : roomList) {
						insertRoom.setInt(1, room.getRoomID());//second id just copying from primary key to use as a second foreign key
						insertRoom.setInt(2, room.getRoomNumber());
						insertRoom.setString(3, room.getRoomName());
						insertRoom.addBatch();
					}
					insertRoom.executeBatch();
					System.out.println("Rooms table populated");
					
					
					insertAccount = conn.prepareStatement("insert into accounts (account_id_2, firstname, lastname, email, password, schoolID, faculty) values (?,?,?,?,?,?,?)");
					for(Account account : accountList) {
						insertAccount.setInt(1, account.getAccountID());
						insertAccount.setString(2, account.getFirstname());
						insertAccount.setString(3,  account.getLastname());
						insertAccount.setString(4,  account.getEmail()); 
						insertAccount.setString(5,  account.getPassword());
						insertAccount.setString(6,  account.getSchoolID());
						insertAccount.setBoolean(7,  account.getFaculty());
						insertAccount.addBatch();
					}
					insertAccount.executeBatch();
					System.out.println("Accounts table populated");
					
					insertStudent = conn.prepareStatement("insert into studentAccounts (status, studentAccount_id_2, account_id_1) values (?,?,?)");
					for (Account account : accountList) {
						Integer id = 1;
						if(!account.getFaculty()) {//only if account is a student
							insertStudent.setBoolean(1, account.getFaculty());//always false upon account creation
							insertStudent.setInt(2, id++);//should match primary key starts at one increments after every insertion
							insertStudent.setInt(3, account.getAccountID());//foreign key from account table
							insertStudent.addBatch();
						}
					}
					insertStudent.executeBatch();
					System.out.println("StudentAccounts table populated");					
					
					insertAdmin = conn.prepareStatement("insert into adminAccounts (account_id_2) values (?)");
					for (Account account : accountList) {
						if(account.getFaculty()) {//if faculty true
							insertAdmin.setInt(1, account.getAccountID());//foreign key to accounts
							insertAdmin.addBatch();
						}
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
					
					
					insertRoomEvent = conn.prepareStatement("insert into roomEvents (studentAccount_id_1, room_id_2, startTime, endTime, lognote, flag) values (?,?,?,?,?,?) ");
					for (RoomEvent event : roomEventList) {
						insertRoomEvent.setInt(1, event.getAccountID());
						insertRoomEvent.setInt(2, event.getRoomID());
						insertRoomEvent.setTimestamp(3, new Timestamp(event.getStartTime().getTime()));
						insertRoomEvent.setTimestamp(4, new Timestamp(event.getEndTime().getTime()));
						insertRoomEvent.setString(5, event.getLognote());
						insertRoomEvent.setBoolean(6, Boolean.FALSE);
						insertRoomEvent.addBatch();
					}
					insertRoomEvent.executeBatch();
					System.out.println("RoomEvents Table populated");
					
									
					insertSubTeam = conn.prepareStatement("insert into subTeams (subTeam_id_2, teamname, topTeam_id) values (?,?,?)");
					for (SubTeam sub : subTeamList) {
						insertSubTeam.setInt(1, sub.getTeamID());
						insertSubTeam.setString(2, sub.getTeamname());
						insertSubTeam.setInt(3, sub.getTopTeamID());
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
					
					insertSubTeamStudents = conn.prepareStatement("insert into subTeamStudents (studentAccount_id_2, subTeam_id_2) values (?,?)");
					for(SubTeamStudent subStudent : subTeamStudentsList) {
						insertSubTeamStudents.setInt(1, subStudent.getStudentID());
						insertSubTeamStudents.setInt(2, subStudent.getTeamID());
						insertSubTeamStudents.addBatch();
					}
					insertSubTeamStudents.executeBatch();
					System.out.println("subTeamStudents table populated");
					
					System.out.println("ALL TABLES POPULATED\n");
					return true;
				} finally {
					DBUtil.closeQuietly(insertStudent);
					DBUtil.closeQuietly(insertAdmin);
					DBUtil.closeQuietly(insertRoom);
					DBUtil.closeQuietly(insertRoomEvent);
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
		
		System.out.println("Capstone Activity Tracker DB successfully initialized!");
	}

	
	//Finds and returns studentAccount for login
	@Override
	public StudentAccount verifyStudentAccount(String email, String password) {
		return executeTransaction(new Transaction<StudentAccount>() { 
			@Override
			public StudentAccount execute(Connection conn) throws SQLException {
				PreparedStatement stmt 	= null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet 	= null;
				ResultSet resultSet2	= null;
				StudentAccount studentAccount =  null;
				
				try {
					stmt = conn.prepareStatement(
						"select accounts.* "
						+ "	from accounts "
						+ " where accounts.email = ?"
						+ " and accounts.password = ?"
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
						studentAccount.setFaculty(resultSet.getBoolean(8));
					
						//general account info obtained successfully 
						//now get the specific studentAccount info from studentAccount table
					
						
						stmt2 = conn.prepareStatement(
								" select studentAccounts.* "
								+ " from studentAccounts, accounts"
								+ " where accounts.account_id_1 = studentAccounts.account_id_1"
								+ " and accounts.account_id_1 = ? "
						);
					
						stmt2.setInt(1, studentAccount.getAccountID());	
						resultSet2 = stmt2.executeQuery();
						
						if (resultSet2.next()) {
							studentAccount.setStudentAccountID(resultSet2.getInt(1));
							studentAccount.setStatus(resultSet2.getBoolean(4));
						}
						else {
							throw new ClassFormatError("No Student Data for Existing Account. Unable to Create studentAccount class model.");
						}
					}
					return studentAccount;//return either null or populated model
				}finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(resultSet2);
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
				PreparedStatement stmt2 = null;
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				StudentAccount studentAccount =  null;
				
				try {
					stmt = conn.prepareStatement(
						"select accounts.* "
						+ "	from accounts "
						+ " where accounts.account_id_1 = ?"
					);
					
					stmt.setInt(1, account_id);
					resultSet = stmt.executeQuery();
					
					if(resultSet.next()) {		
						studentAccount = new StudentAccount();//create new model
						// result set to populate model
						studentAccount.setAccountID(resultSet.getInt(1));//column 1 = account_id_1
						//column 2 = account_id_2 : unused outside of schema and always equals account_id_1
						studentAccount.setFirstname(resultSet.getString(3));//column 3 = firstname
						studentAccount.setLastname(resultSet.getString(4));//column 4 = lastname
						studentAccount.setEmail(resultSet.getString(5));//column 5 = email
						studentAccount.setPassword(resultSet.getString(6));//column 6 = password
						studentAccount.setSchoolID(resultSet.getString(7));//column 7 = schoolID
						studentAccount.setFaculty(resultSet.getBoolean(8));//column 8 = status
					
						//obtained general info
						//now get studentAccount class info
						
						stmt2 = conn.prepareStatement(
								" select studentAccounts.* "
								+ " from studentAccounts, accounts "
								+ " where studentAccounts.account_id_1 = accounts.account_id_1 "
								+ " and accounts.account_id_1 = ?"
						);
						
						stmt2.setInt(1,  studentAccount.getAccountID());
						resultSet2 = stmt2.executeQuery();
						
						if(resultSet2.next()) {//studentAccount info found
							studentAccount.setStudentAccountID(resultSet2.getInt(1));
							studentAccount.setStatus(resultSet2.getBoolean(4));
						}
						else {
							throw new ClassFormatError("No Student Data for Existing Account. Unable to Create studentAccount class model.");
						}
					}
					return studentAccount;//return either null or populated model
				}finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(resultSet2);
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
						studentAccount.setAccountID(resultSet1.getInt(1));//column 1 = studentAccount_id_1
																			//column 2 = studentAccount_id_2 : unused outside of schema and always equals studentAccount_id_1
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
		return executeTransaction(new Transaction<AdminAccount>() {
			@Override
			public AdminAccount execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
					
				ResultSet resultSet1 = null;
					
				AdminAccount admin = null;
					
				try {
					//prepare SQL select
					stmt1 = conn.prepareStatement(
						" select adminAccounts.* "
						+ " from adminAccounts "
						+ " where adminAccounts.adminAccount_id = ?"	
					);
					stmt1.setInt(1, adminAccount_id);
					resultSet1 = stmt1.executeQuery();
					
					if(resultSet1.next()) {
						admin = new AdminAccount();
						loadAdminAccount(admin, resultSet1, 1);
					}
					return admin;
				}finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}

	@Override
	public AdminAccount verifyAdminAccount(String email, String password) {
		return executeTransaction(new Transaction<AdminAccount>() {
			@Override
			public AdminAccount execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				AdminAccount adminAccount =  null;
				
				try {
					stmt = conn.prepareStatement(
						"select adminAccounts.* "
						+ "	from adminAccounts "
						+ " where adminAccounts.email = ?"
						+ " and adminAccounts.password = ?"
					);
					
					stmt.setString(1, email);
					stmt.setString(2, password);
					resultSet = stmt.executeQuery();
					
					if(resultSet.next()) {		
						adminAccount = new AdminAccount();//create new model
						// result set to populate model
						adminAccount.setAccountID(resultSet.getInt(1));
						adminAccount.setFirstname(resultSet.getString(2));
						adminAccount.setLastname(resultSet.getString(3));
						adminAccount.setEmail(resultSet.getString(4));
						adminAccount.setPassword(resultSet.getString(5));
						adminAccount.setSchoolID(resultSet.getString(6));
					}
					
					return adminAccount;//return either null or populated model
				}finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
			}
		});	
	}

	@Override
	public List<Room> getRoomsForASubTeam(Integer subTeam_id) {
		return executeTransaction(new Transaction<List<Room>>() {

			@Override
			public List<Room> execute(Connection conn) throws SQLException {
		
				PreparedStatement stmt1 = null;
				
				ResultSet resultSet1 = null;
				
				List<Room> roomList = new ArrayList<Room>();
				
				try {//prepare SQL 
					stmt1 = conn.prepareStatement(
							" select Rooms.* "
							+ "		from Rooms, teamRooms, subTeams "
							+ " 	where teamRooms.subTeam_id_1 = subTeams.subTeam_id_1 "
							+ " 	and teamRooms.room_id_1 = Rooms.room_id_2 "
							+ " 	and subTeam_id_2 = ? "		
					);		
					
					stmt1.setInt(1, subTeam_id);
					resultSet1 = stmt1.executeQuery();
					
					
					
					//verify a returned result
					while(resultSet1.next()) {
						Room room = new Room();
						room.setRoomID(resultSet1.getInt(1));
						room.setRoomNumber(resultSet1.getInt(3));
						room.setRoomName(resultSet1.getString(4));
						roomList.add(room);
					}

					return roomList;
				}finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(resultSet1);
				}				
			}
		});
	}

	@Override
	public List<RoomEvent> getRoomEventsForStudentWithDates(Integer account_id, Timestamp start, Timestamp end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubTeam> getSubTeamsInTopTeam(String topTeamname) {
		return executeTransaction(new Transaction<List<SubTeam>>() {

			@Override
			public List<SubTeam> execute(Connection conn) throws SQLException {
		
				PreparedStatement stmt1 = null;
				
				ResultSet resultSet1 = null;
				
				List<SubTeam> subTeamList = new ArrayList<SubTeam>();
				
				try {//prepare SQL 
					stmt1 = conn.prepareStatement(
							" select subTeams.* "
							+ "   	from subTeams, topTeams "
							+ " 	where subTeams.topTeam_id = topTeams.topTeam_id "
							+ " 	and topTeams.teamname = ? "		
					);		
					stmt1.setString(1, topTeamname);
					resultSet1 = stmt1.executeQuery();
					
					//verify a returned result
					while(resultSet1.next()) {
						
						SubTeam subTeam = new SubTeam();
						loadSubTeam(subTeam, resultSet1, 2);
						subTeamList.add(subTeam);
					}

					return subTeamList;
				}finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(resultSet1);
				}				
			}
		});
	}

	@Override
	public SubTeam getSubTeamWithTeamname(String teamname) {
		return executeTransaction(new Transaction<SubTeam>() {
			@Override
			public SubTeam execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;//for select
				ResultSet	resultSet1 = null;//result from select
				SubTeam subTeam = null;//for return
				
				try {
					//prepare SQL statement to select
					stmt1 = conn.prepareStatement(
						"select subTeams.* "
						+ "  from subTeams"
						+ "  where subTeams.teamname = ?"
					);
					
					//insert values into prepared statement
					stmt1.setString(1, teamname);
					
					//execute query and get result set
					resultSet1 = stmt1.executeQuery();
					
					if(resultSet1.next()) {//resultSet not empty
						//populate return model
						subTeam = new SubTeam();//new subTeam
						loadSubTeam(subTeam, resultSet1, 2);
					}
					//will either be fully populated or null
					return subTeam;
				}finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(resultSet1);
				}
			}
		});
	}
	
	@Override
	public Boolean createSubTeam(String name, Integer topTeamID) {		
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;//for inserting
				PreparedStatement stmt2 = null;//for getting subTeam back
				PreparedStatement stmt3 = null;//for updating
				
				ResultSet resultSet1 = null;//resultset for stmt2
				
				Integer subTeam_id = -1;
				
				try {
					conn.setAutoCommit(false);
					//prepare SQL statement to insert a new subTeam
					stmt1 = conn.prepareStatement(
							"insert into subTeams " +
							"  (subTeam_id_2, teamname, topTeam_id) "	+	
							"  values(?,?,?) "
					);
					
					stmt1.setInt(1, -1);//use a dud value until update stage
					stmt1.setString(2, name);
					stmt1.setInt(3, topTeamID);
					
					stmt1.executeUpdate();//execute the update
					System.out.println("new subTeam created with dud_id");
					
					//Retrieve the newly inserted student's subTeam_id_1
					stmt2 = conn.prepareStatement(
						"select subTeam_id_1 " +
						" 	from subTeams " +	
						" 	where teamname = ? " +
						"   and topTeam_id = ? " 
					);
					stmt2.setString(1, name);
					stmt2.setInt(2, topTeamID);
					
					//execute the query
					resultSet1 = stmt2.executeQuery();
					
					//get the result
					if (resultSet1.next()) {
						//getting subTeam_ID from the resultSet
						subTeam_id = resultSet1.getInt(1);
					}
					else {
						System.out.println("cant find subTeam");
						return false;
					}
					
					//prepare update to subTeam's subTeam_id_2 from subTeam_id_1
					stmt3 = conn.prepareStatement( 
						"update subTeams"
						+ " set subTeam_id_2 = ? "
						+ " where subTeam_id_1 = ? "	
					);
					stmt3.setInt(1, subTeam_id);
					stmt3.setInt(2, subTeam_id);
					
					//execute update
					stmt3.executeUpdate();
					
					System.out.println("subTeam named <" + name + "> created");
					
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
	public Boolean deleteSubTeam(Integer subTeam_id) {
		return executeTransaction(new Transaction<Boolean>() {
			/*
			 * get rid of subTeam
			 * keep students, lognotes should stay with students
			 * delete entry in subTeamStudents
			 * delete entry in subTeams
			 */
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				
				ResultSet resultSet1 = null;
				ResultSet resultSet2 = null;
				
				try {
					//first verify subTeam that needs to be deleted
					stmt1 = conn.prepareStatement( 
							"select subTeams.* "
							+ " from subTeams"
							+ " where subTeams.subTeam_id_1 = ?"
					); 
					
					stmt1.setInt(1, subTeam_id);
					resultSet1 = stmt1.executeQuery();
		
					//make sure something was returned
					if(!resultSet1.next()) {
						//wasn't found
						System.out.println("SubTeam <" + subTeam_id + "> wasn't found");
						return false;
					}
					//at this point the subTeam was found to exist
					
					//now get all the students associated with the subTeam
					stmt2 = conn.prepareStatement(
						"select subTeamStudents.studentAccount_id_2 "
						+ " from subTeamStudents,subTeams"
						+ " where subTeamStudents.subTeam_id_2 = subTeam_id_1"
						+ " and subTeams.subTeam_id_1 = ?"	
					);
					stmt2.setInt(1, subTeam_id);
					resultSet2 = stmt2.executeQuery();
		
					//assemble list of studentAccount_id_2's from the join table
					List<Integer> ids = new ArrayList<Integer>();
					
					//if the resultSet does not have a first value then all statements are not necessary that follow
					if(resultSet2.next()) {
						ids.add(resultSet2.getInt(1));
						
						while(resultSet2.next()) {
							ids.add(resultSet2.getInt(1));
						}



						//delete relation entry between students and subTeam from table in subTeamStudents
						for (int i = 0; i < ids.size(); i++) {
							stmt3 = conn.prepareStatement(
									" delete from subTeamStudents "
											+ "where subTeamStudents.studentAccount_id_2 = ?"	
									);	

							stmt3.setInt(1, ids.get(i));
							stmt3.executeUpdate();

							DBUtil.closeQuietly(stmt3);//close so loop can reopen if needed
						}
					}
						//now delete the subTeam
						stmt4 = conn.prepareStatement(
								" delete from subTeams "
										+ " where subTeams.subTeam_id_1 = ?"	
								);
						stmt4.setInt(1, subTeam_id);
						stmt4.executeUpdate();
					
					System.out.println("SubTeam <" + subTeam_id + "> deleted from Database");
					
					return true;
				}finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(resultSet2);
				}
			}
	});
	}

	@Override
	public TopTeam getTopTeamWithTeamname(String topTeamname) {
		return executeTransaction(new Transaction<TopTeam>() {
			@Override
			public TopTeam execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;//for select
				ResultSet	resultSet1 = null;//result from select
				TopTeam topTeam = null;//for return
				
				try {
					//prepare SQL statement to select
					stmt1 = conn.prepareStatement(
						"select topTeams.* "
						+ "  from topTeams"
						+ "  where topTeams.teamname = ?"
					);
					
					//insert values into prepared statement
					stmt1.setString(1, topTeamname);
					
					//execute query and get result set
					resultSet1 = stmt1.executeQuery();
					
					if(resultSet1.next()) {//resultSet not empty
						//populate return model
						topTeam = new TopTeam();//new topTeam
						topTeam.setTeamID((resultSet1.getInt(1)));
						topTeam.setTeamname(resultSet1.getString(2));
					}
					//will either be fully populated or null
					return topTeam;
				}finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(resultSet1);
				}
			}
	});
	}
	
	@Override
	public Boolean createTopTeam(String topTeamname) {
		return executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			PreparedStatement stmt1 = null;//for inserting
			
			try {
				conn.setAutoCommit(false);
				//prepare SQL statement to insert a new topTeam
				stmt1 = conn.prepareStatement(
						"insert into topTeams " +
						"  (teamname) "	+	
						"  values(?) "
				);
				
				stmt1.setString(1, topTeamname);
				
				stmt1.executeUpdate();//execute the update
				System.out.println("new topTeam created with autogenerated ID");
				System.out.println("topTeam named <" + topTeamname + "> created");
				
				conn.commit();
				return true;
			}finally {
				DBUtil.closeQuietly(stmt1);
			}				
		}
	});
	}
	
	@Override
	public Boolean deleteTopTeam(Integer topTeam_id) {
		return executeTransaction(new Transaction<Boolean>() {

			/*
			 * use topTeam_id to verify if it exists
			 * delete connections to subTeams
			 * delete SubTeams using method call
			 * delete TopTeam at the very end
			 */

			@Override
			public Boolean execute(Connection conn) throws SQLException {

				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;

				ResultSet resultSet1 = null;
				ResultSet resultSet2 = null;

				try {
					//first verify topTeam that needs to be deleted
					stmt1 = conn.prepareStatement( 
							"select topTeams.* "
									+ " from topTeams"
									+ " where topTeams.topTeam_id = ?"
							); 

					stmt1.setInt(1, topTeam_id);
					resultSet1 = stmt1.executeQuery();

					//make sure something was returned
					if(!resultSet1.next()) {
						//wasn't found
						System.out.println("SubTeam <" + topTeam_id + "> wasn't found");
						return false;
					}
					//at this point the topTeam was found to exist

					//now get all the students associated with the topTeam
					stmt2 = conn.prepareStatement(
							"select subTeamStudents.studentAccount_id_2 "
									+ " from subTeamStudents, subTeams "
									+ " where subTeams.topTeam_id = ? "	
									+ " and subTeamStudents.subTeam_id_2 = subTeams.subTeam_id_1 "	
							);

					stmt2.setInt(1, topTeam_id);
					resultSet2 = stmt2.executeQuery();

					//assemble list of studentAccount_id_2's from the join table
					List<Integer> ids = new ArrayList<Integer>();

					//if the resultSet does not have a first value then all statements are not necessary that follow
					if(resultSet2.next()) {
						ids.add(resultSet2.getInt(1));

						while(resultSet2.next()) {
							ids.add(resultSet2.getInt(1));
						}

						//delete relation entry between students and topTeam from table in topTeamStudents
						for (int i = 0; i < ids.size(); i++) {
							stmt3 = conn.prepareStatement(
									" delete from topTeamStudents "
											+ "where topTeamStudents.studentAccount_id_2 = ?"	
									);	

							stmt3.setInt(1, ids.get(i));
							stmt3.executeUpdate();

							DBUtil.closeQuietly(stmt3);//close so loop can reopen if needed
						}
					}
					
					//now delete the subTeams
					stmt4 = conn.prepareStatement(
							" delete from subTeams "
									+ " where subTeams.topTeam_id = ?"	
							);
					stmt4.setInt(1, topTeam_id);
					stmt4.executeUpdate();
					
					DBUtil.closeQuietly(stmt4);

					System.out.println("SubTeams deleted from Database");

					//now delete the topTeam
					stmt5 = conn.prepareStatement(
							" delete from topTeams "
									+ " where topTeams.topTeam_id = ?"	
							);
					stmt5.setInt(1, topTeam_id);
					stmt5.executeUpdate();

					return true;
				}finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(resultSet2);
				}
			}

		});
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
							"  (studentAccount_id_2, firstname, lastname, email, password, schoolID, status) "	+	
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
					
					//Retrieve the newly inserted student's studentAccount_id_1
					stmt2 = conn.prepareStatement(
						"select studentAccount_id_1 " +
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
					
					//prepare update to studentAccount's studentAccount_id_2
					stmt3 = conn.prepareStatement( 
						"update studentAccounts"
						+ " set studentAccount_id_2 = ? "
						+ " where studentAccount_id_1 = ? "	
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
						adminAccount.setAccountID(resultSet1.getInt(1));//column 1 = studentAccount_id_1
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

	@Override
	public Boolean deleteStudentAccount(Integer account_id) {
		return executeTransaction(new Transaction<Boolean>() {

			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;
				
				ResultSet resultSet1 = null;
				ResultSet resultSet2 = null;
				ResultSet resultSet4 = null;
				
				try {
					//first verify studentAccount that needs to be deleted
					stmt1 = conn.prepareStatement( 
							"select studentAccounts.* "
							+ " from studentAccounts"
							+ " where studentAccounts.studentAccount_id_1 = ?"
					); 
					
					stmt1.setInt(1, account_id);
					resultSet1 = stmt1.executeQuery();
		
					//make sure something was returned
					if(!resultSet1.next()) {
						//wasn't found
						System.out.println("StudentAccount <" + account_id + "> wasn't found");
						return false;
					}
					//at this point the studentAccount was found to exist
					
					//now get all the roomEvents associated with the student
					stmt2 = conn.prepareStatement(
						"select roomEvents.roomEvent_id "
						+ " from roomEvents, studentAccounts "
						+ " where roomEvents.studentAccount_id_1 = studentAccounts.studentAccount_id_1"
						+ " and studentAccounts.studentAccount_id_1 = ?"	
					);
					stmt2.setInt(1, account_id);
					resultSet2 = stmt2.executeQuery();
		
					//assemble list of roomEvents
					List<Integer> eventIDList = new ArrayList<Integer>();
					
					while(resultSet2.next()) {
						Integer id = resultSet2.getInt(1);
						eventIDList.add(id);
					}
		
					//delete roomEvents from list
					for (int i = 0; i < eventIDList.size(); i++) {
						stmt3 = conn.prepareStatement(
								" delete from roomEvents "
								+ "where roomEvents.roomEvent_id = ?"	
						);	
						
						stmt3.setInt(1, eventIDList.get(i));
						stmt3.executeUpdate();
						
						DBUtil.closeQuietly(stmt3);//close so loop can reopen if needed
					}
					
					//get entries in all join tables
					//because derby can't delete something with active foreign key
					stmt4 = conn.prepareStatement(
						" select subTeamStudents.* "
						+ " from subTeamStudents, studentAccounts"
						+ " where subTeamStudents.studentAccount_id_2 = studentAccounts.studentAccount_id_2 "
						+ " and studentAccounts.studentAccount_id_1 = ?"				
					);
					stmt4.setInt(1, account_id);
					resultSet4 = stmt4.executeQuery();
					
					//assemble list of join table entries 
					List<SubTeamStudent> stList = new ArrayList<SubTeamStudent>();
					
					while(resultSet4.next()) {
						SubTeamStudent st = new SubTeamStudent();
						loadSubTeamStudent(st, resultSet4, 1);
						stList.add(st);
					}
					
					for(int i = 0; i<stList.size(); i++) {
						//prepare to delete SubTeamStudents entires
						stmt5 = conn.prepareStatement(
							" delete from subTeamStudents "
							+ " where subTeam_id_2 = ?"
							+ " and studentAccount_id_2 = ?"	
						);		
						stmt5.setInt(1, stList.get(i).getTeamID());
						stmt5.setInt(2, stList.get(i).getStudentID());
						stmt5.executeUpdate();
						
						DBUtil.closeQuietly(stmt5);
						
					}
					
					//now delete the studentAccount
					stmt6 = conn.prepareStatement(
						" delete from studentAccounts "
						+ " where studentAccounts.studentAccount_id_1 = ?"	
					);
					stmt6.setInt(1, account_id);
					stmt6.executeUpdate();
					
					System.out.println("Student Account <" + account_id + "> deleted from Database");
		
					return true;
				}finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt6);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(resultSet4);
				}
			}
		});
	}

	@Override
	public Boolean deleteAdminAccount(Integer adminAccount_id) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
			
				try {
					//prepare SQL statement to delete 
					stmt1 = conn.prepareStatement(
						"delete"
						+ " from adminAccounts "
						+ " where adminAccounts.adminAccount_id = ?"
					);
					
					stmt1.setInt(1, adminAccount_id);
					stmt1.executeUpdate();
					
					return true;
				}finally {
					DBUtil.closeQuietly(stmt1);
				}		
			}
		});	
	}

	@Override
	public boolean createRoomEventForStudentAccountWithID(Integer account_id, Integer room_id, Timestamp start) {
		return executeTransaction(new Transaction<Boolean>() {

			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				
				try {
					//prepare SQL statement to create new RoomEvent
					stmt1 = conn.prepareStatement(
						" insert into roomEvents "
						+ " (studentAccount_id_1, room_id_2, startTime, endTime, flag, lognote) "
						+ " values (?,?,?,?,?,?) "	
					);
					stmt1.setInt(1, account_id);
					stmt1.setInt(2, room_id);
					stmt1.setTimestamp(3,  start);
					stmt1.setTimestamp(4, new Timestamp(0));
					stmt1.setBoolean(5, Boolean.TRUE);
					stmt1.setString(6, "");
					
					stmt1.executeUpdate();
					
					return true;
				}finally {
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}

	@Override
	public boolean updateRoomEventForStudentAccountWithAccountIDandEventID(Integer account_id, Integer roomEvent_id,
			Timestamp end, Boolean flag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<RoomEvent> getAllRoomEventForStudentAccountWithAccountID(Integer account_id) {
		return executeTransaction(new Transaction<List<RoomEvent>>() {

			@Override
			public List<RoomEvent> execute(Connection conn) throws SQLException {
		
				PreparedStatement stmt1 = null;
				
				ResultSet resultSet1 = null;
				
				List<RoomEvent> roomEventList = new ArrayList<RoomEvent>();
				
				try {//prepare SQL 
					stmt1 = conn.prepareStatement(
							" select roomEvents.* "
							+ " from roomEvents, studentAccounts "
							+ " where studentAccounts.studentAccount_id_1 = roomEvents.studentAccount_id_1"
							+ " and studentAccounts.studentAccount_id_1 = ?"		
					);		
					stmt1.setInt(1, account_id);
					resultSet1 = stmt1.executeQuery();
					
					//verify a returned result
					while(resultSet1.next()) {
						
						RoomEvent event = new RoomEvent();
						loadRoomEvent(event, resultSet1, 1);
						roomEventList.add(event);
					}
					
					
					return roomEventList;
				}finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(resultSet1);
				}				
			}
		});
	}

	@Override
	public List<StudentAccount> getAllStudentsInSubTeamWithTeamName(String teamname) {
		return executeTransaction(new Transaction<List<StudentAccount>>() {

			@Override
			public List<StudentAccount> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				List<StudentAccount> studentAccountList = new ArrayList<StudentAccount>();
				
				try {//prepare SQL statement
					stmt1 = conn.prepareStatement(
						" select studentAccounts.* "
						+ " from studentAccounts, subTeams, subTeamStudents "
						+ " where studentAccounts.studentAccount_id_2 = subTeamStudents.studentAccount_id_2 "
						+ " and subTeams.subTeam_id_2 = subTeamStudents.subTeam_id_2 "
						+ " and subTeams.teamname = ?"	
					);
					stmt1.setString(1, teamname);
					resultSet1 = stmt1.executeQuery();
					
					while(resultSet1.next()) {
						
						StudentAccount student = new StudentAccount();
						loadStudentAccount(student, resultSet1, 2);
						studentAccountList.add(student);
					}
					return studentAccountList;
				}finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
}