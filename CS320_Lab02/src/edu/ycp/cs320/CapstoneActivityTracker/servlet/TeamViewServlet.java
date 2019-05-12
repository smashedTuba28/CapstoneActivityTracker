package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CapstoneActivityTracker.controller.ChartController;
import edu.ycp.cs320.CapstoneActivityTracker.model.ChartModel;


public class TeamViewServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//retrieve jsp
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("TeamView Servlet: doGet");
		String account_id = null;
		String accountType = null;
		String subTeamname = null;
		try{
			account_id = req.getSession().getAttribute("account_id").toString();
		}catch(NullPointerException e) {}
		try {
			accountType = req.getSession().getAttribute("accountType").toString();
		}catch(NullPointerException e) {}
		try {
			subTeamname= req.getSession().getAttribute("subTeamname").toString();
		}catch(NullPointerException e) {}
		
		//check session
		if (account_id == null || accountType == null) {
			//redirect
			resp.sendRedirect(req.getContextPath() + "/signIn");
		}
		
		else {	
			ChartController controller = new ChartController();//create controller
			ChartModel model = new ChartModel();//create model
			controller.setModel(model);//populate model
			
			//this weeks start and end date
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.clear(Calendar.MINUTE);
			cal.clear(Calendar.SECOND);
			cal.clear(Calendar.MILLISECOND);
			
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
			Date start= new Date(cal.getTimeInMillis());
			
			cal.add(Calendar.WEEK_OF_YEAR, 1);
			Date end = new Date(cal.getTimeInMillis());
			

			controller.getTeamInfoForAccount(Integer.parseInt(account_id), accountType);
			
			if(subTeamname != null) {
				controller.populateSubTeamWeekWithTeamName(subTeamname, start, end, 0);
			} 
			else {
				//populates model with needed information for jsp
				controller.populateSubTeamWeek(account_id, start, end, 0);
			}
			req.setAttribute("chartModel", model);
			//call the jsp and generate empty form
			req.getRequestDispatcher("/_view/teamView.jsp").forward(req, resp);
			
		}
	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		System.out.println("TeamView Servlet: doPost");
	
		
		String account_id = null;
		String accountType = null;
		
		try{
			account_id = req.getSession().getAttribute("account_id").toString();
		}catch(NullPointerException e) {}
		try {
			accountType = req.getSession().getAttribute("accountType").toString();
		}catch(NullPointerException e) {}
		
		if (account_id == null || accountType == null) {
			//redirect
			resp.sendRedirect(req.getContextPath() + "/signIn");
		}
		
		ChartModel chartModel = new ChartModel();
		ChartController controller = new ChartController();
		controller.setModel(chartModel);
		
		Integer offset =  null;
		Integer change = null;
		String currentSub = null;
		String newSub = null;
		
		try {
			offset = Integer.parseInt(req.getParameter("offset").toString());
		}catch(NullPointerException e){}
		try {
			change = Integer.parseInt(req.getParameter("change").toString());
		}catch(NullPointerException e){}
		try {
			currentSub = req.getParameter("currentSub").toString();
		}catch(NullPointerException e){}
		try {
			newSub = req.getParameter("subTeamButton").toString();
		}catch(NullPointerException e){}
	
		System.out.println(currentSub);
		
		//get current day but clear out time to 00:00:00:00
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);

		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		Date start= new Date(cal.getTimeInMillis());

		cal.add(Calendar.WEEK_OF_YEAR, 1);
		Date end = new Date(cal.getTimeInMillis());
		System.out.println("Post Start: " + start.toString());
		System.out.println("Post End: " + end.toString());
		
		
		try{//fills in side bar info for student
			controller.getTeamInfoForAccount(Integer.parseInt(account_id), accountType);			
		}catch(NullPointerException e) {
			chartModel.setTopTeamName("Contact an admin to be assigned to a team");
			chartModel.setSubTeamNames("");
			chartModel.setStudentNames("");
		}
		
		//determine which week to show
		if(offset != null) {
			if(change != null) {
				if(change.equals(-1)) {
					//decrement offset by 1
					offset -= 1;
				}
				else {
					//increment offset by 1;
					offset += 1;
				}
			}
			//offset ready to make change away from current week
			//start at beginning of current week
			System.out.println(offset);
			
			cal.setTime(start);	
			cal.add(Calendar.WEEK_OF_YEAR, offset);	
			start = new Date(cal.getTimeInMillis());	
			cal.add(Calendar.WEEK_OF_YEAR, 1);	
			end = new Date(cal.getTimeInMillis());
			System.out.println("New Start: " + start.toString());
			System.out.println("New end: " + end.toString());
		}else {
			offset = 0; //show current week
		}
		if(newSub!=null) {
			System.out.println(newSub);
			controller.populateSubTeamWeekWithTeamName(newSub, start, end, offset);
			req.setAttribute("chartModel", chartModel);
			req.getRequestDispatcher("/_view/teamView.jsp").forward(req, resp);
		}
		controller.populateSubTeamWeekWithTeamName(currentSub, start, end, offset);
		req.setAttribute("chartModel", chartModel);
		req.getRequestDispatcher("/_view/teamView.jsp").forward(req, resp);	
	}
}