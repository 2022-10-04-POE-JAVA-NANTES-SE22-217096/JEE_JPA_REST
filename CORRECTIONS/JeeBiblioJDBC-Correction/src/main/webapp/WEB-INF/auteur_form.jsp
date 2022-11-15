<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    

<label for="nomAuteur">Nom : </label>
<input id="nomAuteur" name="nomAuteur" type="text" value="<c:out value="${ auteur.nom }" />" />
<br/>

<label for="prenomAuteur">Prénom : </label>
<input id="prenomAuteur" name="prenomAuteur" type="text" value="<c:out value="${ auteur.prenom }" />" />
<br/>

<label for="telephoneAuteur">Téléphone : </label>
<input id="telephoneAuteur" name="telephoneAuteur" type="text" value="<c:out value="${ auteur.telephone }" />" />
<br/>

<label for="emailAuteur">Email : </label>
<input id="emailAuteur" name="emailAuteur" type="text" value="<c:out value="${ auteur.email }" />" />
<br/>