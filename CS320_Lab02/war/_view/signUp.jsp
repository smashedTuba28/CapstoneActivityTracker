<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Log In to the Capstone Tracker</title>
	</head>

	<body>

		<c:if test="${! empty errorMessage}">
			<div class="error"><h3>${errorMessage}</h3></div>	
		</c:if>
			
	<!---enter user credentials--->
		<form action="${pageContext.servletContext.contextPath}/signUp" method="post">
			<c:if test="${empty model}">
				<table>
					<tr>
						<td class="Label">Please complete to create your account.</td>
					</tr>
					<tr>
						<td class = "Label">First Name:</td>
						<td><input type ="Text" name="email" size="12" value="${firstname}">
					</tr>
					<tr>
						<td class = "Label">Last Name:</td>
						<td><input type = "Text" name="password" size="12" value="${lastname}">
					</tr>	
					<tr>
						<td class = "Label">YCP Email:</td>
						<td><input type ="Text" name="email" size="12" value="${email}">
					</tr>
					<tr>
						<td class = "Label">Student ID Number:</td>
						<td><input type = "Text" name="password" size="12" value="${studentID}">
					</tr>	
					<tr>
						<td class = "Label">Password:</td>
						<td><input type ="Password" name="email" size="12" value="${password}">
					</tr>
					<tr>
						<td class = "Label">Password Confirmation:</td>
						<td><input type = "Password" name="password" size="12" value="${passwordConfirm}">
					</tr>				
				</table>
				<input type="submit" name="signUp" value="Create Account" />
			</c:if>	
		</form>
	</body>
</html>