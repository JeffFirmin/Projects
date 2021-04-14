<%@ page contentType="text/html;charset=UTF-8" %>

<div class="col-md-3" id="navigation">
    <a class="list-group-item list-group-item-light  <%= request.getParameter("servletName").equals("GestionCours") ? "active" : "" %>"
       href="GestionCours">Gestion des cours</a>
    <a class="list-group-item list-group-item-light  <%= request.getParameter("servletName").equals("Scanner") ? "active" : "" %>"
       href="Scanner">Scanner des étudiants</a>
</div>
