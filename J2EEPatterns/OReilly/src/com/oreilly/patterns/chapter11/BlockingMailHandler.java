// Example 11-1: Mail Handler Interface

package com.oreilly.patterns.chapter11;

public interface BlockingMailHandler 
{
  /** Process a message, returning true on success, false on failure.
	* Does not return until message is processed. */ 
  public boolean handleMessage(javax.mail.Message message);
}
