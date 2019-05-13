<!DOCTYPE html>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<html>
	<head>
		<title>Team View</title>	
		
		<link rel="stylesheet" href="styling/style.css">
		<!--icon for ycp shield from ycp.edu-->
		<link rel="icon" href="https://www.ycp.edu/media/york-website/style-assets-2016/images/favicon.ico" type="image/ico">

		
		<!-- chart script assistance from developers.google.com -->
		 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable(${chartModel.data});

        var options = {
       			title: '${chartModel.title}',
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
				<a href="https://www.ycp.edu/">
					<img src="https://www.ycp.edu/media/york-website/style-assets-2016/images/york-college-logo-white.svg"
					id="banner"></img>
				</a>
		</div>
		
		<!-- Navigation Bar -->
	<div class="navbar">
		<c:if test="${accountType.equals('admin')}">
			<a href="http://localhost:8081/CapstoneActivityTracker/adminView">Home</a>
		</c:if>
		<c:if test="${accountType.equals('student')}">
			<a href="http://localhost:8081/CapstoneActivityTracker/studentView">Home</a>
			<a href="http://localhost:8081/CapstoneActivityTracker/teamView">SubTeam</a>
		</c:if>
		<a href="">Account</a> 
		<a href="http://localhost:8081/CapstoneActivityTracker/index">SignOut</a> 
		<img src="https://www.ycp.edu/media/york-website/style-assets-2016/images/york-college-shield.svg"
			id="logo"></img>
	</div>
		
		<div class="row">
			<div class="side">
			<h2>${chartModel.student}</h2>
			<h3>${chartModel.topTeamName}</h3>
				<div class="nameList">
					<c:if test="${accountType.equals('admin')}">
						<form action="${pageContext.servletContext.contextPath}/createTopTeam" method="get">
							<input class="adminbutton" type="submit" name="cTeam" value="Create Capstone Team" /> 
						</form>
						
					</c:if>
					<c:if test="${accountType.equals('student')}">
						<form action="${pageContext.servletContext.contextPath}/teamView" method="post">
							<div id="subTeamList"></div>
						</form>
					</c:if>
				</div>
			<c:if test="${accountType.equals('admin')}"></br></c:if>	
			<c:if test="${accountType.equals('student')}">	
				<h4>${chartModel.mySubTeamName}</h4>
			</c:if>
			<div class="nameList">
				<c:if test="${accountType.equals('admin')}">
					<form action="${pageContext.servletContext.contextPath}/createSubTeam" method="get">
						<input class="adminbutton"  type="submit" name="cSub" value="Create Capstone Sub Team" />
					</form>
				</c:if>
				<c:if test="${accountType.equals('student')}">
					<form action="${pageContext.servletContext.contextPath}/studentView" method="post">
						<input type="hidden" name="currentSub" value="${chartModel.mySubTeamName}"/>
						<div id="studentList"></div>
					</form>
				</c:if>
			</div>	
			<c:if test="${accountType.equals('admin')}">
				</br>
				<div class="nameList">
					<form action="${pageContext.servletContext.contextPath}/assignStudent" method="get">
						<input class="adminbutton" type="submit" name="cTeam" value="Assign Student to Sub Team" /> 
					</form>
			</div>
			</c:if>
		</div>
			<div class="main" style="justify-content:center; align-items:center;">
				<div class="flex-container" style="height:100%; width:100%">
					<form action="${pageContext.servletContext.contextPath}/teamView" method="post">
						<input class="button" type="submit" value="<"></input>
						<input type="hidden" name="change" value="-1"/>
						<input type="hidden" name="currentSub" value="${chartModel.currentSub}"/>
						<input type="hidden" name="offset" value="${chartModel.offset}"/>
					</form>
					<div class="chart" id="columnchart_hours"></div>
					<form action="${pageContext.servletContext.contextPath}/teamView" method="post">
						<input class="button" type="submit" value=">"></input>
						<input type="hidden" name="change" value="1"/>
						<input type="hidden" name="currentSub" value="${chartModel.currentSub}"/>
						<input type="hidden" name="offset" value="${chartModel.offset}"/>
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
			     elem.name = 'subTeamButton';
			     var linebreak = document.createElement("br");
			     elem.appendChild(linebreak);
			     docFrag.appendChild(elem);
			}
		</script>
	</body>
</html>