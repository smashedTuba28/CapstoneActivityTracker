package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
		
		//TODO:
		//create controller
		
		//TODO:
		//assign model to controller
		
		//decode POSted from parameter and dispatch to controller
		try {
			String firstname = req.getParameter("firstname");
			String lastname = req.getParameter("lastname");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String schoolID = req.getParameter("schoolID");
			
			//check that 
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
			
			/*TODO: make sure no illegal characters in password
			if (password.contains()) {
				errorMessage = errorMessage + "Invalid Password: Cannot Contain these Characters\n:
									+ "\t: \n";
			} 
			*/
			
			/*TODO: make sure schoolID has correct basic formating
			if (!schoolID.startsWith(prefix) || schoolID.length() != length) {
				errorMessage = errorMessage + "Invalid School ID #\n";
			}
			*/
			
			//data cleared all initial checks if errorMessage is still null
			if (errorMessage == null) {
				//TODO: send data to controller to verify SignUp
			}
			
		}catch (Exception e) {
			errorMessage = errorMessage + "\nResolve all errors before continuing\n";
		}
		
		
		
		//TODO:
		//set "model" in jsp to reference desired model class from above
		//req.setAttribute("model", model);
		
		//set the errorMessage text to the response
		req.setAttribute("errorMessage", errorMessage);
		
		//forward to view to render the result in jsp
		req.getRequestDispatcher("/view/signUp.jsp").forward(req, resp);
	}
}