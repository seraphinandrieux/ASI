<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Random Card</title>
</head>
<body>

	<h1>WELCOME TO ${requestScope.rcard.name} CARD!</h1>
	<h2>Description: ${requestScope.rcard.description}</h2>
	<h2>Family: ${requestScope.rcard.family}</h2>
	<h2>HP: ${requestScope.rcard.hp}</h2>
	<h2>Energy: ${requestScope.rcard.energy}</h2>
	<h2>Defence: ${requestScope.rcard.defence}</h2>
	<h2>Attack: ${requestScope.rcard.attack}</h2>
	<h2>ID: ${requestScope.rcard.id}</h2>
	<img src=${requestScope.rcard.imgUrl} >

</body>
</html>