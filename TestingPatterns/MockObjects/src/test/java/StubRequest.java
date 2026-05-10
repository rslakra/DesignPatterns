import java.util.*;
import javax.servlet.http.*;

public class StubRequest extends J2EEImplementationOfHttpServletRequest
implements HttpServletRequest{

    public StubRequest(){
        super();
        this.pars = new Properties();
    }

    /**
     * Only one thing matters in this class and that is this method
     * The class extends your appservers implementation of HttpServletRequest
     * Adding this method which adds to the collection of parameters
     * available to the Servlet.  Then when making a call to the servlet you
     * can use
     * servlet(StubRequest, Response)
     * @param name
     * @param value
     */
    public void setParameter( String name,  String value) {        
        this.pars.put(name,value);
    } // end setAttribute 
    
    
} // end StubRequest
