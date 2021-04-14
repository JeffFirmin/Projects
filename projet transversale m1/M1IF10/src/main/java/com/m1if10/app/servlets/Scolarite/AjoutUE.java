package com.m1if10.app.servlets.Scolarite;

import com.m1if10.app.dao.PresenceAltDao;
import com.m1if10.app.dao.ProfDao;
import com.m1if10.app.dao.UeDao;
import com.m1if10.app.modele.EtatPresence;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "AjoutUE", urlPatterns = "/Scola/AjoutUE")
public class AjoutUE extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = Persistence.createEntityManagerFactory("pu-savapa").createEntityManager();
        UeDao ueDao = new UeDao(em);
        if(!ueDao.existeUE(request.getParameter("nameUe"))) {
            if(!request.getParameter("prof").equals("")) {
                ueDao.insertUeProf(request.getParameter("nameUe"), request.getParameter("intitule"), request.getParameter("prof"));
                request.setAttribute("fail", "false");
                request.setAttribute("message", "UE ajouté !");
            }
            else{
                request.setAttribute("fail", "true");
                request.setAttribute("message", "Il faut déjà créer un prof !");
            }
        }
        else{
            request.setAttribute("fail", "true");
            request.setAttribute("message", "UE déjà existante !");
        }

        ProfDao profDao = new ProfDao(em);
        Map<String,String> map = profDao.getAllProfs();
        request.setAttribute("map",map);

        request.getRequestDispatcher("/jsp/scolarite/ajout_ue.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = Persistence.createEntityManagerFactory("pu-savapa").createEntityManager();
        ProfDao profDao = new ProfDao(em);
        Map<String,String> map = profDao.getAllProfs();
        request.setAttribute("map",map);
        request.getRequestDispatcher("/jsp/scolarite/ajout_ue.jsp").forward(request, response);
    }
}
