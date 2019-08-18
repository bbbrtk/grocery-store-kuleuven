/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session;

import ejb.Basket;
import ejb.User;
import javax.ejb.Local;
import javax.servlet.http.HttpSessionEvent;

/**
 *
 * @author Bartek
 */
@Local
public interface ManagementStatefulBeanLocal {

    public static int counter = 0;
    public static User user = null;
    public static String login = "";
    public static String basketName = "";
    public static Basket basket = null;

    public void storeBasket(Basket basketStored);

    public void storeUser(User userStored);

    public User getCurrentUser();

    public Basket getCurrentBasket();

    public void storeUserData(String login);

    public void resetUser();
    
    public void resetBasket();


}
