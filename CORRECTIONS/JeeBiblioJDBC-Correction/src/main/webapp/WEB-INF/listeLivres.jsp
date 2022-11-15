<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste Livres</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />

	<div class="view">
		
		<a href="<c:url value="/ajouterLivre" />"><button>Ajouter un livre</button></a>
		
		<c:choose>
			<c:when test="${ empty livres }">
				<p>Aucun livre trouvé...</p>
			</c:when>
			<c:otherwise>	
				<table>
					<thead>
						<tr>
							<td>Titre</td>
							<td>Catégorie</td>
							<td>Actions</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ livres }" var="livre" varStatus="infoBoucle">
							<tr class="${ infoBoucle.index % 2 == 0 ? 'pair' : 'impair' }">
								<td><c:out value="${ livre.titre}" /></td>
								<td><c:out value="${ livre.categorie}" /></td>
								<td>
									<a href="<c:url value="/detailsLivre"><c:param name="id" value="${ livre.id}" /></c:url>">Voir</a>
									|
									<a href="<c:url value="/modifierLivre"><c:param name="id" value="${ livre.id}" /></c:url>">Modifier</a>
									|
									<a href="<c:url value="/supprimerLivre"><c:param name="id" value="${ livre.id}" /></c:url>">Supprimer</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
		
		<span>${ sessionScope.confirmMessage }</span>
				
	</div>
</body>
</html>