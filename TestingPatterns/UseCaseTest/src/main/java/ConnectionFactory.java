
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	public ConnectionFactory() {
		super();
	}

	private Properties getEnvProps() throws IOException{
		Properties envProps = new Properties();
		FileInputStream in = new FileInputStream(File.separator + "Users" + File.separator + "ua01422" + File.separator + "Documents" + File.separator + "Book" + File.separator + "chapter14" + File.separator + "data" + File.separator + "DBEnvironment.properties");
		envProps.load(in);
		in.close();
		return envProps;
	}

	
	public Connection getConnection(){
		String serverName = null;
		Connection con = null;
		try{
			Properties  dbEnv = getEnvProps();
			serverName = dbEnv.getProperty("DBAvailable").equalsIgnoreCase("true")?"10.0.1.3":"127.0.0.1";
			return getConnection(serverName);
		}catch(IOException ioe){
			ioe.printStackTrace();
			throw new RuntimeException("Could not read in the database properties");
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new RuntimeException("Could not connect to the database on " + serverName);
		}
	}
	
	private Connection getConnection(String serverName) throws SQLException{
		Connection con = null;
		try{
			Class.forName( "org.gjt.mm.mysql.Driver" ) ;
			String dbName = "SamplePayroll";
			String userName = "jdbc";
			String password = "jdbc";
			String url = "jdbc:mysql://" + serverName + "/" + dbName;
			con = DriverManager.getConnection( url,userName,password) ;
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
			throw new RuntimeException("Error while attempting to access the database at " + serverName);
		}
		return con;
	}
}

