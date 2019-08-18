/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import ejb.session.ManagementStatefulBeanLocal;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author Bartek
 */
@MessageDriven(mappedName = "jms/NewMessage", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class UserMessage implements MessageListener {

    @EJB
    private ManagementStatefulBeanLocal msb;

    @Resource
    private MessageDrivenContext mdc;
    @PersistenceContext(unitName = "NewsApp-ejbPU")
    private EntityManager em;

    public UserMessage() {
    }

    @Override
    public void onMessage(Message message) {

        ObjectMessage msg = null;
        try {
            if (message instanceof ObjectMessage) {
                msg = (ObjectMessage) message;
                Object e;

                System.out.println("--- ejb.UserMessage.onMessage  --- ");

                if (msg.getObject().getClass() == new User().getClass()) {
                    e = (User) msg.getObject();
                    save(e);
                    msb.storeUser((User) e);
                    System.out.println("--- current user --- " + msb.getCurrentUser().getLogin() + " --- ");
                    System.out.println("--- USER PERSISTED --- ");
                } else if (msg.getObject().getClass() == new Item().getClass()) {
                    e = (Item) msg.getObject();
                    save(e);
                    System.out.println("--- ITEM PERSISTED --- ");
                } else if (msg.getObject().getClass() == new Countable().getClass()) {
                    e = (Countable) msg.getObject();
                    save(e);
                    System.out.println("--- COUNTABLE PERSISTED --- ");
                } else if (msg.getObject().getClass() == new Uncountable().getClass()) {
                    e = (Uncountable) msg.getObject();
                    save(e);
                    System.out.println("--- UNCOUNTABLE PERSISTED --- ");
                } else if (msg.getObject().getClass() == new Basket().getClass()) {
                    e = (Basket) msg.getObject();
                    save(e);
                    msb.storeBasket((Basket) e);
                    System.out.println("--- current basket --- " + msb.getCurrentBasket().getName() + " --- ");
                    System.out.println("--- BASKET PERSISTED --- ");
                } else if (msg.getObject().getClass() == new BankAccount().getClass()) {
                    e = (BankAccount) msg.getObject();
                    save(e);
                    System.out.println("--- BANKACCOUNT PERSISTED --- ");
                } else {
                    e = (NewsEntity) msg.getObject();
                    save(e);
                    System.out.println("--- NEWENTITY PERSISTED --- ");
                }

//            save(e);
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            te.printStackTrace();
        }

    }

    public void save(Object object) {
        em.persist(object);
    }

}
