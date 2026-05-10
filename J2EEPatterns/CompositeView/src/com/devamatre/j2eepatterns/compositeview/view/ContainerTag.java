package com.devamatre.j2eepatterns.compositeview.view;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.devamatre.j2eepatterns.compositeview.controller.View;

/**
 * A custom tag for including containers within a JSP by name. This tag also
 * contains nested ContainerIncludeTags, which use it to find their assoicated
 * includes.
 */
public class ContainerTag extends TagSupport {
	/** the name of the view in the request */
	private static final String VIEW_ATTR = "view";
	
	/** the name of the container */
	private String name;
	
	/** the view assoicated with this container */
	private View view;
	
	/**
	 * Determine if the named view exists. If it does, proceed with any nested
	 * tage. If it doesn't, just abort.
	 */
	public int doStartTag() throws JspException, JspException {
		// find the view in the request
		view = (View) pageContext.getRequest().getAttribute(VIEW_ATTR);
		if (view == null) {
			return SKIP_BODY;
		}
		
		// see if it has the desired container
		if (!view.hasContainer(name))
			return SKIP_BODY;
		
		return EVAL_BODY_INCLUDE;
	}
	
	/**
	 * Get the view associated with this container. Used by ContainerIncludeTag
	 */
	public View getView() {
		return view;
	}
	
	/**
	 * Called by the tag itself to set the name of the container
	 */
	public void setName(String value) {
		name = value;
	}
	
	/**
	 * Get the name of the container
	 */
	public String getName() {
		return name;
	}
}
