package fr.univ.lyon1.m1if.m1if10Grp13.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ.lyon1.m1if.m1if10Grp13.classes.Creneau;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOCreneau;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOInscrit;
import fr.univ.lyon1.m1if.m1if10Grp13.daoException.DAOException;

/**
 * Servlet implementation class CreneauGestion
 */
@WebServlet(name = "CreneauGestion", urlPatterns="/CreneauGestion")
public class CreneauGestion extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAOCreneau daoCreneau;

    private ServletContext servletContext;
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.servletContext = config.getServletContext();
        this.daoCreneau = (DAOCreneau) servletContext.getAttribute("daoCreneau");
        System.out.println("DaoCreneau cretaed:" + daoCreneau);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO recuperer la liste des paramètres
        String dateCreneauParam = request.getParameter("dateCreneau");;
        String heureCreneauParam = request.getParameter("heureCreneau");;
        String dureeParam = request.getParameter("duree");;
        Creneau creneau = null;
        
        try {
            // Conversion des string récupéré des parametres en objet Date
            Date dateCreneau = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(dateCreneauParam);
            Timestamp heureCreneau = (Timestamp) new SimpleDateFormat("hh:mm").parse(heureCreneauParam);
            Timestamp duree = (Timestamp) new SimpleDateFormat("hh:mm").parse(dureeParam);
            
            // Creation d'une instance Creneau
            creneau = new Creneau(dateCreneau, heureCreneau, duree);
            
            // Ajouter un creneau à la BD
            daoCreneau.creer(creneau);
            
            // TODO DELETE THIS, JUST FOR TESTING
            
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

}
