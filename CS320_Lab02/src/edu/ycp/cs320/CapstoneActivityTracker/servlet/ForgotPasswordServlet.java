package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CapstonActivtyTracker.db.FakeDatabase;

public class ForgotPasswordServlet  extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//retrieve jsp for SignIn
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("ForgotPassword Servlet: doGet");
		//call the jsp and generate empty form
		req.getRequestDispatcher("/_view/forgotPassword.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
	
		System.out.println("ForgotPassword Servlet: doPost");
		
		//error message String to hold message text when applicable
		String errorMessage = null;
		String updateMessage = null;
		
		FakeDatabase fakedb = new FakeDatabase();
		fakedb.init();
		
		//decode POSTed from parameter and dispatch to controller
		try {
			String email = req.getParameter("email");
			String schoolID = req.getParameter("schoolID");
			
			//check if empty
			if (email == null) {
				errorMessage = "Please Enter YCP email address";
			}
			//check that email is from YCP
			else if (!email.endsWith("@ycp.edu")) {
				//if it is not a ycp email
				errorMessage = "Please Enter a Valid YCP email address";
			}
			else if (!schoolID.startsWith("90") || schoolID.length() != 9) {
				errorMessage = errorMessage + "Invalid School ID #";
			}
			//data passes initial checks
			else {
				
				if(fakedb.verifyAccountWithEmailSchoolID(email, schoolID)) {
					updateMessage = "Verification Email Sent (But not really)";
				}
			}
		}catch (Exception e) {
			errorMessage = "Unable to find User: Recheck Email and ID and try again";
		}
		
		//set the errorMessage text to the response
		req.setAttribute("errorMessage", errorMessage);
		//set updateMessage text to the response
		req.setAttribute("updateMessage", updateMessage);
		
		//Forward to view to render the result in jsp
		req.getRequestDispatcher("/_view/forgotPassword.jsp").forward(req, resp);
	}
}
