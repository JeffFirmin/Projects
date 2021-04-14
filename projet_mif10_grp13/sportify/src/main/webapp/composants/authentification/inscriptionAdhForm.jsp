<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar, java.text.SimpleDateFormat" %>
<%
	Date date6yearsAgo = new Date();
	SimpleDateFormat min_age = new SimpleDateFormat ("yyyy-MM-d");
	
	Calendar cal = Calendar.getInstance();
	  cal.setTime(date6yearsAgo);
	  cal.add(Calendar.YEAR, -6);
	  date6yearsAgo = cal.getTime();
%>
<h2>Inscription adhérent</h2>
<form method="POST" action="UserInscription">
	<div class="form-adh">
		<div class="user-box">
			<input type="text" name="nomInscrit" required="">
			<label>Nom*</label>
		</div>
		<div class="user-box">
			<input type="text" name="prenomInscrit" required="">
			<label>Prénom*</label>
		</div>
		<div class="user-box">
			<input type="password" name="password" minlength="8" required="">
			<label>Mot de passe* (min 8 caractères)</label>
		</div>
		<div class="user-box">
			<input type="email" name="emailInscrit" required="">
			<label>Email*</label>
		</div>
		<div class="user-box">
			<input type="tel" name="telInscrit" pattern="^0[0-9]{9}" required="">
			<label>n° téléphone*</label>
		</div>
		<div class="user-box">
			<input type="date" name="naissanceInscrit" value=<%= min_age.format(date6yearsAgo) %> max= <%= min_age.format(date6yearsAgo) %> required>
			<span class="validity"></span>
			<label>Date de naissance* (6ans min)</label>
		</div>
	</div>
	<a>
		<span></span>
		<span></span>
		<input class="valid-form-but" type="submit" value="S'inscrire">
		<span></span>
		<span></span>
	</a>
</form>
<div class="inscrire">
	<a class="aff-connexion">Connexion</a>
	-
	<a class="aff-inscr-club">Inscription Club</a>
</div>