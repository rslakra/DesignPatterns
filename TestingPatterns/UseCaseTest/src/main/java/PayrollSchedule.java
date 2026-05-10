import java.sql.SQLException;
import java.util.*;

public class PayrollSchedule {



    private Date payDate;
    private EmployeeDataAccessObject employeeDataAccessObject; 
    private ArrayList employees;
    private EmployeeDataAccessObject dao;

	public PayrollSchedule(){
		dao = (EmployeeDataAccessObject)DataAccessObjectFactory.getDataAccessObject(DataAccessObjectFactory.EMPLOYEE_DAO);
	}

    public void add(int eid) {        
        try{
        	Employee emp = dao.getEmployee(eid);
        	employees.add(emp);
        }catch(SQLException e){
        	e.printStackTrace();
        }
    } 
    
	public void add(Employee emp) {        
			try{
				employees.add(emp);
			}catch(Exception e){
				e.printStackTrace();
			}
		} 
           

	public ArrayList getEmployees() {
		return employees;
	}

	public EmployeeDataAccessObject getEmployeeDataAccessObject() {
		return employeeDataAccessObject;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setEmployees(ArrayList list) {
		employees = list;
	}

	public void setEmployeeDataAccessObject(EmployeeDataAccessObject object) {
		employeeDataAccessObject = object;
	}

	public void setPayDate(Date date) {
		payDate = date;
	}
	
	public HashMap processPayroll(){
		ListIterator empList = employees.listIterator();
		HashMap payroll = new HashMap();
		while(empList.hasNext()){
			  SalariedEmployee emp =(SalariedEmployee)empList.next(); 
			  payroll.put(
			  	emp.getTaxInformation().getSsn(),
			  	new Double(emp.getSalary().doubleValue() / emp.getNumberOfPayPeriods() * (1-emp.getTaxInformation().getTaxRate()))		
			  );
		}
		return payroll;
	}

}
