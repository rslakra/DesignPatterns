package exceptionSimulation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class PersonDao {

	public PersonDao() {
		super();
	}

	public Person getPerson(int personId){
		//implement lookup here
		return null;
	}
	
	public Person getFather(int childId){
		//implement lookup here
		return null;
	}
	
	private Connection getConnection() throws SQLException{
		Connection con = null;
		try{
			Class.forName( "org.gjt.mm.mysql.Driver" ) ;
			String userName = "jdbc";
			String password = "jdbc";
			String url = "jdbc:mysql://192.168.72.13/person";
			con = DriverManager.getConnection( url,userName,password) ;
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
			throw new RuntimeException("Error while attempting to access the database");
		}
		return con;
	}
	
	public void createPerson(String name, int fatherId, 
							int motherId, char eyeColor,char gender, 
							char hairColor, Date dob,
							ArrayList distinguishingMarks) throws SQLException{
		char comma = ',';
		char tick = 39;
		String sql = "INSERT INTO person (name,gender,father_id, mother_id, eye_color, hair_color,date_of_birth) "
					+"VALUES (" + tick + name + tick + comma 
					+ tick + gender + tick + comma + fatherId + comma + motherId
					+ tick + eyeColor + tick + comma + tick + hairColor + tick + comma
					+ tick + dob + tick;
		Connection con = null;
		try{
			con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
			//			insert distiguishing marks code here...
		}finally{
			con.close();
		}		
	}
}
