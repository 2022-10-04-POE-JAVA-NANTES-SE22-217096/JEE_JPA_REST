<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Détails Auteur</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />

	<div class="view">
		
		<table>
			<thead>
				<tr>
					<td>Nom</td>
					<td>Prénom</td>
					<td>Téléphone</td>
					<td>Email</td>
				</tr>
			</thead>
			<tbody>
				<tr class="impair">
					<td><c:out value="${ auteur.nom}" /></td>
					<td><c:out value="${ auteur.prenom}" /></td>
					<td><c:out value="${ auteur.telephone}" /></td>
					<td><c:out value="${ auteur.email}" /></td>
				</tr>
			</tbody>
		</table>
	
	</div>

</body>
</html>