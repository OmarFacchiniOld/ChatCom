/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcom.rest;

import chatcom.hibernateutil.HibernateUtil;
import chatcom.model.Instance;
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
import com.google.gson.Gson;

/**
 *
 * @author Uberti Davide
 */

@Path("instance")
public class InstanceResource{

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String body) {
        Gson gson = new Gson();
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        Instance instance = gson.fromJson(body, Instance.class);
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        session.save(instance);
        session.getTransaction().commit();

        //deallochiamo le risorse
        session.close();
        sessionFactory.close();
        
        return Response.ok().build();
    }

    /*@PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Instance entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }*/

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Integer id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        Instance instance = (Instance) session.get(Instance.class, id);
        session.getTransaction().commit();

        //deallochiamo le risorse
        session.close();
        sessionFactory.close();
        
        Gson gson = new Gson();
        
        if (instance==null)
            return Response.status(Response.Status.NOT_FOUND).build();
          
        String ret = gson.toJson(instance);
        return Response.ok(ret).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        Gson gson = new Gson();
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        //Codice hibernate per la select *
        List<Instance> instances = (List<Instance>) session.createQuery("from Instance").list();

        //deallochiamo le risorse
        session.close();
        sessionFactory.close();
        
        String ret = gson.toJson(instances);
        return Response.ok(ret).build();
    }
    
}
