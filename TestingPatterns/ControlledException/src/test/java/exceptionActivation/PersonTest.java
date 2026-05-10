package exceptionActivation;
import junit.framework.TestCase;

public class PersonTest extends TestCase {

	private Person person;
	private String[] ids;
	
	public void setUp(){
		person = new Person();
		ids = new String[7];
		ids[0]=String.valueOf(Double.MIN_VALUE);
		ids[1]=String.valueOf(Integer.MIN_VALUE);
		ids[2]=String.valueOf(0);
		ids[3]=String.valueOf(1);
		ids[4]=String.valueOf(Integer.MAX_VALUE);
		ids[5]=String.valueOf(Double.MAX_VALUE);
		ids[6]="foo";
	}
	
	public void testSetId(){
		int i = 0;
		while(i<7){
			try{
				person.setId(ids[i]);
				assertEquals("The Id was not the value it Should have been after mutation ",person.getId() , Integer.parseInt(ids[i]));
				System.out.println("Asserted that the values were equal for the " + i + "nth element and were (" + person.getId() + ").");
			}catch(InvalidIdException e){
				System.out.println("Asserted that the failure at the " + i + "nth element was as it should have been.");
				if(i!=0 || i!=5){
					fail("The value at element " + i + " was not expected to fail.");
				}
			}catch(NumberFormatException n){
				System.out.println("Asserted that the failure at the " + i + "nth element was as it should have been.");
				if(i!=6){
					fail("The value at element " + i + " was not expected to fail with a NumberFormatException.");
				}
			}finally{
				i++;
				continue;	
			}
		}
	}	

}
