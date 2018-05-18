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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.Session;

/**
 * REST Web Service
 *
 * @author Uberti Davide <ubertidavide@gmail.com>
 */
@Path("message")
public class MessageResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MessageResource
     */
    public MessageResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String body) {
        Gson gson = new Gson();

        System.out.println("==================================================");
        System.out.println(body);
        System.out.println("==================================================");
        
        Session session = HibernateUtil.getSessionFactory().openSession();

        Message message = gson.fromJson(body, Message.class);

        //Codice hibernate per il salvataggio
        session.beginTransaction();
        session.save(message);
        session.getTransaction().commit();

        String resp = new Gson().toJson(message.getId());
        return Response.ok(resp).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Message entity) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        //Codice hibernate per il salvataggio
        session.beginTransaction();
        Message message = (Message) session.get(Message.class, id);
        message = entity;

        session.getTransaction().commit();

        return Response.ok(message).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        //Codice hibernate per il salvataggio
        session.beginTransaction();
        Message message = (Message) session.get(Message.class, id);
        session.delete(message);
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
        Message message = (Message) session.get(Message.class, id);
        session.getTransaction().commit();

        Gson gson = new Gson();

        if (message == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        String ret = gson.toJson(message);
        return Response.ok(ret).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@QueryParam("idutente")String idutente, @QueryParam("idchat")String idchat) {
        Gson gson = new Gson();

        Session session = HibernateUtil.getSessionFactory().openSession();

        //Codice hibernate per la select *
        List<Message> messages = (List<Message>) session.createQuery("from Message").list();

        String ret = gson.toJson(messages);
        return Response.ok(ret).build();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        Gson gson = new Gson();

        Session session = HibernateUtil.getSessionFactory().openSession();

        //Codice hibernate per la select *
        List<Message> messages = (List<Message>) session.createQuery("from Message where id > :fromId and id < :toId").setParameter("fromId", from).setParameter("toId", to).list();

        String ret = gson.toJson(messages);
        return Response.ok(ret).build();
    }

}
