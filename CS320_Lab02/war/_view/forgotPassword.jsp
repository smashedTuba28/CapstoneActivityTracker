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
	<c:if test="${! empty errorMessage}">
		<div class="error">
			<h3>${errorMessage}</h3>
		</div>
	</c:if>

	<!-- Header -->
	<div class="header">
		<h1>Capstone Activity Tracker</h1>
		<a href="https://www.ycp.edu/"> <img
			src="https://www.ycp.edu/media/york-website/style-assets-2016/images/york-college-logo-white.svg"
			id="banner"></img>
		</a>

	</div>


	<!---enter user credentials--->
	<div class="emptybox"></div>
	<div class="box">
		<form
			action="${pageContext.servletContext.contextPath}/forgotPassword"
			method="post">

			<div class="login">
				<div class="loginText">
					<label for="login"><b>Forgot Password</b></label>
				</div>
				</br> <input type="Text" name="email" size="12" value="${schoolID}"
					placeholder="YCP Email"> </br> <input type="Text"
					name="schoolID" size="12" value="${email}"
					placeholder="YCP School ID"> </br> </br> <a
					href="http://localhost:8081/CapstoneActivityTracker/signIn"><b>Sign In</b></a>
				<input type="submit" name="createAccount" value="Send Email" />
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


