// Example 10-3: TransactionWrapper.java

package com.oreilly.patterns.chapter10;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import java.sql.Connection;

final class TransactionWrapper {

    /**
     * Decorates a business delegate object with a wrapper. The
     * object returned by this method will implement all of the interfaces
     * originally implemented by the target.
     *
     * @param The Business Delegate to wrap
     * @return The business delegate wrapped in this wrapper
     */
    static Object decorate(Object delegate) {
        return Proxy.newProxyInstance(delegate.getClass().getClassLoader(),
            delegate.getClass().getInterfaces(),
            new XAWrapperHandler(delegate));
    }

    static final class XAWrapperHandler implements InvocationHandler {
        private final Object delegate;

        XAWrapperHandler(Object delegate) {
            // Cache the wrapped delegate, so we can pass method invocations
            // to it.
            this.delegate = delegate;
        }

        /** Invoke the method within a transaction. We retrieve a connection,
         * set auto commit to false (starting the transaction), run the original
         * method, commit the transaction, and return the result. If any 
         * exceptions are thrown (SQLException or not) we roll the transaction
         * back. 
         * 
         * Note that we don't use a finally block to reset the connection to
         * autocommit mode. This approach gives us a better idea of the root
         * cause of any error. In JDK 1.4, we might modify the catch block
         * to attach the original Throwable as the root cause for any exception
         * thrown by the rollback() and setAutoCommit() methods. */
        public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
            Object result = null;
            Connection con = ConnectionManager.getConnection();
            try {
              con.setAutoCommit(false);
              result = method.invoke(delegate, args);
              con.commit();
              con.setAutoCommit(true);
            } catch (Throwable t) {
              // Rollback exception will be thrown by the invoke method
              con.rollback(); 
              con.setAutoCommit(true);
              throw t;
            } 
            
            return result;
        }
    }
}
