<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%-- <%@page import="fr.univ.lyon1.m1if.m1if10Grp13.classes.Inscrit"%> --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2 class="interface-titles">
	<b>Mon Profil</b>
</h2>
<hr class="line">

<!-- Info user -->
<c:if test="${sessionScope.user != null}">
<div class="user-infos">
	<div class="container">
		<div class="row align-items-center">
			<div class="col-md-auto">
				<img class="profil-picture-large" src="img/default-pic.png"
					alt="default profil picture">
			</div>
			<div class="col">
				<div class="info-name">${sessionScope.user.getNomInscrit()}</div>
				<hr class="subline">
				<div class="info-userClub">Club : ${sessionScope.user.getClubOfUserName()!=null? 
															sessionScope.user.getClubOfUserName() 
															:"Vous n'etes pas encore adhérent d'un club."}</div>
			</div>
			<div class="w-100" style="margin: 2vh;"></div>
			<div class="col">
				<span class="infos-th">Email :</span>
				${sessionScope.user.getEmailInscrit()} <br> <span
					class="infos-th">Téléphone : </span>
				${sessionScope.user.getTelInscrit()}
			</div>
			<div class="col">
				<span class="infos-th">Date de naissance :</span>
				<fmt:formatDate pattern="dd-MM-yyyy"
					value="${sessionScope.user.getNaissanceInscrit()}" />
				<br> <span class="infos-th">Date d'inscription :</span>
				<fmt:formatDate pattern="dd-MM-yyyy"
					value="${sessionScope.user.getDateInscription() }" />
			</div>
		</div>

	</div>
</div>
</c:if>

<!-- Info club -->
<c:if test="${sessionScope.club != null}">
<div class="user-infos">
	<div class="container">
		<div class="row align-items-center">
			<div class="col-md-auto">
				<img class="profil-picture-large" src="img/default-pic.png"
					alt="default profil picture">
			</div>
			<div class="col">
				<div class="info-name">${sessionScope.club.getNomClub()}</div>
				<hr class="subline">
				<div class="info-userClub">Coach :
					${sessionScope.club.getNomResponsable()}</div>
			</div>
			<div class="w-100" style="margin: 2vh;"></div>
			<div class="col">
				<span class="infos-th">Email :</span>
				${sessionScope.club.getEmailClub()} <br> 
			</div>
			<div class="col">
				<span class="infos-th">Nombre d'adhérents max :</span>
				${sessionScope.club.getNbMaxAdherent()} <br>
			</div>
		</div>
	</div>
</div>
</c:if>