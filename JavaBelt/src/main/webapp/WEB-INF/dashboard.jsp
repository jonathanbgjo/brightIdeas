<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
	<a href="/logout">Logout</a>
	<h1>Hi, ${ curuser.alias }</h1>
	<form:form action="/addIdea" method="POST" modelAttribute="idea">
		<form:hidden path="user" value="${curuser.id}"></form:hidden>
		<form:label path = "description" for="description">
		<form:input type="text" path="description"/> </form:label>
		<input class="float" type="submit" value="Idea!" />						
	</form:form>

	<c:forEach items = "${ideas}" var = "idea">

				<a href="/users/${idea.user.id}"><c:out value = "${idea.user.alias}"/></a> Says:
				<c:out value = "${idea.description}"/><br>
				<a href="/like/${idea.id}">Like</a> 
				<a href="/bright_ideas/${idea.id}"><c:out value = "${fn:length(idea.likes)}"/></a> People like this
				<c:if test  = "${curuser.id == idea.user.id}">
					<a href="delete/${idea.id}">Delete Idea</a>
				</c:if><br><br>
				
				
				
	</c:forEach>
	
	<c:forEach items="${likes}" var = "like">
		<c:out value="${like.user.alias }"/>
	</c:forEach>
</body>
</html>