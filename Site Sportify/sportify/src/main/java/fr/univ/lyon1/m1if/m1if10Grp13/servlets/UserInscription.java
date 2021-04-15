package fr.univ.lyon1.m1if.m1if10Grp13.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import fr.univ.lyon1.m1if.m1if10Grp13.daoException.DAOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ.lyon1.m1if.m1if10Grp13.classes.Inscrit;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOInscrit;

/**
 * Servlet implementation class UserInscription
 */
@WebServlet(name = "UserInscription", urlPatterns = "/UserInscription")
public class UserInscription extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private DAOInscrit daoInscrit;
    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.servletContext = config.getServletContext();
        this.daoInscrit = (DAOInscrit) servletContext.getAttribute("daoInscrit");

    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInscription() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupération des parametres
        String nomInscrit = request.getParameter("nomInscrit");
        String prenomInscrit = request.getParameter("prenomInscrit");
        String password = request.getParameter("password");
        String emailInscrit = request.getParameter("emailInscrit");
        String telInscrit = request.getParameter("telInscrit");
        Date naissanceInscrit = null;

        try {
            naissanceInscrit = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("naissanceInscrit"));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        
        // Date d'inscription (now)
        Date dateInscription = new Date();
        Inscrit inscrit;
        //System.out.println("Inscrit Instancier");
        
        // Creation d'une instance de l'inscrit
        inscrit = new Inscrit(emailInscrit, null, nomInscrit + " " + prenomInscrit, telInscrit, password, naissanceInscrit, dateInscription);
        try {
            // check if email and password are not null
            if(emailInscrit != null && password != null){
                // Ajouter l'inscrit à la BD
                if(daoInscrit.creer(inscrit)) {
                    /* Utilisateur à bien été créé */
                    PrintWriter out = response.getWriter();
                    out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
                    out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
                    out.println("<script>");
                    out.println("$(document).ready(function() {");
                    out.println("swal(\"WELCOME\", \"Votre compte à bien été créé !\", \"success\");");
                    out.println("});");
                    out.println("</script>");
                } else {
                    /* Utilisateur existe déjà */
                    PrintWriter out = response.getWriter();
                    out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
                    out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
                    out.println("<script>");
                    out.println("$(document).ready(function() {");
                    out.println("swal(\"Oops...\", \"L'utilisateur existe déjà. Cette adresse mail est déjà utilisé sur un autre compte.\", \"error\");");
                    out.println("});");
                    out.println("</script>");
                }
            }
        } catch ( DAOException e) {
                e.printStackTrace();
        }
        // Redirection vers la page de connexion
        RequestDispatcher rd =  request.getRequestDispatcher("./connexion.jsp");
        rd.include(request, response);

    }

}
