package com.m1if10.app.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet to redirect not connected users to the Connexion page.
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
    /**
     * Prohibits access if the user is not connected
     * @param servletRequest: Servlet's Request
     * @param servletResponse: Servlet's Response
     * @param filterChain: Filtering the users that are not connected
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String urlConnexion = request.getContextPath() + "/Connexion";
        String urlCss = request.getContextPath() + "/css";
        boolean estConnecte = session != null && session.getAttribute("user") != null;

        if (request.getRequestURI().equals(urlConnexion) ||
                request.getRequestURI().startsWith(urlCss) ||
                estConnecte) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("Connexion");
        }
    }
}
