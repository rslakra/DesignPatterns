import java.util.ArrayList;
import java.sql.*;

public class EmployeeDataAccessObject extends DataAccessObject {

	public void getConnection(){
		ConnectionFactory fac = new ConnectionFactory();
		if(conn==null){
			conn=fac.getConnection();
		}
	}

    private Connection conn; 
    public Employee employee; 
    public PayrollSchedule payrollSchedule; 
    
	public static final int GLOBAL_NUMBER_OF_PAY_PERIODS = 26;

    public ArrayList getEmployees() throws SQLException {        
        getConnection();
        ArrayList al = null;
        try{
        	String sql = "SELECT emp.*, ti.* FROM employee emp, tax_info ti WHERE employee.id = tax_info.emp_id ";
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ResultSet rs = ps.executeQuery();
	        while(rs.next()){
	     		al = new ArrayList();
	     		SalariedEmployee emp = new SalariedEmployee();
	     		EmployeeTaxInformation ti = new EmployeeTaxInformation();
	     		emp.setId(rs.getInt("employee.id"));
	     		emp.setNumberOfPayPeriods(EmployeeDataAccessObject.GLOBAL_NUMBER_OF_PAY_PERIODS);
	     		emp.setAddress1(rs.getString("emp.address_1"));
	     		emp.setAddress2(rs.getString("emp.address_2"));
	     		emp.setAge(rs.getInt("emp.age"));
	     		emp.setDepartment(rs.getString("emp.department"));
	     		emp.setName(rs.getString("emp.name"));
	     		emp.setPosition(rs.getString("emp.title"));
				emp.setSalary(new Double(rs.getDouble("ti.salary")));
				ti.setDeductions(rs.getInt("ti.deductions"));
	     		ti.setMaritalStatus(rs.getInt("emp.marital_status"));
	     		ti.setSsn(rs.getString("emp.ssn"));
	     		ti.setTaxRate(rs.getDouble("ti.tax_rate"));
	     		emp.setTaxInformation(ti);   	 
	        }
	        return al; 
        }finally{
        	conn.close();
        }
    } // end getEmployees        

    public SalariedEmployee getEmployee(int eid) throws SQLException{        
		getConnection();
		try{
			SalariedEmployee emp = new SalariedEmployee();
			EmployeeTaxInformation ti = new EmployeeTaxInformation();
			String sql = "SELECT emp.*, ti.* FROM employee emp, tax_info ti WHERE employee.id = tax_info.emp_id and employee.id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,eid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				emp.setId(eid);
				emp.setAddress1(rs.getString("emp.address_1"));
				emp.setAddress2(rs.getString("emp.address_2"));
				emp.setAge(rs.getInt("emp.age"));
				emp.setDepartment(rs.getString("emp.department"));
				emp.setName(rs.getString("emp.name"));
				emp.setPosition(rs.getString("emp.title"));
				emp.setNumberOfPayPeriods(EmployeeDataAccessObject.GLOBAL_NUMBER_OF_PAY_PERIODS);
	     		ti.setDeductions(rs.getInt("ti.deductions"));
				ti.setMaritalStatus(rs.getInt("emp.marital_status"));
				ti.setSsn(rs.getString("emp.ssn"));
				emp.setSalary(new Double(rs.getDouble("ti.salary")));
				ti.setTaxRate(rs.getDouble("ti.tax_rate"));
				emp.setTaxInformation(ti);   	 
			}else{
				throw new RuntimeException("The employee with the id '" + eid + "' could not be found");
			}
			if(rs.next()){
				throw new RuntimeException("Too many records were found for the employee with the id '" + eid + "'.");
			}
			return emp; 
		}finally{
			conn.close();
		}
    } // end getEmployee        

    public int persistEmployee(SalariedEmployee emp) throws SQLException{        
      String sqlEmp = "INSERT INTO employee(address_1,address_2,age,department,name,title,marital_status,ssn) VALUES(?,?,?,?,?,?,?)";
	  String sqlTi = "INSERT INTO tax_info(emp_id,deductions,tax_rate, salary) VALUES(?,?,?,?)";
      try{
      	getConnection();
      	conn.setTransactionIsolation(1);
      	PreparedStatement psEmp = conn.prepareStatement(sqlEmp);
		PreparedStatement psTi = conn.prepareStatement(sqlTi);
      	psEmp.setString(1,emp.getAddress1());
		psEmp.setString(2,emp.getAddress2());
		psEmp.setInt(3,emp.getAge());
		psEmp.setString(4,emp.getDepartment());
		psEmp.setString(5,emp.getName());
		psEmp.setString(6,emp.getPosition());
		psEmp.setInt(7,emp.getTaxInformation().getMaritalStatus());
		psEmp.setString(8,emp.getTaxInformation().getSsn());
      	psEmp.execute();
      	ResultSet rsId = conn.prepareStatement("SELECT max(id) FROM EMPLOYEE").executeQuery();
      	if(rsId.next()){
      		psTi.setInt(1,rsId.getInt(1));	
      	}else{
      		throw new RuntimeException("Exception occurren inserting employee");
      	}
      	psTi.setInt(2,emp.getTaxInformation().getDeductions());
      	psTi.setDouble(3,emp.getTaxInformation().getTaxRate());
      	psTi.setDouble(4,emp.getSalary().doubleValue());
		psTi.execute();      
      	conn.commit();
      }finally{
      	conn.close();
      }
      return emp.getId();  
    }         

    public PayrollSchedule getPayrollSchedule() throws SQLException{        
        PayrollSchedule sched = new PayrollSchedule();
        sched.setPayDate(new java.util.Date());
        sched.setEmployees(getEmployees());
        return sched;
    }        

}