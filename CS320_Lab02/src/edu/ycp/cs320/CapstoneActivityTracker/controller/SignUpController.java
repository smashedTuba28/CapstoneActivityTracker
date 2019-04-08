package edu.ycp.cs320.CapstoneActivityTracker.controller;

import edu.ycp.cs320.CapstonActivtyTracker.db.*;
import edu.ycp.db.*;

public class SignUpController {
	FakeDatabase fdb;
	YCPFakeDatabase ycpdb;
	
	public SignUpController() {
		//initialize databases
		fdb = new FakeDatabase();
		ycpdb = new YCPFakeDatabase();
	}
	
	public boolean createAccount(String email, String password, String schoolID) {
		//checking if personnel is within the YCPDB as an admin
		if(ycpdb.verifyAdmin(email, schoolID)) {
			fdb.createAccount(ycpdb.findAdminByEmail(email).getFirstname(), ycpdb.findAdminByEmail(email).getLastname(), email, password, schoolID, true);
			return true;
		}
		//checking if personnel is within the YCPDB as a student
		else if(ycpdb.verifyStudent(email, schoolID)) {
			fdb.createAccount(ycpdb.findStudentByEmail(email).getFirstname(), ycpdb.findStudentByEmail(email).getLastname(), email, password, schoolID, false);
			return true;
		}
		//if the personnel does not exist, return false as the account has not been created
		else
		return false;
	}
}
//use incoming values to get account from YCPDB
//if in ycp database !null 

	//use fdb to createAccount using information from the ycpdb return
		//if creation successful return true

//if not with in ycp database / creation unsuccessful