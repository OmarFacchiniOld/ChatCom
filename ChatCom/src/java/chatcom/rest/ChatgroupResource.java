/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcom.rest;

import chatcom.hibernateutil.HibernateUtil;
import chatcom.model.Chatgroup;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.Session;

/**
 * REST Web Service
 *
 * @author Uberti Davide <ubertidavide@gmail.com>
 */
@Path("chatgroup")
public class ChatgroupResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ChatgroupResource
     */
    public ChatgroupResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String body) {
        
        Gson gson = new Gson();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Chatgroup chatGroup = gson.fromJson(body, Chatgroup.class);
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        session.save(chatGroup);
        session.getTransaction().commit();
        
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Chatgroup entity) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        Chatgroup chatGroup = (Chatgroup) session.get(Chatgroup.class, id);
        chatGroup = entity;
        
        session.getTransaction().commit();
        
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        Chatgroup chatGroup = (Chatgroup) session.get(Chatgroup.class, id);
        session.delete(chatGroup);
        session.getTransaction().commit();
        
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        Chatgroup chatGroup = (Chatgroup) session.get(Chatgroup.class, id);
        session.getTransaction().commit();
        
        Gson gson = new Gson();
        
        if (chatGroup==null)
            return Response.status(Response.Status.NOT_FOUND).build();
          
        String ret = gson.toJson(chatGroup);
        return Response.ok(ret).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        Gson gson = new Gson();
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        //Codice hibernate per la select *
        List<Chatgroup> chatGroups = (List<Chatgroup>) session.createQuery("from Chatgroup").list();
        
        String ret = gson.toJson(chatGroups);
        return Response.ok(ret).build();
    }
    
}
