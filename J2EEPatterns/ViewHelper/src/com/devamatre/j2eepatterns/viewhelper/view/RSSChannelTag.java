package com.devamatre.j2eepatterns.viewhelper.view;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

/**
 * A custom tag for the RSS channel. Exposes two scripting variables
 * for channel name and link. Also used by nested item tags to
 * find the information for each item in the channel
 */
public class RSSChannelTag extends BodyTagSupport {
	/** the names of the scripting variable */
	private static final String NAME_ATTR = "channelName";
	private static final String LINK_ATTR = "channelLink";
	
	/** the url of the RSS page */
	private String url;
	
	/** the RSS parser */
	private RSSInfo rssInfo;
	
	/**
	 * Constructor
	 */
	public RSSChannelTag() {
		rssInfo = new RSSInfo();
	}
	
	/**
	 * Called with the URL attribute of the tag
	 */
	public void setURL(String url) {
		this.url = url;
	}
	
	/**
	 * Called by nested tags to get the RSS parser
	 */
	protected RSSInfo getRSSInfo() {
		return rssInfo;
	}
	
	/**
	 * Parse the RSS and set the scripting variables
	 */
	public int doStartTag() throws JspException {
		try {
			// parse the rss
			rssInfo.parse(pageContext.getServletContext(), url);
			
			// export the scripting variables
			pageContext.setAttribute(NAME_ATTR, rssInfo.getChannelTitle());
			pageContext.setAttribute(LINK_ATTR, rssInfo.getChannelLink());
		} catch (Exception ex) {
			throw new JspException("Unable to parse " + url, ex);
		}
		
		return Tag.EVAL_BODY_INCLUDE;
	}
}
