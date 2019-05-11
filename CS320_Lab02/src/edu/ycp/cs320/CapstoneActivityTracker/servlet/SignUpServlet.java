package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CapstonActivtyTracker.db.FakeDatabase;
import edu.ycp.cs320.CapstonActivtyTracker.db.hashSHA256;
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
		String accountType = null;
		
		//decode POSted from parameter and dispatch to controller
		try {
		email = req.getParameter("email").toString();
		password = req.getParameter("password").toString();
		passConfirm = req.getParameter("passwordConfirm").toString();
		schoolID = req.getParameter("schoolID").toString();
		accountType = req.getParameter("accountType").toString();
		}
		catch(NullPointerException e){
			System.out.println("whaaaaa????");
		}
		
		
		System.out.println(accountType);
		
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
			errorMessage = "Password must contain at least 8 characters";
		}
		else if (!password.equals(passConfirm)) {
			errorMessage = "Passwords Don't Match. Recheck password and confimation password";
		} 
		else if (!schoolID.startsWith("9") || schoolID.length() != 9) {
			errorMessage = "Invalid School ID";
		}
		else if (!(accountType.equals("admin") || accountType.equals("student"))) {
			errorMessage = "Select an Account Type";
		}
		else{
		//passed all error checks and data ready to be added to db
			password = hashSHA256.getHash(password);
			if(!controller.createAccount(email, password, schoolID, accountType)) {
				//account creation failed
				errorMessage = controller.getErrorMessage();
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