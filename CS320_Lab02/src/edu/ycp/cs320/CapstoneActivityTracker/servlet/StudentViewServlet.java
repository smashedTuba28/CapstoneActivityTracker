package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CapstoneActivityTracker.model.ChartModel;
import edu.ycp.cs320.CapstoneActivityTracker.controller.ChartController;;

public class StudentViewServlet  extends HttpServlet { 
	
	private static final long serialVersionUID = 1L;
	
	//retrieve jsp
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("StudentView Servlet: doGet");
		String account_id = null;
		String accountType = null;
		try{
			account_id = req.getSession().getAttribute("account_id").toString();
		}catch(NullPointerException e) {}
		
		//check session 
		
		if (account_id == null) {
			//redirect
			resp.sendRedirect(req.getContextPath() + "/signIn");
		}
		else {	
			ChartController chartController = new ChartController();//create controller
			ChartModel chartModel = new ChartModel();//create model
			chartController.setModel(chartModel);//populate model 
			
			try {
				accountType = req.getSession().getAttribute("accountType").toString();
			}catch(NullPointerException e) {}
			chartController.getTeamInfoForAccount(Integer.parseInt(account_id), accountType);			
			
			
			Date end = new Date();
			end.setHours(24);
			end.setMinutes(0);
			end.setSeconds(0);
			
			Date start = new Date(end.getTime() - 604800000);
	
			//System.out.println("Start Time: " + start.toString());
			//System.out.println("End Time: " + end.toString());
			
			
			//find if needing logged in student or another individual
			String teammate_id = null;
			
			try {
				teammate_id = req.getSession().getAttribute("teammate_id").toString();
			}catch(NullPointerException e) {}
			
			if(teammate_id == null) {//no teammate use logged in account
				chartController.populateStudentWeek(Integer.parseInt(account_id), start, end);
			}
			else {//teammate info being requested
				chartController.populateStudentWeek(Integer.parseInt(teammate_id), start, end);
			}
			
			req.setAttribute("chartModel", chartModel);
			
			System.out.println("Made IT");
			//call the jsp and generate empty form
			req.getRequestDispatcher("/_view/studentView.jsp").forward(req, resp);
		}
	}	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		System.out.println("StudentView Servlet: doPost");
		
		String account_id = null;
		String accountType = null;
		ChartModel chartModel = new ChartModel();
		ChartController controller = new ChartController();
		controller.setModel(chartModel);
		
		try{
			account_id = req.getSession().getAttribute("account_id").toString();
		}catch(NullPointerException e) {}
		try {
			accountType = req.getSession().getAttribute("accountType").toString();
		}catch(NullPointerException e) {}
		
		
		String teammate = null;
		String subTeamTeamname = null;
		String currentSubTeam = null;
		
		
		try {
			teammate = req.getParameter("studentButton").toString();
		}catch(NullPointerException e){}
		try {
			subTeamTeamname = req.getParameter("subTeamButton").toString();
		}catch(NullPointerException e){}
		try {
			currentSubTeam = req.getParameter("currentSub").toString();
		}catch(NullPointerException e){}
		
		Date end = new Date();
		end.setHours(24);
		end.setMinutes(0);
		end.setSeconds(0);
		Date start = new Date(end.getTime() - 604800000);
		
		
		if(teammate != null) {
			String[] name = teammate.split(" ");
			System.out.println(name[0]);
			System.out.println(name[1]);
			System.out.println(currentSubTeam);
			controller.populateStudentWeek(name, currentSubTeam, start, end);
			controller.getTeamInfoForAccount(Integer.parseInt(account_id), accountType);
			System.out.println(chartModel.getData());
			req.setAttribute("chartModel", chartModel);
			req.getRequestDispatcher("/_view/studentView.jsp").forward(req, resp);
		}
		
		
		
		
		
		
	}
	
	
	
	
	
}
