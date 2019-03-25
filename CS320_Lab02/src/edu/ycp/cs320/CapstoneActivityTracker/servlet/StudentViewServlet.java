package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CapstonActivtyTracker.db.FakeDatabase;
import edu.ycp.cs320.CapstoneActivityTracker.model.Log;
import edu.ycp.cs320.CapstoneActivityTracker.model.Week;

public class StudentViewServlet  extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//retrieve jsp
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("StudentView Servlet: doGet");
	
	
		String email = req.getSession().getAttribute("userEmail").toString();
	
			//check session
			if ( email == null) {
				//redirect
				resp.sendRedirect(req.getContextPath() + "/signIn");
			}
			else {
				//create fakedb and initialze
				FakeDatabase fakedb = new FakeDatabase();
				fakedb.init();
				
				List<Week> weekList = fakedb.getAllWeek();
				List<Log> logList = fakedb.getAllLogs();
				
				
				//get appropriate week and log
				if(email.equals("jsteinberg@ycp.edu")) {
					req.setAttribute("week", weekList.get(0));
					req.setAttribute("log", logList.get(0));
				}
				else if(email.equals("twetzel1@ycp.edu")) {
					req.setAttribute("week", weekList.get(1));
					req.setAttribute("log", logList.get(1));
				}
				else {
					
				}
				//call the jsp and generate empty form
				req.getRequestDispatcher("/_view/signUp.jsp").forward(req, resp);
				
			}
	}	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
