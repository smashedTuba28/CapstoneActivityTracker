package edu.ycp.cs320.CapstoneActivityTracker.model;

import java.util.ArrayList;

public class ChartModel {
		private String data;
		private String student;
		private String title;
		private String topTeamName;
		private String subTeamNames;
		private String studentNames;
		private String mySubTeamName;
		private ArrayList<>
		
		public ChartModel() {
		}
		
		public void setData(String data) {
			this.data = data;
		}
		public void setStudent(String student) {
			this.student = student;
		} 
		public void setTitle(String title) {
			this.title = title;
		}
		
		public String getData() {
			return data;
		}
		public String getStudent() {
			return student;
		}
		public String getTitle() {
			return title;
		}


		public String getTopTeamName() {
			return topTeamName;
		}


		public void setTopTeamName(String topTeamName) {
			this.topTeamName = topTeamName;
		}


		public String getSubTeamNames() {
			return subTeamNames;
		}


		public void setSubTeamNames(String subTeamNames) {
			this.subTeamNames = subTeamNames;
		}


		public String getStudentNames() {
			return studentNames;
		}


		public void setStudentNames(String studentNames) {
			this.studentNames= studentNames;
		}


		public String getMySubTeamName() {
			return mySubTeamName;
		}


		public void setMySubTeamName(String mySubTeamName) {
			this.mySubTeamName = mySubTeamName;
		}
}
