<!DOCTYPE html>

<! taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<! taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
		<title>Admin Home</title>	
		
	</head>

	<body>
		<!-- logout form -->
		<div>
			<form action="${pageContext.servletContext.contextPath}/index" method="get">
				<input name="signOut" type="submit" value="Sign Out">
			</form>
		</div>
	
		<div>
			<select size="1" name="topTeam" required= "required">
						<option>Capstone Team</option>
        				<option value ="admin"></option>
        				<option value ="student"></option>
			</select>
		</div> 
		<div>
			<select size="1" name="subTeam" required= "required">
						<option>Sub-Team</option>
        				<option value ="admin"></option>
        				<option value ="student"></option>
			</select>
		</div> 
		<div>
			<select size="1" name="accountType" required= "required">
						<option>Student</option>
        				<option value ="admin"></option>
        				<option value ="student"></option>
			</select>
		</div> 
	</body>
</html>