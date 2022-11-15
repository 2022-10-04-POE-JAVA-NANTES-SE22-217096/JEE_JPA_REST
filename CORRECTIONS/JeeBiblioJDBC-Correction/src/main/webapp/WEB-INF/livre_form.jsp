<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
	

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