
package com.oreilly.patterns.chapter9;

import java.io.Serializable;

public final class SalesRecordKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int saleId;

	public SalesRecordKey() {
	}

	public SalesRecordKey(int saleId) {
		this.saleId = saleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other instanceof SalesRecordKey) {
			return (((SalesRecordKey) other).saleId == saleId);
		} else {
			return false;
		}
	}

	public int hashCode() {
		return saleId;
	}

}
