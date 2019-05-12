<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- used images from www.YCP.edu -->
<html>
<head>
<title>Create Account</title>
<link rel="stylesheet" href="styling/style.css">
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
	<div class="createTopTeambox">
		<form action="${pageContext.servletContext.contextPath}/createTopTeam"
			method="post">

			<div class="createTopTeam">
				<div class="createTopTeamText">
					<c:if test="${! empty errorMessage}">
						<div class="error">
							<h3>${errorMessage}</h3>
						</div>
					</c:if>
					<label for="createTopTeam"><b>Create Capstone Team</b></label>
				</div>
				</br> <input type="Text" name="teamname" size="12" value="${teamname}" placeholder="Capstone Team Name"/>
                </br>
                </br>
				<input type="submit" name="createTeam" value="Create Capstone Team" />
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


