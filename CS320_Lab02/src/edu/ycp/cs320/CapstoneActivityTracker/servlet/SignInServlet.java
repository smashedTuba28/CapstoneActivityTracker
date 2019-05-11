package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//TODO:
//import model
//import controller
import edu.ycp.cs320.CapstonActivtyTracker.db.*;
import edu.ycp.cs320.CapstoneActivityTracker.controller.SignInController;
import edu.ycp.cs320.CapstoneActivityTracker.model.Account;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;

public class SignInServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//retrieve jsp for SignIn
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("SignIn Servlet: doGet");
		//call the jsp and generate empty form
		req.getRequestDispatcher("/_view/signIn.jsp").forward(req, resp);
		
		//Got idea for using sessions from Joseph Landau and Edward Nardo
		//used tutorialspoint for understanding
		//https://www.tutorialspoint.com/servlets/servlets-session-tracking.htm		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		System.out.println("SignIn Servlet: doPost");
		
		//error message String to hold message text when applicable
		String errorMessage = null;
		
		boolean valid = false;
		String email = null;
		String password = null;
		String accountType = null;
		SignInController controller = new SignInController();
		Account model = null;
		controller.setModel(model);
		
		
		//decode POSTed from parameters and dispatch to controller
		
		email = req.getParameter("email").toString();//input from jsp under field labeled email
		password = req.getParameter("password").toString();
		accountType = req.getParameter("accountType").toString();
		
		//check if either is empty
		if (email == null || password == null) {//either empty
			errorMessage = "Please Enter Both your YCP Email and Password";
		}
		//check that email is from YCP
		else if (!email.endsWith("@ycp.edu")) {
			//if it is not a ycp email
			errorMessage = errorMessage + "Please Enter your YCP email";
		}
		//check password length matches exceeds minimum length
		//else if (password.length() < 8) {
		//	errorMessage = "Password Invalid: must contain at least 8 characters";
		//}
		else {
		//data clears initial check
			 
			//encrypt password
			password = hashSHA256.getHash(password);
			
			//verify account 
			if(controller.validateCredentials(email, password, accountType)) { 
				//add account_id to session
				
		
				model = controller.getModel();
				req.getSession().setAttribute("account_id", String.valueOf(model.getAccountID()));//citation at top 
				req.getSession().setAttribute("accountType", accountType);
				
				if(accountType.equals("admin")) {
					//get faculty view if successful login
					resp.sendRedirect(req.getContextPath() + "/adminView");
				}
				else {
					//get student view if successful login
					resp.sendRedirect(req.getContextPath() + "/studentView");
				}
				
			}
			else {
				errorMessage = "Log In Failed: unable to verify Account: Recheck Credentials and Try Again";
			}
		}
		
		if(errorMessage != null) {
			//set the errorMessage text to the response
			req.setAttribute("errorMessage", errorMessage); 
			//Forward to view to render the result in jsp
			req.getRequestDispatcher("/_view/signIn.jsp").forward(req, resp);
		}
	}
}