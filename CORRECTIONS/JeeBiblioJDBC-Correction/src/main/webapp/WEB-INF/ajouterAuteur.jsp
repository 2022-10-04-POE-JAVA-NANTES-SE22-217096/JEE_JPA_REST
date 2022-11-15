<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter Auteur</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />

	<div class="view">
		
		<form method="POST" action="<c:url value="/ajouterAuteur" />">
			
			<fieldset>
				<legend>Ajouter un auteur</legend>
		
				<c:import url="/WEB-INF/auteur_form.jsp" />
			</fieldset>
			
			<input type="submit" value="Valider" />
			<input type="reset" value="Remettre à zéro" />
			
		</form>

	
	</div>

</body>
</html>