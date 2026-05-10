package com.devamatre.j2eepatterns.resourcepool;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * A factory that generates XML parsers
 */
public class XMLParserFactory implements ResourceFactory {
	/** the single DocumentBuilderFactory */
	DocumentBuilderFactory dbf;
	
	/**
	 * Constructor. Create the factory.
	 */
	public XMLParserFactory() {
		dbf = DocumentBuilderFactory.newInstance();
	}
	
	/**
	 * Create a new DocumentBuilder to add to the pool
	 */
	public Object createResource() {
		try {
			return dbf.newDocumentBuilder();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Check that a returned DocumentBuilder is valid.
	 * Reset parameters to defaults.
	 */
	public boolean validateResource(Object o) {
		if (!(o instanceof DocumentBuilder)) {
			return false;
		}
		
		DocumentBuilder db = (DocumentBuilder) o;
		db.setEntityResolver(null);
		db.setErrorHandler(null);
		
		return true;
	}
}
