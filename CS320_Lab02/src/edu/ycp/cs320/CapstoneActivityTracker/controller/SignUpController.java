package edu.ycp.cs320.CapstoneActivityTracker.controller;

import edu.ycp.cs320.CapstonActivtyTracker.db.*;
import edu.ycp.db.*;
import edu.ycp.model.YCPPersonnel;

public class SignUpController {
	private IDatabase db;
	private YCPFakeDatabase ycpdb;
	private YCPPersonnel personnel;
	private String errorMessage = null;
	
	public SignUpController() { 
		//initialize databases
		DatabaseProvider.setInstance(new DerbyDatabase());//lab6
		db = DatabaseProvider.getInstance();//lab6
		ycpdb = new YCPFakeDatabase();
	}
	
	public boolean createAccount(String email, String password, String schoolID, String accountType) {
		
		if(accountType.equals("admin")) {
			//trying to create an admin account
			
			//look for admin in YCP DB
			personnel = ycpdb.verifyAdmin(email, schoolID);
			if(personnel!=null) {//if an admin personnel was found
				//check if personnel already has an account
				if(db.getAdminAccountWithEmailandSchoolID(email, schoolID) != null) {
					//account already exists
					errorMessage = "AdminAcount Already Exists.";
				}
				else {//account does not yet exist
					//attempt to insert new adminAccount into DB
					if(db.createAdminAccount(personnel.getFirstname(), personnel.getLastname(), email, password, schoolID)) {
						return true;//successfully created admin account
					}
					else {
						errorMessage = "Unable to create AdminAccount.";
					}
				}
			}
			else {
				errorMessage = "Unable to verify YCP faculty information.";
			}
		}
		else if(accountType.equals("student")) {
			//trying to create a student account
			personnel = ycpdb.verifyStudent(email, schoolID);
			if(personnel!=null) {//if a student personnel was found
				
				//check to see if account already exists
				if(db.getStudentAccountWithEmailandSchoolID(email, schoolID)!=null) {
					//account already exists
					errorMessage = "StudentAccount Already Exists";
				}
				else {//account does not yet exists
					//attempt to insert new studentAccount into DB
					if(db.createStudentAccount(personnel.getFirstname(), personnel.getLastname(), email, password, schoolID)) {
						return true;//successfully created a student account
					}
					else {
						errorMessage = "Unable to create StudentAccount";
					}
				}
			}
			else {
				errorMessage = "Unable to verify YCP student information";
			}
		}
		return false;//if unable to create an account for any reason return false
	}
	
	
	//method to get the errorMessage set from createAccount() 
	//Only called if createAccount() returns false
	public String getErrorMessage() {
		return errorMessage;
	}
}
//use incoming values to get account from YCPDB
//if in ycp database !null 

	//use fdb to createAccount using information from the ycpdb return
		//if creation successful return true

//if not with in ycp database / creation unsuccessful