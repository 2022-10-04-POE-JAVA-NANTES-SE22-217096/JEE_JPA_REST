<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="menu">
	<h1>Gestion de Bibliothèque</h1>
	<ul>
		<li><a href="<c:url value="/" />">Accueil</a></li>
		<li><a href="<c:url value="/listeAuteurs" />">Auteurs</a></li>
		<li><a href="<c:url value="/listeLivres" />">Livres</a></li>
		<li><a href="<c:url value="/deconnexion" />">Se déconnecter</a></li>
	</ul>
</div>