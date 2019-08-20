/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.Basket;
import ejb.BasketFacade;
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
@Path("basket")
public class BasketFacadeREST extends AbstractFacade<Basket> {
    @PersistenceContext(unitName = "GroceryPersistance")
    private EntityManager em;
    
        @EJB
    private BasketFacade basketFacade;

    public BasketFacadeREST() {
        super(Basket.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Basket entity) {
        basketFacade.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Basket entity) {
        basketFacade.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        basketFacade.remove(basketFacade.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Basket find(@PathParam("id") Long id) {
        return basketFacade.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Basket> findAll() {
        return basketFacade.findAll();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(basketFacade.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
