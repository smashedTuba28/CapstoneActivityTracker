package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CapstoneActivityTracker.controller.AssignStudentController;
import edu.ycp.cs320.CapstoneActivityTracker.model.AssignStudent;


public class AssignStudentServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("AssignStudent Servlet: doGet");

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
			//initializing controller and model for assignStudent
			AssignStudentController controller = new AssignStudentController();
			AssignStudent model = new AssignStudent();
			controller.setModel(model);
			
			//initially populates subTeams and Students
			controller.populateModelWithStudentsandSubTeams();
			req.setAttribute("model", model);

			//call the JSP and generate empty form
			req.getRequestDispatcher("/_view/assignStudent.jsp").forward(req, resp);

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{

		System.out.println("AssignStudent Servlet: doPost");

		//initializing controller and model for assignStudent
		AssignStudentController controller = new AssignStudentController();
		AssignStudent model = new AssignStudent();
		controller.setModel(model);


		//error message String to hold message text when applicable
		String errorMessage = null;
		String subTeam = null;
		String student = null;
		String createAssignment = null;

		try {
			//decode POSted from parameter and dispatch to controller
			subTeam = req.getParameter("subTeam").toString();

		}
		catch(NullPointerException e) {

		}
		try {
			//decode POSted from parameter and dispatch to controller
			student = req.getParameter("student").toString();

		}
		catch(NullPointerException e) {

		}
		try {
			//decode POSted from parameter and dispatch to controller
			createAssignment = req.getParameter("createTeam").toString();

		}
		catch(NullPointerException e) {

		}

		//initially populates subTeams and Students
		controller.populateModelWithStudentsandSubTeams();
		
		//check that none are empty
		if(createAssignment != null) {
			if (subTeam.equals("null") ) {
				errorMessage = "Please Enter a Team Name";
				System.out.println("subTeam==null");
				req.setAttribute("model", model);
				req.getRequestDispatcher("/_view/createSubTeam.jsp").forward(req, resp);
			}
			else {
				if(student.equals("val")) {
					System.out.println("need to select a Student");
					errorMessage = "Please select a Student";
					req.setAttribute("model", model);
					req.getRequestDispatcher("/_view/createSubTeam.jsp").forward(req, resp);
				}
				else if (student.equals("null")) {
					System.out.println("student: null");
					req.setAttribute("model", model);
					req.getRequestDispatcher("/_view/createSubTeam.jsp").forward(req, resp);
				}
				else {
					controller.assignStudentToSubTeam(subTeam, student);
					errorMessage = "Team Successfully Created!";
					System.out.println("Team Successfully Created!");
					req.getRequestDispatcher("/_view/createSubTeam.jsp").forward(req, resp);
				}
			}
		}
	}
}
