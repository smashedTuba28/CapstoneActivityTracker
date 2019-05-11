<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Administrator View</title>
<link rel="stylesheet" href="styling/style.css">
</head>
<body>
	<div class="dropDownAdmin">
		<form action="${pageContext.servletContext.contextPath}/adminView"
			method="post">
			<select size="1" name="topTeam" required="required">
				<option value="val">${model.topTeam.teamname}</option>
				<c:forEach items="${model.topTeamList}" var="topTeam">
					<option value="${topTeam.teamname}">${topTeam.teamname}</option>
				</c:forEach>
			</select> 
			<select size="1" name="subTeam" required="required">
				<option value="val">${model.subTeam.teamname}</option>
				<c:forEach items="${model.subTeamList}" var="subTeam">
					<option value="${subTeam.teamname}">${subTeam.teamname}</option>
				</c:forEach>
			</select> 
			<select size="1" name="student">
				<option value="val">${model.student.lastname}</option>
				<c:forEach items="${model.students}" var="student">
					<option value="${student.accountID}">${student.firstname}</option>
				</c:forEach>
			</select> 
			<input type="submit" name="b1" value=">" /> 
			<input type="submit" name="b2" value=">" /> 
			<input type="submit" name="b3" value="View Chart" />
		</form>
	</div>
	<div class="adminSidePanel">
		<form action="${pageContext.servletContext.contextPath}/adminView"
			method="post">
			<input type="submit" name="cTeam" value="Create Capstone Team" /> 
			<input type="submit" name="cSub" value="Create Capstone Sub Team" />
		</form>
	</div>
</body>
</html>