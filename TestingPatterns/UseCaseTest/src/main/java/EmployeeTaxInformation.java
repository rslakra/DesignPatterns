public class EmployeeTaxInformation {

	private String ssn;
	private int deductions;
	private double taxRate;
	private int maritalStatus;
	
	public static final int MARITAL_STATUS_SINGLE = 1;
	public static final int MARITAL_STATUS_MARRIED_JOINT = 2;
	public static final int MARITAL_STATUS_MARRIED_SEPARATE = 3;
	public static final int MARITAL_STATUS_SEPARATED = 4;
	public static final int MARITAL_STATUS_DIVORCED = 5;
	public static final int MARITAL_STATUS_WIDOWED = 6;

	public int getDeductions() {
		return deductions;
	}

	public int getMaritalStatus() {
		return maritalStatus;
	}

	public String getSsn() {
		return ssn;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setDeductions(int i) {
		deductions = i;
	}

	public void setMaritalStatus(int i) {
		maritalStatus = i;
	}

	public void setSsn(String string) {
		ssn = string;
	}

	public void setTaxRate(double d) {
		taxRate = d;
	}

}