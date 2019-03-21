package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentViewServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//retrieve jsp for SignIn
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("SignIn Servlet: doGet");
		
		//Got idea for using sessions from Joseph Landau and Edward Nardo
		//used tutorialspoint for understanding
		//https://www.tutorialspoint.com/servlets/servlets-session-tracking.htm		
		
		if(req.getAttribute("userID") == null) {
			//userId hasn't been set: redirect to login page
			req.getRequestDispatcher("/_view/signIn.jsp").forward(req, resp);	
		}
		else {
				//TODO: populate models from database
			
				//populate account with hardcoded data
				//get userId and convert from object to string then from string to Integer
				//TODO:	Account accountModel = new Account(Integer.parseInt(req.getAttribute("userId").toString()));
				
				//set accountModel for jsp use
				//TODO:
				//req.setAttribute("accountModel", accountModel);
			
				req.getRequestDispatcher("/_view/studentView.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
	}
}
