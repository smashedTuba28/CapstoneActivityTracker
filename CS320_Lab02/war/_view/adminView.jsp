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
			
		
		</div> 
		
	
	
	
	
		<!-- HTML element for google chart -->
		<div id="columnchart_hours" style="width: 800px; height: 500px"></div>

		<!-- Link to subteams displayed here -->
		<div id="subteams"></div>
		
		<a href = "http://localhost:8081/CapstoneActivityTracker/studentView">Student View</a>
		
		
	</body>
</html>