package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CapstonActivtyTracker.db.FakeDatabase;
import edu.ycp.cs320.CapstoneActivityTracker.controller.SignUpController;
import edu.ycp.cs320.CapstoneActivityTracker.model.Account;


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
		
		
		//create controller
		SignUpController controller = new SignUpController();
			
		//error message String to hold message text when applicable
		String errorMessage = null;
		String email = null;
		String password = null;
		String passConfirm = null;
		String schoolID = null;
		boolean valid;
		
		//decode POSted from parameter and dispatch to controller
		email = req.getParameter("email").toString();
		password = req.getParameter("password").toString();
		passConfirm = req.getParameter("passwordConfirm").toString();
		schoolID = req.getParameter("schoolID").toString();
				
		//check that none are empty
		if (email == null || password == null || schoolID == null
				|| passConfirm == null) {
			errorMessage = "All fields required";
		}
		//all fields have info entered
		else if (!email.endsWith("@ycp.edu")) {
			//if it is not a ycp email
			errorMessage = "Enter a valid YCP email";	
		}
		else if (password.length() < 8) {
			errorMessage = "Password must contain at least 8 characters\n";
		}
		
		else if (!password.equals(passConfirm)) {
			errorMessage = "Passwords Don't Match. Recheck password and confimation password\n";
		} 
			
		else if (!schoolID.startsWith("90") || schoolID.length() != 9) {
			errorMessage = "Invalid School ID #\n";
		}	
		else{
		//passed all error checks and data ready to be added to db
			System.out.println("Starting here");
			valid = controller.createAccount(email, password, schoolID);
			System.out.println("Exiting here");
			if (!valid){
				errorMessage = "Personnel Not Resgistered at York College of Pennsylvania";
			}
		}	
			
		if (errorMessage == null) {
			//account was made
			//redirect to login page
			System.out.println("Successful Account Creation: Redirect to SignIn");
			resp.sendRedirect(req.getContextPath() +"/signIn");
		}
		else {
		//set the errorMessage text to the response
		req.setAttribute("errorMessage", errorMessage);
		//forward to view to render the result in jsp
		req.getRequestDispatcher("/_view/signUp.jsp").forward(req, resp);
		}
	}
}