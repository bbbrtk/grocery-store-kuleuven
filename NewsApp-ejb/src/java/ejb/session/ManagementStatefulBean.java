/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session;

import ejb.Basket;
import ejb.BasketFacade;
import ejb.User;
import ejb.UserFacade;
import ejb.interceptor.BeanInterceptor;
import ejb.timer.UserLogoutTimerLocal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import javax.servlet.http.HttpSessionEvent;

/**
 *
 * @author Bartek
 */
@Stateful
public class ManagementStatefulBean implements ManagementStatefulBeanLocal {

    @EJB
    private UserLogoutTimerLocal timerBean;

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
    }

    @Interceptors(BeanInterceptor.class)
    public void storeUser(User userStored) {

        if (userStored != null) {
            clearState();
        } else {
            this.user = userStored;
        }

    }

    public void clearState() {
        this.user = null;
        this.basket = null;
    }

    public void storeBasket(Basket basketStored) {
        this.basket = basketStored;
    }

    public User getCurrentUser() {
        return user;
    }

    public Basket getCurrentBasket() {
        return basket;
    }

}
