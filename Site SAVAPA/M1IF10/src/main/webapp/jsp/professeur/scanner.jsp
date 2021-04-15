<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="../header.jsp">
    <jsp:param name="servletName" value="Scanner"/>
    <jsp:param name="title" value="Scanner des étudiants" />
    <jsp:param name="userType" value="prof"/>
</jsp:include>

<h1 class="h3">Scanner des étudiants</h1>

<c:if test="${cours != null}">
    <p>Vérification des présences pour <strong>${cours.getNameUe().getIntitule()}</strong></p>

    <div id="scanned" class="invisible">
        <h2 class="h4">Étudiants scannés</h2>
        <table id="scannedtable" class="table">
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>N° Étudiant</th>
                    <th>Photo</th>
                </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>

    <div id="scanner" class="text-center">

    </div>

    <div id="afterScan">

    </div>
</c:if>

<c:if test="${cours == null}">
    <span>Vous n'avez aucun cours en ce moment.</span>
</c:if>



<script type="text/javascript" src="${pageContext.request.contextPath}/js/scan/jsqrscanner.nocache.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scan/scan.js"></script>
<jsp:include page="../footer.jsp" />
