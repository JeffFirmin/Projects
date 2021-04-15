package com.m1if10.app.servlets.Scolarite;

import com.m1if10.app.classes.CryptageMdp;
import com.m1if10.app.classes.RandomString;
import com.m1if10.app.dao.AlternantDao;
import com.m1if10.app.dao.ProfDao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * Servlet to manage Users
 */
@WebServlet(name = "GestionUtilisateurs", urlPatterns = "/Scola/GestionUtilisateurs")
@MultipartConfig
public class GestionUtilisateurs extends HttpServlet {
    /**
     * The entity manager to manage changes of User
     */
    private EntityManager em;

    /**
     * Method POST: Manages to add Alternant or Prof to the DB
     * @param request: HTTP Servlet's Request
     * @param response: HTTP Servlet's Response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //creation user et insertion
        em = Persistence.createEntityManagerFactory("pu-savapa").createEntityManager();
        String nom = request.getParameter("nom").toLowerCase();
        String prenom = request.getParameter("prenom").toLowerCase();
        String type = request.getParameter("type");
        String password =  new RandomString(16).nextString();
        String hashedPwd = CryptageMdp.encrypt(password);
        //gestion photo
        Part filePart = request.getPart("photo");
        InputStream fileContent = filePart.getInputStream();
        String fileName = null;

        if (type.equals("alt")) {
            AlternantDao alternantDao = new AlternantDao(em);
            if(!alternantDao.existeAlternant(nom,prenom)) {
                fileName = prenom + "_" + nom +"_"+ type + ".png";
                String entreprise = request.getParameter("entreprise");
                String groupe = request.getParameter("groupe");
                String numEtu = request.getParameter("numEtu");
                alternantDao.createAlternant(hashedPwd, nom, prenom, fileName, entreprise, groupe, numEtu);
                request.setAttribute("message", "Alternant ajouté !");
                request.setAttribute("pdw", password);
            }
            else{
                request.setAttribute("message", "Alternant déjà existant !");
            }
        } else {
            ProfDao profDao = new ProfDao(em);
            if(!profDao.existeProf(nom, prenom)) {
                fileName = prenom + "_" + nom +"_"+ type + ".png";
                profDao.createProf(hashedPwd, nom, prenom, fileName);
                request.setAttribute("message", "Professeur ajouté !");
                request.setAttribute("pdw", password);
            }
            else{
                request.setAttribute("message", "Professeur déjà existant !");
            }
        }

        //on check si photo ou pas
        if (fileContent.available() != 0 && fileName != null) {
            //sauvegarde dans apache-tomcat-9.0.27/photos
            String urlSauvegarde = "../photos/";
            File directory = new File(urlSauvegarde);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File location = new File(urlSauvegarde + fileName);
            Files.copy(fileContent, location.toPath());
        }

        request.getRequestDispatcher("/jsp/scolarite/gestion_utilisateurs.jsp").forward(request, response);
    }

    /**
     * Method GET: redirects to the user managing form
     * @param request: HTTP Servlet's Request
     * @param response: HTTP Servlet's Response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/scolarite/gestion_utilisateurs.jsp").forward(request, response);
    }
}
