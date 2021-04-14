package com.m1if10.app.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet managing the log out.
 */
@WebServlet(name = "Deconnexion", urlPatterns = "/Deconnexion")
public class Deconnexion extends HttpServlet {
    /**
     * Method GET: Invalidate the session and redirects to Connexion page.
     * @param request: HTTP Servlet's Request
     * @param response: HTTP Servlet's Response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("Connexion");
    }
}
