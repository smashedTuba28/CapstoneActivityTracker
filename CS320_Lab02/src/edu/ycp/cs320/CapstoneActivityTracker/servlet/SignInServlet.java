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
		
		//create db instance
		FakeDatabase fake = new FakeDatabase();
		fake.init();
		
		boolean valid = false;
		String email = null;
		String password = null;
		//String faculty[] = null;
		
		//decode POSTed from parameters and dispatch to controller
		try {
			email = req.getParameter("email").toString();//input from jsp under field labeled email
			password = req.getParameter("password").toString(); //input from jsp under field labeled password
			//faculty = req.getParameterValues("faculty");
			
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
			//TODO: search for only faculty or student
			valid = fake.verifyAccount(email, password);
		} catch (Exception e){
			errorMessage = "Log In Failed: Recheck Email and Password and try again";
		}
		
		//determine if credentials were successful
		if (valid == true) {
			req.getSession().setAttribute("userEmail", email);//citation at top 
			if (true) {
				//get student view if successful login
				resp.sendRedirect(req.getContextPath() + "/studentView");
			}
			//TODO: fix to use checkbox input
			else if(false){
				//get faculty view if successful login
				resp.sendRedirect(req.getContextPath() + "/adminView");
			}
			else {
				//hopefully never get here
				req.setAttribute("errorMessage", "Student/Faculty value Failure");
				//Forward to view to render the result in jsp
				req.getRequestDispatcher("/_view/signIn.jsp").forward(req,  resp);
			}
		}
		else {
			//report error
			//set the errorMessage text to the response
			req.setAttribute("errorMessage", errorMessage);
			//Forward to view to render the result in jsp
			req.getRequestDispatcher("/_view/signIn.jsp").forward(req,  resp);
		}
	}
	
	//code borrowed from Lab02 then modified for int
	private Integer getIntFromParameter(String s) {
		if (s == null || s.equals("")) {
			return null;
		} else {
			return Integer.parseInt(s);
		}
	}
}