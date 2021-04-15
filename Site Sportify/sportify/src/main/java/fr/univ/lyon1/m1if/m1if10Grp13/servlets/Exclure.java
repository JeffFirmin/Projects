package fr.univ.lyon1.m1if.m1if10Grp13.servlets;

import fr.univ.lyon1.m1if.m1if10Grp13.classes.Club;
import fr.univ.lyon1.m1if.m1if10Grp13.classes.Inscrit;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOClub;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOCreneau;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOInscrit;
import fr.univ.lyon1.m1if.m1if10Grp13.daoException.DAOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Exclure", urlPatterns = "/Exclure")
public class Exclure extends HttpServlet {
    private DAOInscrit daoInscrit;
    private DAOClub daoClub;
    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.servletContext = config.getServletContext();
        this.daoClub = (DAOClub) servletContext.getAttribute("daoClub");
        this.daoInscrit = (DAOInscrit) servletContext.getAttribute("daoInscrit");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("adh");
        HttpSession session = request.getSession();
        Club club = (Club) session.getAttribute("club");
        Inscrit inscrit = (Inscrit) daoInscrit.afficher(email);
        inscrit.setclub(null);

        try {
                daoInscrit.update(inscrit,null);

        }catch ( DAOException e){
            e.printStackTrace();
        }
        session.setAttribute("adhList", daoClub.listerAdherents(email));
        request.getRequestDispatcher("/interface.jsp").forward(request, response);
    }
}
