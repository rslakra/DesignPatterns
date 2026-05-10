import java.lang.reflect.*;
import junit.framework.*;

public class ErrorHanderTest extends TestCase implements  InvocationHandler {

    HUD realHUD = new HUD();
    EnvironmentalControls realEnvironmentalControls = new EnvironmentalControls();
    boolean fixedHUD = false;
    boolean fixedEnvironment = false;

    public testCorrectSystems(){

        // create a proxy for both the HUD and EnvironmentalControls
        Object proxy = Proxy.newProxyInstance( HUD.class.getClassLoader(),
                                          new Class[]{ HUD.class, EnvironmentalControls.class },
                                          this );

        PilotControlSystem pcs = new PilotControlSystem();
        pcs.setHUD( (HUD)proxy );
        pcs.setEnvironmentalControls( (EnvironmentalControls)proxy);

        ErrorHandler errorhandler = new ErrorHandler();
        errorhandler.correctSystems( pcs );
        assertTrue( fixedHUD );         // we fixed the HUD
        assertTrue( fixedEnvironment ); // we fixed the EnvironmentalControls
    }

    // this method will "intercept" any calls to the HUD and EnvironmentalControls
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

        if( HUD.class.equals(proxy.getClass()) ){
            if( "correctVisibility".equals(method.getName()) ){
                fixedHUD = true;    
            }
            return method.invoke( realHUD, args );
        }
        else if( EnvironmentalControls.class.equals( proxy.getClass())){
            if( "normalize".equals( method.getName() )){
                fixedEnvironment = true;
            }
            return method.invoke( realEnvironmentalControls, args );
        }
        else
            throw new NoSuchMethodException();
    }
}
