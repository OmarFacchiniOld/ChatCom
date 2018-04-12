/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcom.rest;

import chatcom.hibernateutil.HibernateUtil;
import chatcom.model.Message;
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
 * @author Uberti Davide
 */

@Path("message")
public class MessageResource{

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String body) {
        Gson gson = new Gson();
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        Message message = gson.fromJson(body, Message.class);
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        session.save(message);
        session.getTransaction().commit();

        //deallochiamo le risorse
        session.close();
        sessionFactory.close();
        
        return Response.ok().build();
    }

    /*@PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Message message, String body) {
        
        try {
            Gson g = new Gson();
            
            String newMessage = "";  //inserire codice che prenda il messaggio che verra modificato nella parte grafica e lo metta nella stringa
            
            
            String query = "UPDATE `message` SET `data` =" + newMessage+ " WHERE `id` =" + id + ";"; //o al posto di id mettere message.getId();
            message = g.fromJson(body, Message.class);
            PreparedStatement s1 = connection.prepareStatement(query);
            int r = s1.executeUpdate(query);
            
            
            //DatabaseManager.getInstance().setLog(log);
        } catch (SQLException ex) {
            Logger.getLogger(MessageResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.ok(message).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        
        try {
            Gson g = new Gson();
            
            String query = "DELETE FROM `message` WHERE `id`" + id + ";";
            PreparedStatement s1 = connection.prepareStatement(query);
            int r = s1.executeUpdate(query);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MessageResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.ok().build();
        
    }*/

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Integer id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        Message message = (Message) session.get(Message.class, id);
        session.getTransaction().commit();

        //deallochiamo le risorse
        session.close();
        sessionFactory.close();
        
        Gson gson = new Gson();
        
        if (message==null)
            return Response.status(Response.Status.NOT_FOUND).build();
          
        String ret = gson.toJson(message);
        return Response.ok(ret).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        Gson gson = new Gson();
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        //Codice hibernate per la select *
        List<Message> messages = (List<Message>) session.createQuery("from Message").list();

        //deallochiamo le risorse
        session.close();
        sessionFactory.close();
        
        String ret = gson.toJson(messages);
        return Response.ok(ret).build();
    }

    /*@GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Message> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }*/
    
}
