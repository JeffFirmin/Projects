<%@ page import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="../header.jsp">
    <jsp:param name="servletName" value="AjoutUE"/>
    <jsp:param name="title" value="Ajout d'une ue" />
    <jsp:param name="userType" value="scola"/>
</jsp:include>

<form action="AjoutUE" method="post">
    <div class="container">
        <div class="form-group">
            <label>Nom :
                <input type="text" name="nameUe" class="form-control" placeholder="nom de l'ue" required>
            </label>
        </div>
        <div class="form-group">
            <label>Intitule :
                <input type="text" name="intitule" class="form-control" placeholder="intitule de l'ue" required>
            </label>
        </div>
        <div class="form-group">
            <label> Groupe :
                <select name="prof">
                    <% Map<String,String> profs =(Map<String,String>) request.getAttribute("map");
                        for(String prof : profs.keySet()){%>
                    <option value="<%= prof %>"><%= profs.get(prof) %></option>
                    <%}%>
                </select>
            </label>
        </div>
        <input type="submit" value="enregistrer UE">
    </div>
</form>

<c:choose>
    <c:when test="${requestScope.fail == 'false'}">
        <div class="alert alert-success">
            <span><c:out value="${requestScope.message}" /></span>
        </div>
    </c:when>
    <c:when test="${requestScope.fail=='true'}">
        <div class="alert alert-danger">
            <span><c:out value="${requestScope.message}" /></span>
        </div>
    </c:when>
</c:choose>


<jsp:include page="../footer.jsp" />
