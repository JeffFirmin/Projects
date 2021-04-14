<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<style>
.soonNews {
	width: 430px;
	height: 648px;
	border: solid red 3px;
	background-color: #F2F2F2;
	overflow: scroll;
}

.banniere-sn {
	width: 100%;
	heigth: 60px;
	background-color: #282828;
}

.banniere-sn h2 {
	color: white;
}

hr.line {
	width: 95%;
	margin-left: 10px;
	border: 1px solid gold;
}

.content-sn {
	width: 95%;
	/*height: 440px;*/
	background-color: #D9D9D9;
	margin-bottom: 20px;
	margin-left: 10px;
	margin-top: 20px;
	/*margin-right:10px;*/
}

.soon-news {
	overflow: hidden !important;
}

</style>


<div class="soonNews">

	<div class="banniere-sn">
		<h2>Prochaines actualités :</h2>
	</div>
	<div class="content-sn">Samedi tournois des poussins au stade de
		Villeurbanne. Venez à partir de 15h.</div>

	<hr class="line">

	<div class="content-sn">Au stade de Mermoz changement de pelouse.
		Le stade revient d'ici février tout neuf pour vous acceuillir.</div>

	<hr class="line">

	<div class="content-sn">Le Club "L'Aigle" avec son coach Philippe
		vient de nous rejoindre !</div>

	<hr class="line">

	<div class="content-sn">Car la cohesion d'équipe est importante
		dans le foot, tous les premiers dimanches du mois repas partagé au
		Parc de la tete d'or. Prenez à boire ou à manger et venez rencontrer
		les adhérents de Sportify. Ballon, match et crampons seront au coeur
		des conversations. (En cas d'intemperies le repas se déroulera dans la
		salle des fêtes de croix rousse)</div>


</div>

