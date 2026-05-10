package com.devamatre.j2eepatterns.compositeview.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * A dispatcher for dispatching composite views. This dispatcher uses the pages
 * attribute of the request to choose a page described in the views.xml file,
 * and generate a composite from the pieces it describes.
 */
public class CompositeDispatcher implements Dispatcher {
	/** tags in the views.xml file */
	private static final String VIEWS_TAG = "views";
	private static final String VIEW_TAG = "view";
	private static final String CONTAINER_TAG = "container";
	private static final String INCLUDE_TAG = "include";

	/** attributes in the views.xml file */
	private static final String TEMPLATE_ATTR = "template";
	private static final String NAME_ATTR = "name";
	private static final String URL_ATTR = "url";
	private static final String PAGE_ATTR = "page";

	/** attribute used by the custom tag */
	private static final String VIEW_ATTR = "view";

	/** the name of the default view */
	private static final String DEFAULT_VIEW_NAME = "default";

	/** the servlet context */
	private ServletContext context;

	/** the views, mapped by name */
	private HashMap views;

	/**
	 * Called by the front controller to set the context for this dispatcher.
	 * Parse the views.xml file.
	 */
	public void setContext(ServletContext context) throws IOException {
		this.context = context;

		// read the views.xml file & parse it
		InputStream is = context.getResourceAsStream("/views.xml");
		try {
			views = parseXML(is);
		} catch (Exception ex) {
			throw new IOException(ex.getMessage());
		}
	}

	/**
	 * Get the next page based on the page attribute in the request and
	 * corresponding entry in views.xml
	 */
	public String getNextPage(HttpServletRequest req) {
		// read the page attribute from the request
		String page = (String) req.getParameter(PAGE_ATTR);

		// get the parsed view
		View v = (View) views.get(page);

		// if no views were found, use the default
		if (v == null) {
			v = (View) views.get(DEFAULT_VIEW_NAME);
		}

		// store the current view in the request, for use by the
		// custom tags
		req.setAttribute(VIEW_ATTR, v);
		return (v.getTemplate());
	}

	/**
	 * Parse the views.xml file
	 */
	private HashMap parseXML(InputStream is) throws Exception {
		// parse the XML
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(is);

		// find all the view tags
		NodeList viewList = doc.getElementsByTagName(VIEW_TAG);
		HashMap viewMap = new HashMap();

		// parse each tag
		for (int i = 0; i < viewList.getLength(); i++) {
			Element curView = (Element) viewList.item(i);
			String template = curView.getAttribute(TEMPLATE_ATTR);
			String curName = curView.getAttribute(NAME_ATTR);

			// create the view
			View newView = new View(template);
			viewMap.put(curName, newView);
			if (i == 0) {
				// the first view is the default
				viewMap.put(DEFAULT_VIEW_NAME, newView);
			}

			// parse all the containers in the view
			NodeList containerList = curView.getElementsByTagName(CONTAINER_TAG);
			for (int k = 0; k < containerList.getLength(); k++) {
				Element curCont = (Element) containerList.item(k);
				String contName = curCont.getAttribute(NAME_ATTR);

				// parse all the includes in the container
				NodeList includeList = curView.getElementsByTagName(INCLUDE_TAG);
				for (int l = 0; l < includeList.getLength(); l++) {
					Element curInclude = (Element) includeList.item(l);

					String iName = curInclude.getAttribute(NAME_ATTR);
					String iUrl = curInclude.getAttribute(URL_ATTR);

					// add the include to the view
					newView.addInclude(contName, iName, iUrl);
				}
			}
		}

		return viewMap;
	}
}
