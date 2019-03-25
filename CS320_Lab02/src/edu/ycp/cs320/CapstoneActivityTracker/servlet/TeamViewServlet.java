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

public class TeamViewServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//retrieve jsp
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("TeamView Servlet: doGet");
	
	
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
				
			//get appropriate week and logs hard coded in
				req.setAttribute("week0", weekList.get(0));
				req.setAttribute("log0", logList.get(0));
				req.setAttribute("week1", weekList.get(1));
				req.setAttribute("log1", logList.get(1));
				req.setAttribute("week2", weekList.get(2));
				req.setAttribute("log2", logList.get(2));
				req.setAttribute("week3", weekList.get(3));
				req.setAttribute("log3", logList.get(3));
		
			//call the jsp and generate empty form
			req.getRequestDispatcher("/_view/teamView.jsp").forward(req, resp);
		}
	}		
}