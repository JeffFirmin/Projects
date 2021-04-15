<div class="user-info">
	 <img class="profil-picture" src="img/default-pic.png" alt="default profil picture">
     <div class="user-name">${sessionScope.user.getNomInscrit()}</div>
     <div class="user-club">club : ${sessionScope.user.getClubOfUserName()==null? "Aucun" : sessionScope.user.getClubOfUserName()}</div>
</div>

<div class="btn-list">
	<button class="menu-button home-button" onclick="showAccueil()">Accueil</button>
	<button class="menu-button calendrier-button" onclick="showCalendrier()">Reserver</button>
	<button class="menu-button club-button" onclick="showListeclubs()">Liste des Clubs</button>
	<button class="menu-button profil-button" onclick="showProfil()">Mon compte</button>
</div>




<script>
	function showAccueil(){
		document.getElementById("accueil").style.display="block";
		document.getElementById("reserver").style.display="none";
		document.getElementById("listeclubs").style.display="none";
		document.getElementById("profil").style.display="none";
	}

	function showCalendrier(){
		document.getElementById("accueil").style.display="none";
		document.getElementById("reserver").style.display="block";
		document.getElementById("listeclubs").style.display="none";
		document.getElementById("profil").style.display="none";
	}

	function showListeclubs(){
		document.getElementById("accueil").style.display="none";
		document.getElementById("reserver").style.display="none";
		document.getElementById("listeclubs").style.display="block";
		document.getElementById("profil").style.display="none";
	}

	function showProfil(){
		document.getElementById("accueil").style.display="none";
		document.getElementById("reserver").style.display="none";
		document.getElementById("listeclubs").style.display="none";
		document.getElementById("profil").style.display="block";
	}

</script>

