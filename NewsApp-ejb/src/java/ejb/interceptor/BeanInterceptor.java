/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.interceptor;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Bartek
 */
public class BeanInterceptor {
    
    private int actions = 0;
    
    @EJB
    private SingletonSessionStateLocal sssl;
    
    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        
        System.out.println("Interceptor: \t intercept ejb.UserMessage.onMessage with class: " + context.getClass().getName() );
        Object result = context.proceed();
        actions++;
        sssl.incrementActions();
 
        System.out.println("Interceptor: \t USER actions [session]  :  \t " + actions );
        System.out.println("Interceptor: \t SINGLETON actions [all] :  \t " + sssl.getActions() );
 
        return result;
    }
}
