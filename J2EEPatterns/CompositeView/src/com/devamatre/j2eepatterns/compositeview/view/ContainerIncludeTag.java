package com.devamatre.j2eepatterns.compositeview.view;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.devamatre.j2eepatterns.compositeview.controller.View;

/**
 * A custom tag that is nested within a Container tag to include an element of a
 * composite.
 */
public class ContainerIncludeTag extends TagSupport {
	/** the name of the element to include */
	private String name;
	
	/**
	 * Get the view from the current container, and map the name of this include
	 * to a page to include in that view
	 */
	public int doEndTag() throws JspException {
		// find the Container tag and get the view from it
		ContainerTag ct = (ContainerTag) findAncestorWithClass(this, ContainerTag.class);
		View v = ct != null ? ct.getView() : null;
		if (v == null) {
			return EVAL_PAGE;
		}
		
		// get the url for this view
		String viewURL = v.getUrl(ct.getName(), name);
		if (viewURL != null) {
			try {
				// include it
				pageContext.include(viewURL);
			} catch (Exception ex) {
				throw new JspException("Unable to include " + viewURL, ex);
			}
		}
		return EVAL_PAGE;
	}
	
	/**
	 * Used by the container to set the name of this composite element
	 */
	public void setName(String value) {
		name = value;
	}
}
