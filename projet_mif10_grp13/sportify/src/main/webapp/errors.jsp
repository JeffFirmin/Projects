<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>Erreur</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
	<link  rel="stylesheet" href="css/style-error.css">	
</head>
<body>
	<div class="error">
		<div class="error-title">Oups, nous avons rencontré un souci...</div>
		<hr>
		<div class="error-msg">${sessionScope.error_msg}</div>
	</div>
	
	<c:if test="${sessionScope.user != null || sessionScope.club != null}">
		<form method="GET" action="BackInterne">
			<button type="submit" class="btn btn-primary mb-2">Retour</button>
		</form>
	</c:if>
	<c:if test="${sessionScope.club == null && sessionScope.user == null}">
		<form method="GET" action="Deco">
			<button type="submit" class="btn btn-primary mb-2">Retour</button>
		</form>
	</c:if>
	
</body>
</html>

