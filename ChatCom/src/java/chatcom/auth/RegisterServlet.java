/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcom.auth;

import chatcom.hibernateutil.HibernateUtil;
import chatcom.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Uberti Davide <ubertidavide@gmail.com>
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Prendo tutti i parametri passati dal form
        String paramFirstname = request.getParameter("firstname");
        String paramLastname = request.getParameter("lastname");
        String paramNickname = request.getParameter("nickname");
        String paramEmail = request.getParameter("email");
        String paramPassword = request.getParameter("password");
        String paramConfirmPassword = request.getParameter("confirm-password");
        
        //Controllo se sono stati inseriti tutti i campi
        if(!paramFirstname.trim().isEmpty() && !paramLastname.trim().isEmpty() && !paramNickname.trim().isEmpty()
                && !paramEmail.trim().isEmpty() && !paramPassword.trim().isEmpty() && !paramConfirmPassword.trim().isEmpty() 
                && paramPassword.trim().equals(paramConfirmPassword.trim())){
            
            //TODO: Opportune verifiche tipo se esiste gia il nickname o l' email criptazione password?
            Session session = HibernateUtil.getSessionFactory().openSession();

            User user = new User();
            user.setFirstname(paramFirstname);
            user.setLastname(paramLastname);
            user.setNickname(paramNickname);
            user.setEmail(paramEmail);
            user.setPassword(paramPassword);

            //Codice hibernate per il salvataggio
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            
        }
        
        response.sendRedirect("index.jsp");
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Register Servlet";
    }

}
