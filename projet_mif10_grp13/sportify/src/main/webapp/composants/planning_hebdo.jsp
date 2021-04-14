<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>


<%
   Date dNow = new Date();
   SimpleDateFormat n_semaine = new SimpleDateFormat ("w");
   SimpleDateFormat YMD = new SimpleDateFormat ("yyyy-MM-dd");
%>

<h2 class="interface-titles"><b>Reservation</b></h2>
<hr class="line">

<div class="reservation-form">
    <form method="POST" action="Reservation">
	  <div class="form-row align-items-center">
	  	<div class="col-auto">
	  		<div class="form-group">
	  			<label for="selectTerrain">Terrain : </label>
	  			<select class="form-control" id="selectTerrain" onchange="refreshTerrain()" name="terrain">
				  <option value="1">Terrain 1
				  <option value="2">Terrain 2
				  <option value="3">Terrain 3
				  <option value="4">Terrain 4
				  <option value="5">Terrain 5
				  <option value="6">Terrain 6
				</select>
	  		</div>
	  	</div>
	    <div class="col-auto">
	      <div class="form-group">
		    <label for="selectJour">Jour :</label>
		    <input type="date" id="day" name="day"
       				value="<%=YMD.format(dNow)%>"
       				min="<%=YMD.format(dNow)%>">
		  </div>
	    </div>
	    <div class="col-auto">
	      <div class="form-group">
		    <label for="selectHeure">Heure :</label>
		    <select class="form-control" id="selectHeure" name="horaire">
		     	<c:forEach var="i" begin="8" end="20" step="1">
		     		<option value="${i}">${i}h</option>
		     	</c:forEach>
		    </select>
		  </div>
	    </div>
	    <div class="col-auto">
	      <div class="form-group">
		    <label for="selectDuree">Duree :</label>
		    <select class="form-control" id="selectDuree" name="duree">
		     	<option value="1">1h</option>
		     	<option value="2">2h</option>
		     	<option value="3">3h</option>
		    </select>
		  </div>
	    </div>
	    <div class="col-auto">
	    	<label></label>
	    	<button type="submit" class="btn btn-primary mb-2">Reserver</button>
	    </div>
	  </div>
	</form>
</div>



<div class="table-responsive planning">
  <table class="table">
	<thead class="planning-thead">

        <tr>
          <th scope="col">Sem. <%=n_semaine.format(dNow)%></th>
          <th scope="col">Lundi</th>
          <th scope="col">Mardi</th>
          <th scope="col">Mercredi</th>
          <th scope="col">Jeudi</th>
          <th scope="col">Vendredi</th>
          <th scope="col">Samedi</th>
          <th scope="col">Dimanche</th>
        </tr>


      </thead>
	<tbody>
	<c:forEach var="plan" items="${sessionScope.planning}">
		<tr>
			<td class="">${plan.getValue()}</td>
			<td class="">${plan.getValue()}</td>
			<td class="">${plan.getclubId()}</td>
			<td class="">${plan.getclubId()}</td>
			<td class="">${plan.getclubId()}</td>
			<td class="">${plan.getclubId()}</td>
			<td class="">${plan.getclubId()}</td>
		</tr>
	</c:forEach>
      </tbody>
  </table>
</div>

<a id="ter1" href="PlanningGestion?stade=1"></a>
<a id="ter2" href="PlanningGestion?stade=2"></a>
<a id="ter3" href="PlanningGestion?stade=3"></a>
<a id="ter4" href="PlanningGestion?stade=4"></a>
<a id="ter5" href="PlanningGestion?stade=5"></a>
<a id="ter6" href="PlanningGestion?stade=6"></a>

<script>
	function refreshTerrain() {
		var x = document.getElementById("selectTerrain").value;
		console.log("refresh terrain "+x);
		var but = document.getElementById("ter" + x);
		but.click();
	}
</script>