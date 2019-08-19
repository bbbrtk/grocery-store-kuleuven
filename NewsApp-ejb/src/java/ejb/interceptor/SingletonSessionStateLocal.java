/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.interceptor;

import javax.ejb.Local;

/**
 *
 * @author Bartek
 */
@Local
public interface SingletonSessionStateLocal {
    public int getActions();
    public void setActions(int actions);
    public void incrementActions();
}
