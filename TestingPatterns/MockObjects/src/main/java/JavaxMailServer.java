import java.util.Properties;
import javax.mail.*;

public class JavaxMailServer {

    public String[] getMessages(String server, String user, String password){
      Folder folder = null;
      Store store = null;
      String[] mails = null;
      try{
        //     Create empty properties
         Properties props = new Properties();
        //     Get session
         Session session = Session.getDefaultInstance(props, null);
        //     Get the store
         store = session.getStore("pop3");
         store.connect(server, user, password);
        //     Get folder
         folder = store.getFolder("INBOX");
         folder.open(Folder.READ_ONLY);
         //     Get directory
         Message message[] = folder.getMessages();
         mails = new String[message.length];
         String nextMessage = null;
         for (int i=0, n=message.length; i<n; i++) {
            nextMessage = i + ": " + message[i].getFrom()[0]  + "\t" +
message[i].getSubject();
              mails[i]=nextMessage;
           }
          folder.close(false);
          store.close(); 
    }catch(Exception e){
        e.printStackTrace();
    }
    return mails;
    }
}
