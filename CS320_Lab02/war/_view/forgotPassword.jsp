<!DOCTYPE html>

<html>
	<head>
		<title>Welcome to the YCP Capstone Activity Tracker</title>
	</head>

	<body>
		<div>
			<c:if test="${empty model}">
				<table>
					<tr>
						<td class="Label">Please enter your information:</td>
					</tr>
					<tr>
						<td class = "Label">Email</td>
						<td><input type ="Text" name="email" size="12" value="${email}">
					</tr>
					<tr>
						<td class = "Label">StudentID</td>
						<td><input type = "Text" name="studentID" size="12" value="${studentID}">
					</tr>				
				</table>
				<input type="submit" name="forgotPassword" value="Forgot Password" />
			</c:if>
			
		</div>
	</body>
</html>
