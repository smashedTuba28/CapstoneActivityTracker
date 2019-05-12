package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CapstoneActivityTracker.controller.CreateSubTeamController;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;

public class CreateSubTeamServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("CreateSubTeam Servlet: doGet");
		
		String account_id= null;
		
		try {
			account_id = req.getSession().getAttribute("account_id").toString();
		}
		catch(NullPointerException e) {

		}
		//check session
		if ( account_id == null) {
			//redirect when the user does not have an account_id
			resp.sendRedirect(req.getContextPath() + "/signIn");
		}
		else {
			CreateSubTeamController controller = new CreateSubTeamController();
			req.setAttribute("ListOfTopTeam", controller.getTopTeamsList());

			//call the jsp and generate empty form
			req.getRequestDispatcher("/_view/createSubTeam.jsp").forward(req, resp);
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		System.out.println("createSubTeam Servlet: doPost");
		
		//create controller
		CreateSubTeamController controller = new CreateSubTeamController();
			
		//error message String to hold message text when applicable
		String errorMessage = null;
		String teamname = null;
		String topTeam = null;
		
		try {
			//decode POSted from parameter and dispatch to controller
			teamname = req.getParameter("teamname").toString();
			
		}
		catch(NullPointerException e) {
			
		}
		try {
			//decode POSted from parameter and dispatch to controller
			topTeam = req.getParameter("topTeamname").toString();
			
		}
		catch(NullPointerException e) {
			
		}
		
		//check that none are empty
		if (teamname == null ) {
			errorMessage = "Please Enter a Team Name";
			System.out.println("teamname==null");
			req.setAttribute("ListOfTopTeam", controller.getTopTeamsList());
			req.getRequestDispatcher("/_view/createSubTeam.jsp").forward(req, resp);
		}
		
			if(topTeam=="val") {
				System.out.println("need to select a TopTeam");
				errorMessage = "Please select a Capstone Team";
				req.setAttribute("ListOfTopTeam", controller.getTopTeamsList());
				req.getRequestDispatcher("/_view/createSubTeam.jsp").forward(req, resp);
			}
			
			else {
				errorMessage = "Team Successfully Created!";
				controller.createSubTeam(teamname, topTeam);
				req.setAttribute("ListOfTopTeam", controller.getTopTeamsList());
				req.getRequestDispatcher("/_view/createSubTeam.jsp").forward(req, resp);
			}
		
		errorMessage = "Shoot, something went wrong";
		System.out.println("Failure: Line 93 CreateSubTeamServlet reached");
		req.getRequestDispatcher("/_view/createSubTeam.jsp").forward(req, resp);
		
	}
}









