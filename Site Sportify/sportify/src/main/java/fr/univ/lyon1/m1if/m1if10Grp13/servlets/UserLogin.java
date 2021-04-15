package fr.univ.lyon1.m1if.m1if10Grp13.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import java.io.PrintWriter;

import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.univ.lyon1.m1if.m1if10Grp13.classes.Club;
import fr.univ.lyon1.m1if.m1if10Grp13.classes.Inscrit;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOClub;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOInscrit;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet(name = "UserLogin", urlPatterns = "/UserLogin")
public class UserLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServletContext servletContext;
    private DAOInscrit daoInscrit;
    private DAOClub daoClub;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.servletContext = config.getServletContext();
        this.daoInscrit = (DAOInscrit) servletContext.getAttribute("daoInscrit");
        this.daoClub = (DAOClub) servletContext.getAttribute("daoClub");
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String isclub = request.getParameter("adminbox");
        HttpSession session = request.getSession(true);
        if (session.getAttribute("user") != null || session.getAttribute("club") != null) {

            request.getSession().invalidate();

            PrintWriter out = response.getWriter();
            out.println(
                    "<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
            out.println("<script>");
            out.println("$(document).ready(function() {");
            out.println(
                    "swal(\"Oops...\", \"Une session était déjà en cours. Nous venons de fermer la session, maintenant vous pouvez vous connecter.\", \"error\");");
            out.println("});");
            out.println("</script>");

            RequestDispatcher rd = request.getRequestDispatcher("/connexion.jsp");
            rd.include(request, response);
        } else {

            Inscrit inscrit = null;
            Club club = null;
            String url = "/connexion.jsp";

            // verifier que le mot de passe et l'email sont dans les parametres du form
            if (email != null && password != null) {
                try {

                    if (isclub == null) {
                        // Rercher un inscrit par son email
                        inscrit = (Inscrit) daoInscrit.afficher(email);

                        // verifier si l'utilisateur existe ou pas
                        if (inscrit != null) {
                            if (inscrit.getPassword().equals(password)) {

                                // ajouter l'utilisateur à la session
                                session.setAttribute("user", inscrit);
                                session.setAttribute("clubList", daoClub.afficherAll());

                                // Redirection vers la page personnelle
                                this.servletContext.getRequestDispatcher("/interface.jsp").forward(request, response);
                            } else {
                                //System.out.println("Wrong email or password");
                                error_msg(response);
                                //this.servletContext.getRequestDispatcher(url).forward(request, response);
                                RequestDispatcher rd =  request.getRequestDispatcher(url);
                                rd.include(request, response);
                            }
                        } else {
                            //System.out.println("Wrong email or password");
                        	error_msg(response);
                            //this.servletContext.getRequestDispatcher(url).forward(request, response);
                            RequestDispatcher rd =  request.getRequestDispatcher(url);
                            rd.include(request, response);
                        }

                        // L'utilisateur veut se connecter autant que club
                    } else {

                        // chercher un club par son email
                        club = (Club) daoClub.afficher(email);

                        System.out.println("Club password is " + club.getPasswordClub());

                        // Authentication
                        if (club.getPasswordClub().equals(password)) {

                            // Ajouter le club à la session
                            session.setAttribute("club", club);
                            session.setAttribute("isclub", true);
                            session.setAttribute("adhList", daoClub.listerAdherents(email));

                            // Redirection vers la page personnelle du club
                            //this.servletContext.getRequestDispatcher("/interface.jsp").forward(request, response);
                            RequestDispatcher rd =  request.getRequestDispatcher("/interface.jsp");
                            rd.include(request, response);
                        } else {
                            //System.out.println("Wrong email or password");
                            error_msg(response);
                            //this.servletContext.getRequestDispatcher(url).forward(request, response);
                            RequestDispatcher rd =  request.getRequestDispatcher(url);
                            rd.include(request, response);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //System.out.println("Wrong email or password");
                    error_msg(response);
                    //this.servletContext.getRequestDispatcher(url).forward(request, response);
                    RequestDispatcher rd =  request.getRequestDispatcher(url);
                    rd.include(request, response);

                }
            }
        }
    }
    
    private void error_msg(HttpServletResponse response) throws IOException {
    	PrintWriter out = response.getWriter();
        out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
        out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
        out.println("<script>");
        out.println("$(document).ready(function() {");
        out.println("swal(\"Ooops...\", \"Impossible de se connecter.\", \"error\");");
        out.println("});");
        out.println("</script>");
    }
    
}
