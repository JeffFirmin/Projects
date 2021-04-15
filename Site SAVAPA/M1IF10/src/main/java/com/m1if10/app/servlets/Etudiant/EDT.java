package com.m1if10.app.servlets.Etudiant;

import com.m1if10.app.dao.CoursDao;
import com.m1if10.app.dao.PresenceAltDao;
import com.m1if10.app.dao.UeDao;
import com.m1if10.app.modele.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Servlet to manage the EDT model
 */
@WebServlet(name = "EDT", urlPatterns = "/Etudiant/EDT")
public class EDT extends HttpServlet {

    /**
     * CoursDao manager for the Cours in the EDT model
     */
    private CoursDao cours;

    /**
     * Initialisation of the servlet
     */
    @Override
    public void init() throws ServletException {
        super.init();
    }

    /**
     * Destroy the servlet
     */
    @Override
    public void destroy() {
        super.destroy();
    }

    /**
     * Adds extra class time for the Alternant in the EDT
     * @param request: HTTP Servlet's Request
     * @param response: HTTP Servlet's Response
     */
    private void addTA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = Persistence.createEntityManagerFactory("pu-savapa").createEntityManager();


        String tmpDate = request.getParameter("date");
        String tmpHeure = request.getParameter("heure");
        String duree = request.getParameter("duree");
        String altstring = request.getParameter("mail");
        System.out.println(altstring);
        String dateString = tmpDate + " " + tmpHeure;
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        UeDao uedao = new UeDao(em);
        UE ue = new UE();
        ue = uedao.getUe("TA");

        CoursDao coursdao = new CoursDao(em);
        Cours coursCreated = coursdao.createCours(ue,"TA", "", date, "", Double.parseDouble(duree));

        PresenceAltDao paDao= new PresenceAltDao(em);
        Alternant alternant = em.find(Alternant.class,altstring);
        paDao.createPresence(alternant,coursCreated, EtatPresence.PRST);

    }

    /**
     * Method POST: adds extra time if specified and redirects to the EDT page
     * @param request: HTTP Servlet's Request
     * @param response: HTTP Servlet's Response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addTA(request,response);
        response.sendRedirect("EDT");
    }

    /**
     * Method GET: redirects to the EDT view page
     * @param request: HTTP Servlet's Request
     * @param response: HTTP Servlet's Response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAction(request);
        request.getRequestDispatcher("/jsp/etudiant/edt.jsp").forward(request, response);
    }

    /**
     * Finds all Cours of the Alternant in the session
     * @param request
     */
    private void setAction(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        String email = currentUser.getEmail();
        request.setAttribute("email",email);
        findAll(email,request);
    }

    /**
     * Finds all Cours of the Alternant
     * @param mail: mail of the Alternant
     * @param request: HTTP Servlet's request
     */
    public void findAll( String mail,HttpServletRequest request) {
        EntityManager em = Persistence.createEntityManagerFactory("pu-savapa").createEntityManager();
        Map<Cours,EtatPresence> tmp = new PresenceAltDao(em).getPresencesAlt(mail);

        request.setAttribute("allCourse", tmp);
    }
}
