package com.m1if10.app.servlets.Professeur;

import com.m1if10.app.modele.Prof;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class to prohibit the access of different users than Prof
 */
@WebFilter(filterName = "ProfFilter", urlPatterns = "/Prof/*")
public class ProfFilter implements Filter {
    /**
     * Method GET: Prohibits access if the user is not a Prof
     * @param servletRequest: Servlet's Request
     * @param servletResponse: Servlet's Response
     * @param filterChain: Filtering the users
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!(request.getSession().getAttribute("user") instanceof Prof)) {
            response.sendRedirect(request.getContextPath() + "/Connexion");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
