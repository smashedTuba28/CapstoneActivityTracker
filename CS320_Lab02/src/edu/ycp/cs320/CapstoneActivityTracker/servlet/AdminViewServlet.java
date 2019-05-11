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
		
		//buttons for creating Capstone team and sub Team
		String cTeam = null;
		String cSub = null;
		
		//intiallizing controller and model for adminView
		AdminViewController controller = new AdminViewController();
		AdminView model = new AdminView();

		//setting the controller model
		controller.setModel(model);
		try {
			//requesting parameters from JSP
			topTeam = req.getParameter("topTeam").toString();
		}
		catch(NullPointerException e) {

		}
		try {
			subTeam = req.getParameter("subTeam").toString();
		}
		catch(NullPointerException e) {

		}
		try {
			student = req.getParameter("student").toString();
		}
		catch(NullPointerException e) {

		}
		try {
			b1 		= req.getParameter("b1").toString();
		}
		catch(NullPointerException e) {

		}
		try {
			b2 		= req.getParameter("b2").toString();
		}
		catch(NullPointerException e) {

		}
		try {
			b3 		= req.getParameter("b3").toString();
		}
		catch(NullPointerException e) {

		}try {
			cTeam 		= req.getParameter("cTeam").toString();
		}
		catch(NullPointerException e) {

		}
		try {
			cSub 		= req.getParameter("cSub").toString();
		}
		catch(NullPointerException e) {

		}
		
		if(cTeam==null && cSub==null) {
		if(b1!=null) {
			if(topTeam.equals("val")) {
				
				//intially populates TopTeams once page is opened
				controller.populateModelWithTopTeams();
				System.out.println(model.getTopTeamList());
				model.setSubTeamList(new ArrayList<SubTeam>());
				model.setStudents(new ArrayList<StudentAccount>());
				//req.setAttribute("model", model);
				req.setAttribute("model", model);

				//call the jsp and generate empty form
				req.getRequestDispatcher("/_view/adminView.jsp").forward(req, resp);
				
				
				
				
				
				/*errorMessage = "select topTeam";
				System.out.println(topTeam);
				controller.populateModelWithTopTeams();

				//req.setAttribute("model", model);
				req.setAttribute("model", model);
				req.getRequestDispatcher("/_view/adminView.jsp").forward(req, resp);*/
			}
			else{
				
				System.out.print(topTeam);
				controller.populateModelWithSubTeams(topTeam);
				//intially populates TopTeams once page is opened
				controller.populateModelWithTopTeams();
				System.out.println(model.getTopTeamList());
				model.setStudents(new ArrayList<StudentAccount>());
				//req.setAttribute("model", model);
				req.setAttribute("model", model);

				//call the jsp and generate empty form
				req.getRequestDispatcher("/_view/adminView.jsp").forward(req, resp);
				
				/*controller.getTopTeam(topTeam);
				controller.populateModelWithTopTeams();
				controller.populateModelWithSubTeams(topTeam);
				//req.setAttribute("model", model);
				req.setAttribute("model", model);
				//call the jsp and generate empty form
				req.getRequestDispatcher("/_view/adminView.jsp").forward(req, resp);*/
			}
		}
		else if(b2!=null) {
			if(subTeam.equals("val")) {
				errorMessage = "select subTeam";
				controller.getTopTeam(topTeam);
				controller.populateModelWithTopTeams();
				controller.populateModelWithSubTeams(topTeam);
			}
			else {
				controller.getTopTeam(topTeam);
				controller.getSubTeam(subTeam);
				controller.populateModelWithTopTeams();
				controller.populateModelWithSubTeams(topTeam);
				controller.populateModelWithStudents(subTeam);
				//req.setAttribute("model", model);
				req.setAttribute("model", model);
				//call the jsp and generate empty form
				req.getRequestDispatcher("/_view/adminView.jsp").forward(req, resp);
			}
		}
		else if(b3!=null) {
			if(subTeam.equals("val")) {
				errorMessage = "select subTeam";
				controller.getTopTeam(topTeam);
				controller.populateModelWithTopTeams();
				controller.populateModelWithSubTeams(topTeam);
				//req.setAttribute("model", model);
				req.setAttribute("model", model);
				//call the jsp and generate empty form
				req.getRequestDispatcher("/_view/adminView.jsp").forward(req, resp);
				
			}
			else {
				if(student==null) {
					req.getSession().setAttribute("subTeamname", subTeam);
					//get student view if successful login
					resp.sendRedirect(req.getContextPath() + "/teamView");
				}
				else {
					req.getSession().setAttribute("teammate_id", student);
					//get student view if successful login
					resp.sendRedirect(req.getContextPath() + "/studentView");
				}
			}
		}
		}
		else {
			if(cTeam!=null) {
				resp.sendRedirect(req.getContextPath() + "/createTopTeam");
			}
			else {
				resp.sendRedirect(req.getContextPath() + "/createSubTeam");
			}
		}


	}
}
