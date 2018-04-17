/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcom.login;

import chatcom.hibernateutil.HibernateUtil;
import chatcom.model.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Uberti Davide <ubertidavide@gmail.com>
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
        
        String paramUsername = request.getParameter("username");
        String paramPassword = request.getParameter("password");
        
        if(!paramUsername.trim().isEmpty() && !paramPassword.trim().isEmpty()){
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();

            //Codice hibernate per il salvataggio
            session.beginTransaction();
            //Codice hibernate per la select *
            List<User> users = (List<User>) session.createQuery("from User where nickname = :nickname and password = :password").setParameter("nickname", paramUsername).setParameter("password", paramPassword).list();

            session.getTransaction().commit();

            //deallochiamo le risorse
            session.close();
            sessionFactory.close();
            
            if(users.size() == 1){
                User user = users.get(0);
                
                //Se l' autenticazione va a buon fine
                if(user.getPassword().equals(paramPassword) && user.getNickname().equals(paramUsername)){
                    //Recupero la sessione
                    HttpSession oldSessionHttp = request.getSession(false);
                    
                    if(oldSessionHttp != null)
                        oldSessionHttp.invalidate();//invalida sessione se esiste
                    
                    HttpSession currentSession = request.getSession();//crea nuova sessione
                    currentSession.setAttribute("user", user);
                    currentSession.setMaxInactiveInterval(5*60);//5 minuti di inattività massima
                    
                    response.sendRedirect("chat.jsp");
                }
            }
            
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
        return "Login Servlet";
    }

}
