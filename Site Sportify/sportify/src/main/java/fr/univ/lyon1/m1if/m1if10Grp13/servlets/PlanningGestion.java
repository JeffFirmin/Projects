package fr.univ.lyon1.m1if.m1if10Grp13.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.univ.lyon1.m1if.m1if10Grp13.classes.ReservationTerrain;
import fr.univ.lyon1.m1if.m1if10Grp13.dao.DAOReservationTerrain;

/**
 * Servlet implementation class PlanningGestion
 */
@WebServlet(name = "PlanningGestion", urlPatterns = "/PlanningGestion")
public class PlanningGestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext servletContext;
	private DAOReservationTerrain daoReservation;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlanningGestion() {

	}

	@Override
	public void init(ServletConfig config) throws ServletException {

		this.servletContext = config.getServletContext();
		this.daoReservation = (DAOReservationTerrain) this.servletContext.getAttribute("daoReservation");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stade = (String) request.getParameter("stade");
		Long stadeId = Long.valueOf(stade);
		TreeMap<String, ArrayList> planning = daoReservation.getWeekReservations(stadeId);
		HttpSession session = request.getSession();
		session.setAttribute("planning", planning);

		request.getRequestDispatcher("/planning_hebdo.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Not implemented because no post requests will be sent
	}

}
