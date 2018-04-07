/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcom.rest;

import chatcom.hibernateutil.HibernateUtil;
import chatcom.model.Message;
import com.google.gson.Gson;
import java.util.List;
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
@Stateless
@Path("chatcom.model.message")
public class MessageFacadeREST extends AbstractFacade<Message> {

    @PersistenceContext(unitName = "")
    private EntityManager em;

    public MessageFacadeREST() {
        super(Message.class);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMessage(String body) {
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
        
        return Response.ok(message).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Message entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Message find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Message> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Message> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
