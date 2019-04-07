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
	        var data = google.visualization.arrayToDataTable([
	          ['Date', 'Jason Steinberg', 'Travis Wetzel', 'William Taylor', 'Robert California'],
	          ['3-19', wk0dur0, wk1dur0, wk2dur0, wk3dur0],
	          ['3-20', wk0dur1, wk1dur1, wk2dur1, wk3dur1],
	          ['3-21', wk0dur2, wk1dur2, wk2dur2, wk3dur2],
	          ['3-22', wk0dur3, wk1dur3, wk2dur3, wk3dur3],
	          ['3-23', wk0dur4, wk1dur4, wk2dur4, wk3dur4],
	          ['3-24', wk0dur5, wk1dur5, wk2dur5, wk3dur5],
	          ['3-25', wk0dur6, wk1dur6, wk2dur6, wk3dur6],
	        ]);

	        var options = {
	            title: '${model.title}'
	        };

	        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));
	        chart.draw(data, google.charts.Bar.convertOptions(options));
	      }

		</script>
	</head>

	<body>
		<!-- logout form -->
		<div>
			<form action="${pageContext.servletContext.contextPath}/index" method="get">
				<input name="signOut" type="submit" value="Sign Out"
				\/form>
		</div>
	
		<!-- HTML element for google chart -->
		<div id="columnchart_material" style="width: 800px; height: 500px"></div>

		<!-- Link to subteams displayed here -->
		<div id="subteams"></div>
		
		<a href = "http://localhost:8081/CapstoneActivityTracker/studentView">Student View</a>
		
		
	</body>
</html>