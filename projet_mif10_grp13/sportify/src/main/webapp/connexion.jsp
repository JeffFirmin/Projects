<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Connexion SPORTIFY</title>
	<link rel="stylesheet" href="css/style-connexion.css">
</head>
<body>
	<div class="connexion-title">SPORTIFY</div>
	
	<div id="c-c" class="cadre-authentification cadre-connexion">
		<%@ include file="composants/authentification/connexionForm.jsp" %>
	</div>

	<div id="c-i-a" class="cadre-authentification cadre-inscription-adh" style="left:-50%;">
		<%@ include file="composants/authentification/inscriptionAdhForm.jsp" %>
	</div>
	
	<div id="c-i-c" class="cadre-authentification cadre-inscription-club" style="left:-50%;">
		<%@ include file="composants/authentification/inscriptionClubForm.jsp" %>
	</div>
	
	<div class="connexion-footer">
		<p>© SPORTIFY by Team13 - All right reserved</p>
	</div>
	
	
	<script type="text/javascript">
		var affClub = document.getElementsByClassName("aff-inscr-club");
		var affCo = document.getElementsByClassName("aff-connexion");
		var affAdh = document.getElementsByClassName("aff-inscr-adh");

		var affInscrClub = function() {
			console.log("fonction");
		    var cadreToShow = document.getElementById("c-i-c");
		    var cadreToHide1 = document.getElementById("c-i-a");
		    var cadreToHide2 = document.getElementById("c-c");
		    cadreToShow.style.left = "50%";
		    cadreToHide1.style.left = "-50%";
		    cadreToHide2.style.left = "-50%";
		};
		
		var affInscrAdh = function() {
			console.log("fonction");
		    var cadreToShow = document.getElementById("c-i-a");
		    var cadreToHide1 = document.getElementById("c-i-c");
		    var cadreToHide2 = document.getElementById("c-c");
		    cadreToShow.style.left = "50%";
		    cadreToHide1.style.left = "-50%";
		    cadreToHide2.style.left = "-50%";
		};
		
		var affConnexion = function() {
			console.log("fonction");
		    var cadreToShow = document.getElementById("c-c");
		    var cadreToHide1 = document.getElementById("c-i-a");
		    var cadreToHide2 = document.getElementById("c-i-c");
		    cadreToShow.style.left = "50%";
		    cadreToHide1.style.left = "-50%";
		    cadreToHide2.style.left = "-50%";
		};
		
		for (var i = 0; i < affClub.length; i++) {
		    affClub[i].addEventListener('click', affInscrClub, false);
		}
		for (var i = 0; i < affAdh.length; i++) {
		    affAdh[i].addEventListener('click', affInscrAdh, false);
		}
		for (var i = 0; i < affCo.length; i++) {
		    affCo[i].addEventListener('click', affConnexion, false);
		}
	</script>
	
</body>
</html>