import junit.framework.*;
//Test Class
public class SelfShunt extends TestCase implements Collaborator {

    // flag to indicate that the test has passed
    boolean testPassed = false;

    // Test method
    public void testTestableMethod(){
        Testable testable = new Testable( this );
        testable.testableMethod();
        assertTrue( testPassed );
    }

    // implementation of Collaborator interface...
    public void collaboratorMethod(){
        testPassed = true;
    }
}

