<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="../header.jsp">
    <jsp:param name="servletName" value="GestionCours"/>
    <jsp:param name="title" value="Gestion des cours" />
    <jsp:param name="userType" value="prof"/>
</jsp:include>

<jsp:include page="../footer.jsp" />
