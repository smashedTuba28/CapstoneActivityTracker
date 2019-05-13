<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Administrator View</title>
<link rel="stylesheet" href="styling/style.css">
<!--icon for ycp shield from ycp.edu-->
<link rel="icon" href="https://www.ycp.edu/media/york-website/style-assets-2016/images/favicon.ico" type="image/ico"> 

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
		<a href="http://localhost:8081/CapstoneActivityTracker/adminView">Home</a>
		<a href="">Account</a> <a
			href="http://localhost:8081/CapstoneActivityTracker/index">Sign
			Out</a> <img
			src="https://www.ycp.edu/media/york-website/style-assets-2016/images/york-college-shield.svg"
			id="logo"></img>
	</div>

	<!-- The flexible grid (content) -->
	<div class="row" style="height: 600px;">
		<div class="side">
		<!-- adminname from servlet -->
			<h2>${model.adminname}</h2>
				<div class="nameList">
					<form action="${pageContext.servletContext.contextPath}/createTopTeam" method="get">
						<input class="adminbutton" type="submit" name="cTeam" value="Create Capstone Team" /> 
					</form>
				</div>
				</br>
			<div class="nameList">
				<form action="${pageContext.servletContext.contextPath}/createSubTeam" method="get">
					<input class="adminbutton"  type="submit" name="cSub" value="Create Capstone Sub Team" />
				</form>
			</div>
			</br>
			<div class="nameList">
					<form action="${pageContext.servletContext.contextPath}/assignStudent" method="get">
						<input class="adminbutton" type="submit" name="cTeam" value="Assign Student to Sub Team" /> 
					</form>
			</div>
		</div>
		<div class="main" style="justify-content:center; align-items:center;">
			<div class="flex-containeradmin">
				<form class="adminform" action="${pageContext.servletContext.contextPath}/adminView"
					method="post">
						<div class="adminviewchart">
							<input type="submit" name="b3" value="View Chart" />
						</div>

					<div class="adminmain">
					<select size="1" name="topTeam" required="required">
						<option value="val">${model.topTeam.teamname}</option>
						<c:forEach items="${model.topTeamList}" var="topTeam">
						<option value="${topTeam.teamname}">${topTeam.teamname}</option>
						</c:forEach>
					</select> 
					
					<input type="submit" name="b1" value=">" /> 
					
					<select size="1" name="subTeam" required="required">
						<option value="val">${model.subTeam.teamname}</option>
						<c:forEach items="${model.subTeamList}" var="subTeam">
							<option value="${subTeam.teamname}">${subTeam.teamname}</option>
						</c:forEach>
					</select> 
					
					<input type="submit" name="b2" value=">" /> 
					
					<select size="1" name="student">
						<option value="val">${model.student.lastname}</option>
						<c:forEach items="${model.students}" var="student">
							<option value="${student.accountID}">${student.firstname} ${student.lastname}</option>
						</c:forEach>
					</select> 
					</div>
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
	
	
	
	
	
	<!--  
	

	<div class="dropDownAdmin">
		<form action="${pageContext.servletContext.contextPath}/adminView"
			method="post">
			<select size="1" name="topTeam" required="required">
				<option value="val">${model.topTeam.teamname}</option>
				<c:forEach items="${model.topTeamList}" var="topTeam">
					<option value="${topTeam.teamname}">${topTeam.teamname}</option>
				</c:forEach>
			</select> 
			<select size="1" name="subTeam" required="required">
				<option value="val">${model.subTeam.teamname}</option>
				<c:forEach items="${model.subTeamList}" var="subTeam">
					<option value="${subTeam.teamname}">${subTeam.teamname}</option>
				</c:forEach>
			</select> 
			<select size="1" name="student">
				<option value="val">${model.student.lastname}</option>
				<c:forEach items="${model.students}" var="student">
					<option value="${student.accountID}">${student.firstname}</option>
				</c:forEach>
			</select> 
			<input type="submit" name="b1" value=">" /> 
			<input type="submit" name="b2" value=">" /> 
			<input type="submit" name="b3" value="View Chart" />
		</form>
	</div>
	<div class="adminSidePanel">
		<form action="${pageContext.servletContext.contextPath}/adminView"
			method="post">
			<input type="submit" name="cTeam" value="Create Capstone Team" /> 
			<input type="submit" name="cSub" value="Create Capstone Sub Team" />
		</form>
	</div>
	-->

</body>
</html>