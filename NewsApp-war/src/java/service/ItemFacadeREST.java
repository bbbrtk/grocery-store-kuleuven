/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.Item;
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
@Path("item")
public class ItemFacadeREST extends AbstractFacade<Item> {
    @PersistenceContext(unitName = "GroceryPersistance")
    private EntityManager em;

    public ItemFacadeREST() {
        super(Item.class);
    }
    
    @EJB
    private ItemFacade itemFacade;

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Item entity) {
        itemFacade.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Item entity) {
        itemFacade.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        itemFacade.remove(itemFacade.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Item find(@PathParam("id") Long id) {
        return itemFacade.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Item> findAll() {
        return itemFacade.findAll();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(itemFacade.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
