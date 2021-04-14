<%@page import="fr.univ.lyon1.m1if.m1if10Grp13.classes.Club"%>
<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>

<h2 class="interface-titles"> <b>Liste des clubs</b> </h2>
<hr class="line">

<div class="table-responsive clubList">
  <table class="table">
	<thead class="clubList-thead">
        <tr>
          <th scope="col">Nom</th>
          <th scope="col">Email</th>
          <th scope="col">Responsable</th>
          <th scope="col">Nombre d'adh�rants</th>
        </tr>
      </thead>
	<tbody>
		<c:set var="count" value="0" scope="page" />
		<c:forEach var="club" items="${sessionScope.clubList}">
		  	<tr>
		          <td class="l-club-name">${club.getNomClub()}</td>
		          <td class="l-club-email">${club.getEmailClub()}</td>
		          <td class="l-club-resp">${club.getNomResponsable()}</td>
		          <td class="l-club-nb-adh">${club.getNbMaxAdherent()}</td>
		          <td> <button id="club${count}" class="btn btn-primary mb-2" value="${club.getEmailClub()}" > Rejoindre </button></td>
	        </tr>
<%--	        <a id="call${count}" href="Adherer?club=${club.getEmailClub()}"></a>--%>
			<a id="call${count}" href="<c:url value="/Adherer?club=${club.getEmailClub()}"/>"></a>
	        <c:set var="count" value="${count + 1}" scope="page"/>
		</c:forEach>
	</tbody>
  </table>
</div>
	
<script>
	var nb_club = document.getElementsByClassName("l-club-name").length;
	for (let i=0; i<nb_club; i++) {
		$("#club"+i).click(function() {
			//var email = document.getElementById("club"+i).value;
			//console.log(email);
			var callServlet = document.getElementById("call"+i);
			callServlet.click()
			
		});
	}
</script>
	