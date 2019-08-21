/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.BankAccount;
import ejb.BankAccountFacade;
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
@Path("bank")
public class BankAccountFacadeREST extends AbstractFacade<BankAccount> {
    @PersistenceContext(unitName = "GroceryPersistance")
    private EntityManager em;
    
        @EJB
    private BankAccountFacade bankAccountFacade;

    public BankAccountFacadeREST() {
        super(BankAccount.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(BankAccount entity) {
        bankAccountFacade.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, BankAccount entity) {
        bankAccountFacade.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        bankAccountFacade.remove(bankAccountFacade.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public BankAccount find(@PathParam("id") Long id) {
        return bankAccountFacade.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<BankAccount> findAll() {
        return bankAccountFacade.findAll();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(bankAccountFacade.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
