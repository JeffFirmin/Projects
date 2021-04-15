<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!doctype html>
<html lang="fr">
<style>
    #demo{
        margin: 1em 0 0.5em 0;
        color: #343434;
        font-weight: normal;
        font-family: 'Ultra', sans-serif;
        font-size: 40px;
        line-height: 42px;
        text-transform: uppercase;
        text-shadow: 0 2px white, 0 3px #777;
    }
</style>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>SAVAPA - Connexion</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
</head>
<body class="text-center">
<form class="form-signin" method="post">
    <h1 id="demo">Connexion</h1>
    <c:if test="${requestScope.message != null}">
        <div class="alert alert-danger">
            <span><c:out value="${requestScope.message}" /></span>
        </div>
    </c:if>
    <label for="inputIdentifiant" class="sr-only">Identifiant</label>
    <input type="text" id="inputIdentifiant" name="inputIdentifiant" class="form-control" placeholder="Identifiant" required autofocus>
    <label for="inputPassword" class="sr-only">Mot de passe</label>
    <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Mot de passe" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Connexion</button>
</form>
</body>
</html>
