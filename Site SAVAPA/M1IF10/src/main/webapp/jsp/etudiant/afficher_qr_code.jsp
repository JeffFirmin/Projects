<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.m1if10.app.modele.Alternant" %>


<jsp:include page="../header.jsp">
    <jsp:param name="servletName" value="AfficherQRCode"/>
    <jsp:param name="title" value="Afficher le QRCode" />
    <jsp:param name="userType" value="etu"/>
</jsp:include>
<% Alternant alternant = (Alternant) session.getAttribute("user");%>

<img src="${pageContext.request.contextPath}/qrcode/<%= alternant.getEmail() %>.png" style="width: 100%;max-width: 400px; height: auto;" alt="qrcode"/>

<jsp:include page="../footer.jsp" />
