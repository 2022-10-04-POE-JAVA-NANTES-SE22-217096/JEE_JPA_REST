<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
	

<label for="auteurLivre">Auteur</label>
<select id="auteurLivre" name="auteurLivre">
	<c:forEach items="${ auteurs }" var="auteur">
		<option value="<c:out value="${ auteur.id }"/>" ${ auteur.id == livre.auteur.id ? "selected" : "" }><c:out value="${ auteur.prenom }"/> <c:out value="${ auteur.nom }"/></option>
	</c:forEach>
</select>
<span class="erreur">${ erreurs['auteurLivre'] }</span>
<br/>

<label for="titreLivre">Titre</label>
<input type="text" id="titreLivre" name="titreLivre" value="<c:out value="${ livre.titre }" />" size="50" />
<span class="erreur">${ erreurs['titreLivre'] }</span>
<br/>

<label for="nbPagesLivre">Nombre de pages</label>
<input type="number" id="nbPagesLivre" name="nbPagesLivre" value="<c:out value="${ livre.nbPages }" />" size="10" />
<span class="erreur">${ erreurs['nbPagesLivre'] }</span>
<br/>

<label for="categorieLivre">Catégorie</label>
<input type="text" id="categorieLivre" name="categorieLivre" value="<c:out value="${ livre.categorie }" />" size="20" />
<span class="erreur">${ erreurs['categorieLivre'] }</span>
<br/>

<br/>
<span class="erreur">${ erreurs['livre'] }</span>
<p class="erreur">${ resultat }</p>