<!DOCTYPE html>

<! taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<! taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
		<title>Welcome to the YCP Capstone Activity Tracker</title>
		
		<!-- getters/setters for Javascript -->
		<script>
			function readText (id) {	
   		 		var data = document.getElementById('id').value;
   		 		return data;
			}

			function writeText (id, data) {
    			id.inputbox.value = data;
			}
		</script>
		
		<!-- chart script from developers.google.com -->
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		<script type="text/javascript">
 			google.charts.load('current', {'packages': ['bar']});
 			google.charts.setOnLoadCallback(drawChart);
 
  	 		var d0 = readText(dur0);
  	 		var d1 = 2;
  	 		var d2 = 3;
  	 		var d3 = 0;
  	 		var d4 = 5;
  	 		var d5 = 8;
  	 		var d6 = 8;
  	 		
 			function drawChart() {
  	 		// Create the data table.
  	 		var date1 = new Date();
  	 		var date2 = new Date();
  	

        	var data = google.visualization.arrayToDataTable([
          	['Date', 'Hours'],
          	['3-19', d0],
          	['3-20', 2],
          	['3-21', 3],
          	['3-22', 5],
          	['3-23', 0],
          	['3-24', 8],
          	['3-25', 8],
        	]);
      	
       		// Set chart options
        	var options = {'title':'Weekly Activity'};
        
        	var chart = new google.charts.Bar(document.getElementById('columnchart_material'));
        
        	chart.draw(data, google.charts.Bar.convertOptions(options));
 		}
		</script>
	</head>

	<body>
		<!-- heading with student name -->
		<div>
			<p id="studentFirstname" value="${account.firstname}">${account.firstname}</p>
			<p id="studentLastname" value="${account.lastname }"> ${account.lastname}</p>
		</div>
		<!-- logout form -->
		<div>
			<form action="${pageContext.servletContext.contextPath}/index" method="get">
				<input name="signOut" type="submit" value="Sign Out" />
		</div>
	
		<!-- HTML element for google chart -->
		<div id="columnchart_material" style="width: 800px; height: 500px"></div>
		
		<!-- lognote to be displayed -->
		
		<div>
			<form action="${pageContext.servletContext.contextPath}/teamView" method="get">
				<input name="teamView" type="submit" value="See Your Team Progress" />
		</div>
		
		<!-- field to enter a lognote -->
		<tr>
			<td><input type ="hidden" name="newNote" value="${newNote}">
 		</tr>
 		<input type="Submit" name="submit" value="Creat Lognote">
 		
 			

		<!-- Hidden fields to be used for chart creation -->
		<!-- Duration in each day of the week -->
		
		<form name="servletData">
			<table>
				<tr>
					<!--  \${week.dur0} -->
					<td><input type ="hidden" id="dur0" name="dur0" size="12" value="2">
					<td><input type ="hidden" id="dur1" name="dur1" size="12" value=2>
					<td><input type ="hidden" id="dur2" name="dur2" size="12" value=2>
					<td><input type ="hidden" id="dur3" name="dur3" size="12" value=2>
					<td><input type ="hidden" id="dur4" name="dur4" size="12" value=2>
					<td><input type ="hidden" id="dur5" name="dur5" size="12" value=2>
					<td><input type ="hidden" id="dur6" name="dur6" size="12" value=2>
				</tr>
				<!-- lognotes for each day -->
				<tr>
					<td><input type ="hidden" id="log0" name="log0" size="12" value="${logs.log0}">
					<td><input type ="hidden" id="log1" name="log1" size="12" value="${logs.log1}">
					<td><input type ="hidden" id="log2" name="log2" size="12" value="${logs.log2}">
					<td><input type ="hidden" id="log3" name="log3" size="12" value="${logs.log3}">
					<td><input type ="hidden" id="log4" name="log4" size="12" value="${logs.log4}">
					<td><input type ="hidden" id="log5" name="log5" size="12" value="${logs.log5}">
					<td><input type ="hidden" id="log6" name="log6" size="12" value="${logs.log6}">
				</tr>
			</table>
		</form>
	</body>
</html>