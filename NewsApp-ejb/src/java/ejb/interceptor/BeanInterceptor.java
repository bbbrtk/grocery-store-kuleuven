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
    
    private int users = 0;
    
    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
 
        System.out.println("SimpleInterceptor - Logging BEFORE calling method :"+context.getMethod().getName() );
 
        Object result = context.proceed();
        users++;
 
        System.out.println("SimpleInterceptor | users: " + users + " -  AFTER:" + context.getMethod().getName() );
 
        return result;
    }
}
