package edu.ycp.cs320.CapstoneActivityTracker.servlet;

import java.io.IOException;
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
		String email = (String) req.getSession().getAttribute("userEmail");
		
		//check session
		if ( email == null) {
			//redirect to signIn
			resp.sendRedirect(req.getContextPath() + "/signIn");
		}
		
		else {	
			ChartController controller = new ChartController();//create controller
			ChartModel model = new ChartModel();//create model
			controller.setModel(model);//populate model
			
			Date end = new Date();
			end.setHours(24);
			end.setMinutes(0);
			end.setSeconds(0);
			Date start = new Date(end.getTime() - 604800000);
			
			//populates model with needed information for jsp
			controller.populateTopTeamWeek(email, start, end);
			
			req.setAttribute("model", model);
			//call the jsp and generate empty form
			req.getRequestDispatcher("/_view/teamView.jsp").forward(req, resp);
		}
	}		
}