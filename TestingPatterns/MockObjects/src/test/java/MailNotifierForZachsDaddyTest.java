import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import junit.framework.TestCase;


public class MailNotifierForZachsDaddyTest extends TestCase {

    private MailNotifierForZachsDaddy notifier;
    private Properties testEnvironment;
    
    private Properties getTestProps() throws IOException{
    //        create and load default properties
     Properties testProps = new Properties();
     FileInputStream in = new FileInputStream("testEnvironment.properties");
     testProps.load(in);
     in.close();
    return testProps;
    }
    
    public void setUp(){
        try{
            testEnvironment = getTestProps();
        if(!testEnvironment.getProperty("SERVER_UNAVAILABLE").
equalsIgnoreCase("true")){
                notifier = new MailNotifierForZachsDaddy();
            }else{
                    notifier = new StubNotifier();
            }
        }catch(IOException ioe){
            fail("Could not read the properties file");
        }
    }
        
    public MailNotifierForZachsDaddyTest() {
        super();
    }

    public void testCheckAndNotify(){
        notifier.checkAndNotify();
    }
}
