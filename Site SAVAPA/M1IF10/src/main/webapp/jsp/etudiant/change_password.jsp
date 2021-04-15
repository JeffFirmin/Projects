<%@ page import="com.m1if10.app.modele.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="../header.jsp">
    <jsp:param name="servletName" value="ChangePwd"/>
    <jsp:param name="title" value="Modification du mot de passe" />
    <jsp:param name="userType" value="etu"/>
</jsp:include>
<style>
input[type=submit] {
    background-color: #007bff;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: #005fff;
}
</style>
<%
    User currentUser = (User) session.getAttribute("user");
%>
<body>


    <c:if test="${requestScope.message != null}">

        <c:choose>
            <c:when test="${requestScope.balise eq 'succes'}">
                <div class="alert alert-success">
                    <span><c:out value="${requestScope.message}" /></span>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-danger">
                    <span><c:out value="${requestScope.message}" /></span>
                </div>
            </c:otherwise>
        </c:choose>

    </c:if>

    <div style="border-radius: 5px;background-color: #f2f2f2; padding: 20px;">
        <form action="ChangePwd" method="post" >
            <label>
                Choisissez un nouveau mot de passe: <input type="password" name="pwd" class="form-control" required>
            </label>
            <br />
            <label>
                Confirmer votre mot de passe: <input type="password" name="confirmPwd" class="form-control" required>
            </label>
            <br />
            <input type="hidden" name="mail" id="mail" value="<%= currentUser.getEmail()%>">
            <input type="submit" value="Enregistrer">
        </form>
    </div>
</body>
<jsp:include page="../footer.jsp" />
