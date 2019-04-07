<!DOCTYPE html>

<! taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<! taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
		<title>Your Team View</title>	
		<!-- chart script assistance from developers.google.com -->
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		<script type="text/javascript">
	      	google.charts.load('current', {'packages':['bar']});
	      	google.charts.setOnLoadCallback(drawChart);

	      function drawChart() {
	        var data = google.visualization.arrayToDataTable(${model.data});

	        var options = {
	            title: '${model.title}'
	        };

	        var chart = new google.charts.Bar(document.getElementById('columnchart_hours'));
	        chart.draw(data, google.charts.Bar.convertOptions(options));
	      }

		</script>
	</head>

	<body>
		<!-- logout form -->
		<div>
			<form action="${pageContext.servletContext.contextPath}/index" method="get">
				<input name="signOut" type="submit" value="Sign Out">
			</form>
		</div>
	
		<!-- HTML element for google chart -->
		<div id="columnchart_hours" style="width: 800px; height: 500px"></div>

		<!-- Link to subteams displayed here -->
		<div id="subteams"></div>
		
		<a href = "http://localhost:8081/CapstoneActivityTracker/studentView">Student View</a>
		
		
	</body>
</html>