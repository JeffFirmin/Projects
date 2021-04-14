<h2>Inscription Club</h2>
<form method="post" action="ClubInscription">
	<div class="form-club">
		<div class="user-box">
			<input type="text" name="nomClub" required="">
			<label>Nom du club*</label>
		</div>
		<div class="user-box">
			<input type="text" name="emailClub" required="">
			<label>Email*</label>
		</div>
		<div class="user-box">
			<input type="text" name="nomResponsable" required="">
			<label>Nom responsable*</label>
		</div>
		<div class="user-box">
			<input type="password" name="passClub" minlength="8" required="">
			<label>Mot de passe*</label>
		</div>
		<div class="user-box">
			<input type="number" name="nbMax" required="">
			<label>Capacité* (nb adhérent max)</label>
		</div>
	</div>
	<a>
		<span></span>
		<span></span>
		<input class="valid-form-but" type="submit" value="Inscrire le club">
		<span></span>
		<span></span>
	</a>
</form>
<div class="inscrire">
	<a class="aff-connexion">Connexion</a>
	-
	<a class="aff-inscr-adh">Inscription Adhérent</a>
</div>