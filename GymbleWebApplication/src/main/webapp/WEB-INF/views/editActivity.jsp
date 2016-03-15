<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Edit Activity</title>
	<style>
		.error
		{
			color: #ff0000;
		}
	</style>
</head>

<body>
	<h2>Edit an activity</h2>
	
	<form:form method="POST" modelAttribute="activity">
		<form:input type="hidden" path="id" id="id" />
		<table>
			<tr>
				<td><label for="name">Code: </label></td>
				<td><form:input path="code" id="code" disabled="true"/></td>
				<td><form:errors path="code" cssClass="error" /></td>
			</tr>
			<tr>
				<td><label for="name">Name: </label></td>
				<td><form:input path="name" id="name" /></td>
				<td><form:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Update" /></td>
			</tr>
		</table>
	</form:form>
	
</body>

</html>