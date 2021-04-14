<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="col-md-3" id="navigation">

    <a class="list-group-item list-group-item-light  <%= request.getParameter("servletName").equals("EDT") ? "active" : "" %>"
       href="EDT">Emploi du temps</a>
    <a class="list-group-item list-group-item-light <%= request.getParameter("servletName").equals("AfficherQRCode") ? "active" : "" %>"
       href="AfficherQRCode">Afficher le QRCode</a>
    <a class="list-group-item list-group-item-light <%= request.getParameter("servletName").equals("ChangePwd") ? "active" : "" %>"
       href="ChangePwd">Paramètres</a>
</div>
