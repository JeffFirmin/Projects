package fr.univ.lyon1.m1if.m1if10Grp13.filtres;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpFilter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/*")
public class AuthFilter extends HttpFilter {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // liste des pages permises
        List permissionList = new ArrayList();
        permissionList.addAll(Arrays.asList("/connexion.jsp", "/index.jsp"));

        // recupération de la session
        HttpSession session = request.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        // Permettre css de charger
        Pattern pattern = Pattern.compile("jsp$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(request.getServletPath());
        boolean isJsp = matcher.find();

        if (isLoggedIn && request.getServletPath().equals("/connexion.jsp")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("interface.jsp");
            dispatcher.forward(request, response);
        } else if (isLoggedIn || permissionList.contains(request.getServletPath()) || !isJsp) {
            // passer la requete normalement

            chain.doFilter(request, response);

        } else {
            // Redirection vers la page de connexion
            RequestDispatcher dispatcher = request.getRequestDispatcher("connexion.jsp");
            dispatcher.forward(request, response);

        }

    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
