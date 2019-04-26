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
				
				try {
					//room table
					stmt1 = conn.prepareStatement(
						"create table rooms (" +
						"	room_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	number integer," +
						"	name varchar(70)" +
						")"
					);	
					stmt1.executeUpdate();
					System.out.println("Rooms table created");
					
					// student accounts table
					stmt2 = conn.prepareStatement(
							"create table studentAccounts(" +
							"	account_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	firstname varchar(20)," +
							"	lastname varchar(20)," +
							"   email varchar(35)," + 
							" 	password varchar(100)," +
							"	schoolID varchar(9)" +
							")"
					);
					stmt2.executeUpdate();
					System.out.println("studentAccounts table created");					
					
					
					//room events table
					stmt3 = conn.prepareStatement(
							"create table roomEvents (" +
							"	account_id integer constraint account_id references studentAccounts," +
							"	room_id integer constraint room_id references rooms," + 
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
							"	subTeam_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +									
							"	teamname varchar(40)," +
							" 	topTeam_id integer constraint topTeam_id references topTeams," +
							" 	account_id integer constraint studentAccount_id references studentAccounts" +
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
					
				/*	//teamRooms
					stmt6 = conn.prepareStatement(
							"create table teamRooms (" +
									"	team_id   integer constraint team_id references subTeams, " +
									"	room_id integer constraint room_id references rooms " +
									")"
					);
					stmt6.executeUpdate();
					System.out.println("teamRooms table created");
					*/
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
			//	List<RoomEvent> roomEventList;
			//	List<StudentAccount> studentList;
			//	List<AdminAccount> adminList;
				List<Room> roomList;
			//	List<TopTeam> topTeamList;
			//	List<SubTeam> subTeamList;
				
			//	List<TeamRoom> teamRoomList;
				
				
				try {
			//		roomEventList     	= InitialData.getRoomEvents();
			//		studentList       	= InitialData.getStudentAccounts();
			//		adminList 			= InitialData.getAdminAccounts();
					roomList			= InitialData.getRooms();
			//		topTeamList			= InitialData.getTopTeams();
			//		subTeamList			= InitialData.getSubTeams();
			//		teamRoomList		= InitialData.getTeamRooms();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
			//	} catch (ParseException e) {
			//		throw new SQLException("Couldn't parse initial data", e);
				}

			//	PreparedStatement insertRoomEvent     	= null;
			//	PreparedStatement insertStudent       	= null;
			//	PreparedStatement insertAdmin 			= null;
				PreparedStatement insertRoom			= null;
			//	PreparedStatement insertTopTeam			= null;
			//	PreparedStatement insertSubTeam			= null;
			//	PreparedStatement insertTeamRoom		= null;
				
				try {
					
					insertRoom = conn.prepareStatement("insert into rooms (number, name) values (?, ?)");
					for (Room room : roomList) {
						insertRoom.setInt(1, room.getRoomNumber());
						insertRoom.setString(2, room.getRoomName());
						insertRoom.addBatch();
					}
					insertRoom.executeBatch();
					System.out.println("Rooms table populated");
					
		/*			
					insertStudent = conn.prepareStatement("insert into studentAccounts (firstname, lastname, email, password, schoolID) values (?, ?, ?, ?, ?)");
					for (StudentAccount student : studentList) {
						insertStudent.setString(1, student.getFirstname());
						insertStudent.setString(2, student.getLastname());
						insertStudent.setString(3, student.getEmail());
						insertStudent.setString(4, student.getPassword());
						insertStudent.setString(5, student.getSchoolID());
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
					
					
					
					insertRoomEvent = conn.prepareStatement("insert into roomEvents (account_id, room_id, startTime, endTime, lognote) values (?,?,?,?,?) ");
					for (RoomEvent event : roomEventList) {
						insertRoomEvent.setInt(1, event.getAccountID());
						insertRoomEvent.setInt(2, event.getRoomID());
						insertRoomEvent.setTimestamp(3, new Timestamp(event.getStartTime().getTime()));
						insertRoomEvent.setTimestamp(4, new Timestamp(event.getEndTime().getTime()));
						insertRoomEvent.setString(5,  event.getLognote());
						insertRoomEvent.addBatch();
					}
					insertRoomEvent.executeBatch();
					System.out.println("RoomEvents Table populated");
					
										
					insertSubTeam = conn.prepareStatement("insert into subTeams (teamname, topTeam_id, account_id) values (?,?,?)");
					for (SubTeam sub : subTeamList) {
						insertSubTeam.setString(1, sub.getTeamname());
						insertSubTeam.setInt(2, sub.getTopTeamID());
						insertSubTeam.setInt(3, sub.getAccountID());
						insertSubTeam.addBatch();
					}
					insertSubTeam.executeBatch();
					System.out.println("SubTeams Table populated");
					
					insertTeamRoom = conn.prepareStatement("insert into teamRooms (teamID, roomID) values (?,?)");
					for (TeamRoom tr : teamRoomList) {
						insertTeamRoom.setInt(1, tr.getTeamID());
						insertTeamRoom.setInt(2, tr.getRoomID());
						insertTeamRoom.addBatch();
					}
					insertTeamRoom.executeBatch();
					System.out.println("TeamRooms table populated");
			*/		
					return true;
				} finally {
				//	DBUtil.closeQuietly(insertStudent);
				//	DBUtil.closeQuietly(insertAdmin);
					DBUtil.closeQuietly(insertRoom);
				//	DBUtil.closeQuietly(insertRoomEvent);
				//	DBUtil.closeQuietly(insertTopTeam);
				//	DBUtil.closeQuietly(insertSubTeam);
				//	DBUtil.closeQuietly(insertTeamRoom);
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

	@Override
	public boolean verifyAccount(String email, String password) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				Boolean found = false;
				try {
					stmt = conn.prepareStatement(
						"select studentAccounts.email, studentAccounts.password "
						+ "	from studentAccounts "
						+ " where studentAccounts.email = ?"
						+ " and studentAccounts.password = ?"
					);
					
					stmt.setString(1, email);
					stmt.setString(2, password);
					resultSet = stmt.executeQuery();
					
					if(resultSet.next()) {
						found = true;
					}
					return found;
				}finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
			}
		});
		
		
		
		
		
		
	}
}
