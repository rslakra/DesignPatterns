public class Employee {
    private String name, department, position;
    private int age, id; 
    private EmployeeTaxInformation taxInformation; 
    private EmployeeDataAccessObject dao;
    private String address1;
    private String address2;

	public Employee(){
		dao = (EmployeeDataAccessObject)DataAccessObjectFactory.getDataAccessObject(DataAccessObjectFactory.EMPLOYEE_DAO);
	}

   public int getAge() {
		return age;
	}

	public String getDepartment() {
		return department;
	}
	
	
	public EmployeeTaxInformation getTaxInformation() {
		return taxInformation;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setAge(int i) {
		age = i;
	}
	
	public void setDepartment(String string) {
		department = string;
	}
	
	public void setTaxInformation(EmployeeTaxInformation information) {
		taxInformation = information;
	}
	
	public void setName(String string) {
		name = string;
	}
	
	public void setPosition(String string) {
		position = string;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress1(String string) {
		address1 = string;
	}

	public void setAddress2(String string) {
		address2 = string;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}

}
