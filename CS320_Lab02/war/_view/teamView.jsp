<!DOCTYPE html>

<! taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<! taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
		<title>Your Team View</title>
		<script>
		// script function for reading from text fields
		function readText(form){
			data = form.inputbox.value;
		}
		</script>
		
		<script>
		//script function for writing to text fields
		function writeText(form, var data){
			form.inputbox.value = data;
		}
		</script>
		
		<script>
			// week and time durations for team members
			/*var wk0dur0 = document.getElementById("wk0dur0");
			var wk0dur1 = document.getElementById("wk0dur0");
			var wk0dur2 = document.getElementById("wk0dur0");
			var wk0dur3 = document.getElementById("wk0dur0");
			var wk0dur4 = document.getElementById("wk0dur0");
			var wk0dur5 = document.getElementById("wk0dur0");
			var wk0dur6 = document.getElementById("wk0dur0");
			var wk1dur0 = document.getElementById("wk0dur0");
			var wk1dur1 = document.getElementById("wk0dur0");
			var wk1dur2 = document.getElementById("wk0dur0");
			var wk1dur3 = document.getElementById("wk0dur0");
			var wk1dur4 = document.getElementById("wk0dur0");
			var wk1dur5 = document.getElementById("wk0dur0");
			var wk1dur6 = document.getElementById("wk0dur0");
			var wk2dur0 = document.getElementById("wk0dur0");
			var wk2dur1 = document.getElementById("wk0dur0");
			var wk2dur2 = document.getElementById("wk0dur0");
			var wk2dur3 = document.getElementById("wk0dur0");
			var wk2dur4 = document.getElementById("wk0dur0");
			var wk2dur5 = document.getElementById("wk0dur0");
			var wk2dur6 = document.getElementById("wk0dur0");
			var wk3dur0 = document.getElementById("wk0dur0");
			var wk3dur1 = document.getElementById("wk0dur0");
			var wk3dur2 = document.getElementById("wk0dur0");
			var wk3dur3 = document.getElementById("wk0dur0");
			var wk3dur4 = document.getElementById("wk0dur0");
			var wk3dur5 = document.getElementById("wk0dur0");
			var wk3dur6 = document.getElementById("wk0dur0");*/
			var wk0dur0 = parseFloat(${week0.dur0}) / 60;
  	 		var wk0dur1 = parseFloat(${week0.dur1}) / 60;
  	 		var wk0dur2 = parseFloat(${week0.dur2}) / 60;
  	 		var wk0dur3 = parseFloat(${week0.dur3}) / 60;
  	 		var wk0dur4 = parseFloat(${week0.dur4}) / 60;
  	 		var wk0dur5 = parseFloat(${week0.dur5}) / 60;
  	 		var wk0dur6 = parseFloat(${week0.dur6}) / 60;
			var wk1dur0 = parseFloat(${week1.dur0}) / 60;
			var wk1dur1 = parseFloat(${week1.dur1}) / 60;
			var wk1dur2 = parseFloat(${week1.dur2}) / 60;
			var wk1dur3 = parseFloat(${week1.dur3}) / 60;
			var wk1dur4 = parseFloat(${week1.dur4}) / 60;
			var wk1dur5 = parseFloat(${week1.dur5}) / 60;
			var wk1dur6 = parseFloat(${week1.dur6}) / 60;
			var wk2dur0 = parseFloat(${week2.dur0}) / 60;
			var wk2dur1 = parseFloat(${week2.dur1}) / 60;
			var wk2dur2 = parseFloat(${week2.dur2}) / 60;
			var wk2dur3 = parseFloat(${week2.dur3}) / 60;
			var wk2dur4 = parseFloat(${week2.dur4}) / 60;
			var wk2dur5 = parseFloat(${week2.dur5}) / 60;
			var wk2dur6 = parseFloat(${week2.dur6}) / 60;
			var wk3dur0 = parseFloat(${week3.dur0}) / 60;
			var wk3dur1 = parseFloat(${week3.dur1}) / 60;
			var wk3dur2 = parseFloat(${week3.dur2}) / 60;
			var wk3dur3 = parseFloat(${week3.dur3}) / 60;
			var wk3dur4 = parseFloat(${week3.dur4}) / 60;
			var wk3dur5 = parseFloat(${week3.dur5}) / 60;
			var wk3dur6 = parseFloat(${week3.dur6}) / 60;
		</script>
		
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
	          chart: {
	            title: 'Drone Team',
	            subtitle: 'Hours worked by student',
	          }
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
				<input name="signOut" type="submit" value="Sign Out" />
		</div>
	
		<!-- HTML element for google chart -->
		<div id="columnchart_material" style="width: 800px; height: 500px"></div>

		<!-- Link to subteams displayed here -->
		<div id="subteams"></div>
		
		<a href = "http://localhost:8081/CapstoneActivityTracker/studentView">Student View</a>
		
		
		

		<!-- Hidden fields to be used for chart creation -->
		<!-- Duration in each day of the week -->
		<table>
			<tr>
				<td><input type ="hidden" name="wk0dur0" size="12" value="${week0.dur0}">
				<td><input type ="hidden" name="wk0dur1" size="12" value="${week0.dur1}">
				<td><input type ="hidden" name="wk0dur2" size="12" value="${week0.dur2}">
				<td><input type ="hidden" name="wk0dur3" size="12" value="${week0.dur3}">
				<td><input type ="hidden" name="wk0dur4" size="12" value="${week0.dur4}">
				<td><input type ="hidden" name="wk0dur5" size="12" value="${week0.dur5}">
				<td><input type ="hidden" name="wk0dur6" size="12" value="${week0.dur6}">
			</tr>
			<tr>
				<td><input type ="hidden" name="wk1dur0" size="12" value="${week1.dur0}">
				<td><input type ="hidden" name="wk1dur1" size="12" value="${week1.dur1}">
				<td><input type ="hidden" name="wk1dur2" size="12" value="${week1.dur2}">
				<td><input type ="hidden" name="wk1dur3" size="12" value="${week1.dur3}">
				<td><input type ="hidden" name="wk1dur4" size="12" value="${week1.dur4}">
				<td><input type ="hidden" name="wk1dur5" size="12" value="${week1.dur5}">
				<td><input type ="hidden" name="wk1dur6" size="12" value="${week1.dur6}">
			</tr>
			<tr>
				<td><input type ="hidden" name="wk2dur0" size="12" value="${week2.dur0}">
				<td><input type ="hidden" name="wk2dur1" size="12" value="${week2.dur1}">
				<td><input type ="hidden" name="wk2dur2" size="12" value="${week2.dur2}">
				<td><input type ="hidden" name="wk2dur3" size="12" value="${week2.dur3}">
				<td><input type ="hidden" name="wk2dur4" size="12" value="${week2.dur4}">
				<td><input type ="hidden" name="wk2dur5" size="12" value="${week2.dur5}">
				<td><input type ="hidden" name="wk2dur6" size="12" value="${week2.dur6}">
			</tr>
			<tr>
				<td><input type ="hidden" name="wk3dur0" size="12" value="${week3.dur0}">
				<td><input type ="hidden" name="wk3dur1" size="12" value="${week3.dur1}">
				<td><input type ="hidden" name="wk3dur2" size="12" value="${week3.dur2}">
				<td><input type ="hidden" name="wk3dur3" size="12" value="${week3.dur3}">
				<td><input type ="hidden" name="wk3dur4" size="12" value="${week3.dur4}">
				<td><input type ="hidden" name="wk3dur5" size="12" value="${week3.dur5}">
				<td><input type ="hidden" name="wk3dur6" size="12" value="${week3.dur6}">
			</tr>
		</table>

	</body>
</html>