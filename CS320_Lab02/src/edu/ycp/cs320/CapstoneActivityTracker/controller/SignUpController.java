package edu.ycp.cs320.CapstoneActivityTracker.controller;

import edu.ycp.cs320.CapstonActivtyTracker.db.*;
import edu.ycp.db.*;

public class SignUpController {
	FakeDatabase fdb;
	YCPFakeDatabase ycpdb;
	
	public SignUpController() {
		//initialize databases
		fdb = new FakeDatabase();
		fdb.init();
		ycpdb = new YCPFakeDatabase();
	}
	
	public boolean createAccount(String email, String password, String schoolID, String accountType) {
		
		if(accountType.equals("admin")) {
			//if trying to create an admin account
			//verify that they are a YCP faculty member
			if(ycpdb.verifyAdmin(email, schoolID)) {
				//ycp faculty exists
				
				
				
			}
			else {
				//not found as a ycp faculty member
				return false;
			}
		}
		else if(accountType.equals("student")) {
			
		}
		
		
		
		
		
		
		
		
		
		
		//checking if personnel is within the YCPDB as an admin
		if(ycpdb.verifyAdmin(email, schoolID)) {
			fdb.createAccount(ycpdb.findAdminByEmail(email).getFirstname(), ycpdb.findAdminByEmail(email).getLastname(), email, password, schoolID, true);
			return true;
		}
		//checking if personnel is within the YCPDB as a student
		else if(ycpdb.verifyStudent(email, schoolID)) {
			System.out.println("Student Exsisted");
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