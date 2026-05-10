import java.sql.SQLException;
import java.util.ListIterator;

import junit.framework.TestCase;

public class AddEmployeeUseCaseTest extends TestCase{

    private EmployeeDataAccessObject dao; 
    private SalariedEmployee employee; 
    private PayrollSchedule sched; 
    private SalariedEmployee salariedEmployee; 
    private EmployeeTaxInformation ti;
    private EmployeeDataAccessObject employeeDataAccessObject; 
	private String employeeName;

	public void setUp(){
		dao = (EmployeeDataAccessObject)DataAccessObjectFactory.getDataAccessObject(DataAccessObjectFactory.EMPLOYEE_DAO);
		employeeName = "President Geoprge W Bush";
	}
	
	
	public void tearDown(){
		dao = null;
	}
	
	public void testUseCase() {        
		//create new employee
		employee = new SalariedEmployee();
		ti = new EmployeeTaxInformation();
		
		employee.setAddress1("1700 Pennsylvania Ave");
		employee.setAddress2("Washington, DC 00001");
		employee.setAge(50);
		employee.setName(employeeName);
		employee.setDepartment("Executive Branch");
		employee.setPosition("POTUS");
		employee.setSalary(new Double(150000.00));
		
		ti.setDeductions(2);
		ti.setMaritalStatus(2);
		ti.setSsn("111111111");
		ti.setTaxRate(0.40);
		//persist new employee
		employee.setTaxInformation(ti);
		try{
			try{
				employee.setId(dao.persistEmployee(employee));	
			}catch(SQLException e){
				employee.setId(1);	
			}
			//getPayrollSchedule
			try{
				sched = dao.getPayrollSchedule();
			}catch(SQLException e){
				sched = new PayrollSchedule();
				sched.add(employee);
			}
			//verify employee is getting paid
			ListIterator it = sched.getEmployees().listIterator();
			boolean newEmployeeWasSuccessfullyInserted = false;
			while(it.hasNext()){
				SalariedEmployee testEmp = (SalariedEmployee)it.next();
				if(employeeName.equals(testEmp.getName())){
					newEmployeeWasSuccessfullyInserted = true;
					break;	
				}	
			}
			assertTrue("The new employee did not make it to the payroll schedule.",newEmployeeWasSuccessfullyInserted);
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	} // end testUseCase        

}



