<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Détails Livre</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />

	<div class="view">
		
		<table>
			<thead>
				<tr>
					<td>Titre</td>
					<td>Auteur</td>
					<td>Catégorie</td>
					<td>Nombre de pages</td>
				</tr>
			</thead>
			<tbody>
				<tr class="impair">
					<td><c:out value="${ livre.titre}" /></td>
					<td><c:out value="${ livre.auteur.prenom } ${ livre.auteur.nom }" /></td>
					<td><c:out value="${ livre.categorie }" /></td>
					<td><c:out value="${ livre.nbPages }" /></td>
				</tr>
			</tbody>
		</table>
	
	</div>

</body>
</html>