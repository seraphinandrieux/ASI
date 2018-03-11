
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My card ${requestScope.ncard.name}</title>
</head>
<%--Page statique qui vq recuperer les infos du servlet et les afficher on utilise ici la techno JSP --%> 
<body>

	<h1>This is your ${requestScope.ncard.name} CARD!</h1><%--On recupere une par une les infos a afficher par une requete au servlet des infos qu'il communique qvec nous --%>
	<h2>Description: ${requestScope.ncard.description}</h2><%-- C'est via la methode setAttribute du servlet que nous avons les infos --%>
	<h2>Family: ${requestScope.ncard.family}</h2>
	<h2>HP: ${requestScope.ncard.hp}</h2>
	<h2>Energy: ${requestScope.ncard.energy}</h2>
	<h2>Defence: ${requestScope.ncard.defence}</h2>
	<h2>Attack: ${requestScope.ncard.attack}</h2>
	<h2>ID: ${requestScope.ncard.id}</h2>
	<img src=${requestScope.ncard.imgUrl} >

</body>
</html>