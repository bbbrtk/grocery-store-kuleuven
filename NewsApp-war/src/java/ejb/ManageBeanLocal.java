/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Local;

/**
 *
 * @author Bartek
 */
@Local
public interface ManageBeanLocal {

    void storeUserData(String login, String password);

    String getCurrentUser();

    String getPassword();
}
