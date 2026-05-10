import java.util.ArrayList;

public class AssertEqualsTest {

	private String String1;
	private String String2; 
	private Boolean bool1;
	private Boolean bool2;
	private Integer int1;
	private Integer int2;
	private Object obj1;
	private Object obj2;
	
	
	private AssertEquals assert;
	
	public void setAssert(AssertEquals setMe){
		assert = setMe;
	}
	
	public static AssertEqualsTest getInstance(){
		return new AssertEqualsTest();
	}
	
	public AssertEqualsTest(){
		String1 = "Jon";
		String2 = "John";
		bool1 = Boolean.valueOf("true");
		bool2=Boolean.valueOf("true");
		int1=new Integer(Integer.MAX_VALUE);
		int2=new Integer(Integer.MAX_VALUE-1);
		obj1=new Object();
		obj2=obj1;
	}
	
	private AssertEquals setAssertForTest(){
		AssertEquals theReturn = new AssertEquals();
		ArrayList al = new ArrayList();
		ArrayList failAl = new ArrayList();
		
		//set strings
		EqualsCondition eq = new EqualsCondition();
		eq.setObj1(String1);
		eq.setObj2(String2);
		al.add(eq);
		Failure fail = new Failure();
		fail.setMessage("The two STRING VALUES you are comparing do not have equal values.");
		failAl.add(fail);
		
		//set bools
		eq = new EqualsCondition();
		eq.setObj1(bool1);
		eq.setObj2(bool2);
	 	al.add(eq);
		fail = new Failure();
		fail.setMessage("The two BOOLEAN VALUES you are comparing do not have equal values.");
		failAl.add(fail);

		//set ints
		eq = new EqualsCondition();
		eq.setObj1(int1);
		eq.setObj2(int2);
		al.add(eq);
		fail = new Failure();
		fail.setMessage("The two INTEGER VALUES you are comparing do not have equal values.");
		failAl.add(fail);

		//set objects
		eq = new EqualsCondition();
		eq.setObj1(obj1);
		eq.setObj2(obj2);
		al.add(eq);
		fail = new Failure();
		fail.setMessage("The two OBJECT REFERENCES you are comparing do not have equal values.");
		failAl.add(fail);

		theReturn.setEqualsConditions(al);
		theReturn.setEqualsFailures(failAl);
		return theReturn;
	}
	
	
	public static void main (String args[]){
		AssertEqualsTest myThis = AssertEqualsTest.getInstance();
		myThis.setAssert(myThis.setAssertForTest());
		myThis.assert.assert();
	}
}
