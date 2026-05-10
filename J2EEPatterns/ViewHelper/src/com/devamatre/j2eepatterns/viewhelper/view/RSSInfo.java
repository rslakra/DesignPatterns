package com.devamatre.j2eepatterns.viewhelper.view;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletContext;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A class for parsing RSS information from a file
 */
public class RSSInfo {
	/** RSS tags for channel, item, title and link */
	private static final String CHANNEL_TAG = "channel";
	private static final String ITEM_TAG = "item";
	private static final String TITLE_TAG = "title";
	private static final String LINK_TAG = "link";
	
	/** internal representation of an item in the XML file */
	class Item {
		String title;
		String link;
	}
	
	/** all the items in the RSS file */
	private Item[] items;
	
	/** the channel information (one per file) */
	private Item channel;
	
	/**
	 * Get the title of the channel
	 */
	public String getChannelTitle() {
		return channel.title;
	}
	
	/**
	 * Get the link associated with the channel
	 */
	public String getChannelLink() {
		return channel.link;
	}
	
	/**
	 * Get the title for the item with the given index
	 */
	public String getTitleAt(int index) {
		return items[index].title;
	}
	
	/**
	 * Get the link for the item with the given index
	 */
	public String getLinkAt(int index) {
		return items[index].link;
	}
	
	/**
	 * Get the number of items
	 */
	public int getItemCount() {
		return items.length;
	}
	
	/**
	 * Parse RSS XML. If {@code pathOrAbsoluteUrl} starts with {@code http://} or
	 * {@code https://}, it is fetched over the network; otherwise it is read as a
	 * web application resource path (e.g. {@code /patternsnews.xml}).
	 */
	public void parse(ServletContext ctx, String pathOrAbsoluteUrl) throws Exception {
		DocumentBuilder db = newNonFetchingDocumentBuilder();
		Document doc;
		try (InputStream in = openInputStream(ctx, pathOrAbsoluteUrl)) {
			doc = db.parse(in);
		}
		
		// find the rss element
		Element rss = doc.getDocumentElement();
		
		// parse the channel
		NodeList rssList = rss.getElementsByTagName(CHANNEL_TAG);
		channel = new Item();
		Element channelElem = (Element) rssList.item(0);
		channel.title = getElementValue(channelElem, TITLE_TAG);
		channel.link = getElementValue(channelElem, LINK_TAG);
		
		// get all the items
		NodeList channelList = channelElem.getElementsByTagName(ITEM_TAG);
		items = new Item[channelList.getLength()];
		
		// parse each item
		for (int i = 0; i < channelList.getLength(); i++) {
			Element cElem = (Element) channelList.item(i);
			items[i] = new Item();
			items[i].title = getElementValue(cElem, TITLE_TAG);
			items[i].link = getElementValue(cElem, LINK_TAG);
		}
	}

	private static InputStream openInputStream(ServletContext ctx, String pathOrAbsoluteUrl) throws IOException {
		String s = pathOrAbsoluteUrl.trim();
		if (s.startsWith("http://") || s.startsWith("https://")) {
			URLConnection uc = new URL(s).openConnection();
			return uc.getInputStream();
		}
		String path = s.startsWith("/") ? s : "/" + s;
		InputStream in = ctx.getResourceAsStream(path);
		if (in == null) {
			throw new IOException("Web application resource not found: " + path);
		}
		return in;
	}

	/**
	 * Parser that does not load external DTDs or schemas (avoids hangs / failures offline).
	 */
	private static DocumentBuilder newNonFetchingDocumentBuilder() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		factory.setValidating(false);
		try {
			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		} catch (ParserConfigurationException ignored) {
			// optional depending on implementation
		}
		try {
			factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		} catch (ParserConfigurationException ignored) {
			// optional
		}
		try {
			factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
			factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
		} catch (IllegalArgumentException ignored) {
			// older parsers
		}
		return factory.newDocumentBuilder();
	}
	
	/**
	 * Read a value from a text node in the XML
	 */
	private String getElementValue(Element parent, String child) throws DOMException {
		// find the child
		NodeList list = parent.getElementsByTagName(child);
		if (list == null || list.getLength() == 0)
			return null;
		
		Element childElem = (Element) list.item(0);
		
		// find the text within the child
		Node text = childElem.getFirstChild();
		if (text == null)
			return null;
		
		return text.getNodeValue();
	}
	
}
