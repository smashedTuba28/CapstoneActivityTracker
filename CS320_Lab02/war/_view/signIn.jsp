<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Log In to the Capstone Tracker</title>
	</head>

	<body>
	
	<!---enter user credentials--->
		<form action="${pageContext.servletContext.contextPath}/signIn" method="post">
			<c:if test="${empty model}">
				<table>
					<tr>
						<td class="Label"> Please Enter Email/Password</td>
					</tr>
					<tr>
						<td class = "Label">Email</td>
						<td><input type ="Text" name="email" size="12" value="${email}">
					</tr>
					<tr>
						<td class = "Label">Password</td>
						<td><input type = "Text" name="password" size="12" value="${password}">
					</tr>				
				</table>
				<input type="submit" name="signIn" value="Log In" />
				<input type="submit" name="forgotPassword" value="Forgot Password" />
			</c:if>
			
	<!---reprompt for incorrect information--->
			<c:if test="${! empty model}">	
				<h3>Invalid Email or Password!</h3>
				<table>
					<tr>
						<td class="Label"> Please Enter Email/Password</td>
					</tr>
					<tr>
						<td class = "Label">Email</td>
						<td><input type ="Text" name="email" size="12" value="${email}">
					</tr>
					<tr>
						<td class = "Label">Password</td>
						<td><input type = "Text" name="password" size="12" value="${password}">
					</tr>				
				</table>
				<input type="submit" name="signIn" value="Log In" />
				<input type="submit" name="forgotPassword" value="Forgot Password" />
			</c:if>
		</form>
	</body>
</html>