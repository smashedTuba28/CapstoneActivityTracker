package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CapstoneActivityTracker.controller.AdminViewController;
import edu.ycp.cs320.CapstoneActivityTracker.controller.ChartController;
import edu.ycp.cs320.CapstoneActivityTracker.model.AdminView;
import edu.ycp.cs320.CapstoneActivityTracker.model.ChartModel;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.SubTeam;

public class AdminViewServlet extends HttpServlet { 

	private static final long serialVersionUID = 1L;

	//retrieve jsp
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("StudentView Servlet: doGet");
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
			AdminView model = new AdminView();
			AdminViewController controller = new AdminViewController();
			controller.setModel(model);

			//intially populates TopTeams once page is opened
			controller.populateModelWithTopTeams();
			System.out.println(model.getTopTeamList());
			model.setSubTeamList(new ArrayList<SubTeam>());
			model.setStudents(new ArrayList<StudentAccount>());
			//req.setAttribute("model", model);
			req.setAttribute("model", model);

			//call the jsp and generate empty form
			req.getRequestDispatcher("/_view/adminView.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		System.out.println("SignIn Servlet: doPost");

		//error message String to hold message text when applicable
		String errorMessage = null;

		//setting all strings to null
		String topTeam = null;
		String subTeam = null;
		String student = null;
		//b == button press
		String b1 = null;
		String b2 = null;
		String b3 = null;
		//intiallizing controller and model for adminView
		AdminViewController controller = new AdminViewController();
		AdminView model = new AdminView();

		//setting the controller model
		controller.setModel(model);
		try {
			//requesting parameters from JSP
			topTeam = req.getParameter("topTeam").toString();
			subTeam = req.getParameter("subTeam").toString();
			student = req.getParameter("student").toString();
			b1 		= req.getParameter("b1").toString();
			b2 		= req.getParameter("b2").toString();
			b3 		= req.getParameter("b3").toString();
		}
		catch(NullPointerException e) {

		}
		if(b1!=null) {
			if(topTeam==null) {
				errorMessage = "select topTeam";
				System.out.println("topTeam==null");
			}
			else{
				controller.populateModelWithSubTeams(topTeam);
				
			}
		}
		else if(b2!=null) {
			if(subTeam==null) {
				errorMessage = "select subTeam";
			}
			else {
				controller.populateModelWithStudents(subTeam);
				//req.setAttribute("model", model);
				req.setAttribute("model", model);
				//call the jsp and generate empty form
				req.getRequestDispatcher("/_view/adminView.jsp").forward(req, resp);
			}
		}
		else if(b3!=null) {
			if(subTeam==null) {
				errorMessage = "select subTeam";
			}
			else {
				if(student==null) {
					//req.setAttribute("model", model);
					req.setAttribute("model", model);
					//call the jsp and generate empty form
					req.getRequestDispatcher("/_view/teamView.jsp").forward(req, resp);
				}
				else {
					//req.setAttribute("model", model);
					req.setAttribute("model", model);
					//call the jsp and generate empty form
					req.getRequestDispatcher("/_view/studentView.jsp").forward(req, resp);
				}
			}
		}


	}
}
