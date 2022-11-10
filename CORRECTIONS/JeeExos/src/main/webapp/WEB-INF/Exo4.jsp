<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exo 4</title>
</head>
<body>
	
	<h2>Auteur</h2>
	<table>
		<tr>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Téléphone</th>
			<th>Email</th>
		</tr>
		<tr>
			<td>${ auteur.nom }</td>
			<td>${ auteur.prenom }</td>
			<td>${ auteur.telephone }</td>
			<td>${ auteur.email }</td>
		</tr>
	</table>
	
	<h2>Livre</h2>
	<table>
		<tr>
			<th>Auteur</th>
			<th>Titre</th>
			<th>Nombre de pages</th>
			<th>Catégorie</th>
		</tr>
		<tr>
			<td>${ livre.auteur.nom } ${ livre.auteur.prenom }</td>
			<td>${ livre.titre }</td>
			<td>${ livre.nbPages }</td>
			<td>${ livre.categorie }</td>
		</tr>
	</table>
	
</body>
</html>