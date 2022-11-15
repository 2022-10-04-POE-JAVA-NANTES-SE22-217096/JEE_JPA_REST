<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier Livre</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />

	<div class="view">
		
		<form method="POST" action="<c:url value="/modifierLivre"><c:param name="id" value="${ livre.id}" /></c:url>">
		
			<fieldset>
				<legend>Modifier un livre</legend>
				
				<label for="auteurLivre">Auteur : </label>
				<select id="auteurLivre" name="auteurLivre">
					<c:forEach items="${ auteurs }" var="auteur">
						<option value="${ auteur.id}" ${ auteur.id == livre.auteur.id ? "selected" : "" }><c:out value="${ auteur.prenom}" /> <c:out value="${ auteur.nom}" /></option>
					</c:forEach>
				</select>
				<br/>
				
				<label for="titreLivre">Titre : </label>
				<input id="titreLivre" name="titreLivre" type="text" value="<c:out value="${ livre.titre }" />" />
				<br/>
				
				<label for="nbPagesLivre">Nombre de pages : </label>
				<input id="nbPagesLivre" name="nbPagesLivre" type="number" value="<c:out value="${ livre.nbPages }" />" />
				<br/>
				
				<label for="categorieLivre">Email : </label>
				<input id="categorieLivre" name="categorieLivre" type="text" value="<c:out value="${ livre.categorie }" />" />
				<br/>
			</fieldset>
			
			<input type="submit" value="Valider" />
			<input type="reset" value="Remettre à zéro" />
			
		</form>

	
	</div>

</body>
</html>