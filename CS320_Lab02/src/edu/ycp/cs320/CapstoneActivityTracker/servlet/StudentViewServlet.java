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
	
	
		String email = (String) req.getSession().getAttribute("userEmail");
	
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
				else if (email.equals("wtaylor1@ycp.edu")){
					req.setAttribute("week", weekList.get(2));
					req.setAttribute("log", logList.get(2));
				}
				else if(email.equals("lizardking@ycp.edu")){
					req.setAttribute("week", weekList.get(3));
					req.setAttribute("log", logList.get(3));
				}
				else {
					//email not from hard coded list jump back to signIn
					resp.sendRedirect(req.getContentType() + "/signIn");
				}
				//call the jsp and generate empty form
				req.getRequestDispatcher("/_view/studentView.jsp").forward(req, resp);
			}
	}	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
