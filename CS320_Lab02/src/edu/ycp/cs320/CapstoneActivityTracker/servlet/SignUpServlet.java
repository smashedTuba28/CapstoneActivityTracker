package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import edu.ycp.cs320.CapstoneActivityTracker.model.*;

public class SignUpServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//retrieve jsp for SignUp
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("SignUp Servlet: doGet");
		//call the jsp and generate empty form
		req.getRequestDispatcher("/_view/signUp.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		System.out.println("SignUp Servlet: doPost");
		
		//error message String to hold message text when applicable
		String errorMessage = null;
		
		//TODO:
		//create model
		//Account model = null;
		
		//TODO:
		//create controller
		
		//TODO:
		//assign model to controller
		
		//FOR HARDCODE
		String firstname = null;
		String lastname = null;
		String email = null;
		String password = null;
		String passConfirm = null;
		String schoolID = null;
		//FOR HARDCODE
		
		//decode POSted from parameter and dispatch to controller
		try {
			/*String*/ firstname = req.getParameter("firstname");
			/*String*/ lastname = req.getParameter("lastname");
			/*String*/ email = req.getParameter("email");
			/*String*/ password = req.getParameter("password");
			/*String*/ passConfirm = req.getParameter("passConfrim");
			/*String*/ schoolID = req.getParameter("schoolID");
			//TODO: boolean for faculaty (jsp have a check box or drop down select)
	 
			//check that none are empty
			if (firstname == null || lastname == null || email == null 
					|| password == null || schoolID == null) {
				
				errorMessage = "All fields required\n";
			}
			//all fields have info entered
			if (!email.endsWith("@ycp.edu")) {
				//if it is not a ycp email
				errorMessage = errorMessage + "Enter a valid YCP email\n";	
			}
			if (password.length() < 8) {
				errorMessage = errorMessage + "Password must contain at least 8 characters\n";
			}
		
			if (!password.equals(passConfirm)) {
				errorMessage = errorMessage + "Passwords Don't Match. Recheck password and confimation password\n";
			} 
			
			if (!schoolID.startsWith("90") || schoolID.length() != 9) {
				errorMessage = errorMessage + "Invalid School ID #\n";
			}			
		}catch (Exception e) {
			errorMessage = errorMessage + "\nResolve all errors before continuing\n";
		}
		
		
		boolean valid = true;
		//data cleared all initial checks if errorMessage is still null
		if (errorMessage == null) {
			//TODO: send data to controller to create Account and verify signUp
			
			/*
			//HARD CODE EXAMPLE SECTION BEGINS
			model.setFirstname(firstname);
			model.setLastname(lastname);
			model.setEmail(email);
			model.setSchoolID(schoolID);
			model.setPassword(password);
			model.setFacualty(faculty);
			valid = model.validAccount();
			//END HARDCODE SECTION
			*/
		}
		
		if (valid == true) {
			//if account was made
			//redirect to login page
			req.getRequestDispatcher("/_view/signIn.jsp").forward(req, resp);
		}
		else {
		
		//TODO:set "model" in jsp to reference desired model class from above
		//req.setAttribute("model", model);
		//set the errorMessage text to the response
		req.setAttribute("errorMessage", errorMessage);
		//forward to view to render the result in jsp
		req.getRequestDispatcher("/_view/signUp.jsp").forward(req, resp);
		}
	}
}