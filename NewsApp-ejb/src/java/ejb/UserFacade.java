/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Bartek
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "NewsApp-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public List<Basket> myBaskets(Long id) {
        Query q = em.createNativeQuery("select * from BASKETS where USER_ID = ?");
        q.setParameter(1, id);
        List<Basket> list = q.getResultList();
        return list;
    }

    public List<String> myBasketsName(Long id) {
        Query q = em.createNativeQuery("select NAME from BASKETS where USER_ID = ?");
        q.setParameter(1, id);
        List<String> list = q.getResultList();
        return list;
    }
    
    public List<Item> myItems(Long id) {
        Query q = em.createNativeQuery("select * from ITEM where BASKET_ID in (select ID from BASKETS where USER_ID = ?)");
        q.setParameter(1, id);
        List<Item> list = q.getResultList();
        return list;
    }
    
    public List<Item> myItemsName(Long id) {
        Query q = em.createNativeQuery("select NAME from ITEM where BASKET_ID in (select ID from BASKETS where USER_ID = ?)");
        q.setParameter(1, id);
        List<Item> list = q.getResultList();
        return list;
    }
    
    public List<String> myBankAccounts(Long id) {
        Query q = em.createNativeQuery("select BANKNAME from BANKS, USER_BANKACCOUNT where ((ID=BANKACCOUNT_ID) and (USER_ID = ?))");
        q.setParameter(1, id);
        List<String> list = q.getResultList();
        return list;
    }

}
