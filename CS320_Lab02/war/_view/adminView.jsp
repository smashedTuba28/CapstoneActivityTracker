<!DOCTYPE html>

<! taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<! taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
		<title>Administrator View</title>
		<link rel="stylesheet" href="styling/style.css">	
		
	</head>

	<body>
		<div>
		
			<select size="1"
					name="Top Teams" required="required">
					<c.forEach items="${model.topTeamList}" var="topTeam">
					
					<option value="${topTeam.teamID}">${topTeam.teamname}</option>
					</c.forEach>

			</select>
			
			<select size="1"
				name="Sub Teams" required="required">
				<c.forEach items="${model.subTeamList}" var="subTeam">
				<option value="${subTeam.teamID}">${subTeam.teamname}</option>
				</c.forEach>
			</select>
			
			<select size="1"
				name="Students">
				<c.forEach items="${model.studentList}" var="student">
				<option value="${student.}">${student.name}</option>
				</c.forEach>
			</select>
			
			
				
		</div>
	</body>
</html>