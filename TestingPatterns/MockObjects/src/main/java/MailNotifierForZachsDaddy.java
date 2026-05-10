import java.util.HashMap;

public class MailNotifierForZachsDaddy {

    public MailNotifierForZachsDaddy() {
        super();
    }

    private HashMap mailInfo;
    
    private void getMailInfo(){
        mailInfo = new HashMap();
        mailInfo.put("server","blah.west.cox.net");
        mailInfo.put("user","whatever");
        mailInfo.put("password","asIf");
    }

    public  void checkAndNotify() {
        getMailInfo();
        JavaxMailServer srv = new JavaxMailServer();
        String[] theMail = srv.getMessages((String)mailInfo.get("server"),
                (String)mailInfo.get("user"),
                (String)mailInfo.get("password"));
        for(int i=0;i<theMail.length;i++){
        /*here we would send some notification or persist each message for the
sake of clarity in our example we will simply write out to the console*/
            System.out.println("To:" 
                                   + mailInfo.get("user") 
                                   + "@" + mailInfo.get("server")  
                                   + " it was " + theMail[i]);
        }
    }
}
