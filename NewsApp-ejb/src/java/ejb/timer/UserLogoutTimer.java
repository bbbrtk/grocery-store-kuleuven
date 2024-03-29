/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.timer;

import ejb.session.ManagementStatefulBeanLocal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author Bartek
 */
@Stateless
public class UserLogoutTimer implements UserLogoutTimerLocal {

    @EJB
    private ManagementStatefulBeanLocal msb;

    private int counter = 300;

    @Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "*/1", persistent = false)
    @Override
    public void myTimer() {
        counter -= 1;

        if (counter % 10 == 0) {
            System.out.println("Timer status: " + counter + "sec");
        }

        if (counter == 0) {
            counter = 300;
            msb.storeUser(null);
        }
    }

    public int getCounter() {
        return counter;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
