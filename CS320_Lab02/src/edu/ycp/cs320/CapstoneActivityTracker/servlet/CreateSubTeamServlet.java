package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CapstoneActivityTracker.controller.CreateSubTeamController;
import edu.ycp.cs320.CapstoneActivityTracker.model.createSubTeam;

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
			//initializing controller and model for adminView
			CreateSubTeamController controller = new CreateSubTeamController();
			createSubTeam model = new createSubTeam();
			controller.setModel(model);
			
			//intially populates TopTeams once page is opened
			controller.populateModelWithTopTeams();

			req.setAttribute("model", model);

			//call the jsp and generate empty form
			req.getRequestDispatcher("/_view/createSubTeam.jsp").forward(req, resp);

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{

		System.out.println("CreateSubTeam Servlet: doPost");

		//initializing controller and model for adminView
		CreateSubTeamController controller = new CreateSubTeamController();
		createSubTeam model = new createSubTeam();
		controller.setModel(model);


		//error message String to hold message text when applicable
		String errorMessage = null;
		String teamname = null;
		String topTeam = null;
		String createTeam = null;

		try {
			//decode POSted from parameter and dispatch to controller
			teamname = req.getParameter("teamname").toString();

		}
		catch(NullPointerException e) {

		}
		try {
			//decode POSted from parameter and dispatch to controller
			topTeam = req.getParameter("topTeam").toString();

		}
		catch(NullPointerException e) {

		}
		try {
			//decode POSted from parameter and dispatch to controller
			createTeam = req.getParameter("createTeam").toString();

		}
		catch(NullPointerException e) {

		}

		//initially populates TopTeams once page is opened
		controller.populateModelWithTopTeams();
		
		//check that none are empty
		if(createTeam != null) {
			if (teamname.equals("null") ) {
				errorMessage = "Please Enter a Team Name";
				System.out.println("teamname==null");
				req.setAttribute("model", model);
				req.getRequestDispatcher("/_view/createSubTeam.jsp").forward(req, resp);
			}
			else {
				if(topTeam.equals("val")) {
					System.out.println("need to select a TopTeam");
					errorMessage = "Please select a Capstone Team";
					req.setAttribute("model", model);
					req.getRequestDispatcher("/_view/createSubTeam.jsp").forward(req, resp);
				}
				else if (topTeam.equals("null")) {

					System.out.println("topTeam: null");
					req.setAttribute("model", model);
					req.getRequestDispatcher("/_view/createSubTeam.jsp").forward(req, resp);
				}
				else {
					errorMessage = "Team Successfully Created!";
					System.out.println("Team Successfully Created!");
					controller.createSubTeam(teamname, topTeam);
					req.getRequestDispatcher("/_view/createSubTeam.jsp").forward(req, resp);
				}
			}
		}

	}
}









