/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Bartek
 */
public class BeanInterceptor {
    
    private int actions = 0;
    
    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        
        System.out.println("Interceptor: \t ejb.UserMessage.onMessage  \t " + context.getMethod().getName() );
        Object result = context.proceed();
        actions++;
 
        System.out.println("Interceptor: \t Performed actions:  \t " + actions );
 
        return result;
    }
}
