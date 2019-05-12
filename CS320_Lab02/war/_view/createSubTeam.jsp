<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- used images from www.YCP.edu -->
<html>
<head>
<title>Create Account</title>
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

	<!---enter Top Team credentials--->
	<div class="emptybox"></div>
	<div class="createSubTeambox">
		<form action="${pageContext.servletContext.contextPath}/createSubTeam"
			method="post">

			<div class="createSubTeam">
				<div class="createSubTeamText">
					<c:if test="${! empty errorMessage}">
						<div class="error">
							<h3>${errorMessage}</h3>
						</div>
					</c:if>
					<label for="createTopTeam"><b>Create Capstone Sub Team</b></label>
				</div>
				</br> <input type="Text" name="teamname" size="12" value="${teamname}" placeholder="Capstone Team Name"/>
                </br>
                
                </br>
                <select size="1" name="topTeam" required="required">
					<option value="val">Capstone Team</option>
					<c:forEach items="${model.topTeamList}" var="topTeam">
						<option value="${topTeam.teamname}">${topTeam.teamname}</option>
					</c:forEach>
				</select>
				<input type="submit" name="createTeam" value="Create Capstone Sub Team" />
			</div>
		</form>
	</div>
	<div class="empty"></div>
	
	
	<div class="footerlogin">
		<div class="flex-container">
			<div class="footnote">Designed by Jason Steinberg, Travis
				Wetzel, and William Taylor</div>
			<div class="footnote">Developed by Jason Steinberg and Travis
				Wetzel</div>
			<div class="footnote">York College of PA CS320 Spring 2019</div>

		</div>
	</div>
</body>
</html>


