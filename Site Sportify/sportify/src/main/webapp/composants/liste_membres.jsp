<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<h2 class="interface-titles"> <b>Liste des membres</b> </h2>
<hr class="line">

<div class="table-responsive adhList">
  <table class="table">
	<thead class="adhList-thead">
        <tr>
          <th scope="col">Nom :</th>
          <th scope="col">Email :</th>
          <th scope="col">Telephone :</th>
          <th scope="col">Date de naissance :</th>
        </tr>
      </thead>
	<tbody>
		<c:set var="count2" value="0" scope="page" />
		<c:forEach var="adh" items="${sessionScope.adhList}">
		  	<tr>
		          <td class="l-adh-name">${adh.getNomInscrit()}</td>
		          <td class="l-adh-email">${adh.getEmailInscrit()}</td>
		          <td class="l-adh-resp">${adh.getTelInscrit()}</td>
		          <td class="l-adh-nb-adh"><fmt:formatDate pattern="dd-MM-yyyy" value="${adh.getNaissanceInscrit()}" /></td>
		          <td> <button id="adh${count2}" class="btn btn-primary mb-2" value="${adh.getEmailInscrit()}" > Exclure </button></td>
	        </tr>
	        <a id="call${count2}" href="<c:url value="/Exclure?adh=${adh.getEmailInscrit()}"/>"></a>
	        <c:set var="count2" value="${count2 + 1}" scope="page"/>
		</c:forEach>
	</tbody>
  </table>
</div>

<script>
	var nb_adh = document.getElementsByClassName("l-adh-name").length;
	for (let i=0; i<nb_adh; i++) {
		$("#adh"+i).click(function() {
			var callServlet = document.getElementById("call"+i);
			callServlet.click()
		});
	}
</script>