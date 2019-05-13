<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- SomeImages taken from York College of Pennsylvania. The image links directly to the src it was taken from -->
<!-- styling is based of of code examples from w3schools.com-->

<html>
<head>
<title>Individual Student View</title>
<link rel="stylesheet" href="styling/style.css">
<!--icon for ycp shield from ycp.edu-->
<link rel="icon" href="https://www.ycp.edu/media/york-website/style-assets-2016/images/favicon.ico" type="image/ico">
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
       			subtitle: '${chartModel.currentStudent}',
       			colors: ['#008000'],
       			chartArea: {
       				width: "75%",
       				heigth: "50%"
       			},
       			vAxis: {
       				viewWindowMode: 'explcit',
       				viewWindow: {
       					min:0,
       					max:24
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
		<a href="">Account</a> 
		<a href="http://localhost:8081/CapstoneActivityTracker/index">SignOut</a> 
		<img src="https://www.ycp.edu/media/york-website/style-assets-2016/images/york-college-shield.svg"
			id="logo"></img>
	</div>

	<!-- The flexible grid (content) -->
	<div class="row">
		<div class="side">
			<h2>${chartModel.student}</h2>
			<h3>${chartModel.topTeamName}</h3>
				<div class="nameList">
					<form action="${pageContext.servletContext.contextPath}/teamView" method="post">
						<div id="subTeamList"></div>
					</form>
				</div>
			<h4>${chartModel.mySubTeamName}</h4>
			<div class="nameList">
				<form action="${pageContext.servletContext.contextPath}/studentView" method="post">
					<input type="hidden" name="currentSub" value="${chartModel.mySubTeamName}"/>
					<div id="studentList"></div>
				</form>
			</div>
		</div>
		<div class="main">
			<div class="flex-container">
				<form action="${pageContext.servletContext.contextPath}/studentView" method="post">
					<input class="button" type="submit" value="<"></input>
					<input type="hidden" name="change" value="-1"/>
					<input type="hidden" name="currentSub" value="${chartModel.mySubTeamName}"/>
					<input type="hidden" name="s" value="${chartModel.currentStudent}"/>
					<input type="hidden" name="offset" value="${chartModel.offset}"/>
				</form>
				<div class="chart" id="columnchart_hours"></div>
				<form action="${pageContext.servletContext.contextPath}/studentView" method="post">
					<input class="button" type="submit" value=">"></input>
					<input type="hidden" name="change" value="1"/>
					<input type="hidden" name="currentSub" value="${chartModel.mySubTeamName}"/>
					<input type="hidden" name="s" value="${chartModel.currentStudent}"/>
					<input type="hidden" name="offset" value="${chartModel.offset}"/>
				</form>
			</div>
			
			
			<p>Week Lognotes:</p>
			<div class="flex-container">
				<form action="${pageContext.servletContext.contextPath}/studentView" method="post">
					<c:forEach items="${chartModel.events}" var="events">
						<div class="flex-containerLogs">
							<div class="flagbox">
								<c:if test="${events.flag}">
									<img alt="System Flagged Event" class="flag" src="styling/clipart786779.png"> <!--png from clipartmax.com -->
								</c:if>
								<c:if test="${!events.flag}">
									</br>
								</c:if>
							</div>
							<div class="timebox">
								<p class="time">${events.startTime}-${events.endTime}::</p>
							</div>
							<form action="${pageContext.servletContext.contextPath}/studentView" method="post">
								<c:if test="${chartModel.currentStudent.equals(chartModel.student)}">
									<div class="logbox">
										<input type="hidden" name="offset" value="${chartModel.offset}"/>
										<input type="hidden" name="event_id" value="${events.roomEventID}"/>
										<input type="hidden" name="s" value="${chartModel.currentStudent}"/>
										<input type="text" size="80" name="lognote" value="${events.lognote}"/>
										</br>
									</div>
									<input type="submit" name="logButton" value="Update Me"/>
								</c:if>
							</form>
							<c:if test="${! chartModel.currentStudent.equals(chartModel.student)}">
								<div class="logbox">
									<p>${events.lognote}</p>
									</br>
								</div>
							</c:if>
						</div>
					</c:forEach>
				</form>		
			</div>
			
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
		
	<script type="text/javascript">
			//ceates the submit buttons based needed size
			var docFrag = document.getElementById("studentList");
			var names = ${chartModel.studentNames};
		
			for (var i=0; i < names.length ; i++){
			     var elem = document.createElement('input');
			     elem.type = 'submit';
			     elem.value = names[i];
			     elem.name = "studentButton";
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
			     elem.type = 'submit';
			     elem.value = names[i];
			     elem.name = "subTeamButton"
			     var linebreak = document.createElement("br");
			     elem.appendChild(linebreak);
			     docFrag.appendChild(elem);
			}
		</script>
</body>
</html>