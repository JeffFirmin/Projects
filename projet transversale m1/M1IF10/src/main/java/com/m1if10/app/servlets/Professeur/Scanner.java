package com.m1if10.app.servlets.Professeur;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m1if10.app.dao.AlternantDao;
import com.m1if10.app.dao.PresenceAltDao;
import com.m1if10.app.modele.Alternant;
import com.m1if10.app.modele.Cours;
import com.m1if10.app.modele.EtatPresence;
import com.m1if10.app.modele.Prof;
import com.m1if10.app.modele.jackson.AlternantJackson;
import org.apache.commons.io.FileUtils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Servlet of QRCode scanner
 */
@WebServlet(name = "Scanner", urlPatterns = "/Prof/Scanner")
public class Scanner extends HttpServlet {

    /**
     * Initialize the scanner with Alternant and PresenceAlternant managers
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
    }

    /**
     * Method POST: Manages the post-scan of the QRCode of Alternant by Prof
     * @param request: HTTP Servlet's Request
     * @param response: HTTP Servlet's Response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cleQrParam = request.getParameter("cleQr");
        EntityManager em = Persistence.createEntityManagerFactory("pu-savapa").createEntityManager();
        Alternant alt = new AlternantDao(em).findByCleQr(cleQrParam);
        PresenceAltDao paDao = new PresenceAltDao(em);
        Prof currentProf = (Prof) request.getSession().getAttribute("user");
        Cours cours = getCurrentCours(currentProf);

        if (alt == null || cours == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            paDao.begin();
            try {
                paDao.createPresence(alt, cours, EtatPresence.PRST);
            } catch (Exception e) {
                // TODO
            }
            paDao.end();

            // Retourner l'alternant en JSON
            String photo;
            try {
                byte[] fileContent = FileUtils.readFileToByteArray(new File("../photos/" + alt.getUrlPhoto()));
                photo = Base64.getEncoder().encodeToString(fileContent);
                System.out.println(photo);
            } catch (IOException e) {
                photo = "";
            }
            AlternantJackson altJackson = new AlternantJackson(alt,photo);
            String altJson = new ObjectMapper().writeValueAsString(altJackson);
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(altJson);
            out.flush();
        }
    }

    /**
     * Method GET: redirects to the Scanning page
     * @param request: HTTP Servlet's Request
     * @param response: HTTP Servlet's Response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Prof currentProf = (Prof) request.getSession().getAttribute("user");
        Cours currentCours = getCurrentCours(currentProf);

        if (currentCours != null) {
            request.setAttribute("cours", currentCours);
        }

        request.getRequestDispatcher("/jsp/professeur/scanner.jsp").forward(request, response);
    }

    /**
     * Get the current time Cours to be scanned
     * @param prof: Prof model of the searched Cours model
     * @return Currently available Cours of Prof
     */
    private Cours getCurrentCours(Prof prof) {
        Date currentDate = new Date();
        List<Cours> currentCours = new ArrayList<>();
        prof.getUEs().forEach(liaisonProfUe -> {
            liaisonProfUe.getUE().cours.forEach(cours -> {
                Date dateDebut = cours.getContent().getDate();
                Calendar c = Calendar.getInstance();
                c.setTime(dateDebut);
                c.add(Calendar.HOUR_OF_DAY, (int) cours.getContent().getDuree() + 1);
                Date dateFin = c.getTime();
                if (currentDate.after(dateDebut) && currentDate.before(dateFin)) currentCours.add(cours);
            });
        });
        if (currentCours.isEmpty()) return null;
        return currentCours.get(0);
    }
}
