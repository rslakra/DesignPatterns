public class DataAccessObjectFactory {
	public static final int EMPLOYEE_DAO = 1;
    public static DataAccessObject getDataAccessObject(int daoType) {
    	switch(daoType){
    	case DataAccessObjectFactory.EMPLOYEE_DAO:
    		return new EmployeeDataAccessObject();
    	default :
	    	return null;
    	}
    } // end getDataAccessObject        

}



