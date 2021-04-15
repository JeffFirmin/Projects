package fr.univ.lyon1.m1if.m1if10Grp13.servlets;

import fr.univ.lyon1.m1if.m1if10Grp13.classes.Club;
import fr.univ.lyon1.m1if.m1if10Grp13.classes.Inscrit;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOClub;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOCreneau;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOInscrit;
import fr.univ.lyon1.m1if.m1if10Grp13.daoException.DAOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Adherer", urlPatterns = "/Adherer")
public class Adherer extends HttpServlet {
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
        String email = request.getParameter("club");
        HttpSession session = request.getSession();
        Inscrit inscrit = (Inscrit) session.getAttribute("user");
        Club club = (Club) daoClub.afficher(email);
       
        List<Inscrit> l_adh = daoClub.listerAdherents(club.getEmailClub());
        
        if(club.getNbMaxAdherent() > l_adh.size())
        {
	        inscrit.setclub(club);
	        try {
	                daoInscrit.update(inscrit,club.getEmailClub());
	
	        }catch ( DAOException e){
	            e.printStackTrace();
	        }
        } else {
        	PrintWriter out = response.getWriter();
            out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
            out.println("<script>");
            out.println("$(document).ready(function() {");
            out.println("swal(\"Club plein !\", \"Le club que vous souhaitez rejoindre a atteint sa capacite maximal.\", \"error\");");
            out.println("});");
            out.println("</script>");
        }
        RequestDispatcher rd =  request.getRequestDispatcher("./interface.jsp");
        rd.include(request, response);
    }
}
