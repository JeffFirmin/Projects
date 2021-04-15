<%@ page contentType="text/html;charset=UTF-8" %>

<div class="col-md-3" id="navigation">
    <a class="list-group-item list-group-item-light  <%= request.getParameter("servletName").equals("GestionAbsences") ? "active" : "" %>"
       href="GestionAbsences">Gestion des absences</a>
    <a class="list-group-item list-group-item-light  <%= request.getParameter("servletName").equals("GestionUtilisateurs") ? "active" : "" %>"
       href="GestionUtilisateurs">Gestion des utilisateurs</a>
    <a class="list-group-item list-group-item-light  <%= request.getParameter("servletName").equals("AjoutUE") ? "active" : "" %>"
       href="AjoutUE">Ajout d'UE</a>
</div>
