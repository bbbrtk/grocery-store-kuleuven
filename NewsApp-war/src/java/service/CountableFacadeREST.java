/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.Countable;
import ejb.CountableFacade;
import ejb.ItemFacade;
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
@Path("countable")
public class CountableFacadeREST extends AbstractFacade<Countable> {
    @PersistenceContext(unitName = "GroceryPersistance")
    private EntityManager em;
    
        @EJB
    private CountableFacade countableFacade;

    public CountableFacadeREST() {
        super(Countable.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Countable entity) {
        countableFacade.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Countable entity) {
        countableFacade.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        countableFacade.remove(countableFacade.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Countable find(@PathParam("id") Long id) {
        return countableFacade.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Countable> findAll() {
        return countableFacade.findAll();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(countableFacade.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
