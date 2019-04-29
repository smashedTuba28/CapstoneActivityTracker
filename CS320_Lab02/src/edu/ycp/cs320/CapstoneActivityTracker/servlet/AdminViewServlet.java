package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CapstoneActivityTracker.controller.ChartController;
import edu.ycp.cs320.CapstoneActivityTracker.model.ChartModel;

public class AdminViewServlet extends HttpServlet { 
	
	private static final long serialVersionUID = 1L;
	
	//retrieve jsp
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("StudentView Servlet: doGet");
	
	
		String account_id = req.getSession().getAttribute("account_id").toString();
		
		//check session
		if ( account_id == null) {
			//redirect
			resp.sendRedirect(req.getContextPath() + "/signIn");
		}
		else {	
			
			//req.setAttribute("model", model);
			//call the jsp and generate empty form
			req.getRequestDispatcher("/_view/adminView.jsp").forward(req, resp);
		}
	}		
}
