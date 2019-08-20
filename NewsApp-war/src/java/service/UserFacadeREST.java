/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.User;
import ejb.UserFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
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

/**
 *
 * @author Bartek
 */
@RequestScoped
@Path("user")
public class UserFacadeREST extends AbstractFacade<User> {
    @PersistenceContext(unitName = "GroceryPersistance")
    private EntityManager em;
    
        @EJB
    private UserFacade userFacade;

    public UserFacadeREST() {
        super(User.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(User entity) {
        userFacade.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, User entity) {
        userFacade.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        userFacade.remove(userFacade.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public User find(@PathParam("id") Long id) {
        return userFacade.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<User> findAll() {
        return userFacade.findAll();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(userFacade.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
