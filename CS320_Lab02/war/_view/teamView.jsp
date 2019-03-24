<!DOCTYPE html>

<! taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<! taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
		<title>Your Team View</title>
		
		<script>
			// week and time durations for team members
			var wk0dur0 = '';
			var wk0dur1 = '';
			var wk0dur2 = '';
			var wk0dur3 = '';
			var wk0dur4 = '';
			var wk0dur5 = '';
			var wk0dur6 = '';
			var wk1dur0 = '';
			var wk1dur1 = '';
			var wk1dur2 = '';
			var wk1dur3 = '';
			var wk1dur4 = '';
			var wk1dur5 = '';
			var wk1dur6 = '';
			var wk2dur0 = '';
			var wk2dur1 = '';
			var wk2dur2 = '';
			var wk2dur3 = '';
			var wk2dur4 = '';
			var wk2dur5 = '';
			var wk2dur6 = '';
			var wk3dur0 = '';
			var wk3dur1 = '';
			var wk3dur2 = '';
			var wk3dur3 = '';
			var wk3dur4 = '';
			var wk3dur5 = '';
			var wk3dur6 = '';
		</script>
		
		<!-- chart script from developers.google.com -->
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		<script type="text/javascript">
 			google.charts.load('current', {'packages': ['bar']});
 			google.charts.setOnLoadCallback(drawChart);
 		
 		function drawChart() {
  	 	// Create the data table.
  	 	var d = new Date();
  	 	var date;
        var data = google.visualization.arrayToDataTable([
          ['Date', 'Hours'],
          ['3-19', 3],
          ['3-20', 2],
          ['3-21', 4],
          ['3-22', 0],
          ['3-23', 0],
          ['3-24', 8],
          ['3-25', 2],
        ]);

      	
        // Set chart options
        var options = {'title':'Weekly Activity'};
        
        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));
        
        chart.draw(data, google.charts.Bar.convertOptions(options));
 		}
		</script>
	</head>

	<body>

	
		<!-- HTML element for google chart -->
		<div id="columnchart_material" style="width: 800px; height: 500px"></div>

		<!-- Link to subteams displayed here -->
		<div id="subteams"></div>

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
