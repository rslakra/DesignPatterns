import java.sql.*;

public abstract class DataAccessObject {

    private Connection conn; 
    public DataAccessObjectFactory dataAccessObjectFactory; 
    public abstract void getConnection() ;

}



