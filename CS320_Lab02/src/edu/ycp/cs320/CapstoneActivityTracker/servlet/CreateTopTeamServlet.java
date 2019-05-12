

package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CapstoneActivityTracker.controller.CreateTopTeamController;


public class CreateTopTeamServlet extends HttpServlet {
	 
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("CreateTopTeam Servlet: doGet");
		String account_id= null;
		try {
			account_id = req.getSession().getAttribute("account_id").toString();
		}
		catch(NullPointerException e) {

		}
		//check session
		if ( account_id == null) {
			//redirect
			resp.sendRedirect(req.getContextPath() + "/signIn");
			
		}
		else {
			
				//call the jsp and generate empty form
				req.getRequestDispatcher("/_view/createTopTeam.jsp").forward(req, resp);

			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		System.out.println("createTopTeam Servlet: doPost");
		
		
		//create controller
		CreateTopTeamController controller = new CreateTopTeamController();
			
		//error message String to hold message text when applicable
		String errorMessage = null;
		String teamname = null;
		boolean TTcreation = false;
		
		try {
			//decode POSted from parameter and dispatch to controller
			teamname = req.getParameter("teamname").toString();
			
		}
		catch(NullPointerException e) {
			
		}
		System.out.println(teamname);
		
		//check that none are empty
		if (teamname == null ) {
			errorMessage = "Please Enter a Team Name";
		}
		
		else {
				TTcreation = controller.createTopTeam(teamname);
		}
		
		if(!TTcreation) {
			errorMessage = "Top Team not created!";
			req.getRequestDispatcher("/_view/createTopTeam.jsp").forward(req, resp);;
			
		}
		else{
			errorMessage = "Successful Top Team Creation";
			req.getRequestDispatcher("/_view/createTopTeam.jsp").forward(req, resp);
		}
		errorMessage = "Whoops! That wasn't supposed to happen. Try Again.";
		req.getRequestDispatcher("/_view/createTopTeam.jsp").forward(req, resp);
	}
}