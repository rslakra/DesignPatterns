package com.rslakra.j2eepatterns.asyncpage.model;

import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
	 * Parse an RSS file, given its URL
	 */
	public void parse(String url) throws Exception {
		// connect to the url
		URLConnection uc = (new URL(url)).openConnection();
		
		// parse the XML
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = factory.newDocumentBuilder();
		Document doc = db.parse(uc.getInputStream());
		
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
