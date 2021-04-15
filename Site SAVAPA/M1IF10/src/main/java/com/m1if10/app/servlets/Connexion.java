package com.m1if10.app.servlets;

import com.m1if10.app.classes.CryptageMdp;
import com.m1if10.app.dao.UserDao;
import com.m1if10.app.modele.AdminSco;
import com.m1if10.app.modele.Alternant;
import com.m1if10.app.modele.Prof;
import com.m1if10.app.modele.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet managing the connection to the system
 */
@WebServlet(name = "Connexion", urlPatterns = "/Connexion")
public class Connexion extends HttpServlet {
    /**
     * The entity manager to manage changes of User
     */
    private EntityManager em;
    /**
     * The manager of User
     */
    private UserDao userDao;

    /**
     * Initialize connection with Entity manager and User manager
     */
    @Override
    public void init() throws ServletException {
        super.init();
        em = Persistence.createEntityManagerFactory("pu-savapa").createEntityManager();
        userDao = new UserDao(em);
    }

    /**
     * Destroy the servlet
     */
    @Override
    public void destroy() {
        super.destroy();
        em.close();
    }

    /**
     * Method POST: redirects the User to the corresponding page if login informations are correct
     * @param request: HTTP Servlet's Request
     * @param response: HTTP Servlet's Response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String identifiant = request.getParameter("inputIdentifiant");
        String password = request.getParameter("inputPassword");
        HttpSession session = request.getSession();

        User u = attemptLogin(identifiant, password);
        if (u == null) {
            request.setAttribute("message", "Identifiant ou mot de passe invalide");
            request.getRequestDispatcher("jsp/connexion.jsp").forward(request, response);
        } else {
            session.setAttribute("user", u);
            sendUserToHome(u, response);
        }
    }

    /**
     * Method GET: redirects to the password changing page
     * @param request: HTTP Servlet's Request
     * @param response: HTTP Servlet's Response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = getUserFromSession(request.getSession());
        if (u != null) {
            sendUserToHome(u, response);
        } else {
            request.getRequestDispatcher("jsp/connexion.jsp").forward(request, response);
        }
    }

    private User getUserFromSession(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    /**
     * Redirects the User to the corresponding page
     * @param u: User to redirect to home path
     * @param response: HTTP Servlet's response
     */
    private void sendUserToHome(User u, HttpServletResponse response) throws IOException {
        if (u instanceof Alternant) {
            response.sendRedirect("Etudiant/EDT");
        } else if (u instanceof AdminSco) {
            response.sendRedirect("Scola/GestionAbsences");
        } else if (u instanceof Prof) {
            response.sendRedirect("Prof/GestionCours");
        }
    }


    /**
     * Checks and returns the User attempting to login
     * @param email: User's mail
     * @param password: User's password
     * @return User model that attempted the login
     */
    private User attemptLogin(String email, String password) {
        if (email == null || password == null) return null;

        User u = userDao.getUserByEmail(email);
        if (u == null) return null;

        password = CryptageMdp.encrypt(password);
        if (password.equals(u.getPassword())) {
            return u;
        }
        return null;
    }
}
