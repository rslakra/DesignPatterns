public class ImplementingClass1Test extends InterfaceOrAbstractClassTest{


	public ImplementingClass1Test() {
		super();
	}

	public void testDoSomething1() {
		assertEquals(iut.doSomething1(),"doSomething1 in impl1");		
	}

	public void testDoSomething2() {
		assertEquals(iut.doSomething2(),"doSomething2 in impl1");
	}

	public void testDoSomething3() {
		assertEquals(iut.doSomething3(),"doSomething3 in impl1");
	}

	public void setUp() {
		iut = super.getInterfaceOrAbstractClass(1);
	}

}
