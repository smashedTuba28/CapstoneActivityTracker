<!DOCTYPE html>

<! taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<! taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Welcome to the YCP Capstone Activity Tracker</title>
		
		<!-- chart script from developers.google.com -->
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		<script type="text/javascript">
 			google.charts.load('current', {'packages': ['bar']});
 			google.charts.setOnLoadCallback(drawChart);
 			
 			function drawChart() {
  	 		// Create the data table.			
  	 		
  	 		
        	var data = google.visualization.arrayToDataTable(${model.data});
        	
       		// Set chart options
        	var options = {'title': ' ${model.title} '};
        
        	var chart = new google.charts.Bar(document.getElementById('columnchart_hours'));
        	chart.draw(data, google.charts.Bar.convertOptions(options));
 		}
		</script>
	</head>

	<body>
		<!-- logout form -->
		<div>
			<form action="${pageContext.servletContext.contextPath}/index" method="get">
				<input name="index" type="submit" value="Sign Out" />
			</form>
		</div>
		
		<!-- header using student name -->
		<div>
			<h1> ${model.student} </h1>
		</div>
	
		<!-- HTML element for google chart -->
		<div id="columnchart_hours" style="width: 800px; height: 500px"></div>
		<div>
			<a href = "http://localhost:8081/CapstoneActivityTracker/teamView">Team View</a>
		</div>
	</body>
</html>