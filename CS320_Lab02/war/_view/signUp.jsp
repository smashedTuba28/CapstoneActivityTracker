<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Account SignUp</title>
	</head>

	<body>

		<c:if test="${! empty errorMessage}">
			<div class="error"><h3>${errorMessage}</h3></div>	
		</c:if>
			
	<!---enter user credentials--->
		<form action="${pageContext.servletContext.contextPath}/signUp" method="post">
				<table>
					<tr>
						<td class="Label">Please complete to create your account.</td>
					</tr>
					<tr>
						<td class = "Label">Student ID:</td>
						<td><input type = "Text" name="schoolID" size="12" value="${schoolID}">
					</tr>		
					<tr>
						<td class = "Label">Email:</td>
						<td><input type ="Text" name="email" size="12" value="${email}">
					</tr>
					<tr>
						<td class = "Label">Password:</td>
						<td><input type ="Password" name="password" size="12" value="${password}">
					</tr>
					<tr>
						<td class = "Label">Password Confirmation:</td>
						<td><input type = "Password" name="passwordConfirm" size="12" value="${passwordConfirm}">
					</tr>	
					<tr>
						<td class = "Label">Faculty Member:</td>
						<td>
							<select size="1" name="accountType" required= "required">
							<option>Select-One</option>
        					<option value ="admin">Administrator</option>
        					<option value ="student">Student</option>
							</select>
						</td>	
				</table>
					

				
				<input type="submit" name="signUp" value="Create Account" />
		</form>
	</body>
</html>