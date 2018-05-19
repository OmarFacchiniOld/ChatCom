/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcom.rest;

import chatcom.hibernateutil.HibernateUtil;
import chatcom.model.Chatgroup;
import chatcom.model.Instance;
import chatcom.model.Message;
import chatcom.model.User;
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
import org.hibernate.Query;
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
    @Path("/user/{userid}/chatgroup/{chatid}")
    //TODO: Aggiungere una instanza anche per il tipo di cui abbiamo dato il nickname
    public Response createUserChat(String body, @PathParam("userid")Integer userid, @PathParam("chatid")Integer chatid) {
        
        Gson gson = new Gson();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Message message = gson.fromJson(body, Message.class);
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        session.save(message);
        
        //Almeno l' utente esiste per forza perchè ha creato la chat
        Query query = session.createQuery("from User usr where usr.id = :userid");
        query.setParameter("userid", userid);
        List<User> users = (List<User>) query.list();
        
        //Verificio se il messaggio esiste
        Query query1 = session.createQuery("from Chatgroup chat where chat.id = :chatid");
        query1.setParameter("chatid", chatid);
        List<Chatgroup> chatgroups = (List<Chatgroup>) query1.list();
        
        Instance instance = new Instance();
        instance.setUser(users.get(0));
        instance.setChatgroup(chatgroups.get(0));
        instance.setMessage(message);
        
        session.save(instance);
        
        session.getTransaction().commit();
        
        String resp = new Gson().toJson(message);
        return Response.ok(resp).build();
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
    public Response findAll(@QueryParam("idchat")String idchat, @QueryParam("lastmsg")String lastmsg) {
        Gson gson = new Gson();

        Session session = HibernateUtil.getSessionFactory().openSession();

        //Codice hibernate per la select *
        List<Message> messages = (List<Message>) session.createQuery("from Message").list();
        
        //query per predere l' ultimo messaggio
        if(idchat != null && lastmsg != null && lastmsg == "1"){
            Query query = session.createQuery("select msg as mess from Instance ins join ins.chatgroup chat where chat.id = :chatid order by msg.id desc limit 1");
            query.setParameter("chatid", idchat);
            messages = (List<Message>) query.list();
        }

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
