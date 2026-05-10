package com.rslakra.j2eepatterns.antipatterns.ejbs;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@rslakra.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2003-02-01 11:18:23 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public final class AddressBookEntryKey implements java.io.Serializable {
	
	public int entryId;
	
	public AddressBookEntryKey() {
	}
	
	public AddressBookEntryKey(int entryId) {
		this.entryId = entryId;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(java.lang.Object otherOb) {
		
		if (this == otherOb) {
			return true;
		}
		if (!(otherOb instanceof AddressBookEntryKey)) {
			return false;
		}
		AddressBookEntryKey other = (AddressBookEntryKey) otherOb;
		return (
		
		(entryId == other.entryId)
		
		);
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return (
		
		((int) entryId)
		
		);
	}
	
}
