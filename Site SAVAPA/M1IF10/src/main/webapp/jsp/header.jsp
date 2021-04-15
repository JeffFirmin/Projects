<%@ page import="com.m1if10.app.modele.User" %>
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
        font-size: 36px;
        line-height: 42px;
        text-transform: uppercase;
        text-shadow: 0 2px white, 0 3px #777;
    }
</style>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">

    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">

    <title><c:out value="${param['title']}" /></title>
</head>

<body class="container" style="background:white;">
    <%
    User currentUser = (User) session.getAttribute("user");
%>
<header>
    <img src="../css/logo.png" width="300" height="100" alt="" class="rounded mx-auto d-block">
    <h1 id="demo">
        <c:choose>
            <c:when test="${param['userType'] == 'etu'}">
                <c:out value=" Étudiant" />
            </c:when>
            <c:when test="${param['userType'] == 'prof'}">
                <c:out value=" Professeur" />
            </c:when>
            <c:when test="${param['userType'] == 'scola'}">
                <c:out value=" Scolarité" />
            </c:when>
        </c:choose>
        :
        <%= currentUser.getNom() %>
        <%= currentUser.getPrenom() %>
        <%= currentUser.getEmail() %>
    </h1>
</header>

<main class="row">
    <c:choose>
    <c:when test="${param['userType'] == 'etu'}">
        <jsp:include page="/jsp/etudiant/nav_etu.jsp" />
    </c:when>
    <c:when test="${param['userType'] == 'prof'}">
        <jsp:include page="/jsp/professeur/nav_prof.jsp" />
    </c:when>
    <c:when test="${param['userType'] == 'scola'}">
        <jsp:include page="/jsp/scolarite/nav_scola.jsp" />
    </c:when>
    </c:choose>
    <div class="col-md-9">
        <div class="card">