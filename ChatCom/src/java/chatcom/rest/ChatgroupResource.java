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
    @Path("/user/{userid}")
    //TODO: Aggiungere una instanza anche per il tipo di cui abbiamo dato il nickname
    public Response createUserChat(String body, @PathParam("userid")Integer userid) {
        
        Gson gson = new Gson();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Chatgroup chatGroup = gson.fromJson(body, Chatgroup.class);
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        session.save(chatGroup);
        
        //Almeno l' utente esiste per forza perchè ha creato la chat
        Query query = session.createQuery("from User usr where usr.id = :userid");
        query.setParameter("userid", userid);
        List<User> users = (List<User>) query.list();
        
        //Verificio se il messaggio esiste
        Query query1 = session.createQuery("from Message msg where msg.id = 1");
        List<Message> messages = (List<Message>) query1.list();
        
        Message message;
        if(messages.size() > 0){
            message = messages.get(0);
        }else{
            message = new Message();
            message.setData("Chat Creata");
            session.save(message);
        }
        
        Instance instance = new Instance();
        instance.setUser(users.get(0));
        instance.setChatgroup(chatGroup);
        instance.setMessage(message);
        
        session.save(instance);
        
        session.getTransaction().commit();
        
        String resp = new Gson().toJson(chatGroup);
        return Response.ok(resp).build();
    }

    /*@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String body) {
        
        Gson gson = new Gson();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Chatgroup chatGroup = gson.fromJson(body, Chatgroup.class);
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        session.save(chatGroup);
        session.getTransaction().commit();
        
        String resp = new Gson().toJson(chatGroup);
        return Response.ok(resp).build();
    }*/

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
    public Response findAll(@QueryParam("chatname") String chatname, @QueryParam("userid")Integer userid) {
        Gson gson = new Gson();
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        //Codice hibernate per la select *
        List<Chatgroup> chatGroups = (List<Chatgroup>) session.createQuery("from Chatgroup").list();
        
        if(chatname != null){
            Query query = session.createQuery("from Chatgroup chat where chat.name = :chatname order by chat.id desc limit 1");
            query.setParameter("chatname", chatname);
            chatGroups = (List<Chatgroup>)query.list();
        }else if(userid != null){
            Query query = session.createQuery("select chat as chati from Instance ins join ins.user usr join ins.chatgroup chat where usr.id = :userid order by chat.id asc");
            query.setParameter("userid", userid);
            chatGroups = (List<Chatgroup>)query.list();
        }
        
        String ret = gson.toJson(chatGroups);
        return Response.ok(ret).build();
    }
    
}
