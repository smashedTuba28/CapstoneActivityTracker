package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SignInServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//retrieve jsp for SignIn
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("AddNumbers Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/signIn.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		System.out.println("SignIn Servlet: doPost");
		
		//error message String to hold message text when applicable
		String errorMessage = null;
		
		
		//TODO:
		// create model
	
		//TODO:
		//create controller
		
		//TODO:
		//assign model reference to controller
		
		//decode POSTed from parameters and dispatch to controller
		try {
			String email = req.getParameter("email");//input from jsp under field labeled email
			String password = req.getParameter("password"); //input from jsp under field labeled password
	
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
			else if (password.length() < 8) {
				errorMessage = "Password Invalid: must contain at least 8 characters";
			}
			//data clears initial check
			//TODO: send data to controller to verify log in
			//model should have a boolean attribute called loggedIn
		} catch (Exception e){
			errorMessage = "Log In Failed: Recheck Email and Password and try again";
		}
		
		//TODO:
		//set "model" in jsp to reference desired model class from above
		//req.setAttribute("model", model);
		
		//set the errorMessage text to the response
		req.setAttribute("errorMessage", errorMessage);
		
		//Forward to view to render the result in jsp
		req.getRequestDispatcher("/_view/signIn.jsp").forward(req,  resp);
	}
}
