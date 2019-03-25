package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CapstonActivtyTracker.db.FakeDatabase;


public class SignUpServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//retrieve jsp for SignUp
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("SignUp Servlet: doGet");
		
		//String email = req.getSession().getAttribute("userEmail").toString();
		
		//check session
		//redirect is already logged in
		//if ( email != null) {
			//FakeDatabase fake = new FakeDatabase();
			//fake.init();
			
			//check for faculty or student
			//if(fake.isFaculty(email)) {
				//redirect faculty to adminView
			//	req.getRequestDispatcher("/_view/adminView.jsp").forward(req, resp);
			//}
			//else {
				//redirect students to studentView
				//resp.sendRedirect(req.getContextPath() + "/studentView");
			//}
		//}
		//else {
		//call the jsp and generate empty form
		req.getRequestDispatcher("/_view/signUp.jsp").forward(req, resp);
		//}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		System.out.println("SignUp Servlet: doPost");
		
		//error message String to hold message text when applicable
		String errorMessage = null;
		String firstname = null;
		String lastname = null;
		String email = null;
		String eConfirm = null;
		String password = null;
		String passConfirm = null;
		String schoolID = null;
		String faculty = "1";
		Boolean valid = false;
		
		FakeDatabase fake = new FakeDatabase();
		fake.init();
		
		//decode POSted from parameter and dispatch to controller
		try {
			firstname = req.getParameter("firstname").toString();
			lastname = req.getParameter("lastname").toString();
			email = req.getParameter("email").toString();
			eConfirm = req.getParameter("emailConfirm").toString();
			password = req.getParameter("password").toString();
			passConfirm = req.getParameter("passwordConfirm").toString();
			schoolID = req.getParameter("schoolID").toString();
			//faculty = req.getParameter("faculty").toString();
			//TODO: boolean for faculaty (jsp have a check box or drop down select)
	 
			
			
			
			//check that none are empty
			if (firstname == null || lastname == null || email == null 
					|| password == null || schoolID == null || eConfirm == null
					|| passConfirm == null) {//|| faculty == null) {
				errorMessage = "All fields required";
			}
			//all fields have info entered
			else if (!email.endsWith("@ycp.edu")) {
				//if it is not a ycp email
				errorMessage = errorMessage + "Enter a valid YCP email";	
			}
			else if(!email.equals(eConfirm)) {
				errorMessage = errorMessage + "Emails Don't Match\n";
			}
			else if (password.length() < 8) {
				errorMessage = errorMessage + "Password must contain at least 8 characters\n";
			}
		
			else if (!password.equals(passConfirm)) {
				errorMessage = errorMessage + "Passwords Don't Match. Recheck password and confimation password\n";
			} 
			
			else if (!schoolID.startsWith("90") || schoolID.length() != 9) {
				errorMessage = errorMessage + "Invalid School ID #\n";
			}	
			
			else{
			//passed all error checks and data ready to be added to db	
				if(faculty.equals("0")){//student
					fake.createAccount(firstname, lastname, email, password, schoolID, false);
				}
				else {//admin
					fake.createAccount(firstname, lastname, email, password, schoolID, true);
				}
			}	
			
			//make sure creation was successful
			valid = fake.verifyAccount(email, password);

			
		}catch (Exception e) {
			errorMessage = errorMessage + "\nUNABLE TO CREATE ACCOUNT: CHECK CREDENTIALS AND TRY AGAIN\n";
		}
		
		
		if (valid == true) {
			//if account was made
			//redirect to login page
			System.out.println("Successful Account Creation: Redirect to SignIn");
			resp.sendRedirect(req.getContextPath() +"/signIn");
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