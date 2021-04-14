package com.m1if10.app.servlets.Scolarite;

import com.m1if10.app.dao.PresenceAltDao;
import com.m1if10.app.modele.EtatPresence;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to manage absence in Cours
 */
@WebServlet(name = "GestionAbsences", urlPatterns = "/Scola/GestionAbsences")
public class GestionAbsences extends HttpServlet {

    /**
     * Method POST: Update the state of the presence of the Alternant
     * @param request: HTTP Servlet's Request
     * @param response: HTTP Servlet's Response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = Persistence.createEntityManagerFactory("pu-savapa").createEntityManager();
        PresenceAltDao pa = new PresenceAltDao(em);
        pa.begin();
        EtatPresence updateEtat = EtatPresence.ABJ;
        String etatString = request.getParameter("etat");
        for(EtatPresence etat : EtatPresence.class.getEnumConstants()) {
            if (etat.toString().equals(etatString)) {
                updateEtat = etat;
            }
        }
        System.out.println(updateEtat.toString() + ", alt : "+ request.getParameter("idAlt") + ", cours : " + request.getParameter("idCours"));
        pa.updateAbsence(updateEtat,request.getParameter("idAlt"),request.getParameter("idCours"));
        pa.end();
        response.sendRedirect("GestionAbsences");
    }

    /**
     * Method GET: get absences of the Alternant and redirects to the absences managing page
     * @param request: HTTP Servlet's Request
     * @param response: HTTP Servlet's Response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = Persistence.createEntityManagerFactory("pu-savapa").createEntityManager();
        PresenceAltDao pa = new PresenceAltDao(em);
        pa.begin();
        request.setAttribute("listeAbsences",pa.getAbsences(EtatPresence.ABINJ));
        pa.end();
        request.getRequestDispatcher("/jsp/scolarite/gestion_absences.jsp").forward(request, response);
    }
}
