/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session;

import javax.ejb.Stateful;

/**
 *
 * @author Bartek
 */
@Stateful
public class ManagementStatefulBean implements ManagementStatefulBeanLocal {

    private String login;
    private String password;
    
    @Override
    public void storeUserData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String getCurrentUser() {
        return this.login;
    }

    public String getPassword() {
        return password;
    }
    
    
    
    

    
}
