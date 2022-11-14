<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste Auteurs</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />

	<div class="view">
		
		<c:choose>
			<c:when test="${ empty auteurs }">
				<p>Aucun auteur trouvé...</p>
			</c:when>
			<c:otherwise>	
				<table>
					<thead>
						<tr>
							<td>Nom</td>
							<td>Prénom</td>
							<td>Actions</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ auteurs }" var="auteur" varStatus="infoBoucle">
							<tr class="${ infoBoucle.index % 2 == 0 ? 'pair' : 'impair' }">
								<td><c:out value="${ auteur.nom}" /></td>
								<td><c:out value="${ auteur.prenom}" /></td>
								<td>PAS D'ACTIONS</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
		
	</div>
</body>
</html>