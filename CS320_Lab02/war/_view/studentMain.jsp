<!DOCTYPE html>

<! taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<! taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
		<title>Welcome to the YCP Capstone Activity Tracker</title>
		
		<!--- chart script from developers.google.com --->
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
		<div><script>document.write("I hate Javascript");</script></div>
	</body>
</html>
