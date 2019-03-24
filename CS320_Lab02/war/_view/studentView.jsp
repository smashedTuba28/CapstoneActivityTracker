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
		<div id="columnchart_material" style="width: 800px; height: 500px"></div>
		<!-- field to enter a lognote -->
		<tr>
			<td><input type ="hidden" name="newNote" value="${newNote}">
 		</tr>
 		<input type="Submit" name="submit" value="Creat Lognote">			

		<!-- Hidden fields to be used for chart creation -->
		<!-- Duration in each day of the week -->
		<table>
			<tr>
				<td><input type ="hidden" name="dur0" size="12" value="${week.dur0}">
				<td><input type ="hidden" name="dur1" size="12" value="${week.dur1}">
				<td><input type ="hidden" name="dur2" size="12" value="${week.dur2}">
				<td><input type ="hidden" name="dur3" size="12" value="${week.dur3}">
				<td><input type ="hidden" name="dur4" size="12" value="${week.dur4}">
				<td><input type ="hidden" name="dur5" size="12" value="${week.dur5}">
				<td><input type ="hidden" name="dur6" size="12" value="${week.dur6}">
			</tr>
		<!-- lognotes for each day -->
			<tr>
				<td><input type ="hidden" name="log0" size="12" value="${logs.log0}">
				<td><input type ="hidden" name="log1" size="12" value="${logs.log1}">
				<td><input type ="hidden" name="log2" size="12" value="${logs.log2}">
				<td><input type ="hidden" name="log3" size="12" value="${logs.log3}">
				<td><input type ="hidden" name="log4" size="12" value="${logs.log4}">
				<td><input type ="hidden" name="log5" size="12" value="${logs.log5}">
				<td><input type ="hidden" name="log6" size="12" value="${logs.log6}">
			</tr>
		</table>
	</body>
</html>