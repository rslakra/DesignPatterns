// Example 9-6 - a Session Fa�ade EJB

package com.oreilly.patterns.chapter9;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;

public class SalesFacadeBean implements SessionBean {
	private SessionContext context;
	private LocalCustomerHome customerHome;
	private LocalItemHome itemHome;
	private LocalSalesRecordHome recordHome;

	public void setSessionContext(SessionContext aContext) {
		context = aContext;
	}

	public void ejbActivate() {
	}

	public void ejbPassivate() {
	}

	public void ejbRemove() {
		customerHome = null;
		itemHome = null;
		recordHome = null;
	}

	public void ejbCreate() {
		try {
			InitialContext ic = new InitialContext();
			customerHome = (LocalCustomerHome) ic.lookup("java:comp/env/ejb/local/Customer");
			itemHome = (LocalItemHome) ic.lookup("java:comp/env/ejb/local/Item");
			recordHome = (LocalSalesRecordHome) ic.lookup("java:comp/env/ejb/local/Record");
		} catch (Exception ex) {
			throw new EJBException("Error looking up home object: " + ex, ex);
		}
	}

	public ReceiptDTO doSale(int itemNumbers[], int customerId) {
		try {
			LocalCustomer cust = customerHome.findByCustomerId(customerId);

			LocalItem items[] = new LocalItem[itemNumbers.length];
			for (int i = 0; i < itemNumbers.length; i++) {
				items[i] = itemHome.findByItemNumber(itemNumbers[i]);
			}

			LocalSalesRecord record = recordHome.createRecord(items, cust);
			return (new ReceiptDTO(items, record));
		} catch (Exception ex) {
			throw new EJBException("Error processing sale: " + ex, ex);
		}
	}
}
