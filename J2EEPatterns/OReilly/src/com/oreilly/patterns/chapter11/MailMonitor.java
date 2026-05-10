// Example 11-2: Stand-Alone MailMonitor

package com.oreilly.patterns.chapter11;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;

public class MailMonitor extends Thread {
  boolean interupted = false;
  BlockingMailHandler handler = null;
  private static String username = "contentmail";
  private static String password = "poppasswd";
  private static String mailServer = "mail.company.com";
  
  public MailMonitor(BlockingMailHandler mh) {
    handler = mh;
  }

  public void stopRunning() {
    interupted = true;
  }
  
  public void run() {
    Session session = Session.getDefaultInstance(System.getProperties());
    Store store = null;
    try	 {
      store = session.getStore("pop3");
    } catch (NoSuchProviderException nspe) {
      nspe.printStackTrace();
      return;
    }

    while(!interupted) {
      System.out.println("Looping to collect mail");
      try {
        if (!store.isConnected()) // should always be true
          store.connect(mailServer, -1, username, password);
        System.out.println("Connected");  
        Folder folder = store.getDefaultFolder();
        folder = folder.getFolder("INBOX");
        if (folder == null) {
          System.out.println("Unable to open INBOX");
          return;
        }

      System.out.println("Opening folders");
      // Try to open read/write. Open read-only if that fails.
      try {
        folder.open(Folder.READ_WRITE);
      } catch (MessagingException ex) {
        folder.open(Folder.READ_ONLY);
      }

      int totalMessages = folder.getMessageCount();
      int newMessages = folder.getNewMessageCount();
      System.out.println("Total Messages: " + totalMessages);
      try {
        Message messages[] = null;
        messages = folder.getMessages(1, totalMessages);

        for (int i = 0, n = messages.length; i < n; i++) {
          boolean mbDelete = handler.handleMessage(messages[i]);									
          // Delete the message
          if (mbDelete) {
            messages[i].setFlag(Flags.Flag.DELETED, true);
          }
        } // end for
      } catch (MessagingException e) {
        System.out.println("Unable to get Messages");
      }

      // Close the folder and store
      folder.close(true);
      store.close();

      } catch (MessagingException e) {
        System.out.println(e.toString());
        return;
      } 
      
      try {
        this.sleep(10000);
      } catch (InterruptedException e) {
        System.out.println("Exiting");
        return;
      }
    }
  }
}
