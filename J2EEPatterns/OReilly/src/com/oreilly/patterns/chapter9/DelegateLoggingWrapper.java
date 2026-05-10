// Example 9-2: DelegateLoggingWrapper.java

package com.oreilly.patterns.chapter9;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


final class DelegateLoggingWrapper {
    /**
     * Decorates a business delegate object with a Logging Wrapper. 
     * The object returned by this method will implement all of the 
     * interfaces originally implemented by the target
     * and loaded by the same class loader as that of the target.
     * @param delegate The Business Delegate to wrap
     * @return The business delegate wrapped in this wrapper
     */
    static Object decorate(Object delegate) {
        return Proxy.newProxyInstance(
            delegate.getClass().getClassLoader(),
            delegate.getClass().getInterfaces(),
            new LoggingWrapperHandler(delegate));
    }

    static final class LoggingWrapperHandler 
                       implements InvocationHandler {
        private final Object delegate;

        LoggingWrapperHandler(Object delegate) {
            this.delegate = delegate;
        }

        /** Invoke the target method, but display logging 
            information first. */
        public Object invoke(Object proxy, Method method, 
                             Object[] args)
            throws Throwable {
            System.out.println("Invoked Business Delegate Method: " +
                method.getName());

            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    System.out.print(args[i]); 
                    System.out.print(
                      (i < (args.length - 1)) ? "," : "\n");
                }
            }

            Object result = method.invoke(delegate, args);
            System.out.println("Completed Without Exception");

            return result;
        }
    }
}
