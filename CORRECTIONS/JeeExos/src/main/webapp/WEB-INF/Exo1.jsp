<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exo1</title>
</head>
<body>

	<c:import url="/WEB-INF/menu.jsp" />

	${ liste.get(0) }
	${ liste[1] }
	${ liste.get(2) }
	${ liste.get(3) }
</body>
</html>