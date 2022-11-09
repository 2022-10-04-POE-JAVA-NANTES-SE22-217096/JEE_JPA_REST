<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exo 3</title>
</head>
<body>

	<c:import url="/WEB-INF/menu.jsp" />

	<h1>Bonjour ${ prenom } ${ nom } !</h1>
	<a href="/JeeExos/exo2?">Retour à l'exo 2</a>
</body>
</html>