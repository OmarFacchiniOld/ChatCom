/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcom.rest;

import chatcom.hibernateutil.HibernateUtil;
import chatcom.model.User;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author UbertiDavide
 */

@Path("user")
public class UserResource{

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String body) {
        
        Gson gson = new Gson();
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        User user = gson.fromJson(body, User.class);
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

        //deallochiamo le risorse
        session.close();
        sessionFactory.close();
        
        return Response.ok().build();
    }

    /*@PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id) {         // vedere cosa verrà utilizzato per l'autenticazione e di conseguenza cosa l'user potrà modificare
        
        try {
            Gson g = new Gson();
            
            String newUsername = "";  //inserire codice che prenda il nuovo nome della chat che verra modificato nella parte grafica e lo metta nella stringa
            
            
            String query = "UPDATE `user` SET `nickname` =" + newUsername+ " WHERE `id` =" + id + ";"; //o al posto di id mettere message.getId();   al momento l'user può modificare il nickname
            //message = g.fromJson(body, Message.class);
            PreparedStatement s1 = connection.prepareStatement(query);
            int r = s1.executeUpdate(query);
            
            
            //DatabaseManager.getInstance().setLog(log);
        } catch (SQLException ex) {
            Logger.getLogger(MessageResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.ok().build();
        
    }*/

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();

        //deallochiamo le risorse
        session.close();
        sessionFactory.close();
        
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Integer id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.getTransaction().commit();

        //deallochiamo le risorse
        session.close();
        sessionFactory.close();
        
        Gson gson = new Gson();
        
        if (user==null)
            return Response.status(Response.Status.NOT_FOUND).build();
          
        String ret = gson.toJson(user);
        return Response.ok(ret).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        Gson gson = new Gson();
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        //Codice hibernate per la select *
        List<User> users = (List<User>) session.createQuery("from User").list();

        //deallochiamo le risorse
        session.close();
        sessionFactory.close();
        
        String ret = gson.toJson(users);
        return Response.ok(ret).build();
    }
    
}
