<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    

<label for="nomAuteur">Nom : </label>
<input id="nomAuteur" name="nomAuteur" type="text" value="<c:out value="${ auteur.nom }" />" />
<span class="erreur">${ erreurs['nomAuteur'] }</span>
<br/>

<label for="prenomAuteur">Prénom : </label>
<input id="prenomAuteur" name="prenomAuteur" type="text" value="<c:out value="${ auteur.prenom }" />" />
<span class="erreur">${ erreurs['prenomAuteur'] }</span>
<br/>

<label for="telephoneAuteur">Téléphone : </label>
<input id="telephoneAuteur" name="telephoneAuteur" type="text" value="<c:out value="${ auteur.telephone }" />" />
<span class="erreur">${ erreurs['telephoneAuteur'] }</span>
<br/>

<label for="emailAuteur">Email : </label>
<input id="emailAuteur" name="emailAuteur" type="text" value="<c:out value="${ auteur.email }" />" />
<span class="erreur">${ erreurs['emailAuteur'] }</span>
<br/>

<br/>
<span class="erreur">${ erreurs['auteur'] }</span>
<c:if test="${ not empty erreurs }">
	<p class="erreur">Echec de la sauvegarde de l'auteur.</p>
</c:if>