<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Login</title>
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


	<!---enter user credentials--->
	<div class="empty"></div>
	<div class="box">
		<form action="${pageContext.servletContext.contextPath}/signIn"
			method="post">

			<div class="login">
				<div class="loginText">
					<c:if test="${! empty errorMessage}">
						<div class="error">
							<h3>${errorMessage}</h3>
						</div>
					</c:if>
					<label for="login"><b>Sign In</b></label>
				</div>
				
				</br> </br> <input type="Text" name="email" size="12" value="${email}"
					placeholder="Email"> </br> <input type="Password"
					name="password" size="12" value="${password}"
					placeholder="Password"> </br> </br> <select size="1"
					name="accountType" required="required">

					<option value="student">Student</option>
					<option value="admin">Administrator</option>

				</select> </br> </br> <a
					href="http://localhost:8081/CapstoneActivityTracker/passForgot"><b>Forgot
						Password?</b></a> <input type="submit" name="signIn" value="Sign In" /> </br>
				<a href="http://localhost:8081/CapstoneActivityTracker/signUp"><b>Create
						Account</b></a>
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
	<img id="person"
		src=https://www.ycp.edu/media/york-website/style-assets-2016/images/YCPStudentFooter.png />
</body>
</html>


