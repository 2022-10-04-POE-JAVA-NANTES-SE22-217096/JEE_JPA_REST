<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Ma premiere page</title>
</head>
<body>
	<p>Bonjour JSP en HTML !</p>
	<p>TOTO est �gale � ${toto}</p>
	<p>TATA est �gale � ${ tata }</p>
	<p>Le prenom de l'auteur est ${ test.id }</p>
	
	<p>${ maListe.get(2) }</p>
</body>
</html>