/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.interceptor;

import ejb.BankAccount;
import ejb.BankAccountFacade;
import ejb.Basket;
import ejb.BasketFacade;
import ejb.Countable;
import ejb.CountableFacade;
import ejb.Uncountable;
import ejb.UncountableFacade;
import ejb.User;
import ejb.UserFacade;
import ejb.enumeration.Size;
import ejb.enumeration.Unit;
import java.util.Date;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Singleton;

/**
 *
 * @author Bartek
 */
@Singleton
public class SingletonSessionState implements SingletonSessionStateLocal {
    @EJB
    private UserFacade userFacade;
    @EJB
    private UncountableFacade uncountableFacade;
    @EJB
    private CountableFacade countableFacade;
    @EJB
    private BasketFacade basketFacade;
    @EJB
    private BankAccountFacade bankAccountFacade;

    
    private int actions = 0;
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public int getActions() {
        return actions;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }
    
    public void incrementActions(){
        actions++;
        if(actions==1){
            for(int i=1; i<4;i++){
                Random r = new Random();
                int rand = i + r.nextInt(100);
                
                // to create basket
                User user = new User();
                user.setLogin("user"+String.valueOf(rand));
                user.setPassword("user"+String.valueOf(rand));
                userFacade.create(user);
                
//                BankAccount ba = new BankAccount();
//                ba.setBankName("bankaccount"+String.valueOf(rand));
//                ba.setMoney(1999.0);
//                bankAccountFacade.create(ba);
                
                Basket basket = new Basket();
                basket.setName("basket"+String.valueOf(rand));
                basket.setUser(user);
                basketFacade.create(basket);
                
                Countable c = new Countable();
                c.setName("countable"+String.valueOf(rand));
                c.setCountry("Belgium");
                c.setOverdue(new Date());
                c.setQuantity(17.0);
                c.setPrice(Double.valueOf(String.valueOf(rand)));
                c.setSize(Size.BIG);
                c.setBasket(basket);
                countableFacade.create(c);
                
                Uncountable u = new Uncountable();
                u.setName("uncountable"+String.valueOf(rand));
                u.setCountry("Poland");
                u.setOverdue(new Date());
                u.setQuantity(12.0);
                u.setPricePerWeight(Double.valueOf(String.valueOf(rand)));
                u.setUnit(Unit.KG);
                u.setBasket(basket);
                uncountableFacade.create(u);
            }
        }
    }
}
