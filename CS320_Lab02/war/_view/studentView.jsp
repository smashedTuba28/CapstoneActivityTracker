<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- SomeImages taken from York College of Pennsylvania. The image links directly to the src it was taken from -->
<!-- styling is based of of code examples from w3schools.com-->

<html>
<head>
<title>Individual Student View</title>
<link rel="stylesheet" href="styling/style.css">

<!-- chart script from developers.google.com -->
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
 			google.charts.load('current', {'packages': ['bar']});
 			google.charts.setOnLoadCallback(drawChart);
 			
 			function drawChart() {
  	 		// Create the data table.			
  	 		
  	 		
        	var data = google.visualization.arrayToDataTable(${chartModel.data});
        	
       		// Set chart options
        	var options = {
       			title: '${chartModel.title}',
       			subtitle: '${model.student}',
       			colors: ['#008000'],
       			vAxis: {
       				title: "Time (Hours)",
       				viewWindowMode: 'explcit',
       				viewWindow: {
       					min:0
       				}
       			},
       			hAxis: {
       				title: "Date"
       			}
       		};
        
        	var chart = new google.charts.Bar(document.getElementById('columnchart_hours'));
        	chart.draw(data, google.charts.Bar.convertOptions(options));
 		}
		</script>



</head>

<body>
	<!-- Header -->
	<div class="header">
		<h1>Capstone Activity Tracker</h1>
		<a href="https://www.ycp.edu/"> <img
			src="https://www.ycp.edu/media/york-website/style-assets-2016/images/york-college-logo-white.svg"
			id="banner"></img>
		</a>
	</div>

	<!-- Navigation Bar -->
	<div class="navbar">
		<a href="http://localhost:8081/CapstoneActivityTracker/studentView">Home</a>
		<a href="http://localhost:8081/CapstoneActivityTracker/teamView">SubTeam</a>
		<a href="">Account</a> <a
			href="http://localhost:8081/CapstoneActivityTracker/index">Sign
			Out</a> <img
			src="https://www.ycp.edu/media/york-website/style-assets-2016/images/york-college-shield.svg"
			id="logo"></img>
	</div>

	<!-- The flexible grid (content) -->
	<div class="row">
		<div class="side">
			<h2>${account.firstname}<br>${account.lastname}</h2>
			<h3>${chartModel.topTeamName}</h3>
			<form>
				<div class="nameList">
					<div id="subTeamList"></div>
				</div>
			</form>
			<h4>${chartModel.mySubTeamName}</h4>
			<div class="nameList">
				<div id="studentList"></div>
			</div>
		</div>
		<div class="main">
			<div class="flex-container">
				<form action="${pageContext.servletContext.contextPath}/studentView"
					method="post">
					<input class="button" type="button" value="<"></input>
				</form>
				<div class="chart" id="columnchart_hours"></div>
				<form action="${pageContext.servletContext.contextPath}/studentView"
					method="post">
					<input class="button" type="button" value=">"></input>
				</form>
			</div>
			<p>Week Log notes:</p>
			<div>
				<c:forEach items="${chartModel.events}" var="events">
					<tr>
						<td class="time">${events.startTime}</td>
						<td class="log">${events.lognote}</td>
						</br>
					</tr>
				</c:forEach>
			</div>
		</div>
		<!-- Footer -->
		<div class="footer">
			<div class="flex-container">
				<div class="footnote">Designed by Jason Steinberg, Travis
					Wetzel, and William Taylor</div>
				<div class="footnote">Developed by Jason Steinberg and Travis
					Wetzel</div>
				<div class="footnote">York College of PA CS320 Spring 2019</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
			//ceates the submit buttons based needed size
			var docFrag = document.getElementById("studentList");
			var names = ${chartModel.studentNames};
		
			for (var i=0; i < names.length ; i++){
			     var elem = document.createElement('input');
			     elem.type = 'button';
			     elem.value = names[i];
			     var linebreak = document.createElement("br");
			     elem.appendChild(linebreak);
			     docFrag.appendChild(elem);
			}
		</script>
	<script type="text/javascript">
			//ceates the submit buttons based needed size
			var docFrag = document.getElementById("subTeamList");
			var names = ${chartModel.subTeamNames};
		
			for (var i=0; i < names.length ; i++){
			     var elem = document.createElement('input');
			     elem.type = 'button';
			     elem.value = names[i];
			     var linebreak = document.createElement("br");
			     elem.appendChild(linebreak);
			     docFrag.appendChild(elem);
			}
		</script>
</body>
</html>