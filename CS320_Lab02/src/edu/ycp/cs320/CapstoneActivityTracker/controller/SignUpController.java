package edu.ycp.cs320.CapstoneActivityTracker.controller;

import edu.ycp.cs320.CapstonActivtyTracker.db.*;
import edu.ycp.db.*;

public class SignUpController {
	FakeDatabase fdb;
	YCPFakeDatabase ycpdb;
	
	public SignUpController() {
		//initialize databases
	}
	
	public boolean createAccount(String email, String password, String schoolID) {
		//use incoming values to get account from YCPDB
		
		//if in ycp database !null 
		
			//use fdb to createAccount using information from the ycpdb return
				//if creation successful return true
		
		//if not with in ycp database / creation unsuccessful
		return false;
	}
}
