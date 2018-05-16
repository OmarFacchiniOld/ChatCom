/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcom.rest;

import chatcom.hibernateutil.HibernateProxyTypeAdapter;
import chatcom.hibernateutil.HibernateUtil;
import chatcom.model.Instance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
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
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * REST Web Service
 *
 * @author Uberti Davide <ubertidavide@gmail.com>
 */
@Path("instance")
public class InstanceResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of InstanceResource
     */
    public InstanceResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String body) {
        Gson gson = new Gson();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Instance instance = gson.fromJson(body, Instance.class);
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        session.save(instance);
        session.getTransaction().commit();
        
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Instance entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        Instance instance = (Instance) session.get(Instance.class, id);
        instance = entity;
        
        session.getTransaction().commit();
    
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        Instance instance = (Instance) session.get(Instance.class, id);
        session.delete(instance);
        session.getTransaction().commit();
        
        return Response.ok().build();
    }
    
    /*@GET
    @Path("{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("userid") Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        List<Instance> instance = (List<Instance>) session.createQuery("from Instance where id_user =:userid ").setParameter(":userid", id).list();
        session.getTransaction().commit();
        
        Gson gson = new Gson();
        
        if (instance==null)
            return Response.status(Response.Status.NOT_FOUND).build();
          
        String ret = gson.toJson(instance);
        return Response.ok(ret).build();
    }*/

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id")Integer id, @QueryParam("userid") Integer userId, @QueryParam("chatid") Integer chatId, @QueryParam("fromid") Integer fromId) {
        
        Gson gson = new /*Gson();*/GsonBuilder()
        .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
        .create();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        String sql = "from Instance ins join fetch ins.user usr join fetch ins.chatgroup chat join fetch ins.message msg";
        
        if(userId != null)
            sql +="where usr.id = :userid";
        if(chatId != null)
            sql +="where chat.id = :chatid";
        if(fromId != null)
            sql +="where msg.id > :fromid";
        
        if(userId != null)
            sql +="group by chat.id ";
        
        sql +="order by msg.id desc";
        
        Query query = session.createQuery(sql);
        
        if(userId != null)
            query.setParameter("userid", userId);
        if(chatId != null)
            query.setParameter("chatid", chatId);
        if(fromId != null)
            query.setParameter("fromid", fromId);
         
        List<Instance> instances = (List<Instance>) query.list();
        session.getTransaction().commit();
        
        Type listtype = new TypeToken<List<Instance>>() {}.getType();
        
        if (instances==null)
            return Response.status(Response.Status.NOT_FOUND).build();
          
        String ret = gson.toJson(instances, listtype);
        return Response.ok(ret).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        Gson gson = /*new Gson();*/new GsonBuilder()
        .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
        .create();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        //Codice hibernate per la select *
        List<Instance> instances = (List<Instance>) session.createQuery("from Instance").list();
        Type listType = new TypeToken<List<Instance>>() {}.getType();
        String ret = gson.toJson(instances,listType);
        return Response.ok(ret).build();
    }
    
}
