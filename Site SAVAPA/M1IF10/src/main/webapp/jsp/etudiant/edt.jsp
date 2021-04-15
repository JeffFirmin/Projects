<%@ page import="java.util.List" %>
<%@ page import="com.m1if10.app.modele.Cours" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.m1if10.app.modele.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="../header.jsp">
    <jsp:param name="servletName" value="EDT"/>
    <jsp:param name="title" value="Emploi du temps" />
    <jsp:param name="userType" value="etu"/>
</jsp:include>
<style>
    #cours{
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }

    #cours td, #cours th {
        border: 1px solid #ddd;
        padding: 8px;
    }

    #cours tr:nth-child(even){background-color: #f2f2f2;}

    #cours tr:hover {background-color: #ddd;}

    #cours th {
        padding-top: 12px;
        padding-bottom: 12px;
        text-align: left;
        background-color: #007bff;
        color: white;
        font-weight: bold;
    }
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
<body>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="currentWeek" value="${now}" pattern="w" />
<c:set var="currentWeek" value="${currentWeek}" />
<h2><c:out value="Emploi du temps (Semaine ${currentWeek})" /> </h2>
<br />
<div class="table-responsive">
    <table id="cours">
        <tr>
            <th>Date</th>
            <th>UE</th>
            <th>Cours</th>
            <th>Salle</th>
            <th>Duree</th>
            <th>Groupe</th>
            <th>Etat</th>
        </tr>

        <c:set var="compteur" value="0" />
        <c:forEach items="${allCourse.keySet()}" var="cours">
            <fmt:formatDate value="${cours.content.date}" pattern="w" var="week" />
            <c:if test="${currentWeek eq week}">
                <c:set var="dureeEntier" value="${cours.content.duree.intValue()}" />
                <c:set var="dureeDecimale" value="${(((cours.content.duree - (cours.content.duree.intValue()).doubleValue()) * 30) / 0.5).intValue() }" />
                <tr>
                    <td> <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${cours.content.date}" /></td>
                    <td> <c:out  value="${cours.nameUe.idUe}" /> </td>
                    <td> <c:out  value="${cours.content.nature}" /> </td>
                    <td> <c:out  value="${cours.content.salle}" /> </td>
                    <td>
                        <c:out  value="${dureeEntier}h"/>
                        <c:if test="${dureeDecimale ne 0}">
                            <c:out value="${dureeDecimale}" />
                        </c:if>
                    </td>
                    <td> <c:out  value="${cours.content.groupe}" /> </td>
                    <td>
                        <c:choose>
                            <c:when test="${allCourse.get(cours).toString() eq 'NULL'}">
                                <c:out value=""/>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${allCourse.get(cours)}"/>
                                <br />
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <c:set var="compteur" value="${compteur + cours.content.duree}" />
            </c:if>
        </c:forEach>
    </table>
</div>
<br />
<c:if test="${compteur < 35}">
    <a style="color: red"><c:out value="Vous n'avez pas 35h de cours veuillez vous rajouter des heures de travail en autonomie: "/></a>
    <br />
    <br />
    <c:if test="${requestScope.message != null}">
        <div class="alert alert-success">
            <span><c:out value="${requestScope.message}" /></span>
        </div>
    </c:if>
    <%
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        Date debutSemaine = c.getTime();
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
        Date finSemaine = c.getTime();
    %>
    <c:set var="minDate" value="<%= debutSemaine%>" />
    <fmt:formatDate value="${minDate}" pattern="yyyy-MM-dd" var="minDate" />
    <c:set var="maxDate" value="<%= finSemaine%>" />
    <fmt:formatDate value="${maxDate}" pattern="yyyy-MM-dd" var="maxDate" />

    <div style="border-radius: 5px;background-color: #f2f2f2; padding: 20px;">
        <form action="EDT" method="post" >
            <label>
                Choisissez une date:
                <input type="date" min="${minDate}" max="${maxDate}" name="date" id="date" value="${minDate}" required/>
            </label>
            <br />
            <label>
                Choisissez une heure (entre 7h45 et 18h):
                <input type="time" id="heure" name="heure" min="07:45" max="18:00" value="07:45" required>
            </label>
            <br />
            <label>
                Choisissez la durée de votre créneau de travail en autonomie:
                <input name="duree" type="number" min="1" max="${35 - compteur}" step="0.5" value="1" onkeypress="return false;" required/>
            </label>
            <br />
            <%
                User currentUser = (User) session.getAttribute("user");
            %>
            <input type="hidden" name="mail" id="mail" value="<%= currentUser.getEmail()%>">
            <input type="submit" value="Enregistrer">
        </form>
    </div>
</c:if>
</body>
<jsp:include page="../footer.jsp" />