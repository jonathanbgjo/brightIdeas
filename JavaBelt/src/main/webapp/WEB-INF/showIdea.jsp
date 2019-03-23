<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="/dashboard">Bright Ideas</a>
	<a href="/logout">Logout</a><br>

<c:out value="${idea.user.alias}"/> Says <c:out value="${idea.description}"/>

<br><br><br>
People who have liked this post <br>
<table>
	<tr>
		<td>Alias</td>
		<td>Name</td>
	</tr>
	<c:forEach items="${likes}" var = "like">
		<c:if test = "${like.idea.id == idea.id }">
			<tr>
				<td><a href="/users/${like.user.id}"><c:out value="${like.user.alias }"/> </a></td>
				<td><c:out value="${like.user.name}"/></td>
			</tr>
		</c:if>
	</c:forEach>
</table>
</body>
</html>