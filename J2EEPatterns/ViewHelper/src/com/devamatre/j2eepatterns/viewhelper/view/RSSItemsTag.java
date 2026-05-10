package com.devamatre.j2eepatterns.viewhelper.view;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.Tag;

/**
 * A custom tag class for the RSSItems tag. Iterates through each item in
 * the RSS channel, exposing the name and link of each item
 * as scripting variables
 */
public class RSSItemsTag extends BodyTagSupport implements IterationTag {
	/** the names of the scripting variables */
	private static final String NAME_ATTR = "itemName";
	private static final String LINK_ATTR = "itemLink";
	
	/** the index of the current item in the channel */
	private int counter;
	
	/** the RSS parser */
	private RSSInfo rssInfo;
	
	/**
	 * Constructor
	 */
	public RSSItemsTag() {
		super();
		counter = 0;
	}
	
	/**
	 * Find the RSS parser in the parent tag and set the intial value of
	 * the scripting variables.
	 */
	public int doStartTag() throws JspException {
		counter = 0;
		RSSChannelTag rct = (RSSChannelTag) findAncestorWithClass(this, RSSChannelTag.class);
		rssInfo = rct.getRSSInfo();

		pageContext.setAttribute(NAME_ATTR, rssInfo.getTitleAt(counter));
		pageContext.setAttribute(LINK_ATTR, rssInfo.getLinkAt(counter));
		return Tag.EVAL_BODY_INCLUDE;
	}
	
	/**
	 * After each pass, iterate the counter. If there are still any items
	 * left, set the scripting variables
	 */
	public int doAfterBody() throws JspException {
		if (++counter >= rssInfo.getItemCount()) {
			return IterationTag.SKIP_BODY;
		} else {
			pageContext.setAttribute(NAME_ATTR, rssInfo.getTitleAt(counter));
			pageContext.setAttribute(LINK_ATTR, rssInfo.getLinkAt(counter));
			return IterationTag.EVAL_BODY_AGAIN;
		}
	}

	@Override
	public void release() {
		super.release();
		counter = 0;
		rssInfo = null;
	}
}
