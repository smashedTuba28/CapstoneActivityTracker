<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Sign In to the Capstone Tracker</title>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error"><h3>${errorMessage}</h3></div>	
		</c:if>
		
		<a href = "http://localhost:8081/CapstoneActivityTracker/signUp">Create a Capstone Tracker Account!</a>
		
	<!---enter user credentials--->
		<form action="${pageContext.servletContext.contextPath}/signIn" method="post">
			<c:if test="${empty model}">
				<table>
					<tr>
						<td class="Label">Please Enter Email/Password</td>
					</tr>
					<tr>
						<td class = "Label">Email:</td>
						<td><input type ="Text" name="email" size="12" value="${email}">
					</tr>
					<tr>
						<td class = "Label">Password:</td>
						<td><input type = "Password" name="password" size="12" value="${password}">
					</tr>		
				</table>
				<input type="submit" name="signIn" value="Log In" />
				<a href = "http://localhost:8081/CapstoneActivityTracker/passForgot">Forgot Password</a>
			</c:if>
		</form>
	</body>
</html>