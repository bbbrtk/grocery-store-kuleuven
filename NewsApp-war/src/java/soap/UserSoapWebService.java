/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap;

import ejb.Basket;
import ejb.Item;
import ejb.User;
import ejb.UserFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Bartek
 */
@WebService(serviceName = "UserSoapWebService")
public class UserSoapWebService {
    @EJB
    private UserFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "create")
    @Oneway
    public void create(@WebParam(name = "entity") User entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "entity") User entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") User entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "find")
    public User find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<User> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<User> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }

    @WebMethod(operationName = "myBaskets")
    public List<Basket> myBaskets(@WebParam(name = "id") Long id) {
        return ejbRef.myBaskets(id);
    }

    @WebMethod(operationName = "myBasketsName")
    public List<String> myBasketsName(@WebParam(name = "id") Long id) {
        return ejbRef.myBasketsName(id);
    }

    @WebMethod(operationName = "myItems")
    public List<Item> myItems(@WebParam(name = "id") Long id) {
        return ejbRef.myItems(id);
    }

    @WebMethod(operationName = "myItemsName")
    public List<Item> myItemsName(@WebParam(name = "id") Long id) {
        return ejbRef.myItemsName(id);
    }

    @WebMethod(operationName = "myBankAccounts")
    public List<String> myBankAccounts(@WebParam(name = "id") Long id) {
        return ejbRef.myBankAccounts(id);
    }
    
}
