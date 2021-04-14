package com.m1if10.app.servlets.Etudiant;


import com.m1if10.app.dao.UserDao;
import com.m1if10.app.modele.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet of password change
 */
@WebServlet(name = "ChangePwd", urlPatterns = "/Etudiant/ChangePwd")
public class ChangePwd extends HttpServlet {
    /**
     * Manages the password with this manager
     */
    private EntityManager em;

    /**
     * Method GET: redirects to the password changing page
     * @param request: HTTP Servlet's Request
     * @param response: HTTP Servlet's Response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/etudiant/change_password.jsp").forward(request, response);
    }

    /**
     * Method POST: Checks User's password's state
     * @param request: HTTP Servlet's Request
     * @param response: HTTP Servlet's Response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        em = Persistence.createEntityManagerFactory("pu-savapa").createEntityManager();
        String mail = request.getParameter("mail");
        String pwd = request.getParameter("pwd");
        String confirmPwd = request.getParameter("confirmPwd");

        if(pwd.equals(confirmPwd)) {
            UserDao userdao = new UserDao(em);
            if(userdao.changePassword(mail,pwd)) {
                request.setAttribute("message", "Votre mot de passe a été modifié avec succès !");
                request.setAttribute("balise","succes");
            } else {
                request.setAttribute("message", "Erreur lors de la modification du mot de passe.");
                request.setAttribute("balise","erreur");
            }
        } else {
            request.setAttribute("message", "Veuillez saisir le même mot de passe dans les 2 champs ci-dessous");
            request.setAttribute("balise","erreur");
        }
        request.getRequestDispatcher("/jsp/etudiant/change_password.jsp").forward(request, response);
    }
}
