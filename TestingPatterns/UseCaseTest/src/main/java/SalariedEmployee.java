
public class SalariedEmployee extends Employee {


	private Double yearlySalary;
	private int numberOfPayPeriods;


	
	public Double getSalary() {
		return yearlySalary;
	}
	
	public void setSalary(Double currency) {
		yearlySalary = currency;
	}

	public int getNumberOfPayPeriods() {
		return numberOfPayPeriods;
	}

	public void setNumberOfPayPeriods(int i) {
		numberOfPayPeriods = i;
	}

}



