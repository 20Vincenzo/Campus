<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/templates/header.jspf" %>
<h1>${facolta.facolta}</h1>
<table>
<tr>
	<th>Corso</th>
	<th>Professore</th>
	<th>Rimuovi corso</th>
</tr>
<c:forEach items="${facolta.corsi}" var="cor">
<tr>
	<td>${cor.corso }</td>
	<td>${cattedre.get(cor.id)}</td>
	<td><a href="/Campus/CorsiFacoltaDelete?facolta=${facolta.id}&corso=${cor.id}">Rimuovi</a></td>
</tr>
</c:forEach>

<h2>Aggiungi corsi a Facolt�</h2>
<form action="/Campus/CorsiFacolta" method="post">

<input type="hidden" name="facolta" value="${facolta.id}">

<select name="corso">
<c:forEach items="${corsi}" var="c">
	<option value="${c.id}">${c.corso}</option>
</c:forEach>
</select>

<input type="submit">
</form>
</table>
</body>
</html>