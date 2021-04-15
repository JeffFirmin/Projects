package fr.univ.lyon1.m1if.m1if10Grp13.servlets;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOClub;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOCreneau;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOInscrit;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOReservationTerrain;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOTerrain;

import java.io.IOException;

@WebServlet(name = "Init", urlPatterns = "/Init", loadOnStartup = 1)
public class Init extends HttpServlet {


    


    private static final long serialVersionUID = 1L;
    private ServletContext servletContext;
    private EntityManagerFactory factory;

    /**
     * Instanciation des DAO et du factory.
     */
    @Override
    public void init(ServletConfig config) throws ServletException {

        this.servletContext = config.getServletContext();
        this.factory = Persistence.createEntityManagerFactory("pu-sportify");
        DAOInscrit daoInscrit = new DAOInscrit(this.factory);
        DAOClub daoClub = new DAOClub(this.factory);
        DAOCreneau daoCreneau = new DAOCreneau(this.factory);
        DAOReservationTerrain daoReservation = new DAOReservationTerrain(this.factory);
        DAOTerrain daoTerrain = new DAOTerrain(this.factory);
        

        servletContext.setAttribute("daoInscrit", daoInscrit);
        servletContext.setAttribute("daoClub", daoClub);
        servletContext.setAttribute("daoCreneau", daoCreneau);
        servletContext.setAttribute("daoReservation", daoReservation);
        servletContext.setAttribute("daoTerrain", daoTerrain);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Do nothing because no post requests are redirected to init
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("interface.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            System.out.print("Error has Occured while requesting page");
        }
    }
}
