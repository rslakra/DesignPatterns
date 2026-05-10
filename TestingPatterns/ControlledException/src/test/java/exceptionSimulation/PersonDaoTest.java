package exceptionSimulation;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.*;
 

public class PersonDaoTest extends PersonDao{

	private PersonDao dao;
	private String name;
	private int fatherId, motherId;
	private char hairColor, eyeColor, gender;
	private Date dob;
	private ArrayList marks;


	public void setUp(){
		dao = this;
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
	
	public void testCreatePersonControlledException(){
		try{
			dao.createPerson(name,fatherId,motherId,eyeColor,gender,hairColor,dob,marks);	
		}catch(Exception e){
			Assert.assertEquals(e.getClass().getName(),"java.sql.SqlException");
			Assert.assertEquals(e.getCause().getClass().getName(),"java.net.ConnectException");	
		}
	}
	
	public void createPerson(String name, int fatherId, 
								int motherId, char eyeColor,char gender, 
								char hairColor, Date dob,
								ArrayList distinguishingMarks) throws SQLException{
		ConnectException ex = new ConnectException();
		SQLException se = new SQLException();
		se.initCause(ex);
		throw se;								
	}
	
	public static void main(String[] args){
		PersonDaoTest test = new PersonDaoTest();
		test.setUp();
		test.testCreatePersonControlledException();
	}
	
	public PersonDaoTest(){
	}
}