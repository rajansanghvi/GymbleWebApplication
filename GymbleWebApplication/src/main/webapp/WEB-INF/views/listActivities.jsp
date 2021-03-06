<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Activities</title>
</head>
<body>
	<h2>List of ${status} Activities</h2>
	<table>
		<thead>
			<tr>
				<th>Activity Code</th>
				<th>Activity Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${activities}" var = "activity">
				<tr>
					<td>${activity.code}</td>
					<td>${activity.name}</td>
					<td><a href='<c:url value="/activities/edit-activity/${activity.code}" />'>Edit</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>