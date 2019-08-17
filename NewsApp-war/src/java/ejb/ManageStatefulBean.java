/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author nb
 */
@Stateful
@LocalBean
@WebListener
public class ManageStatefulBean implements HttpSessionListener {

    private static int counter = 0;

    private static User user = null;
    private static String login = "";
    private static String basketName = "";
    private static Basket basket = null;

    @EJB
    private UserFacade userFacade;

    @EJB
    private BasketFacade basketFacade;

    public void storeUserData(String login) {
        this.login = login;
        getCurrentUser();
    }

    public String getCurrentUserLogin() {
        return login;
    }

    public User getCurrentUser() {
        if (login != "") {
            List users = userFacade.findAll();
            for (Iterator it = users.iterator(); it.hasNext();) {
                User elem = (User) it.next();
                if (elem.getLogin().equals(login)) {
                    user = elem;
                    return elem;
                }
            }
        }
        return new User();
    }

    public void storeBasketData(String basketName) {
        if (basketName != "") {
            List baskets = basketFacade.findAll();
            for (Iterator it = baskets.iterator(); it.hasNext();) {
                Basket elem = (Basket) it.next();
                System.out.println("-----elem getName-- " + elem.getName() + " --- ");
                if (elem.getName().equals(basketName)) {
                    basket = elem;
                    this.basketName = basketName;
                    System.out.println("------- STORE BASKET -- " + this.basketName + " --- ");
                }
            }
        }
    }

    public String getLastBasketName() {
        
        return basketName;
    }

    public void sessionCreated(HttpSessionEvent se) {
        counter++;
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        counter--;
    }

    public int getActiveSessionsCount() {
        return counter;
    }

}
