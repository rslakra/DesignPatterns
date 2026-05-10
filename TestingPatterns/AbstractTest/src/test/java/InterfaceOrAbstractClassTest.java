import junit.framework.TestCase;

public abstract class InterfaceOrAbstractClassTest extends TestCase{

    protected InterfaceOrAbstractClass iut; 

    public InterfaceOrAbstractClass interfaceOrAbstractClass; 
    
    public final InterfaceOrAbstractClass getInterfaceOrAbstractClass(int whichOne){
    	InterfaceOrAbstractClass returnMe = null;
    	switch(whichOne){
    		case 1:
				returnMe =  new ImplementingClass1();
				break;
    		case 2:
				returnMe =  new ImplementingClass2();
				break;
		}
		return returnMe;
    }

    public abstract void testDoSomething1() ;

    public abstract void testDoSomething2() ;

    public abstract void testDoSomething3() ;
    
    public final void testDivideBy1(){
    	assertEquals(iut.divideBy1(777),777);
    	System.out.println("The int asserted by the final method was good.");
    }
    
    public abstract void setUp() ;        
}


