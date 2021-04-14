package com.m1if10.app.servlets.Etudiant;

import com.m1if10.app.classes.SimpleQrcodeGenerator;
import com.m1if10.app.modele.Alternant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet to display the QRCode
 */
@WebServlet(name = "AfficherQRCode", urlPatterns = "/Etudiant/AfficherQRCode")
public class AfficherQRCode extends HttpServlet {
    /**
     * Method GET: redirects to the password changing page
     * @param request: HTTP Servlet's Request
     * @param response: HTTP Servlet's Response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Alternant alternant = (Alternant) session.getAttribute("user"); //QRCode of the alternant in the session
        //Generate a QRCode
        SimpleQrcodeGenerator qrcode = new SimpleQrcodeGenerator(request.getServletContext().getRealPath("/"));
        //Create it for the alternant
        qrcode.createQRCode(alternant.getEmail(),alternant.getCleQr());
        //Redirect to the QRCode displaying view
        request.getRequestDispatcher("/jsp/etudiant/afficher_qr_code.jsp").forward(request, response);
    }
}
