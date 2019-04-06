package edu.ycp.cs320.CapstoneActivityTracker.model;

public class ChartModel {
		private String data;
		private String student;
		private String title;
		
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
}
