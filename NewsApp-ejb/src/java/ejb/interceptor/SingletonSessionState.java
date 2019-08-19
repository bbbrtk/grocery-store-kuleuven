/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.interceptor;

import javax.ejb.Singleton;

/**
 *
 * @author Bartek
 */
@Singleton
public class SingletonSessionState implements SingletonSessionStateLocal {

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
    }
}
