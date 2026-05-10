package exceptionInducement;

import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;

public class PersonDaoTest extends TestCase {

	private PersonDao dao;
	private String name;
	private int fatherId, motherId;
	private char hairColor, eyeColor, gender;
	private Date dob;
	private ArrayList marks;


	public void setUp(){
		dao = new PersonDao();
		name = "Zachary";
		fatherId = 777;
		motherId = 888;
		hairColor = 'R';
		eyeColor = 'H';
		gender = 'M';
		dob = new Date();
		marks = new ArrayList();
		marks.add("scar under chin");
	}
	
	public PersonDaoTest() {
		super();
	}

	public PersonDaoTest(String arg0) {
		super(arg0);
	}

	public void testCreatePersonControlledException(){
		try{
			DatabaseUtility.stopDatabaseServices();
			dao.createPerson(name,fatherId,motherId,eyeColor,gender,hairColor,dob,marks);	
			DatabaseUtility.startDatabaseServices();
		}catch(Exception e){
			assertEquals(e.getClass().getName(),"java.sql.SqlException");
			assertEquals(e.getCause().getClass().getName(),"java.net.ConnectException");	
		}
	}
}


