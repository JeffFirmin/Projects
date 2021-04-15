<%@ page import="java.util.List" %>
<%@ page import="com.m1if10.app.modele.Alternant" %>
<%@ page import="com.m1if10.app.modele.Cours" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.m1if10.app.modele.EtatPresence" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="../header.jsp">
    <jsp:param name="servletName" value="GestionAbsences"/>
    <jsp:param name="title" value="Gestion des absences" />
    <jsp:param name="userType" value="scola"/>
</jsp:include>

<%
    Map<Alternant, List<Cours>> absences = new HashMap<>();
    absences = (Map<Alternant, List<Cours>>) request.getAttribute("listeAbsences");
%>

    <%if(absences.size()!=0){
        for(Alternant alt : absences.keySet()){
            for(Cours cours : absences.get(alt)){%>
                <div class="absence">
                    <p onclick=""><%=alt.getNom() + " "  + alt.getPrenom() + " absent au cours de " + cours.getNameUe().getIntitule() + " à " + cours.getContent().getDate()%></p>
                    <form action="GestionAbsences" method="post">
                        <label> Nouvel etat :
                            <select name="etat">
                                <% EtatPresence[] etats = EtatPresence.class.getEnumConstants();
                                for(EtatPresence etat : etats){
                                    if(!etat.toString().equals("ABINJ") && !etat.toString().equals("NULL")){%>
                                        <option value="<%= etat %>"><%= etat %></option>
                                    <%}
                                }%>
                            </select>
                        </label>
                        <input type="hidden" name="idAlt" value="<%=alt.getEmail()%>">
                        <input type="hidden" name="idCours" value="<%=cours.getIdCour()%>">
                        <input type="submit" value="Changer etat presence">
                    </form>
                </div>
            <%}
        }
    }
    else{%>
        <div id="AucuneAbsence">
            <p>Pas d'absence cette semaine</p>
        </div>
    <%}%>

<jsp:include page="../footer.jsp" />
