package com.m1if10.app.servlets.Professeur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to manage Cours
 */
@WebServlet(name = "GestionCours", urlPatterns = "/Prof/GestionCours")
public class GestionCours extends HttpServlet {

    /**
     * Method GET: redirects to the Cours managing page
     * @param request: HTTP Servlet's Request
     * @param response: HTTP Servlet's Response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/professeur/gestion_cours.jsp").forward(request, response);
    }
}
