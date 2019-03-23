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
	Name: <c:out value="${showUser.name}"/><br>
	Alias: <c:out value="${showUser.alias}"/><br>
	Email: <c:out value="${showUser.email}"/><br>
	----------------------------------------------------- <br>
	Total number of posts: <c:out value = "${showUser.ideas.size()}"/><br>
	Total number of likes: <c:out value = "${likes.size()}"/><br>
	
</body>
</html>