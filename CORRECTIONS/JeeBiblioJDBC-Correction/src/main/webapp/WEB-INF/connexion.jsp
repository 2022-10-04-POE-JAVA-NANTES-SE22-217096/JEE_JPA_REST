<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Se connecter</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />

	<div class="view">
		<form method="post" action="<c:url value="/connexion" />">
		
			<fieldset>
				<legend>Se connecter</legend>
				
				<label for="login">Nom d'utilisateur : </label>
				<input type="text" id="login" name="login" size="20" />
				<br/>
				
				<label for="password">Mot de passe : </label>
				<input type="password" id="password" name="password" size="20" />
				<br/>
			</fieldset>
		
			<input type="submit" value="Valider" />
			<input type="reset" value="Remettre à zéro" />
		</form>
		
		<span>${ loginMessage }</span>
	</div>
</body>
</html>