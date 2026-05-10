public class ImplementingClass2Test extends InterfaceOrAbstractClassTest{


	public ImplementingClass2Test() {
		super();
	}

	public void testDoSomething1() {
		assertEquals("Did not get the expected value from doSomething1",iut.doSomething1(),"doSomething1 in impl2");		
	}

	public void testDoSomething2() {
		assertEquals("Did not get the expected value from doSomething2",iut.doSomething2(),"doSomething2 in impl2");
	}

	public void testDoSomething3() {
		assertEquals("Did not get the expected value from doSomething3",iut.doSomething3(),"doSomething3 in impl2");
	}

	public void setUp() {
		iut = super.getInterfaceOrAbstractClass(2);
	}

}
