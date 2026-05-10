// Example 9-3: BusinessDelegateFactory with Logging

package com.oreilly.patterns.chapter9;

public class BusinessDelegateFactory {

  private static boolean useLogging = false;

  public static PatientManagementDelegate 
                 getPatientManagementDelegate() {
    PatientManagementDelegate delegate = 
        new PatientManagementDelegateImpl();
    return (PatientManagementDelegate)wrap(delegate);   
  }

  private static Object wrap(Object o) {
    if(useLogging)
      return DelegateLoggingWrapper.decorate(o);
    return o;
  }
  
  public static void startLogging() {
    useLogging = true;
  }

  public static void stopLogging() {
    useLogging = false;
  }

  public static boolean getLogStatus() {
    return useLogging;
  }
}
