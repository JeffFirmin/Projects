<%@ page import="com.m1if10.app.modele.Groupe" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="../header.jsp">
    <jsp:param name="servletName" value="GestionUtilisateurs"/>
    <jsp:param name="title" value="Gestion des utilisateurs" />
    <jsp:param name="userType" value="scola"/>
</jsp:include>

<style>
    #demo-1{
        margin: 1em 0 0.5em 0;
        color: royalblue;
        font-weight: normal;
        font-family: 'Ultra', sans-serif;
        font-size: 24px;
        line-height: 42px;
        text-transform: uppercase;
        text-shadow: 0 2px white, 0 3px #777;
    }
</style>

<script type="text/javascript" language="javascript">
    function hidealternant() {
        var alt = document.getElementById("alternant");
        alt.style.display = "none";
        var type = document.getElementById("type");
        type.value = "prof";

        var entreprise = document.getElementById("entreprise");
        var numEtu = document.getElementById("numEtu");
        entreprise.required = false;
        numEtu.required = false;
    }
    function showalternant() {
        var alt = document.getElementById("alternant");
        alt.style.display = "block";
        var type = document.getElementById("type");
        type.value = "alt";

        var entreprise = document.getElementById("entreprise");
        var numEtu = document.getElementById("numEtu");
        entreprise.required = true;
        numEtu.required = true;
    }
</script>

<button onclick="showalternant();" class="btn btn-info" type="button" style="margin: 20px 0px;">Enregistrer un alternant</button>
<button onclick="hidealternant();" class="btn btn-info" type="button">Enregistrer un professeur</button>

<form action="GestionUtilisateurs" method="post" enctype="multipart/form-data">
    <div class="container">
        <h1 id="demo-1">Enregistrer un membre</h1>
        <div class="form-group">
            <label for="nom">Nom</label>
            <input type="text" class="form-control" id="nom" name="nom" aria-describedby="emailHelp" placeholder="Saisissez le nom">
        </div>
        <div class="form-group">
            <label for="prenom">Prénom</label>
            <input type="text" class="form-control" id="prenom" name="prenom" aria-describedby="emailHelp" placeholder="Saisissez le prénom">
        </div>
        <div id="alternant">
            <div class="form-group">
                <label for="entreprise">Entreprise</label>
                <input type="text" class="form-control" id="entreprise" name="entreprise" aria-describedby="emailHelp" placeholder="Saisissez l'entreprise">
            </div>
            <div class="form-group">
                <label for="nom">Numéro étudiant :</label>
                <input type="text" class="form-control" id="numEtu" name="numEtu" aria-describedby="emailHelp" placeholder="Saisissez le numéro étudiant">
            </div>
            <div class="form-group">
                <label> Groupe :
                    <select name="groupe">
                        <% Groupe[] groupes = Groupe.class.getEnumConstants();
                            for(Groupe grp : groupes){%>
                        <option value="<%= grp %>"><%= grp %></option>
                        <%}%>
                    </select>
                </label>
            </div>
            <label> Image :
                <input type="file" name="photo" accept="image/png, image/jpeg">
            </label>
        </div>
        <input type="hidden" name="type" id="type" value="alt">
        <button type="submit" class="btn btn-primary mb-2">Enregistrer</button>
    </div>
</form>

<c:choose>
    <c:when test="${requestScope.pdw != null and requestScope.message != null}">
        <div class="alert alert-success">
            <span><c:out value="${requestScope.message}" /></span>
            <span>Password : <c:out value="${requestScope.pdw}" /></span>
        </div>
    </c:when>
    <c:when test="${requestScope.pdw == null and requestScope.message != null}">
        <div class="alert alert-danger">
            <span><c:out value="${requestScope.message}" /></span>
        </div>
    </c:when>
</c:choose>

<jsp:include page="../footer.jsp" />
