/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

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
public class SessionManagerBean implements HttpSessionListener {

    private static int counter = 0;
    private static String login = "";

    public void sessionCreated(HttpSessionEvent se) {
        counter++;
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        counter--;
    }

    public int getActiveSessionsCount() {
        return counter;
    }

    public void storeUserData(String login, String password) {
        this.login = login;
//        this.password = password;
    }

    public String getCurrentUser() {
        return login;
    }

}
